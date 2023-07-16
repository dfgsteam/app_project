package bauernhof.app.player.types.MoveTree;

import java.util.ArrayList;

import bauernhof.app.system.GameBoard;
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
     * @param depth
     */
    public void setDepth(int depth);

    /**
     * A setter for the next 
     * @param next_moves (ashset of nodes)
     */
    public void setNextNodes(ArrayList<T> next_moves);

    /**
     * Getter for the Hashset of the next moves
     * @return hashset of next moves
     */
    public ArrayList<T> getNextNodes();

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
     * Set a new moveNode to the ArrayList of Next_Moves (AND SET THE PREV_NODE OF ADDED MOVE TO THE ACTUAL_NODE)
     * @param nextMoveNode
     */
    public void addNextMoveNode(T nextMoveNode);

    /**
     * Get the board_state where a move has to be done on
     * @return GameBoard
     */
    public GameBoard getActualBoardState();

    /**
     * Set the actual_boardState
     * @param state
     */
    public void setActualBoardState(GameBoard state);

}
