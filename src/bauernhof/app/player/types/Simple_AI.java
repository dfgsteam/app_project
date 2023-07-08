package bauernhof.app.player.types;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

public class Simple_AI extends AbstractGamePlayer implements AIHeader {

    public Simple_AI(String name, GameBoardState state) {
        super(name, state, PlayerType.SIMPLE_AI);
    }

    @Override
    public Move calculateNextMove() {
        Card to_take, to_remove;
        if (this.getState().getDepositedCards().isEmpty()) {
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
       int max_score = this.getAddScore(this.getState().getDepositedCards().get(0));
       Card from_deposit = this.getState().getDepositedCards().get(0);

       for (Card card : this.getState().getDepositedCards()) {
            if (this.getAddScore(card) > max_score) {
                from_deposit = card;
                max_score = this.getAddScore(card);
            }
       }

       return from_deposit;
    }

    @Override
    public Card cardFromStack() {
        return this.getState().getDrawPileCards().firstElement();
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
