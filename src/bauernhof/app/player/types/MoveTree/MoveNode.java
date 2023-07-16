package bauernhof.app.player.types.MoveTree;

import java.util.ArrayList;

import bauernhof.app.system.GameSystem;
import bauernhof.preset.Move;

public class MoveNode implements MoveNodeHandler<MoveNode> {

    private MoveNode prev_node;
    private Move move;
    private GameSystem actual_state;
    private ArrayList<MoveNode> next_nodes;
    private int depth;

    /**
     * Empty Constructor
     * Signalises an Empty Node -> the Root Node (empy Move)
     */
    public MoveNode() {
        this.setMove(null);
        this.setNextNodes(new ArrayList<MoveNode>());
        this.setDepth(0);
    }

    public MoveNode(Move move) {
        this();
        this.setMove(move);
    }

    public MoveNode(Move move, MoveNode prev_node) {
        this(move);
        prev_node.addNextMoveNode(this);
    }

    public MoveNode(GameSystem state) {
        this();
        this.setActualBoardState(state);
    }


    public MoveNode(Move move, GameSystem state) {
        this(state);
        this.setMove(move);
    }

    public MoveNode(Move move, MoveNode prev_Node, GameSystem state) {
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
    public GameSystem getActualBoardState() {
        return this.actual_state;
    }

    @Override
    public void setActualBoardState(GameSystem state) {
        this.actual_state = state;
    }


}
