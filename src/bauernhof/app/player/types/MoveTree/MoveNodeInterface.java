package bauernhof.app.player.types.MoveTree;

import java.util.HashSet;

import bauernhof.preset.Move;

/**
 * This interface was created by
 * @author Viktor Tevosyan
 * This interface helps to implement a node class for nodes, which will help to make a MoveTree for the adanced ai
 */
public interface MoveNodeInterface<T> {
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
     * Getter for the value evil-bvar
     * -> this value is an identificator, which tells how a move sequence affects the advantage of the Advanced AI
     * @return int
     */
    public int getEvil();

    /**
     * Setter for the value of the evil-bar
     * @param int
     */
    public void setEvil(int evil_value);

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

}
