package bauernhof.app.player.types.MoveTree.Threads;

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
 * This class represents a Thread, which will be oriented to do its calculations on th right side of given Tree
 */
public class RightThread extends AbstractThread {
    

    public RightThread(MoveTree tree) {
        super(tree);
    }

    @Override
    public void calcNextNode(int cardNumTake, int cardNumPut) {
        Card to_take, to_put;
        if (cardNumTake < 0) {
            to_take = this.getTree().getActualRightNode().getActualBoardState().getDrawPileCards().firstElement();
        }

        else {
            to_take = this.getTree().getActualRightNode().getActualBoardState().getDepositedCards().get(cardNumTake);
        }

        if (cardNumPut < 0) {
            to_put = to_take;
        }

        else {
            to_put = this.getTree().getActualRightNode().getActualPlayer().getCards().get(cardNumPut);
        }


        Move the_move = new Move(to_take, to_put);

        //!!!!!!!!!!!!!!Attention used methods are not implemented
        GameBoardState copy = this.getTree().getActualRightNode().getActualBoardState().clone();
        copy.doMove(the_move, true);
        //-----------------------------------------

        AbstractGamePlayer next_player = (AbstractGamePlayer) copy.getActualPlayer();

        MoveNode new_node = new MoveNode(the_move, this.getTree().getActualRightNode(), copy, next_player);
        this.getTree().addRightDepthNode(new_node);
    }

    //-----------------------
    @Override
    public void run() {

    }
    
}
