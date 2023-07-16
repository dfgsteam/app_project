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

public class GameBoard extends GameSystem {
    private boolean run;
    private Player[] players;
    private Settings settings;
    public GameBoard(final Settings settings, final GameConfiguration configuration, final UiGame graphics) {
        super(settings.playerNames.size(), configuration, graphics);
        this.settings = settings;
        players = new Player[settings.playerTypes.size()];
    }
    public void initPlayers(final ArrayList<S2CConnection> connection) throws Exception {
        players = new Player[settings.playerTypes.size()];
        int remotecounter = -1;
        for (int playerid = 0; playerid < players.length; playerid++) {
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
        initPlayers(players);
    }
    public void initPlayers(final Player[] players) throws Exception {
        for(int playerid = 1; playerid <= players.length; playerid++)
            players[playerid].init(configuration, getDrawPileCards(), players.length, playerid);
        for (int playerid = 0; playerid < players.length; playerid++) {
            Thread.sleep(settings.delay);
            this.initBeginnerCards(playerid);
        }
        this.round++;
        executeMove(players[getActivePlayerID()].request());
    }
    public Player getWinner() throws Exception {
        ArrayList<Integer> scores = new ArrayList<>(getAllScores());
        Collections.sort(scores);
        if (scores.get(scores.size() - 1) == scores.get(scores.size() - 2)) return null;
        for (final Player player : this.getPlayers())
            if (player.getScore() == scores.get(scores.size() - 1))
                return player;
        return null;
    }

    public boolean executeMove(final Move move) throws Exception {
        super.executeMove(move);
        // Update Moves on Players
        if (getActualPlayer() instanceof AbstractGamePlayer) ((AbstractGamePlayer) getActualPlayer()).executeMove(move);
        for (final Player player : players)
            if (!player.equals(getActualPlayer()))
                player.update(move);
        // Check End Conditions
        if (this.getRound() > 30 || this.getDrawPileCards().isEmpty() || getDepositedCards().size() >= configuration.getNumDepositionAreaSlots()) run = false;
        if (getGraphics() != null) getGraphics().move(!run);
        // Do Normal Move
        if (run) {
            if (!(getActualPlayer() instanceof HumanPlayer || getActualPlayer() instanceof RemotePlayer)) Thread.sleep(settings.delay);
            this.executeMove(getActualPlayer().request());
        } else for (final Player player : players)
                player.verifyGame(getAllScores());
        return true;
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

    public GameConfiguration getConfiguration() {
        return configuration;
    }
}
