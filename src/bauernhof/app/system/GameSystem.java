package bauernhof.app.system;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.*;
import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.*;
import bauernhof.preset.networking.S2CConnection;

import java.awt.*;
import java.util.*;

/**
 * The GameSystem class is the main handler for the entire game board.
 * It provides information about the current state of the game.
 * The class also contains instances of the current {@link Player}s.
 * It serves as a loader for saved game states as well.
 *
 * This class extends the {@link GameBoard} and manages the game's main functionalities.
 * It handles player creation, initialization, and execution of moves.
 * The class also checks for end conditions to determine when the game is finished.
 * If a GUI is enabled, it updates the game graphics accordingly.
 * The GameSystem also provides methods to retrieve player information, such as name and score.
 *
 * @author Ramon Cemil Kimyon
 */

public class GameSystem extends GameBoard {
    private Player[] players;
    private boolean run;

    /**
     * Constructs a new GameSystem instance.
     *
     * @param settings      The game settings.
     * @param configuration The game configuration.
     */

    public GameSystem(final Settings settings, final GameConfiguration configuration) {
        super(settings.playerNames.size(), settings, configuration);
        players = new Player[numplayers];
        this.run = true;
    }

     /**
     * Creates player instances based on the provided connections.
     *
     * @param connections The list of S2CConnection instances representing connected players.
     * @throws Exception If there is an error while creating the players.
     */
    
     public void createPlayers(final ArrayList<S2CConnection> connections) throws Exception {
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
    
    /**
     * Initializes the player instances.
     *
     * @throws Exception If there is an error while initializing the players.
     */
    
     public void initPlayers() throws Exception {
        for (int playerid = 1; playerid <= numplayers; playerid++)
            this.players[playerid - 1].init(configuration, getDrawPileCards(), numplayers, playerid);
        if (settings.showGUI && graphics == null)
            graphics = new UiGame(this);
            for (int playerid = 0; playerid < numplayers; playerid++) {
                if (settings.delay <= 0 && settings.numTournamentRounds == 0) return;
                Thread.sleep(settings.delay);
                updatePlayerID();
                initBeginnerCards(playerid);
            }
            if (settings.delay > 0) Thread.sleep(settings.delay);
            executeMove(this.players[getCurrentPlayerID()].request());
    }

    /**
     * Executes a move in the game.
     *
     * @param move The move to execute.
     * @return True if the move was successfully executed, false otherwise.
     * @throws Exception If there is an error while executing the move.
     */

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
                    if (settings.delay <= 0 && settings.showGUI && settings.numTournamentRounds == 0) return true;
                    else Thread.sleep(settings.delay);
                this.executeMove(getCurrentPlayer().request());
            } else  for (final Player player : players) {
                player.verifyGame(getAllScores());
                if (getGraphics() != null && settings.numTournamentRounds < 1)
                getGraphics().showScorePopup();
            }
        }
        return true;
    }

    /**
     * Initializes beginner cards for a specific player.
     *
     * @param playerid The ID of the player.
     */

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
    
    /**
     * Gets the array of players in the game.
     *
     * @return The array of Player instances.
     */
    
     public Player[] getPlayers() {
        return this.players;
    }

    /**
     * Gets the current player.
     *
     * @return The current Player instance.
     */

    public Player getCurrentPlayer() {
        return players[getCurrentPlayerID()];
    }

    /**
     * Gets the color of a specific player.
     *
     * @param playerid The ID of the player.
     * @return The color associated with the player.
     */

    public Color getPlayerColor(final int playerid) {
        return settings.playerColors.get(playerid);
    }

    /**
     * Gets the name of a specific player.
     *
     * @param playerid The ID of the player.
     * @return The name of the player.
     * @throws Exception If there is an error while getting the player name.
     */

    @Override
    public String getName(final int playerid) throws Exception {
        return players[playerid].getName();
    }

    /**
     * Gets the score of a specific player.
     *
     * @param playerid The ID of the player.
     * @return The score of the player.
     * @throws Exception If there is an error while getting the player score.
     */

    @Override
    public int getScore(final int playerid) throws Exception {
        return players[playerid].getScore();
    }
}
