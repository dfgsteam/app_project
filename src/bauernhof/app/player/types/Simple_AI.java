package bauernhof.app.player.types;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

public class Simple_AI extends AbstractGamePlayer implements AIHeader {

    public Simple_AI(String name) {
        super(name, PlayerType.SIMPLE_AI);
    }

    @Override
    public Move calculateNextMove() {
        Card to_take, to_remove;
        if (state.getDepositedCards().isEmpty()) {
            to_take = this.cardFromStack();
        }

        else {
            if (this.getAddScore(cardFromDeposit()) > this.getAddScore(cardFromStack()))
                to_take = cardFromDeposit();
            else
                to_take = cardFromStack();
        }

        to_remove = removeFromOwn();
        return new Move(to_take, to_remove);
    }

    @Override
    public Card cardFromDeposit() {
       int max_score = this.getAddScore(state.getDepositedCards().get(0));
       Card from_deposit = state.getDepositedCards().get(0);

       for (Card card : state.getDepositedCards()) {
            if (this.getAddScore(card) > max_score) {
                from_deposit = card;
                max_score = this.getAddScore(card);
            }
       }

       return from_deposit;
    }

    @Override
    public Card cardFromStack() {
        return state.getDrawPileCards().firstElement();
    }

    @Override
    public Card removeFromOwn() {
        Card[] card_array = (Card[])this.getCards().toArray();
        int max_score = this.getRemoveScore(card_array[0]);
        Card to_remove = card_array[0];
        for (Card card : this.getCards()) {
            if (this.getRemoveScore(card) > max_score) {
                to_remove = card;
                max_score = this.getRemoveScore(card);
            }
        }

        return to_remove;
    }
    
}
