package bauernhof.app.system;

import bauernhof.app.player.PlayerCards;
import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.Move;
import bauernhof.preset.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * @author Ramon Cemil Kimyon
 * @date 15.07.2023 09:57
 */
public class GameBoard {
    private int activeplayerid;
    protected static UiGame graphics;
    protected int round;
    private ArrayList<Card> deposited_cards;
    private Stack<Card> drawpile_cards = new Stack<>();
    private PlayerCards[] playercards;
    protected int numplayers;
    protected GameConfiguration configuration;
    public GameBoard(final PlayerCards[] playercards, final int round, final int activeplayerid, final ArrayList<Card> deposited_cards, ImmutableList<Card> drawpile_cards, final GameConfiguration configuration) {
        this.playercards = playercards;
        this.round = round;
        this.activeplayerid = activeplayerid;
        this.configuration = configuration;
        this.deposited_cards = deposited_cards;
        for (final Card card : drawpile_cards)
            this.drawpile_cards.add(card);
    }
    public GameBoard(final int numplayers, final GameConfiguration configuration, final int round, final int activeplayerid) {
        this.numplayers = numplayers;
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
    public GameBoard(final int numplayers, final GameConfiguration configuration) {
        this(numplayers, configuration, 1, 0);
    }
    public GameBoard(final PlayerCards[] playercards, final int round, final int activeplayerid, final ArrayList<Card> deposited_cards, ImmutableList<Card> drawpile_cards, final GameConfiguration configuration, final UiGame graphics) {
        this(playercards, round, activeplayerid, deposited_cards, drawpile_cards, configuration);
        GameBoard.graphics = graphics;
    }
    public GameBoard clone() {
        final PlayerCards[] playercards = new PlayerCards[this.playercards.length];
        for (int i = 0; i < this.playercards.length; i++)
            playercards[i] = this.playercards[i].clone();
        return new GameBoard(playercards, round, activeplayerid, getDepositedCards(), getDrawPileCards(), configuration);
    }
    public int getActivePlayerID() {
        return activeplayerid;
    }
    public ImmutableList<Integer> getAllScores() throws Exception {
        final ArrayList<Integer> scores = new ArrayList<>();
        for (final PlayerCards playercards : this.playercards)
            scores.add(playercards.getScore());
        return new ImmutableList<>(scores);
    }
    public ArrayList<Card> getDepositedCards() {
        return new ArrayList<>(deposited_cards);
    }
    public ImmutableList<Card> getDrawPileCards() {
        return new ImmutableList<>(this.drawpile_cards);
    }
    public PlayerCards getPlayerCards(final int playerid) {
        return playercards[playerid];
    }
    public PlayerCards getActualPlayerCards() {
        return getPlayerCards(activeplayerid);
    }
    public int getRound() {
        return this.round;
    }
    public void setRound(final int round) {
        this.round = round;
    }
    public GameConfiguration getConfiguration() {
        return configuration;
    }
    private ImmutableList<Card> mixCards(final ImmutableList<Card> cards) {
        ArrayList<Card> cardscopy = new ArrayList<>(cards);
        Collections.shuffle(cardscopy);
        ImmutableList<Card> cardsimmutablelist = new ImmutableList<>(cardscopy);
        return cardsimmutablelist;
    }
    public void initBeginnerCards(final int playerid) throws Exception {
        for (int i = 0; i < configuration.getNumCardsPerPlayerHand(); i++)
            playercards[playerid].add(drawpile_cards.pop());
    }

    public boolean executeMove(final Move move) throws Exception {
        if (deposited_cards.contains(move.getTaken()))
            deposited_cards.remove(move.getTaken());
        else if (!(drawpile_cards.isEmpty()) && drawpile_cards.lastElement().equals(move.getTaken()))
            drawpile_cards.pop();
        else return false;
        deposited_cards.add(move.getDeposited());
        // Update PlayerCards
        getActualPlayerCards().add(move.getTaken());
        getActualPlayerCards().remove(move.getDeposited());
        // Active Player ID UPDATE
        activeplayerid++;
        if (activeplayerid == playercards.length) {
            activeplayerid = 0;
            this.round++;
        }
        return true;
    }
    public int getNumPlayers() {
        return numplayers;
    }
    public static UiGame getGraphics() {
        return graphics;
    }
}
