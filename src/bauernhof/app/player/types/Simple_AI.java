package bauernhof.app.player.types;

import bauernhof.app.player.AIHeader;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.PlayerCards;
import bauernhof.app.system.GameBoard;
import bauernhof.preset.Move;
import bauernhof.preset.Settings;
import bauernhof.preset.card.Card;

/**
 * This class was created by
 * @author Viktor Tevosyan
 * @date 28.06.2023
 * This class represents the Simple_AI, which extends from AbstractGamePlayer, because it is a GamePlayer.
 * This class implements the AIHeader interface, to follow the basic structure of the Artificial Intelligence, which is written in the interface.
 * The mission of the Simple_AI, it is to follow one strict algorithm -> make the move, which is giving the maximal amount of points to the AI (without viewing, what other players do):
 * - Understands how the board looks like now
 * - Can determine which move is a good move and which not
 * - Does not consider the points of enemies
 * - Makes the move, which gives the highest Amount of Points now
 */
public class Simple_AI extends AbstractGamePlayer implements AIHeader {

    /**
     * Parametrized constructor, which creates a Simple_AI player
     * @param settings
     * @param playercards
     * @param gameBoard
     */
    public Simple_AI(final Settings settings, final PlayerCards playercards, final GameBoard gameBoard) {
        super(settings, playercards, gameBoard);
    }

    @Override
    public Move request() {
        Card to_take = null, to_remove = null;

        //First case: DrawPileStack and the deposit are both not empty
        if (!gameBoard.getDepositedCards().isEmpty() && !gameBoard.getDrawPileCards().isEmpty()) {
            if (playercards.getAddScore(cardFromDeposit()) > playercards.getAddScore(cardFromStack())) to_take = cardFromDeposit();     //if the "best" card of the deposit is better than the card from the drawpile, then take from deposit
            else to_take = cardFromStack();     //else take from the drawpile stack
        }
        

        //Second case: DepositedArea is empty (like in the first round as the first player)
        else if (!gameBoard.getDrawPileCards().isEmpty()) { to_take = this.cardFromStack(); }           //Just take from the DrawPileStack

        //Third case: DrawPileStack is empty (uncommon)
        else { to_take = cardFromDeposit(); }                   //Just take from the deposited area

        to_remove = removeFromOwn(to_take);         //Remove the card, which leaves the score best
        return new Move(to_take, to_remove);        //return the created move
    }

    @Override
    /** The method looks for the max_score (sequentially)
     * @return Card from deposit, which is going to give the AI the highest score
     */
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
    /**
     * @return Card from the drawpile stack
     */
    public Card cardFromStack() {
        return gameBoard.getDrawPileCards().get(gameBoard.getDrawPileCards().size() - 1);
    }

    @Override
    /**The method looks for the max_score (sequentially)
     * @return Card from the own hand, which leaves the score in the best Position after putting it away (considering the taking card as well)
     */
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