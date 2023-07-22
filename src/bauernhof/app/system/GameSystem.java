package bauernhof.app.system;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.*;
import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.*;
import bauernhof.preset.networking.RemotePlayer;
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
                    connections.get(remotecounter).setPlayerNames(new ImmutableList<>(settings.playerNames));
                    players[playerid] = connections.get(remotecounter++).getRemotePlayer();
                    break;
            }
        }
    }
    public void initPlayers() throws Exception {
        for (int playerid = 1; playerid <= numplayers; playerid++)
            this.players[playerid - 1].init(configuration, getDrawPileCards(), numplayers, playerid);
        if (settings.showGUI) graphics = new UiGame(configuration, this);
            for (int playerid = 0; playerid < numplayers; playerid++) {
                if (settings.delay <= 0) return;
                Thread.sleep(settings.delay);
                initBeginnerCards(playerid);
                updatePlayerID();
            }

            executeMove(this.players[getCurrentPlayerID()].request());
    }
    public int getWinnerID() throws Exception {
        ArrayList<Integer> scores = new ArrayList<>(getAllScores());
        Collections.sort(scores);
        if (scores.get(scores.size() - 1).equals(scores.get(scores.size() - 2))) return players.length;
        for (int i = 0; i < players.length; i++)
            if (players[i].getScore() == scores.get(scores.size() - 1))
                return i;
        return players.length;
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
        if (this.getRound() > 30 || getDepositedCards().size() >= configuration.getNumDepositionAreaSlots()) run = false;
        if (super.executeMove(move)) {
            if (getGraphics() != null && settings.showGUI) {
                graphics.update(!run);
            }
            // Do Normal Move

            if (run) {
                if (!(getCurrentPlayer() instanceof HumanPlayer || getCurrentPlayer() instanceof RemotePlayer))
                    if (settings.delay <= 0 && settings.showGUI)
                        return true;
                    else Thread.sleep(settings.delay);
                this.executeMove(getCurrentPlayer().request());
            } else for (final Player player : players)
                player.verifyGame(getAllScores());
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
