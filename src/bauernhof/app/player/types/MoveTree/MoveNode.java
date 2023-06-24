package bauernhof.app.player.types.MoveTree;

import java.util.HashSet;

import bauernhof.preset.Move;

public class MoveNode implements MoveNodeHandler<MoveNode> {

    private MoveNode prev_node;
    private Move move;
    private HashSet<MoveNode> next_nodes;
    private int evil_value;

    public MoveNode() {
        move = null;
        next_nodes = new HashSet<MoveNode>();
    }

    public MoveNode(Move move) {
        this();
        this.move = move;
    }

    public MoveNode(Move move, MoveNode prev_node) {
        this(move);
        this.prev_node = prev_node;
    }

    @Override
    public int getEvil() {
        return this.evil_value;
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
    public void setEvil(int evil_value) {
        this.evil_value = evil_value;
    }

    @Override
    public void setNextNodes(HashSet<MoveNode> next_moves) {
        this.next_nodes = next_moves;
    }

    @Override
    public HashSet<MoveNode> getNextNodes() {
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
    public void addNextMove(MoveNode next_move) {
        this.next_nodes.add(next_move);
    }
}
