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
    private boolean run;
    private Player[] players;
    private Settings settings;
    public GameSystem(final Settings settings, final GameConfiguration configuration) {
        super(settings.playerNames.size(), configuration);
        this.settings = settings;
        players = new Player[numplayers];
        this.run = true;
    }
    public void createPlayers(final ArrayList<S2CConnection> connection) throws Exception {
        players = new Player[settings.playerTypes.size()];
        int remotecounter = -1;
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
                    players[playerid] = new Advanced_AI(settings, getPlayerCards(playerid), this.clone());
                    players[playerid] = connection.get(remotecounter++).getRemotePlayer();
                    break;
            }
        }
    }
    public void initPlayers() throws Exception {
        if (settings.showGUI) GameBoard.graphics = new UiGame(configuration, this);
        for(int playerid = 1; playerid <= numplayers; playerid++)
            players[playerid - 1].init(configuration, getDrawPileCards(), numplayers, playerid);
        for (int playerid = 0; playerid < numplayers; playerid++) {
            if (settings.delay <= 0) return;
            Thread.sleep(settings.delay);
            this.initBeginnerCards(playerid);
        }
        this.round++;
        executeMove(players[getActivePlayerID()].request());
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

    public boolean executeMove(final Move move) throws Exception {
        super.executeMove(move);
        // Update Moves on Players
        if (getActualPlayer() instanceof AbstractGamePlayer) ((AbstractGamePlayer) getActualPlayer()).executeMove(move);
        for (final Player player : players)
            if (!player.equals(getActualPlayer()))
                player.update(move);
        // Check End Conditions
        if (this.getRound() > 30 || getDepositedCards().size() >= configuration.getNumDepositionAreaSlots()) run = false;
        if (getGraphics() != null && settings.showGUI) getGraphics().move(!run);
        // Do Normal Move
        if (run) {
            if (!(getActualPlayer() instanceof HumanPlayer || getActualPlayer() instanceof RemotePlayer))
                if (settings.delay <= 0)
                    return true;
                else
                    Thread.sleep(settings.delay);
            this.executeMove(getActualPlayer().request());
        } else for (final Player player : players)
                player.verifyGame(getAllScores());
        return true;
    }
    @Override
    public void initBeginnerCards(final int playerid) throws Exception {
        super.initBeginnerCards(playerid);
        if (graphics != null && settings.showGUI) graphics.move(false);

    }
    public Player[] getPlayers() {
        return this.players;
    }

    public Player getActualPlayer() {
        return players[getActivePlayerID()];
    }

    public Color getPlayerColor(final int playerid) {
        return settings.playerColors.get(playerid);
    }
}
