package bauernhof.app.player.types.MoveTree.Threads;

import java.util.Stack;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.preset.Move;
import bauernhof.preset.card.Card;

/**
 * This class was written by
 * @author Viktor Tevosyan
 * @date 02.07.2023
 * This class repsresents a Thread which will either work with left part of the given MoveTree
 */
public class LeftThread extends AbstractThread {
    
    private Stack<MoveNode> remaining_to_calculate;
    
    public LeftThread(MoveTree tree) {
        super(tree);
        remaining_to_calculate = new Stack<MoveNode>();
        threadAction();
    }

    @Override
    public void calcNextNode(int cardNumTake, int cardNumPut) {
        Card to_take, to_put;
        if (cardNumTake < 0) {
            to_take = this.getTree().getActualLeftNode().getActualBoardState().getDrawPileCards().firstElement();
        }

        else {
            to_take = this.getTree().getActualLeftNode().getActualBoardState().getDepositedCards().get(cardNumTake);
        }

        if (cardNumPut < 0) {
            to_put = to_take;
        }

        else {
            to_put = this.getTree().getActualLeftNode().getActualPlayer().getCards().get(cardNumPut);
        }


        Move the_move = new Move(to_take, to_put);

        //!!!!!!!!!!!!!!Attention used methods are not implemented
        GameBoardState copy = this.getTree().getActualLeftNode().getActualBoardState().clone();
        copy.doMove(the_move);
        //-----------------------------------------

        AbstractGamePlayer next_player = (AbstractGamePlayer) copy.getActualPlayer();

        MoveNode new_node = new MoveNode(the_move, this.getTree().getActualRightNode(), copy, next_player);
        this.getTree().addLeftDepthNode(new_node);
    }

    synchronized public void threadAction() {
        for (int i = -1; i < this.getTree().getActualLeftNode().getDepositSize()/2; i++) {
            for (int j = -1; j < this.getTree().getActualLeftNode().getOwnSize()/2; j++) {
                calcNextNode(i, j);
                this.getTree().getActualLeftNode().setDepth(this.getTree().getActualLeftNode().getPrevNode().getDepth());
                this.remaining_to_calculate.push(this.getTree().getActualLeftNode().clone());    
                this.getTree().LeftGoToParent();
            }
        }
    }


    //------------------------------
    @Override
    public void run() {
        
    }
    
}
