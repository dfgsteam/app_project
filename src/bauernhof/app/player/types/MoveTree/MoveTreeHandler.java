package bauernhof.app.player.types.MoveTree;


/**
 * This interface was created by
 * @author Viktor Tevosyan
 * @date 30.06.2023
 * This interface helps to implement a tree data structure, which will contain Nodes of Moves and certain information about the GameBoard and Players
 */
public interface MoveTreeHandler {
    
    /**
     * Setter for the Root node of the tree
     * @param RootNode
     */
    public void setRootNode(MoveNode root_node);

    /**
     * Getter for RootNode of the actual tree
     * @return RootNode
     */
    public MoveNode getRootNode();

}
