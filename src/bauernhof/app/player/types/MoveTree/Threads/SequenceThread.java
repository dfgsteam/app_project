package bauernhof.app.player.types.MoveTree.Threads;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.preset.PlayerType;

public class SequenceThread extends AbstractThread{
    private static Queue<MoveNode> next_calculations;
    private static ArrayList<Integer> differences;

    public SequenceThread(MoveTree tree, boolean first) {
        if (first) {
            this.setThreadNode(tree.getRootNode());
            next_calculations = new LinkedList<MoveNode>(this.getThreadNode().getNextNodes());
            differences = new ArrayList<Integer>();
        }
    }

    private final int biggest_difference(MoveNode node) throws Exception {
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
        int diff = biggest_difference(best);
        for (MoveNode node : this.getThreadNode().getNextNodes()) {
            if (biggest_difference(node) > diff) {
                best = node;
                diff = biggest_difference(best);
            }
        }
        return best;
    }

    @Override
    synchronized public void sequenceThreadAction() {
        
    }

    @Override
    public boolean goDeeper() {
        if (this.getThreadNode().getDepth() < this.getMaxDepth()) { return true; }
        return false;
    }

    @Override
    public void run() {

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
