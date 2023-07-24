package bauernhof.app.system;

import bauernhof.app.player.PlayerCards;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.Move;
import bauernhof.preset.Settings;
import bauernhof.preset.card.Card;

import java.util.ArrayList;

/**
 * The Game interface defines the contract for managing the state and actions of a game. It provides methods to access
 * various game-related information, such as player IDs, scores, cards, rounds, and game settings. The Game interface
 * also supports executing moves in the game and determining the winner.
 *
 * Implementing classes that represent specific games should implement this interface to provide the necessary game logic
 * and functionality. The interface facilitates a common set of methods for interacting with different game types.
 *
 * The Game interface assumes the existence of a player and card system that allows players to make moves and manage their
 * cards during the game. Players have access to their cards and can execute moves based on the game's rules and mechanics.
 *
 * @see PlayerCards
 * @see GameConfiguration
 * @see ImmutableList
 * @see Move
 * @see Settings
 * @see Card
 * @see java.util.ArrayList
 * @see java.util.List
 * @see java.util.Collections
 * @author Ramon Cemil Kimyon
 */
public interface Game {
      
    /**
     * Gets the ID of the current player who has the turn to make a move.
     *
     * @return The ID of the current player.
     */

    int getCurrentPlayerID();

    /**
     * Gets an immutable list containing the scores of all players in the game.
     *
     * @return An ImmutableList of Integer values representing the scores of all players.
     */

    ImmutableList<Integer> getAllScores();

    /**
     * Gets the list of cards that have been deposited during the game.
     *
     * @return An ArrayList of Card objects representing the deposited cards in the game.
     */

    ArrayList<Card> getDepositedCards();

    /**
     * Gets an immutable list containing the cards remaining in the draw pile.
     *
     * @return An ImmutableList of Card objects representing the cards remaining in the draw pile.
     */

    ImmutableList<Card> getDrawPileCards();

    /**
     * Gets the cards held by a specific player.
     *
     * @param playerid The ID of the player whose cards are to be retrieved.
     * @return A PlayerCards object representing the cards held by the specified player.
     */

    PlayerCards getPlayerCards(final int playerid);

    /**
     * Gets the cards held by the current player who has the turn to make a move.
     *
     * @return A PlayerCards object representing the cards held by the current player.
     */

    PlayerCards getCurrentPlayerCards();

    /**
     * Gets the current round number of the game.
     *
     * @return The current round number.
     */

    int getRound();

    /**
     * Executes a move in the game based on the provided Move object.
     *
     * @param move The Move object representing the move to be executed.
     * @return True if the move is successfully executed, false otherwise.
     * @throws Exception If an error occurs while executing the move.
     */

    boolean executeMove(final Move move) throws Exception;

    /**
     * Gets the total number of players participating in the game.
     *
     * @return The total number of players.
     */

    int getNumPlayers();

    /**
     * Gets the ID of the winner of the game.
     *
     * @return The ID of the player who is the winner, or the total number of players if there is no winner yet.
     */

    int getWinnerID();

    /**
     * Gets the configuration settings of the game.
     *
     * @return A GameConfiguration object representing the game settings and rules.
     */

    GameConfiguration getConfiguration();

    /**
     * Gets the name of a player based on their player ID.
     *
     * @param playerid The ID of the player whose name is to be retrieved.
     * @return The name of the player with the specified ID.
     * @throws Exception If the player ID is invalid or an error occurs while retrieving the name.
     */

    String getName(final int playerid) throws Exception;

    /**
     * Gets the score of a player based on their player ID.
     *
     * @param playerid The ID of the player whose score is to be retrieved.
     * @return The score of the player with the specified ID.
     * @throws Exception If the player ID is invalid or an error occurs while retrieving the score.
     */

    int getScore(final int playerid) throws Exception;

    /**
     * Gets the settings of the game.
     *
     * @return A Settings object representing the game settings and configurations.
     */
    
    Settings getSettings();
}
