package bauernhof.app.player;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.Move;
import bauernhof.preset.card.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author Ramon Cemil Kimyon
 * @date 06.07.2023 22:15
 */
public class PlayerGameBoard {
    private int round = 0;
    private int activeplayerid = 0;
    private ArrayList<Card>[] playergamecards;
    private Stack<Card> drawpile_cards;
    private Set<Card> deposited_cards = new HashSet<>();
    public PlayerGameBoard() {

    }
    public PlayerGameBoard(final int numplayers, final GameConfiguration configuration, final Stack<Card> drawpile_cards) {
        playergamecards = new ArrayList[numplayers];
        this.drawpile_cards = drawpile_cards;
        for (int i = 0; i < numplayers; i++) {
            playergamecards[i] = new ArrayList<>();
            for (int cardcounter = 0; cardcounter < configuration.getNumCardsPerPlayerHand(); cardcounter++)
                playergamecards[i].add(this.drawpile_cards.pop());
        }
    }
    public boolean doMove(final Move move) {
        if (deposited_cards.contains(move.getTaken()))
            deposited_cards.remove(move.getTaken());
        else if(drawpile_cards.lastElement().equals(move.getTaken()))
            drawpile_cards.pop();
        //else return false;
       /* if (!playergamecards[activeplayerid].contains(move.getDeposited()))
            return false;*/
        deposited_cards.add(move.getDeposited());
        playergamecards[activeplayerid].remove(move.getDeposited());
        playergamecards[activeplayerid].add(move.getTaken());
        activeplayerid++;
            if (activeplayerid == playergamecards.length) {
                activeplayerid = 0;
                round++;
            }
        return true;
    }
    public PlayerGameBoard clone() {
        final PlayerGameBoard player_gameboard = new PlayerGameBoard();
        player_gameboard.setActiveplayerid(activeplayerid);
        final Set<Card> deposited_cards = new HashSet<>();
        for (final Card card : this.deposited_cards)
            deposited_cards.add(card);
        player_gameboard.setDeposited_cards(deposited_cards);
        final Stack<Card> drawpile_cards = new Stack<>();
        for (final Card card : this.drawpile_cards)
            drawpile_cards.add(card);
        player_gameboard.setDrawpile_cards(drawpile_cards);
        player_gameboard.setRound(round);
        final ArrayList<Card>[] playergamecards = new ArrayList[this.playergamecards.length];
        for (int i = 0; i < this.playergamecards.length; i++) {
            playergamecards[i] = new ArrayList<>();
            for (final Card card : this.playergamecards[i])
                playergamecards[i].add(card);
        }
        player_gameboard.setPlayergamecards(playergamecards);
        return player_gameboard;
    }
    public void setRound(final int round) {
        this.round = round;
    }
    public void setActiveplayerid(final int activeplayerid) {
        this.activeplayerid = activeplayerid;
    }
    public void setDrawpile_cards(final Stack<Card> drawpile_cards) {
        this.drawpile_cards = drawpile_cards;
    }
    public void setDeposited_cards(final Set<Card> deposited_cards) {
        this.deposited_cards = deposited_cards;
    }
    public void setPlayergamecards(final ArrayList<Card>[] playergamecards) {
        this.playergamecards = playergamecards;
    }
    public ArrayList<Card>[] getCards() {
        return playergamecards;
    }
    public ArrayList<Card> getDepositedCards() {
        return new ArrayList<>(deposited_cards);
    }
    public Stack<Card> getDrawPileCards() {
        return this.drawpile_cards;
    }
}
