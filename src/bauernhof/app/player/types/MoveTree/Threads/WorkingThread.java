package bauernhof.app.player.types.MoveTree.Threads;

import java.util.LinkedList;
import java.util.Queue;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.ui.game.GameBoard;
import bauernhof.preset.Move;
import bauernhof.preset.card.Card;

public class WorkingThread extends AbstractThread {

    private static Queue<MoveNode> next_calculations = new LinkedList<MoveNode>();
    int max;

    

    /**
     * Constructor,that signalises, that the first Thread-Object is going to be created, so there is no MoveTree yet
     * @param actual_state
     * @throws Exception
     */
    public WorkingThread(GameBoardState actual_state) throws Exception {
        super(actual_state);
        max = -100;
        this.setThreadNode(getTree().getRootNode());
        workingThreadAction();
         while (!WorkingThread.next_calculations.isEmpty()) { 
            workingThreadAction();
        }

    }

    /**
     * Constructor, that signalises, that there are some next_calculations and that the tree is not empty
     * @param tree
     */
    public WorkingThread() {
        this.setThreadNode(null);
         while (!WorkingThread.next_calculations.isEmpty()) { try {
            workingThreadAction();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } }
    }


    @Override
    public boolean calcNextNode(int cardNumTake, int cardNumPut) throws Exception {
        if (checkMove(cardNumPut, cardNumTake) == null) { return false; }

        Move new_move = checkMove(cardNumPut, cardNumTake);
        GameBoardState new_state = this.getThreadNode().getActualBoardState().clone();

        MoveNode next_MoveNode = new MoveNode(new_move, this.getThreadNode(), new_state);
        next_MoveNode.setDepth(this.getThreadNode().getDepth()+1);
        this.setThreadNode(next_MoveNode);
        return true;
    }

    @Override
    public boolean workingThreadAction() throws Exception {
        // synchronized (next_calculations) {
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
        this.max = -100;
        this.setThreadNode(null);
        return true;
        // }
    }

    public Move checkMove(int cardNumPut, int cardNumTake) {
        if (this.getThreadNode().getDepth()+1 > this.getMaxDepth() || this.getThreadNode().getActualBoardState().getRound() > 30) { return null; }
        Card to_take, to_put;
        if (cardNumTake < 0) {
            if (this.getThreadNode().getActualBoardState().getDrawPileCards().isEmpty()) { return null; }
            to_take = this.getThreadNode().getActualBoardState().getDrawPileCards().lastElement();
        }

        else {
            if (this.getThreadNode().getActualBoardState().getDepositedCards().size() > this.getThreadNode().getActualBoardState().getConfiguration().getNumDepositionAreaSlots()) { return null; }
            to_take = this.getThreadNode().getActualBoardState().getDepositedCards().get(cardNumTake);
        }

        if (cardNumPut < 0) {
            to_put = to_take;
        }

        else {
            to_put = this.getThreadNode().getActualBoardState().getActualPlayer().getCards().get(cardNumPut);
        }

        
        Move new_move = new Move(to_take, to_put);
        try {
            if (!this.getThreadNode().getActualBoardState().clone().doMove(new_move)) { return null;}
        } catch (Exception e) {
            System.err.println("Can't do a Move");
        }
        if (calculateWinPoints(new_move, this.getThreadNode().getActualBoardState()) < this.max) { return null; }
        this.max = calculateWinPoints(new_move, this.getThreadNode().getActualBoardState());
        return new_move;
    }

    public int calculateWinPoints(Move move, GameBoardState gameboard) {
        int points = 0;
        int actual_id = gameboard.getActualPlayer().getPlayerID();
        
        GameBoardState move_done = gameboard.clone();
        try {
            move_done.doMove(move);
        } catch (Exception e) {
            System.err.println("Can't do a move");
        }
        try {
           points = move_done.getPlayers()[actual_id].getScore();
        } catch (Exception e) {
            System.err.println("Can't get Points of the palyer");
        }
        return points;
    }


    //------------
    // @Override
    // public void run() {
    //     try {
    //         while (!WorkingThread.next_calculations.isEmpty()) { workingThreadAction(); }
    //     } catch (Exception e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    //     Thread.interrupted();
    // }


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
