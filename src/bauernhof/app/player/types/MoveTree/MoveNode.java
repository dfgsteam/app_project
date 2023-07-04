package bauernhof.app.player.types.MoveTree;

import java.util.ArrayList;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.preset.Move;

public class MoveNode implements MoveNodeHandler<MoveNode> {

    private MoveNode prev_node;
    private Move move;
    private GameBoardState actual_state;
    private ArrayList<MoveNode> next_nodes;
    private int depth;

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

    public MoveNode(GameBoardState state) {
        this();
        this.setActualBoardState(state);
    }


    public MoveNode(Move move, GameBoardState state) {
        this(state);
        this.setMove(move);
    }

    public MoveNode(Move move, MoveNode prev_Node, GameBoardState state) {
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
        this.next_nodes.add(next_MoveNode);
        next_MoveNode.setPrevNode(this);
    }

    @Override
    public GameBoardState getActualBoardState() {
        return this.actual_state;
    }

    @Override
    public void setActualBoardState(GameBoardState state) {
        this.actual_state = state;
    }

    @Override
    public String toString() {
        String move_message = "Move: " + this.getMove();
        String parent_message = "Parent: " + this.getPrevNode();
        String board = "Actual Board: " + this.getActualBoardState();
        String list = "List of next possible moves: " + this.getNextNodes();

        String text = move_message + "\n" + parent_message + "\n" + board + "\n" + list + "\n" + "\n";
        return text;
    }


}
