package bauernhof.app.player.types.MoveTree.Threads;

import java.util.LinkedList;
import java.util.Queue;

import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;

public class SequenceThread extends AbstractThread{

    private static Queue<MoveNode> next_calculations = new LinkedList<MoveNode>();

    public SequenceThread(MoveTree move_tree, boolean first) {
        this.setTree(move_tree);
        if (first) this.setThreadNode(move_tree.getRootNode());
        else this.setThreadNode(null);
    }

    

    @Override
    public MoveNode getBestOfEnemy() {
        
    }

    @Override
    synchronized public boolean sequenceThreadAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sequenceThreadAction'");
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
