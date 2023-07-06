package bauernhof.app.player.types.MoveTree.Threads;

import java.util.LinkedList;
import java.util.Queue;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.preset.PlayerType;

public class SequenceThread extends AbstractThread{
    private static Queue<MoveNode> next_calculations = new LinkedList<MoveNode>();

    public SequenceThread(MoveTree move_tree, boolean first) {
        this.setTree(move_tree);
        if (first) this.setThreadNode(move_tree.getRootNode());
        else this.setThreadNode(null);
    }

    private final int biggest_difference_of_points(MoveNode node) throws Exception {
        int diff = node.getActualBoardState().getActualPlayer() == node.getActualBoardState().getPlayers()[0] ? node.getActualBoardState().getActualPlayer().getScore() - node.getActualBoardState().getPlayers()[1].getScore() : node.getActualBoardState().getActualPlayer().getScore() - node.getActualBoardState().getPlayers()[0].getScore();
        for (AbstractGamePlayer compare : node.getActualBoardState().getPlayers()) {
            if (compare == node.getActualBoardState().getActualPlayer()) { continue; }
            if (node.getActualBoardState().getActualPlayer().getScore() - compare.getScore() < diff) {
                diff = node.getActualBoardState().getActualPlayer().getScore() - compare.getScore();
            }
        }
        return diff;
    }
    

    @Override
    public MoveNode getBestOfActual() {
        MoveNode best = this.getThreadNode().getNextNodes().get(0);
        int diff = 0;
        try {
            diff = biggest_difference_of_points(best);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (MoveNode node : this.getThreadNode().getNextNodes()) {
            try {
                if (biggest_difference_of_points(node) > diff) {
                    diff = biggest_difference_of_points(node);
                    best = node;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return best;
    }

    @Override
    synchronized public MoveNode sequenceThreadAction() {
        MoveNode bestNode = this.getTree().getRootNode().getNextNodes().get(0);
        this.setThreadNode(bestNode);
        while (goDeeper());
        int diff;
        try {
            diff = biggest_difference_of_points(this.getThreadNode());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (MoveNode ai_move : this.getTree().getRootNode().getNextNodes()) {
            this.setThreadNode(ai_move);
            while (goDeeper())
                try {
                    if (diff < biggest_difference_of_points(this.getThreadNode())) {
                        diff = biggest_difference_of_points(this.getThreadNode());
                        bestNode = ai_move;
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return bestNode;
    }

    @Override
    public boolean goDeeper() {
        if (this.getThreadNode().getDepth() >= this.getMaxDepth()) { return false; }
        this.setThreadNode(getBestOfActual());
        return true;
    }




    //-----
    @Override
    public void run() {
        while (sequenceThreadAction());
        interrupt();
    }


    //Not used Methods
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
