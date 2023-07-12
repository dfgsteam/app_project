package bauernhof.app.player.types.MoveTree;

public class MoveTree implements MoveTreeHandler {
    
    private MoveNode root;

    public MoveTree(MoveNode start_node) {
        this.setRootNode(start_node);
    }

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
