package bauernhof.app.player.types.MoveTree.Threads;

import bauernhof.app.player.types.MoveTree.MoveTree;

public abstract class AbstractThread extends Thread implements ThreadHandler {

    static private final int MAX_DEEP = 3;
    private MoveTree tree;
    

    public AbstractThread(MoveTree tree) {
        this.tree = tree;
    }

    @Override
    public void setTree(MoveTree tree) {
        this.tree = tree;
    }

    @Override
    public MoveTree getTree() {
        return this.tree;
    }
    
    @Override
    public int getMaxDepth() {
        return MAX_DEEP;
    }
}
