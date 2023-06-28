package bauernhof.app.player.types.MoveTree;


/**
 * This interface was created by
 * @author Viktor Tevosyan
 * This interface helps to implement the future MoveTree
 */
public interface MoveTreeHandler {
    /**
     * Getter for the actual_node
     * @return MoveNode
     */
    public MoveNode getActualNode();
    /**
     * Getter for root of the actual tree
     * @return MoveNode
     */
    public MoveNode getRootNode();
    /**
     * Add a new Move to the Tree (depth) and change the actual_node to that Node
     * @param MoveNode movez_node
     * @return true if added
     */
    public boolean addDepthNode(MoveNode move_node);

    /**
     * Sets actual_node to parent_node
     * @return true, if parent exists
     */
    public boolean goToParent();
    

}
