package bauernhof.app.player.types.MoveTree.Threads;

import java.util.ArrayList;
import java.util.Stack;

import bauernhof.app.launcher.GameBoardState;
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
    private static MoveTree move_tree = null;
    private MoveNode actual_node;

    public AbstractThread() {
        this.setThreadNode(null);
    }

    public static MoveTree getTree() {
        return move_tree;
    }

    public synchronized void pause(int sec) throws InterruptedException {
        if (sec == 0) { wait(); }
        else wait(sec);
    }

    public synchronized void go() {
        notify();
    }

    public static void setTree(MoveTree tree) {
        move_tree = tree;
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
