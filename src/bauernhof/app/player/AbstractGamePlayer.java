package bauernhof.app.player;

/**
 * The AbstractGamePlayer class serves as the base class for all instances of the {@link bauernhof.preset.PlayerType}.
 * It provides common functionality and attributes that are shared among different types of game players.
 *
 * Each concrete player type, such as {@link bauernhof.app.player.types.HumanPlayer} or {@link bauernhof.app.player.types.ComputerPlayer},
 * inherits from this abstract class and implements its own version of methods defined in the Player interface.
 *
 * The AbstractGamePlayer class holds references to game settings, player cards, and the game board system,
 * allowing game players to interact with the game state and make valid moves.
 *
 * This class provides methods for initializing the player, updating the game state based on opponent moves,
 * executing player moves, verifying the game's validity, and getting the player's score.
 *
 * @see Player
 * @see bauernhof.app.player.types.HumanPlayer
 * @see bauernhof.app.player.types.ComputerPlayer
 * @see GameBoard
 * @see GameConfiguration
 * @see PlayerCards
 * @see Settings
 * @see ImmutableList
 * @see Card
 * @see Move
 * @author Ramon Cemil Kimyon
 */

import bauernhof.app.system.GameBoard;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;

public abstract class AbstractGamePlayer implements Player {
    private int playerid;
    protected GameConfiguration configuration;
    protected PlayerCards playercards;
    protected GameBoard gameBoard;
    private Settings settings;

    /**
     * Constructs a new AbstractGamePlayer object with the given game settings, player cards, and game board system.
     *
     * @param settings    The Settings object representing game settings.
     * @param playercards The PlayerCards object representing the player's hand.
     * @param gameBoard   The GameBoard object representing the game board system.
     */

    public AbstractGamePlayer(final Settings settings, final PlayerCards playercards, final GameBoard gameBoard) {
        this.settings = settings;
        this.playercards = playercards;
        this.gameBoard = gameBoard;
    }

    /**
     * Returns the name of the player.
     *
     * @return The name of the player.
     */

    @Override
    public String getName() {
        return this.settings.playerNames.get(playerid);
    }

    /**
     * Initializes the player with the given game configuration, cards, number of players, and player ID.
     *
     * @param configuration The GameConfiguration object representing the game configuration.
     * @param cards         The ImmutableList of Card objects representing the cards in the game.
     * @param numplayers    The number of players in the game.
     * @param playerid      The ID of the player.
     * @throws Exception If an error occurs during player initialization.
     */

    @Override
    public void init(final GameConfiguration configuration, final ImmutableList<Card> cards, final int numplayers, final int playerid) throws Exception {
        this.playerid = playerid - 1;
        this.configuration = configuration;
        this.gameBoard.setDrawPileCards(cards);
        this.gameBoard.setGameConfiguration(configuration);
        for (int i = 0; i < numplayers; i++)
            gameBoard.initBeginnerCards(i);
    }

    /**
     * Updates the player's state based on the opponent's move.
     *
     * @param opponentMove The Move object representing the opponent's move.
     * @throws Exception If an error occurs during the update.
     */

    @Override
    public void update(Move opponentMove) throws Exception {
        if(!gameBoard.executeMove(opponentMove))
            GameBoard.getGraphics().createCheaterPanel(settings.playerNames.get(gameBoard.getCurrentPlayerID()));
    }

    /**
     * Executes the player's move.
     *
     * @param move The Move object representing the player's move.
     * @return True if the move is executed successfully, false otherwise.
     * @throws Exception If an error occurs during the move execution.
     */

    public boolean executeMove(Move move) throws Exception {
        return gameBoard.executeMove(move);
    }

    /**
     * Verifies the validity of the game by comparing the scores of all players.
     * If any player's score does not match the actual score, a cheater panel is displayed.
     *
     * @param scores The ImmutableList of Integer representing the scores of all players.
     * @throws Exception If an error occurs during the verification process.
     */

    @Override
    public void verifyGame(final ImmutableList<Integer> scores) throws Exception {
        for (int playerid = 0; playerid < scores.size(); playerid++)
            if (!scores.get(playerid).equals(gameBoard.getAllScores().get(playerid)))
                GameBoard.getGraphics().createCheaterPanel(settings.playerNames.get(playerid));
    }  

    /**
     * Gets the GameBoard associated with the player.
     *
     * @return The GameBoard object associated with the player.
     */

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Gets the player's score.
     *
     * @return The score of the player.
     * @throws Exception If an error occurs while retrieving the score.
     */
    
    @Override
    public int getScore() throws Exception {
        return playercards.getScore();
    }
}