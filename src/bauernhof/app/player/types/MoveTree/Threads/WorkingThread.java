package bauernhof.app.player.types.MoveTree.Threads;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.PlayerGameBoard;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.preset.Move;
import bauernhof.preset.card.Card;

public class WorkingThread extends AbstractThread {

    private static Queue<MoveNode> next_calculations = new LinkedList<MoveNode>();
    

    public WorkingThread(GameBoardState actual_state) throws Exception {
        this.setTree(new MoveTree(new MoveNode(actual_state)));
        this.setThreadNode(this.getTree().getActualNode());
        workingThreadAction();
        start();
    }

    public WorkingThread(MoveTree tree) {
        this.setTree(tree);
        this.setThreadNode(null);
        start();
    }


    @Override
    public boolean calcNextNode(int cardNumTake, int cardNumPut) throws Exception {
        Card[] owncards = (Card[])this.getThreadNode().getActualBoardState().getActualPlayer().getCards().toArray();
        if (this.getThreadNode().getDepth()+1 < this.getMaxDepth()) { return false; }

        Card to_take, to_put;
        if (cardNumTake < 0) {
            to_take = this.getThreadNode().getActualBoardState().getDrawPileCards().firstElement();
        }

        else {
            to_take = this.getThreadNode().getActualBoardState().getDepositedCards().get(cardNumTake);
        }

        if (cardNumPut < 0) {
            to_put = to_take;
        }

        else {
            to_put = owncards[cardNumPut];
        }

        Move new_move = new Move(to_take, to_put);
        GameBoardState new_state = (GameBoardState) this.getThreadNode().getActualBoardState().clone();

        if (!new_state.doMove(new_move)) { return false; }
        
        MoveNode next_MoveNode = new MoveNode(new_move, this.getThreadNode(), new_state);
        next_MoveNode.setDepth(this.getThreadNode().getDepth()+1);
        return true;
    }

    @Override
    public boolean workingThreadAction() throws Exception {
        synchronized (next_calculations) {
        if (this.getThreadNode() == null) {
            this.setThreadNode(WorkingThread.next_calculations.remove());
        }

        for (int i = -1; i < this.getThreadNode().getActualBoardState().getDepositedCards().size(); i++) {
            for (int j = -1; j < this.getThreadNode().getActualBoardState().getActualPlayer().getCards().size(); j++) {
                if (!calcNextNode(i, j)) { return false; }
                next_calculations.add(this.getThreadNode());
                this.setThreadNode(this.getThreadNode().getPrevNode());
            }
        }
        this.setThreadNode(null);
        return true;
        }
    }


    //------------
    @Override
    public void run() {
        while (!WorkingThread.next_calculations.isEmpty()) {
            try {
                workingThreadAction();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        interrupt();
    }


    //This methods are not used by this Thread
    @Override
    public MoveNode getBestOfActual() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBest'");
    }

    @Override
    public void sequenceThreadAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sequenceThreadAction'");
    }

    @Override
    public boolean goDeeper() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'goDeeper'");
    }
}
