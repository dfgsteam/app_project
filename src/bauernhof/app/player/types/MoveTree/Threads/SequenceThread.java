package bauernhof.app.player.types.MoveTree.Threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.preset.PlayerType;

public class SequenceThread extends AbstractThread {

    private static Queue<MoveNode> next_calculations;
    private static ArrayList<Integer> differences;


    /**
     * Parametrized constructor as a sign for the first Thread-Object of the class
     * @param board
     */
    public SequenceThread(GameBoardState board) {
        super(board);
        differences = new ArrayList<Integer>();
        next_calculations = new LinkedList<MoveNode>(SequenceThread.getTree().getRootNode().getNextNodes());
        start();
    }

    public SequenceThread() {
        super();
        start();
    }



    private final int worst_difference(MoveNode node) throws Exception {
        int diff = node.getActualBoardState().getActualPlayer() == node.getActualBoardState().getPlayers()[0] ? node.getActualBoardState().getActualPlayer().getScore() - node.getActualBoardState().getPlayers()[1].getScore() : node.getActualBoardState().getActualPlayer().getScore() - node.getActualBoardState().getPlayers()[0].getScore();
        for (AbstractGamePlayer player : node.getActualBoardState().getPlayers()) {
            if (player == node.getActualBoardState().getActualPlayer()) { continue; }
            if (node.getActualBoardState().getActualPlayer().getScore() - player.getScore() < diff) { diff = node.getActualBoardState().getActualPlayer().getScore() - player.getScore(); }
        }
        return diff;
    }
    

    @Override
    public MoveNode getBestOfActual() throws Exception {
        MoveNode best = this.getThreadNode().getNextNodes().get(0);
        int best_diff = worst_difference(best);
        for (MoveNode node : this.getThreadNode().getNextNodes()) {
            if (worst_difference(node) > best_diff) {
                best = node;
                best_diff = worst_difference(node);
            }
        }
        return best;
    }

    private final int sequenceDifference() throws Exception {
        while (goDeeper()) {
            this.setThreadNode(getBestOfActual());
        }
       return this.worst_difference(getThreadNode().getPrevNode());
    }

    @Override
    public boolean goDeeper() {
        if (this.getThreadNode().getDepth() < this.getMaxDepth() && !this.getThreadNode().getNextNodes().isEmpty()) { return true; }
        return false;
    }

    @Override
    public void sequenceThreadAction() {
        this.setThreadNode(next_calculations.remove());
        try {
            differences.add(sequenceDifference());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static MoveNode bestNode() {
        int index = differences.indexOf(Collections.max(differences));
        return SequenceThread.getTree().getRootNode().getNextNodes().get(index);
    }

    //--------------------
    @Override
    public void run() {
        while(!next_calculations.isEmpty()) {
            synchronized (next_calculations) {
                if (next_calculations.isEmpty()) { continue; }
                sequenceThreadAction();
            }
        }

    }

    //Not usable methods
    @Override
    public boolean calcNextNode(int cardNumTake, int cardNumPut) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcNextNode'");
    }

    @Override
    public boolean workingThreadAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'workingThreadAction'");
    }
    
}
