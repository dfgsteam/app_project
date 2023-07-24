package bauernhof.app.system;

import bauernhof.app.player.PlayerCards;
import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.Move;
import bauernhof.preset.Settings;
import bauernhof.preset.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * This class represents the game board for the Hofbauern game.
 * It manages the game state, including player cards, draw pile, deposited cards,
 * and other relevant information related to the game state.
 * The GameBoard class implements the Game interface, which defines the methods
 * to access and modify the game state.
 *
 * The class includes methods to initialize the game board, execute player moves,
 * update the active player, and retrieve game-related information like scores and winners.
 * It also provides methods to clone the game board state, shuffle cards, and manage settings.
 *
 * The GameBoard class also holds a reference to the UiGame object, which represents
 * the graphical user interface for players to interact with the game board.
 *
 * @author Ramon Cemil Kimyon
 */

public class GameBoard implements Game {
    protected int activeplayerid;
    public static UiGame graphics;
    protected int round;
    protected ArrayList<Card> deposited_cards;
    protected Stack<Card> drawpile_cards = new Stack<>();
    protected PlayerCards[] playercards;
    protected int numplayers;
    protected GameConfiguration configuration;
    protected Settings settings;


    /**
     * Constructs a new GameBoard object with the given number of players, settings, and game configuration.
     *
     * @param numplayers    The number of players in the game.
     * @param settings      The Settings object representing game settings.
     * @param configuration The GameConfiguration object representing the game configuration.
     */
    
    public GameBoard(final int numplayers, Settings settings, final GameConfiguration configuration) {
        this(numplayers, settings, configuration, 0, 0);
    }


    /**
     * Constructs a new GameBoard object with the given number of players, settings, game configuration,
     * round number, and active player ID.
     *
     * @param numplayers     The number of players in the game.
     * @param settings       The Settings object representing game settings.
     * @param configuration  The GameConfiguration object representing the game configuration.
     * @param round          The current round number.
     * @param activeplayerid The ID of the active player.
     */
    
    public GameBoard(final int numplayers, final Settings settings, final GameConfiguration configuration, final int round, final int activeplayerid) {

        this.numplayers = numplayers;
        this.settings = settings;
        playercards = new PlayerCards[numplayers];
        for (int i = 0; i < numplayers; i++)
            playercards[i] = new PlayerCards();
        this.configuration = configuration;
        this.round = round;
        this.activeplayerid = activeplayerid;
        final ImmutableList<Card> drawpile_cards = mixCards(new ImmutableList<>(configuration.getCards()));
        for (final Card card : drawpile_cards)
            this.drawpile_cards.add(card);
        deposited_cards = new ArrayList<>();
    }

    /**
     * Constructs a new GameBoard object with the given player cards, settings, round number, active player ID,
     * deposited cards, draw pile cards, and game configuration.
     *
     * @param playercards      An array of PlayerCards objects representing player hands.
     * @param settings         The Settings object representing game settings.
     * @param round            The current round number.
     * @param activeplayerid   The ID of the active player.
     * @param deposited_cards  The ArrayList of deposited cards.
     * @param drawpile_cards   The ImmutableList of draw pile cards.
     * @param configuration    The GameConfiguration object representing the game configuration.
     */

    public GameBoard(final PlayerCards[] playercards, final Settings settings, final int round, final int activeplayerid, final ArrayList<Card> deposited_cards, ImmutableList<Card> drawpile_cards, final GameConfiguration configuration) {
        this.playercards = playercards;
        this.settings = settings;
        this.numplayers = playercards.length;
        this.round = round;
        this.activeplayerid = activeplayerid;
        this.configuration = configuration;
        this.deposited_cards = deposited_cards;
        for (final Card card : drawpile_cards)
            this.drawpile_cards.add(card);
    }

    /**
     * Constructs a new GameBoard object with the given player cards, settings, round number, active player ID,
     * deposited cards, draw pile cards, game configuration, and UiGame object representing the graphical user interface.
     *
     * @param playercards      An array of PlayerCards objects representing player hands.
     * @param settings         The Settings object representing game settings.
     * @param round            The current round number.
     * @param activeplayerid   The ID of the active player.
     * @param deposited_cards  The ArrayList of deposited cards.
     * @param drawpile_cards   The ImmutableList of draw pile cards.
     * @param configuration    The GameConfiguration object representing the game configuration.
     * @param graphics         The UiGame object representing the graphical user interface.
     */

    public GameBoard(final PlayerCards[] playercards, final Settings settings, final int round, final int activeplayerid, final ArrayList<Card> deposited_cards, ImmutableList<Card> drawpile_cards, final GameConfiguration configuration, final UiGame graphics) {
        this(playercards, settings, round, activeplayerid, deposited_cards, drawpile_cards, configuration);
        GameBoard.graphics = graphics;
    }

    /**
     * Returns a deep copy (clone) of the GameBoard object.
     *
     * @return A new GameBoard object with the same game state as the original.
     */

    public GameBoard clone() {
        final PlayerCards[] playercards = new PlayerCards[this.playercards.length];
        for (int i = 0; i < this.playercards.length; i++)
            playercards[i] = this.playercards[i].clone();
        return new GameBoard(playercards, settings, round, activeplayerid, getDepositedCards(), getDrawPileCards(), configuration);
    }

    /**
     * Returns the ID of the current active player.
     *
     * @return The ID of the current active player.
     */

    public int getCurrentPlayerID() {
        return activeplayerid;
    }

