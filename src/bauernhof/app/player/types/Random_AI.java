package bauernhof.app.player.types;
import java.util.Iterator;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.*;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

public class Random_AI extends AbstractGamePlayer implements AIHeader{
    PlayerType type;

    public Random_AI(String name, GameBoardState actual_game) {
        super(name, actual_game, PlayerType.RANDOM_AI);
    }

    @Override
    public Move calculateNextMove() {
        Card to_take = null;
        Card to_deposit = null;

        if (this.getState().getDepositedCards().isEmpty() || Math.random() < 0.5) {
            to_take = this.getState().getDrawPileCards().firstElement();
        }

        else {
            to_take = this.getState().getDepositedCards().get(depositRandomNumber());
        }

        if (Math.random() < 0.5) {
            to_deposit = to_take;
        }
        
        else {
            to_deposit = this.getCards().get(ownCardNumber());
        }

        return new Move(to_take, to_deposit);
    }


    /**
     * Random Integer for the own card-index to put into deposit
     * @return int
     */
    private final int ownCardNumber() {
        return (int)Math.round((Math.random() * this.getState().getGameConfiguration().getNumCardsPerPlayerHand()));
    }

    /**
     * Random Integer for index of random card from deposit to take
     * @return
     */
    private final int depositRandomNumber() {
        return (int)(this.getState().getDepositedCards().size() * Math.random());
    }

    @Override
    public Card cardFromDeposit() {
       int deposit_random_index = depositRandomNumber();
       Iterator<Card> it = this.getState().getDepositedCards().iterator();
       for (int i = 0; i < deposit_random_index; i++) {
            it.next();
        }

        return it.next();
    }

    @Override
    public Card cardFromStack() {
        return this.getState().getDrawPileCards().firstElement();
    }

    @Override
    public Card removeFromOwn() {
        int own_random_index = ownCardNumber();
        return this.getCards().get(own_random_index);
    }
    
}
