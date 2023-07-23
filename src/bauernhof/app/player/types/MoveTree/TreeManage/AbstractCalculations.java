package bauernhof.app.player.types.MoveTree.TreeManage;

import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.app.system.GameBoard;


/**
 * This abstract class was written by
 * @author Viktor Tevosyan
 * @date 06.07.2023
 * This abstract class delivers a basic construct of the calculational classes. These have a static variable, which describes how deep the MoveTRee will go, the MoveTree itself and the actualNode (where the calculational object aactually is)
 */
public abstract class AbstractCalculations implements CalculationsHandler {
    /**
     * The Depth of the Tree
     */
    private static final int MAX_DEPTH = 3;

    /**
     * The MoveTree
     */
    private MoveTree move_tree;

    /**
     * Current Node
     */
    private MoveNode actual_node;

    //--------------------------------------------------

    /**
     * Parametrized constructor, which creates a Tree with empty RootNode with actual GameBoard
     * @param GameBoard
     */
    public AbstractCalculations(GameBoard actual_state) {
        move_tree = new MoveTree(new MoveNode(actual_state));
        this.setCurrentNode(getTree().getRootNode());
    }

    /**
     * Empty Constructor (Null-Setter)
     */
    public AbstractCalculations() {
        this.setTree(null);
        this.setCurrentNode(null);
    }

    @Override
    public MoveTree getTree() {
        return this.move_tree;
    }

    @Override
    public void setTree(MoveTree tree) {
        this.move_tree = tree;
    }

    @Override
    public void setCurrentNode(MoveNode move_node) {
        this.actual_node = move_node;
    }

    @Override
    public MoveNode getCurrentNode() {
        return this.actual_node;
    }

    @Override
    public int getMaxDepth() {
        return AbstractCalculations.MAX_DEPTH;
    }
}
