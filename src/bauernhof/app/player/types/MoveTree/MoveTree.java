package bauernhof.app.player.types.MoveTree;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;

public class MoveTree implements MoveTreeHandler {
    
    private MoveNode root;
    private MoveNode actual_left;
    private MoveNode actual_right;

    public MoveTree(GameBoardState state, AbstractGamePlayer player) {
        root = new MoveNode(state, player);
        actual_left = root;
        actual_right = root;
    }
    
    public MoveTree() {
        root = new MoveNode();
        actual_left = root;
        actual_right = root;
    }

    @Override
    public MoveNode getRootNode() {
        return this.root;
    }

    @Override
    public MoveNode getActualLeftNode() {
        return this.actual_left;
    }

    @Override
    public MoveNode getActualRightNode() {
        return this.actual_right;
    }

    @Override
    public boolean LeftGoToParent() {
        if (this.getActualLeftNode().getPrevNode() == null) {
            return false;
        }

        this.actual_left = this.getActualLeftNode().getPrevNode();
        return true;
    }

    @Override
    public boolean RightGoToParent() {
        if (this.getActualRightNode().getPrevNode() == null) {
            return false;
        }

        this.actual_right = this.getActualRightNode().getPrevNode();
        return true;
    }

    @Override
    public void addRightDepthNode(MoveNode moveNode) {
        this.getActualRightNode().addNextMoveNode(moveNode);
        this.actual_right = moveNode;
    }

    @Override
    public void addLeftDepthNode(MoveNode moveNode) {
        this.getActualLeftNode().addNextMoveNode(moveNode);
        this.actual_left = moveNode;
    }
    
    
}
