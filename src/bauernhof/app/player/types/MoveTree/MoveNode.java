package bauernhof.app.player.types.MoveTree;

import java.util.ArrayList;

import bauernhof.app.system.GameBoard;
import bauernhof.preset.Move;

/**
 * This class was written by
 * @author Viktor Tevosyan
 * @date 29.06.2023
 * This class represents a Node Class for storing information about Moves and the Game, which is affected by the Execution of the moves.
 * This Node Class is necessary for the realisation of MoveTree, which is used by the Advanced_AI to calculate Moves while playing the game.
 * The Nodes contain information about the Move, about the GameBoard, but also they have references to the parent and to the Children, which allows the building of a tree.
 */
public class MoveNode implements MoveNodeHandler<MoveNode> {

    /**
     * Reference to the Parent Node
     * - mainly used to determine, who is the Root
     * - also used for travelling through the tree
     */
    private MoveNode prev_node;
    /**
     * Move, which is already executed on the Actual GameBoard of the Node
     */
    private Move move;
    /**
     * GameBoard, where the Move was already executed
     */
    private GameBoard actual_state;
    /**
     * References to the ChildNodes (next Dpeth)
     */
    private ArrayList<MoveNode> next_nodes;
    /**
     *  Calculational Depth
     */
    private int depth;

    /**
     * Empty Constructor
     * Signalises athe Creation of the RootNode
     * - Empty Node with no Move
     * - No Parent
     * - Depth = 0
     */
    public MoveNode() {
        this.setMove(null);
        this.setNextNodes(new ArrayList<MoveNode>());
        this.setDepth(0);
        this.setPrevNode(null);
    }

    /**
     * Parametrized constructor
     * Creates an Empty Node, but with a Move
     * @param move
     */
    public MoveNode(Move move) {
        this();
        this.setMove(move);
    }

    /**
     * Parametrized constructor
     * Creates a Node with a Move and with contact to a parent
     * @param Move
     * @param ParentNode
     */
    public MoveNode(Move move, MoveNode prev_node) {
        this(move);
        prev_node.addNextMoveNode(this);
    }

    /**
     * Parametrized constructor
     * Creates a empty Node with a GameBoard
     * @param state
     */
    public MoveNode(GameBoard state) {
        this();
        this.setActualBoardState(state);
    }

    /**
     * Parametrized constructor
     * Creates a Node, containing a Move and the GameBoard, where the Move was executed on
     * @param Move
     * @param GameBoard
     */
    public MoveNode(Move move, GameBoard state) {
        this(state);
        this.setMove(move);
    }

    /**
     * Parametrized constructor
     * Creates a Node, containing a Move, the Parent Node and the GameBoard, where the Move was executed on
     * @param Move
     * @param ParentNode
     * @param GameBoard
     */
    public MoveNode(Move move, MoveNode prev_Node, GameBoard state) {
        this(move, state);
        prev_Node.addNextMoveNode(this);
    }
    
    @Override
    public int getDepth() {
        return this.depth;
    }

    @Override
    public Move getMove() {
        return this.move;
    }

    @Override
    public void setMove(Move move) {
        this.move = move;
    }

    @Override
    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public void setNextNodes(ArrayList<MoveNode> next_moves) {
        this.next_nodes = next_moves;
    }

    @Override
    public ArrayList<MoveNode> getNextNodes() {
        return this.next_nodes;
    }

    @Override
    public MoveNode getPrevNode() {
        return this.prev_node;
    }

    @Override
    public void setPrevNode(MoveNode prev_node) {
        this.prev_node = prev_node;
    }

    @Override
    public void addNextMoveNode(MoveNode next_MoveNode) {
        this.getNextNodes().add(next_MoveNode);
        next_MoveNode.setPrevNode(this);        //And Sets The Prev_NOde of the added node to the actual
    }

    @Override
    public GameBoard getActualBoard() {
        return this.actual_state;
    }

    @Override
    public void setActualBoardState(GameBoard state) {
        this.actual_state = state;
    }


}
