package bauernhof.app.player.types.MoveTree.Threads;

import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;


/**
 * This abstract class was written by
 * @author Viktor Tevosyan
 * @date 06.07.2023
 * This is a basic strcuture of any thread, The Threads themselves will extend from this class.
 * This abstract class implements (not fully) the ThreadHanlder Interface
 */
public abstract class AbstractThread extends Thread implements ThreadHandler {
    private static final int MAX_DEPTH = 3;
    private MoveTree move_tree;
    private MoveNode actual_node;

    @Override
    public void setTree(MoveTree tree) {
        this.move_tree = tree;
    }

    @Override
    public MoveTree getTree() {
        return move_tree;
    }

    @Override
    public void setThreadNode(MoveNode move_node) {
        this.actual_node = move_node;
    }

    @Override
    public MoveNode getThreadNode() {
        return this.actual_node;
    }

    @Override
    public int getMaxDepth() {
        return AbstractThread.MAX_DEPTH;
    }


}
