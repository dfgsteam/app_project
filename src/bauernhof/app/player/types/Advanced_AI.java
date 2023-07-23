package bauernhof.app.player.types;
import java.util.Collections;
import java.util.HashSet;

import bauernhof.app.player.AIHeader;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.PlayerCards;
import bauernhof.app.player.types.MoveTree.TreeManage.TreeSearcher;
import bauernhof.app.player.types.MoveTree.TreeManage.TreeCreator;
import bauernhof.app.system.GameBoard;
import bauernhof.preset.Move;
import bauernhof.preset.Settings;
import bauernhof.preset.card.Card;

/**
 * This class was written by
 * @author Viktor Tevosyan
 * @date 28.06.2023
 * This class represents the Advanced_AI. It extends from the AbstractGamePlayer, because it is a GamePlayer. 
 * The Implementation of the AIHeader is not full, because this AI calculates the best Moves using the TreeCreator Class to calculate a Tree Of Moves and the TreeSearcher Class to calculate best Move for the Advnaced_AI.
 * The mission of the following Advanced_AI is to play smart. It looks in the future for given Calculational Depth and tries to play the Move, which will give most Advantage for the Future:
 * - Looks what the Enemies can do
 * - Thinks in kind, that the enemies will play most score achieving moves
 * - Compares Points with the enemies
 * - Peeks the Move, which will bring the best score difference in the future ( for the Advanced_AI)
 */
public class Advanced_AI extends AbstractGamePlayer implements AIHeader {
    /**
     * Board one round Before for Compairson (not calculating again, if it was already calculated)
     */
    private GameBoard board_before = null;
    /**
     * Move, which was made before, so it does not calculate the Move again, if the gaming position was the same 1 round before
     */
    private Move move_before = null;

    public Advanced_AI(final Settings settings, final PlayerCards playercards, final GameBoard gameboard) {
        super(settings, playercards, gameboard);
    }

    @Override
    public Move request() throws Exception  {
        //If The players just switched some cards at the same place as before, you do not need to calculate tree and the move again, just use the previous decision
        if (board_before != null) {
            HashSet<Card> before_depoCards = new HashSet<Card>(board_before.getDepositedCards()), actual_depoCards = new HashSet<Card>(this.getGameBoard().getDepositedCards());
            Card before_drawCard = board_before.getDrawPileCards().get(board_before.getDrawPileCards().size()-1), actual_drawCard = this.getGameBoard().getDrawPileCards().get(this.getGameBoard().getDrawPileCards().size()-1);
            if (before_depoCards.equals(actual_depoCards) && before_drawCard.equals(actual_drawCard)) { return move_before; }
        }

        board_before = this.getGameBoard().clone();
        TreeCreator TreeCreator1 = new TreeCreator(gameBoard.clone());          //create the tree of moves
        TreeSearcher TreeSearcher1 = new TreeSearcher(TreeCreator1.getTree());      //calculate valuable point differences at the end of the tree
        Move move = TreeCreator1.getTree().getRootNode().getNextNodes().get(TreeSearcher1.differences.indexOf(Collections.max(TreeSearcher1.differences))).getMove();       //Peek the Move, which could lead to the most valuable Point Difference
        move_before = move;     
        return move;
      }

    //Not usable methods
    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public Card cardFromDeposit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cardFromDeposit'");
    }

    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public Card cardFromStack() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cardFromStack'");
    }

    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public Card removeFromOwn(Card to_take) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFromOwn'");
    }
    
}
