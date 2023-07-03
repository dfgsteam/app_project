package bauernhof.app.player.types.MoveTree;

import java.util.ArrayList;
import java.util.HashSet;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.Move;
import bauernhof.preset.Player;

public class MoveNode implements MoveNodeHandler<MoveNode> {

    private MoveNode prev_node;
    private Player actual_player;
    private int sizeOfDeposited;
    private int sizeOfOwnHand;
    private Move move;
    private GameBoardState actual_state;
    private ArrayList<MoveNode> next_nodes;
    private int depth;

    public MoveNode() {
        move = null;
        next_nodes = new ArrayList<MoveNode>();
        depth = 0;
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
        this.setActualPlayer(state.getActualPlayer());
    }


    public MoveNode(GameBoardState state, Player player) {
        this(state);
        this.actual_player = player;
    }

    public MoveNode(Move move, GameBoardState state, Player player) {
        this(state, player);
        this.move = move;
    }

    public MoveNode(Move move, MoveNode prev_Node, GameBoardState state, Player player) {
        this(move, state, player);
        this.prev_node = prev_Node;
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
        this.actual_player = state.getActualPlayer();
    }

    @Override
    public Player getActualPlayer() {
        return this.actual_player;
    }

    @Override
    public void setActualPlayer(Player player) {
        this.actual_player = player;
    }

    @Override
    public String toString() {
        String move_message = "Move: " + this.getMove();
        String parent_message = "Parent: " + this.getPrevNode();
        String player_message = "Actual Player to move: " + this.getActualPlayer();
        String board = "Actual Board: " + this.getActualBoardState();
        String list = "List of next possible moves: " + this.getNextNodes();

        String text = move_message + "\n" + parent_message + "\n" + player_message + "\n" + board + "\n" + list + "\n" + "\n";
        return text;
    }

    @Override
    public boolean equals(Object node) {
        if (!(node instanceof MoveNode)) { return false; }
        MoveNode compare = (MoveNode)node;

        return this.getMove().getTaken() == compare.getMove().getTaken() && this.getMove().getDeposited() == compare.getMove().getDeposited();
    }

    @Override
    public int getDepositSize() {
        return this.sizeOfDeposited;
    }

    @Override
    public int getOwnSize() {
        return this.sizeOfOwnHand;
    }
    
    @Override
    public void setDepositSize() {
        this.sizeOfDeposited = this.getActualBoardState().getDepositedCards().size();
    }

    @Override
    public void setOwnCardSize() {
        this.sizeOfOwnHand = this.getActualPlayer().getCards().size();
    }


}
