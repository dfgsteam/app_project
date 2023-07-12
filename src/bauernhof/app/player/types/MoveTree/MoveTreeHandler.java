package bauernhof.app.player.types.MoveTree;


/**
 * This interface was created by
 * @author Viktor Tevosyan
 * This interface helps to implement the future MoveTree
 */
public interface MoveTreeHandler {
    
    /**
     * Setter for the Root node of the tree
     * @param root_node
     */
    public void setRootNode(MoveNode root_node);

    /**
     * Getter for root of the actual tree
     * @return MoveNode
     */
    public MoveNode getRootNode();

}
