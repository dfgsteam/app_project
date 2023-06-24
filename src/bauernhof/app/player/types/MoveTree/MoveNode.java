package bauernhof.app.player.types.MoveTree;

import java.util.HashSet;

import bauernhof.preset.Move;

public class MoveNode implements MoveNodeInterface<MoveNode> {

    private MoveNode prev_node;
    private Move move;
    private HashSet<MoveNode> next_nodes;
    private int evil_value;

    public MoveNode() {
        move = null;
        next_nodes = new HashSet<MoveNode>();
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
}
