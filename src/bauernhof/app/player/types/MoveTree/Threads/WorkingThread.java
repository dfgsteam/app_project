package bauernhof.app.player.types.MoveTree.Threads;

import java.util.LinkedList;
import java.util.Queue;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.preset.Move;
import bauernhof.preset.card.Card;

public class WorkingThread extends AbstractThread {

    private static Queue<MoveNode> next_calculations = new LinkedList<MoveNode>();
    

    /**
     * Constructor,that signalises, that the first Thread-Object is going to be created, so there is no MoveTree yet
     * @param actual_state
     * @throws Exception
     */
    public WorkingThread(GameBoardState actual_state) throws Exception {
        super(actual_state);
        this.setThreadNode(getTree().getRootNode());
        workingThreadAction();
        start();
    }

    /**
     * Constructor, that signalises, that there are some next_calculations and that the tree is not empty
     * @param tree
     */
    public WorkingThread() {
        this.setThreadNode(null);
        start();
    }


    @Override
    public boolean calcNextNode(int cardNumTake, int cardNumPut) throws Exception {
        if (this.getThreadNode().getDepth()+1 > this.getMaxDepth()) { return false; }
        Card to_take, to_put;
        if (cardNumTake < 0) {
            to_take = this.getThreadNode().getActualBoardState().getDrawPileCards().lastElement();
        }

        else {
            to_take = this.getThreadNode().getActualBoardState().getDepositedCards().get(cardNumTake);
        }

        if (cardNumPut < 0) {
            to_put = to_take;
        }

        else {
            to_put = this.getThreadNode().getActualBoardState().getActualPlayer().getCards().get(cardNumPut);
        }

        Move new_move = new Move(to_take, to_put);
        GameBoardState new_state = (GameBoardState) this.getThreadNode().getActualBoardState().clone();

        if (!new_state.doMove(new_move)) { return false; }
        
        MoveNode next_MoveNode = new MoveNode(new_move, this.getThreadNode(), new_state);
        next_MoveNode.setDepth(this.getThreadNode().getDepth()+1);
        this.setThreadNode(next_MoveNode);
        return true;
    }

    @Override
    public boolean workingThreadAction() throws Exception {
        synchronized (next_calculations) {
            if (this.getThreadNode() == null) {
                if (next_calculations.isEmpty()) { return false; }
                this.setThreadNode(WorkingThread.next_calculations.remove());
            }

            for (int i = -1; i < this.getThreadNode().getActualBoardState().getDepositedCards().size(); i++) {
                for (int j = -1; j < this.getThreadNode().getActualBoardState().getActualPlayer().getCards().size(); j++) {
                    if (!calcNextNode(i, j)) { continue; }
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
        try {
            while (!WorkingThread.next_calculations.isEmpty()) { workingThreadAction(); }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
