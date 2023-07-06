package bauernhof.app.player.types.MoveTree;

import java.util.HashSet;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.Move;

/**
 * This interface was created by
 * @author Viktor Tevosyan
 * This interface helps to implement a node class for nodes, which will help to make a MoveTree for the adanced ai
 */
public interface MoveNodeHandler<T> {
    /**
     * Getter for the actual Move of the actual node
     * @return Move
     */
    public Move getMove();

    /**
     * Setter for the acual move of the actual node
     * @param move
     */
    public void setMove(Move move);

    /**
     * Getter for the actual depth of node
     */
    public int getDepth();

    /**
     * Setter for the value of the evil-bar
     * @param int
     */
    public void setDepth(int depth);

    /**
     * A setter for the next 
     * @param next_moves (ashset of nodes)
     */
    public void setNextNodes(HashSet<T> next_moves);

    /**
     * Getter for the Hashset of the next moves
     * @return hashset of next moves
     */
    public HashSet<T> getNextNodes();

    /**
     * Getter for the Previous Node of the actual
     * @return previous_node
     */
    public T getPrevNode();

    /**
     * Setter for the prev_node
     * @param prev_node
     */
    public void setPrevNode(T prev_node);

    /**
     * Set a new move to the hashset of next_moves
     * @param nextMoveNode
     */
    public void addNextMoveNode(T nextMoveNode);

    /**
     * Get the board_state where a move has to be done on
     * @return GameBoardState
     */
    public GameBoardState getActualBoardState();

    /**
     * Set the actual_boardState
     * @param GameBoardState state
     */
    public void setActualBoardState(GameBoardState state);

    /**
     * Getter for the Actual Player
     * @return AbstractGamePlayer player
     */
    public AbstractGamePlayer getActualPlayer();

    /**
     * Setter for the actual Player
     * @param actualPlayer
     */
    public void setActualPlayer(AbstractGamePlayer player);

    /**
     * Getter for the Deposit_size
     * @return int
     */
    public int getDepositSize();

    /**
     * Getter for the size of ownhand
     * @return int
     */
    public int getOwnSize();

    /**
     * Sets size of the deposited cards of actual_board
     */
    public void setDepositSize();

    /**
     * Sets size of own hand (actual_player)
     */
    public void setOwnCardSize();


}
