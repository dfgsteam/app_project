package bauernhof.app.player.types.MoveTree;


/**
 * This interface was created by
 * @author Viktor Tevosyan
 * This interface helps to implement the future MoveTree
 */
public interface MoveTreeHandler {

    /**
     * Setter for the actual node
     * @param MoveNode
     */
    public void setActualNode(MoveNode move_node);
    
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
     * Add a new Move to the Tree (depth) and change the actual right node to that Node
     * @param MoveNode moveNode
     */
    public void addNode(MoveNode moveNode);

    /**
     * Sets the actual_node to the parent_node;
     * @return true, if set successfull 
     */
    public boolean goToParent();
}
