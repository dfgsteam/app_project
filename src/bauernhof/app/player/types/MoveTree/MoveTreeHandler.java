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
    public MoveNode getActualLeftNode();

    /**
     * Getter for the actual_right_node
     * @return MoveNode;
     */
    public MoveNode getActualRightNode();

    /**
     * Getter for root of the actual tree
     * @return MoveNode
     */
    public MoveNode getRootNode();
    /**
     * Add a new Move to the Tree (depth) and change the actual right node to that Node
     * @param MoveNode moveNode
     */
    public void addRightDepthNode(MoveNode moveNode);

    /**
     * Add a new MoveNode to the tree and change the actual leftnode to the added one
     * @param MoveNode moveNode
     */
    public void addLeftDepthNode(MoveNode moveNode);

    /**
     * Sets actual_left_node to the parent node, if a parent_node exists, returns true if successfull
     * @return boolean
     */
    public boolean LeftGoToParent();

    /**
     * Sets actual_right node to the parent node, if a parent node exists, returns true if successfull
     * @return true
     */
    public boolean RightGoToParent();
}
