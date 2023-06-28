package bauernhof.app.player.types.MoveTree;

public class MoveTree implements MoveTreeHandler {
    private MoveNode root;
    private MoveNode actual_node;

    public MoveTree() {
        root = new MoveNode();
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
    public void addDepthNode(MoveNode move_node) {
        this.actual_node.addNextMove(move_node);
    }

    @Override
    public boolean goToParent() {
        if (this.actual_node.getPrevNode() == null) return false;
        this.actual_node = this.actual_node.getPrevNode();
        return true;
    }

    @Override
    public MoveNode createNextMoveNode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createNextMoveNode'");
    }
    
    
}
