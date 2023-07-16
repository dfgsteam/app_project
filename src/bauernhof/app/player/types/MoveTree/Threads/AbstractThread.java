package bauernhof.app.player.types.MoveTree.Threads;

import bauernhof.app.system.GameBoard;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;


/**
 * This abstract class was written by
 * @author Viktor Tevosyan
 * @date 06.07.2023
 * This is a basic strcuture of any thread, The Threads themselves will extend from this class.
 * This abstract class implements (not fully) the ThreadHanlder Interface
 */
public abstract class AbstractThread implements ThreadHandler {
    private static final int MAX_DEPTH = 4;
    private static MoveTree move_tree;
    private MoveNode actual_node;

    public AbstractThread(GameBoard actual_state) {

        move_tree = new MoveTree(new MoveNode(actual_state));
        this.setThreadNode(AbstractThread.getTree().getRootNode());
    }

    public AbstractThread() {
        this.setThreadNode(null);
    }

    public static MoveTree getTree() {
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
