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
        else if(drawpile_cards.firstElement().equals(move.getTaken()))
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
