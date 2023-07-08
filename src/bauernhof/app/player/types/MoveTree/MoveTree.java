package bauernhof.app.player.types.MoveTree;

public class MoveTree implements MoveTreeHandler {
    
    private MoveNode root;
    private MoveNode actual_node;

    public MoveTree(MoveNode start_node) {
        this.setRootNode(start_node);
    }

    public MoveTree() {
        this(new MoveNode());
    }

    @Override
    public void setActualNode(MoveNode move_node) {
        this.actual_node = move_node;
    }
    @Override
    public MoveNode getActualNode() {
        return this.actual_node;
    }
    @Override
    public MoveNode getRootNode() {
        return this.root;
    }
    @Override
    public void addNode(MoveNode moveNode) {
        this.actual_node.addNextMoveNode(moveNode);
        this.setActualNode(moveNode);
    }
    @Override
    public boolean goToParent() {
        if (this.actual_node.getPrevNode() == null) { return false; }
        this.setActualNode(this.getActualNode().getPrevNode());
        return true;
    }

    @Override
    public void setRootNode(MoveNode root_node) {
        this.root = root_node;
    }
    
    
}
