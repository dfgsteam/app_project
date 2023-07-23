package bauernhof.app.player.types.MoveTree;


/**
 * This class was created by
 * @author Viktor Tevosyan
 * @date 30.06.2023
 * This class represents a Tree, which contains MoveNodes (check MoveNode class). This data Structure helps to calculate the "best" Move for the Advanced_AI.
 * It implements the MoveTreeHandler interface, which determines the structure of this class.
 */
public class MoveTree implements MoveTreeHandler {
    /**
     * The RootNode of the Tree
     */
    private MoveNode root;

    /**
     * Parametrized Constructor, which Sets the RootNode
     * @param start_node
     */
    public MoveTree(MoveNode start_node) {
        this.setRootNode(start_node);
    }

    /**
     * Empty Constructor
     * Creates a Empty MoveNode (see MoveNode Class)
     * Sets RootNode to this Node
     */
    public MoveTree() {
        this(new MoveNode());
    }

    @Override
    public MoveNode getRootNode() {
        return this.root;
    }

    @Override
    public void setRootNode(MoveNode root_node) {
        this.root = root_node;
    }
    
    
}
