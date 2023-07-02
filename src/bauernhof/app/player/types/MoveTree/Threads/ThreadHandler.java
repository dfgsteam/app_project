package bauernhof.app.player.types.MoveTree.Threads;

import bauernhof.app.player.types.MoveTree.MoveTree;

/**
 * This interface was written by
 * @author Viktor Tevosyan
 * @date 30.06.2023
 * This interface is used for implementation of Threads, which will calculate the Moves in my MoveTree
 */
public interface ThreadHandler {
    
    /**
     * This method calculates a child MoveNode to given actual_node and given tree
     */
    public void calcNextNode(int cardNumTake, int cardNumPut);

    /**
     * Setter for the actual_tree for the thread
     * @param tree
     */
    public void setTree(MoveTree tree);

    /**
     * Getter for the actual_researched tree
     * @return MoveTree
     */
    public MoveTree getTree();
}
