package bauernhof.app.player.types;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.PlayerCards;
import bauernhof.app.system.GameBoard;
import bauernhof.preset.Move;
import bauernhof.preset.Settings;
import bauernhof.preset.card.Card;


public class Simple_AI extends AbstractGamePlayer implements AIHeader {

    public Simple_AI(final Settings settings, final PlayerCards playercards, final GameBoard gameBoard) {
        super(settings, playercards, gameBoard);
    }

    @Override
    public Move request() {
        Card to_take = null, to_remove = null;

        if (!gameBoard.getDepositedCards().isEmpty() && !gameBoard.getDrawPileCards().isEmpty()) {
            if (playercards.getAddScore(cardFromDeposit()) > playercards.getAddScore(cardFromStack()))
                to_take = cardFromDeposit();
            else
                to_take = cardFromStack();
        }

        else if (!gameBoard.getDrawPileCards().isEmpty()) {
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
       int max_score = playercards.getAddScore(gameBoard.getDepositedCards().get(0));
       Card from_deposit = gameBoard.getDepositedCards().get(0);

       for (Card card : gameBoard.getDepositedCards()) {
            if (playercards.getAddScore(card) > max_score) {
                from_deposit = card;
                max_score = playercards.getAddScore(card);
            }
       }

       return from_deposit;
    }

    @Override
    public Card cardFromStack() {
        return gameBoard.getDrawPileCards().get(gameBoard.getDrawPileCards().size() - 1);
    }

    @Override
    public Card removeFromOwn(Card to_take) {
        int max_score = playercards.getAddRemoveScore(to_take, to_take);
        Card to_remove = to_take;
        for (Card card : playercards.getCards()) {
            if (playercards.getAddRemoveScore(to_take, card) > max_score) {
                to_remove = card;
                max_score = playercards.getAddRemoveScore(to_take, card);
            }
        }


        return to_remove;
    }
    
}