    /**
     * Returns an ImmutableList of scores for all players in the game.
     *
     * @return An ImmutableList of scores for all players in the game.
     */

    public ImmutableList<Integer> getAllScores() {
        final ArrayList<Integer> scores = new ArrayList<>();
        for (final PlayerCards playercards : this.playercards)
            scores.add(playercards.getScore());
        return new ImmutableList<>(scores);
    }

    /**
     * Returns an ArrayList of deposited cards on the game board.
     *
     * @return An ArrayList of deposited cards on the game board.
     */

    public ArrayList<Card> getDepositedCards() {
        return new ArrayList<>(deposited_cards);
    }

    /**
     * Sets the draw pile cards to the provided ImmutableList of cards.
     *
     * @param drawpile_cards The ImmutableList of draw pile cards to set.
     */

    public void setDrawPileCards(final ImmutableList<Card> drawpile_cards) {
        this.drawpile_cards = new Stack<>();
        for (final Card card : drawpile_cards)
            this.drawpile_cards.add(card);
    }
    
    /**
     * Returns an ImmutableList of draw pile cards on the game board.
     *
     * @return An ImmutableList of draw pile cards on the game board.
     */

    public ImmutableList<Card> getDrawPileCards() {
        return new ImmutableList<>(this.drawpile_cards);
    }

    /**
     * Returns the PlayerCards object representing the player's hand for the specified player ID.
     *
     * @param playerid The ID of the player to get the hand for.
     * @return The PlayerCards object representing the player's hand.
     */

    public PlayerCards getPlayerCards(final int playerid) {
        return playercards[playerid];
    }

    /**
     * Returns the PlayerCards object representing the hand of the current active player.
     *
     * @return The PlayerCards object representing the hand of the current active player.
     */

    public PlayerCards getCurrentPlayerCards() {
        return getPlayerCards(activeplayerid);
    }
    
    /**
     * Returns the current round number of the game.
     *
     * @return The current round number of the game.
     */

    public int getRound() {
        return this.round;
    }
    
    /**
     * Sets the current round number of the game.
     *
     * @param round The round number to set.
     */

    public void setRound(final int round) {
        this.round = round;
    }

    /**
     * Returns the GameConfiguration object representing the game configuration.
     *
     * @return The GameConfiguration object representing the game configuration.
     */

    public GameConfiguration getConfiguration() {
        return configuration;
    }
        
    /**
     * Sets the GameConfiguration object representing the game configuration.
     *
     * @param configuration The GameConfiguration object to set.
     */

    public void setGameConfiguration(GameConfiguration configuration) {
        this.configuration = configuration;
    }
    @Override
    public String getName(int playerid) throws Exception {
        return settings.playerNames.get(playerid);
    }

    @Override
    public int getScore(int playerid) throws Exception {
        return getPlayerCards(playerid).getScore();
    }

    @Override
    public Settings getSettings() {
        return settings;
    }

    private ImmutableList<Card> mixCards(final ImmutableList<Card> cards) {
        ArrayList<Card> cardscopy = new ArrayList<>(cards);
        Collections.shuffle(cardscopy);
        ImmutableList<Card> cardsimmutablelist = new ImmutableList<>(cardscopy);
        return cardsimmutablelist;
    }

    /**
     * Initializes the beginner cards for the specified player.
     *
     * @param playerid The ID of the player to initialize beginner cards for.
     */

    public void initBeginnerCards(final int playerid) {
        for (int i = 0; i < configuration.getNumCardsPerPlayerHand(); i++)
            playercards[playerid].add(drawpile_cards.pop());
    }

    /**
     * Executes the provided move in the game.
     *
     * @param move The Move object representing the move to execute.
     * @return True if the move is executed successfully, false otherwise.
     * @throws Exception If an error occurs during the move execution.
     */

    public boolean executeMove(final Move move) throws Exception {
        if (deposited_cards.contains(move.getTaken()))
            deposited_cards.remove(move.getTaken());
        else if (!(drawpile_cards.isEmpty()) && drawpile_cards.lastElement().equals(move.getTaken()))
            drawpile_cards.pop().getName();
        else return false;
        deposited_cards.add(move.getDeposited());
        // Update PlayerCards
        getCurrentPlayerCards().add(move.getTaken());
        getCurrentPlayerCards().remove(move.getDeposited());
        updatePlayerID();
        return true;
    }

    /**
     * Updates the active player ID and round number for the next turn.
     */

    public void updatePlayerID() {
        activeplayerid++;
        if (activeplayerid == playercards.length) {
            activeplayerid = 0;
            this.round++;
        }
    }
    
    /**
     * Returns the number of players in the game.
     *
     * @return The number of players in the game.
     */

    public int getNumPlayers() {
        return numplayers;
    }

    /**
     * Returns the ID of the winning player in the game.
     *
     * @return The ID of the winning player in the game.
     */

    @Override
    public int getWinnerID() {
        ArrayList<Integer> scores = new ArrayList<>(getAllScores());
        Collections.sort(scores);
        if (scores.get(scores.size() - 1).equals(scores.get(scores.size() - 2))) return numplayers;
        for (int i = 0; i < numplayers; i++)
            if (playercards[i].getScore() == scores.get(scores.size() - 1))
                return i;
        return playercards.length;
    }

    /**
     * Returns the UiGame object representing the graphical user interface for the game.
     *
     * @return The UiGame object representing the graphical user interface for the game.
     */
    
    public static UiGame getGraphics() {
        return graphics;
    }
}
