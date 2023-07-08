package bauernhof.app.player.types;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.*;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

import java.util.Random;

public class Random_AI extends AbstractGamePlayer implements AIHeader{
    PlayerType type;

    public Random_AI(String name) {
        super(name, PlayerType.RANDOM_AI);
    }

    @Override
    public Move calculateNextMove() {
        Card to_take = null;
        Card to_deposit = null;

        if (state.getDepositedCards().isEmpty() || Math.random() < 0.5) {
            to_take = cardFromStack();
        }

        else {
            to_take = cardFromDeposit();
        }

        if (Math.random() < 0.5) {
            to_deposit = to_take;
        }
        
        else {
            to_deposit = removeFromOwn();
        }

        return new Move(to_take, to_deposit);
    }


    /**
     * Random Integer for the own card-index to put into deposit
     * @return int
     */
    private final int ownCardNumber() {
        Random random = new Random();
        return random.nextInt(configuration.getNumCardsPerPlayerHand());
       // return (int)Math.round((Math.random() * configuration.getNumCardsPerPlayerHand()));
    }

    /**
     * Random Integer for index of random card from deposit to take
     * @return
     */
    private final int depositRandomNumber() {
        Random random = new Random();
        return (int)(random.nextInt(state.getDepositedCards().size()));
    }

    @Override
    public Card cardFromDeposit() {
       int deposit_random_index = depositRandomNumber();
      // System.out.println("deposit_random_index : " + deposit_random_index);
       return state.getDepositedCards().get(deposit_random_index);
    }

    @Override
    public Card cardFromStack() {
        return state.getDrawPileCards().lastElement();
    }

    @Override
    public Card removeFromOwn() {
        int own_random_index = ownCardNumber();
        //System.out.println("own_random_index : " + own_random_index);
        return (Card)this.getCards().toArray()[own_random_index];
    }
    
}
