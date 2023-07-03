package bauernhof.app.player.types.MoveTree.Threads;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.preset.Move;
import bauernhof.preset.card.Card;

public class WorkingThread extends Thread implements ThreadHandler {

    private static final int MAX_DEPTH = 3;
    private static Queue<MoveNode> next_calculations = new LinkedList<MoveNode>();
    private MoveTree move_tree;
    private MoveNode actual_node;

    public WorkingThread(GameBoardState actual_state) {
        move_tree = new MoveTree(new MoveNode(actual_state));
    }


    @Override
    public boolean calcNextNode(int cardNumTake, int cardNumPut) {
        if (this.actual_node.getDepth()+1 < MAX_DEPTH) { return false; }

        Card to_take, to_put;
        if (cardNumTake < 0) {
            to_take = this.actual_node.getActualBoardState().getDrawPileCards().firstElement();
        }

        else {
            to_take = this.actual_node.getActualBoardState().getDepositedCards().get(cardNumTake);
        }

        if (cardNumPut < 0) {
            to_put = to_take;
        }

        else {
            to_put = this.actual_node.getActualPlayer().getCards().get(cardNumPut);
        }

        Move new_move = new Move(to_take, to_put);
        GameBoardState new_state = this.actual_node.getActualBoardState().clone();

        if (!new_state.doMove(new_move)) { return false; }
        
        MoveNode next_MoveNode = new MoveNode(new_move, this.actual_node, new_state, new_state.getActualPlayer());
        next_MoveNode.setDepth(actual_node.getDepth()+1);
        this.actual_node = next_MoveNode;
        return true;
    }

    @Override
    public void setTree(MoveTree tree) {
        this.move_tree = tree;
    }

    @Override
    public MoveTree getTree() {
        return this.move_tree;
    }

    @Override
    public boolean threadAction() {
        for (int i = -1; i < this.actual_node.getDepositSize(); i++) {
            for (int j = -1; j < this.actual_node.getOwnSize(); j++) {
                if (!calcNextNode(i, j)) { return false; }
                next_calculations.add(actual_node.clone());
                this.actual_node = this.actual_node.getPrevNode();
            }
        }
        return true;
    }

    @Override
    public void setThreadNode(MoveNode move_node) {
        this.actual_node = move_node;
    }

    @Override
    public MoveNode getThreadNode() {
        return this.actual_node;
    }


    //------------
    @Override
    public void run() {

    }
}
