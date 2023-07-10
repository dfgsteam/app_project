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
    public Move request() {
        Card to_take, to_remove;
        if (!state.getDepositedCards().isEmpty() && !state.getDrawPileCards().isEmpty()) {
            if (this.getAddScore(cardFromDeposit()) > this.getAddScore(cardFromStack()))
                to_take = cardFromDeposit();
            else
                to_take = cardFromStack();
        }

        else if (!state.getDrawPileCards().isEmpty()) {
            to_take = this.cardFromStack();
        }

        else {
            to_take = cardFromDeposit();
        }

        to_remove = removeFromOwn(to_take);
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
        return state.getDrawPileCards().lastElement();
    }

    @Override
    public Card removeFromOwn(Card to_take) {
        int max_score = this.getRemoveScore(this.getCards().get(0));
        Card to_remove = this.getCards().get(0);
        for (Card card : this.getCards()) {
            if (this.getRemoveScore(card) > max_score) {
                to_remove = card;
                max_score = this.getRemoveScore(card);
            }
        }

        if (this.getAddScore(to_take) - this.getRemoveScore(to_remove) < 0) { to_remove = to_take; }

        return to_remove;
    }
    
}
