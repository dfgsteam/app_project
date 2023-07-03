package bauernhof.app.player.types.MoveTree.Threads;

import bauernhof.app.player.types.MoveTree.MoveNode;
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
     * @return false, if not possible
     */
    public boolean calcNextNode(int cardNumTake, int cardNumPut);

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

    /**
     * The action thread must do (synchronized)
     * @return false, if fail
     */
    public boolean threadAction();

    /**
     * Setter for the actual_node of the node
     * @param MoveNode move_node
     */
    public void setThreadNode(MoveNode move_node);

    /**
     * Getter for the actual_node of the Thread
     * @return MoveNode
     */
    public MoveNode getThreadNode();

}
