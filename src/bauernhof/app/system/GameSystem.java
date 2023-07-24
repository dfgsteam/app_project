package bauernhof.app.system;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.*;
import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.*;
import bauernhof.preset.networking.S2CConnection;

import java.awt.*;
import java.util.*;

/**
 * Diese Klasse ist der Generelle Main Handler für das gesamte Spielbrett.
 * Sie gibt über jeden Status des aktuellen Spiels bescheid.
 * Auch die Instanzen der aktuellen {@link Player} sind enthalten.
 * Zudem dient die Klasse auch zum Laden von gespeicherten Spielständen
 *
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 00:55
 */

public class GameSystem extends GameBoard {
    private Player[] players;
    private boolean run;
    private ArrayList<S2CConnection> connections;
    public GameSystem(final Settings settings, final GameConfiguration configuration) {
        super(settings.playerNames.size(), settings, configuration);
        players = new Player[numplayers];
        this.run = true;
    }
    public void createPlayers(final ArrayList<S2CConnection> connections) throws Exception {
        this.connections = connections;
        int remotecounter = 0;
        for (int playerid = 0; playerid < numplayers; playerid++) {
            switch (settings.playerTypes.get(playerid)) {
                case ADVANCED_AI:
                    players[playerid] = new Advanced_AI(settings, getPlayerCards(playerid), this.clone());
                    break;
                case SIMPLE_AI:
                    players[playerid] = new Simple_AI(settings, getPlayerCards(playerid), this.clone());
                    break;
                case HUMAN:
                    players[playerid] = new HumanPlayer(settings, getPlayerCards(playerid), this.clone());
                    break;
                case RANDOM_AI:
                    players[playerid] = new Random_AI(settings, getPlayerCards(playerid), this.clone());
                    break;
                case REMOTE:
                    ////settings.playerNames.set(playerid, connections.get(remotecounter).getProjectName());
                    players[playerid] = connections.get(remotecounter++).getRemotePlayer();
                    break;
            }
        }
        for (final S2CConnection connection : connections) connection.setPlayerNames(new ImmutableList<>(settings.playerNames));
    }
    public void initPlayers() throws Exception {
        for (int playerid = 1; playerid <= numplayers; playerid++)
            this.players[playerid - 1].init(configuration, getDrawPileCards(), numplayers, playerid);
        if (settings.showGUI && graphics == null)
            graphics = new UiGame(this);
            for (int playerid = 0; playerid < numplayers; playerid++) {
                if (settings.delay <= 0) return;
                Thread.sleep(settings.delay);
                updatePlayerID();
                initBeginnerCards(playerid);
            }
            if (settings.delay > 0) Thread.sleep(settings.delay);
            executeMove(this.players[getCurrentPlayerID()].request());
    }

    @Override
    public boolean executeMove(final Move move) throws Exception {
        // Update Moves on Players
        if (getPlayers()[getCurrentPlayerID()] instanceof AbstractGamePlayer)
            ((AbstractGamePlayer) getPlayers()[getCurrentPlayerID()]).executeMove(move);
        for (final Player player : players)
            if (!player.equals(getPlayers()[getCurrentPlayerID()]))
                player.update(move);
        // Check End Conditions

        if (super.executeMove(move)) {
            if (this.getRound() > 30 || getDepositedCards().size() >= configuration.getNumDepositionAreaSlots()) run = false;
            if (getGraphics() != null && settings.showGUI) graphics.update(!run);
            // Do Normal Move
            if(run) {
                if (!(getCurrentPlayer() instanceof HumanPlayer))
                    if (settings.delay <= 0 && settings.showGUI) return true;
                    else Thread.sleep(settings.delay);
                this.executeMove(getCurrentPlayer().request());
            } else  for (final Player player : players) {
                player.verifyGame(getAllScores());
                getGraphics().showScorePopup();
            }
        }
        return true;
    }
    @Override
    public void initBeginnerCards(final int playerid) {
        super.initBeginnerCards(playerid);
        if (graphics != null) {
            try {
                graphics.update(false);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
    public Player[] getPlayers() {
        return this.players;
    }

    public Player getCurrentPlayer() {
        return players[getCurrentPlayerID()];
    }

    public Color getPlayerColor(final int playerid) {
        return settings.playerColors.get(playerid);
    }
    @Override
    public String getName(final int playerid) throws Exception {
        return players[playerid].getName();
    }
    @Override
    public int getScore(final int playerid) throws Exception {
        return players[playerid].getScore();
    }
}
