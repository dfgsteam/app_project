package bauernhof.app.player.types.MoveTree.Threads;

import bauernhof.app.player.types.MoveTree.MoveNode;
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
    public boolean calcNextNode(int cardNumTake, int cardNumPut) throws Exception;

    /**
     * The action, the workingThread must do (synchronized)
     * @return false, if fail
     */
    public boolean workingThreadAction() throws Exception;

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

    /**
     * Get the actual-best (score based)
     * @throws Exception
     */
    public MoveNode getBestOfActual() throws Exception;

    /**
     * The action, the sequenceThread has to do (synchronized)
     */
    public void sequenceThreadAction();

    /**
     * Getter for the MAX_Depth of the tree
     * @return int
     */
    public int getMaxDepth();

    /**
     * Go deeper in the tree
     */
    public boolean goDeeper();


}
