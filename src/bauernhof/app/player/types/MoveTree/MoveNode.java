package bauernhof.app.player.types.MoveTree;

import java.util.HashSet;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.Move;

public class MoveNode implements MoveNodeHandler<MoveNode> {

    private MoveNode prev_node;
    private AbstractGamePlayer actual_player;
    private Move move;
    private GameBoardState actual_state;
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
        prev_node.addNextMoveNode(this);
    }

    public MoveNode(GameBoardState state) {
        this();
        this.actual_state = state;
    }

    public MoveNode(AbstractGamePlayer player) {
        this();
        this.actual_player = player;
    }

    public MoveNode(GameBoardState state, AbstractGamePlayer player) {
        this(state);
        this.actual_player = player;
    }

    public MoveNode(Move move, GameBoardState state, AbstractGamePlayer player) {
        this(state, player);
        this.move = move;
    }

    public MoveNode(Move move, MoveNode prev_Node, GameBoardState state, AbstractGamePlayer player) {
        this(move, state, player);
        this.prev_node = prev_Node;
        prev_Node.addNextMoveNode(this);
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
    public void setEvilValue() {
        //TO-DO
    }

    @Override
    public AbstractGamePlayer getActualPlayer() {
        return this.actual_player;
    }

    @Override
    public void setActualPlayer(AbstractGamePlayer player) {
        this.actual_player = player;
    }

    @Override
    public String toString() {
        String move_message = "Move: " + this.getMove();
        String parent_message = "Parent: " + this.getPrevNode();
        String player_message = "Actual Player to move: " + this.getActualPlayer();
        String board = "Actual Board: " + this.getActualBoardState();
        String list = "List of next possible moves: " + this.getNextNodes();
        String value = "Actual points for the done move: " + this.getEvil();

        String text = move_message + "\n" + parent_message + "\n" + player_message + "\n" + board + "\n" + list + "\n" + value + "\n";
        return text;
    }

}
