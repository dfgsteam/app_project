package bauernhof.app.player.types.MoveTree;

import java.util.ArrayList;

import bauernhof.app.system.GameBoard;
import bauernhof.preset.Move;

/**
 * This interface was created by
 * @author Viktor Tevosyan
 * @date 30.06.2023
 * This interface gives a structure for the future Node Class, which will store a Move and some Game Information
 */
public interface MoveNodeHandler<T> {
    /**
     * Getter for the current Move of the current Node
     * @return Current Move of the current Node
     */
    public Move getMove();

    /**
     * Setter for the acual move of the actual node
     * @param Move
     */
    public void setMove(Move move);

    /**
     * Getter for the actual depth of the actual Node
     * @return Calculational Depth of the node in the tree
     */
    public int getDepth();

    /**
     * Setter for the value of calculational depth
     * @param Depth
     */
    public void setDepth(int depth);

    /**
     * A setter for the ChildNodes 
     * @param NextNodes (ArrayList)
     */
    public void setNextNodes(ArrayList<T> next_moves);

    /**
     * Getter for the ChildNodes
     * @return NextNodes (ArrayList)
     */
    public ArrayList<T> getNextNodes();

    /**
     * Getter for the Previous Node of the currentNode
     * @return Previous Node
     */
    public T getPrevNode();

    /**
     * Setter for the prev_node
     * @param PrevNode
     */
    public void setPrevNode(T prev_node);

    /**
     * Set a new moveNode to the ArrayList of Next_Moves (AND SET THE PREV_NODE OF ADDED MOVE TO THE ACTUAL_NODE)
     * @param ChildMoveNode
     */
    public void addNextMoveNode(T nextMoveNode);

    /**
     * Get the GameBoard, how it looks like, when the Move of the MoveNode has been done
     * @return GameBoard after MoveExecution
     */
    public GameBoard getActualBoard();

    /**
     * Set the GameBoard for the Actual MoveNode
     * @param GameBoard
     */
    public void setActualBoardState(GameBoard state);
}
