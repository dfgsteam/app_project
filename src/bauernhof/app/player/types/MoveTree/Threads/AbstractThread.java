package bauernhof.app.player.types.MoveTree.Threads;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.preset.Move;

/**
 * This abstract class was written by
 * @author Viktor Tevosyan
 * It contains all variables which are used by both threads
 */
public abstract class AbstractThread extends Thread {

    
    private int number_of_toput_card;
    private int number_of_totake_card;
    private AbstractGamePlayer player;
    private MoveNode actual_node;
    private MoveTree move_tree;

    /**
     * Updates 
     * @return
     */
    public abstract boolean updateForNextMove();
    /**
     * Getter for the number_of_the_own card, which is needed for the next Move
     * @return number_of_toput_card
     */
    public int getNumberOftoPutCard() {
        return this.number_of_toput_card;
    }
    /**
     * Getter for the needed number of deposit_card, needed for the calculation of the next move
     * @return number_of_totake_card
     */
    public int getNumberOftoTakeCard() {
        return this.number_of_totake_card;
    }

    /**
     * Setter for the number of the own card, which has to be put
     * @param number_of_toput_card
     */
    public void setNumberOfPutCard(int number_of_toput_card) {
        this.number_of_toput_card = number_of_toput_card;
    }

    /**
     * Setter for the number of the deposit_card, which has to be taken
     * @param number_of_totake_card
     */
    public void setNumberOftoTakeCard(int number_of_totake_card) {
        this.number_of_totake_card = number_of_totake_card;
    }

    /**
     * Setter for player
     * @param AbstractGamePlayer
     */
    public void setGamePlayer(AbstractGamePlayer player) {
        this.player = player;
    }

    /**
     * Setter for the actual Move_Node (where the THread actually is)
     * @param actual_node
     */
    public void setActualNode(MoveNode actual_node) {
        this.actual_node = actual_node;
    }

    /**
     * Setter for the moveTree of the Thread
     * @param move_tree
     */
    public void setMoveTree(MoveTree move_tree) {
        this.move_tree = move_tree;
    }
    /**
     * Getter for player
     * @return player
     */
    public AbstractGamePlayer getPlayer() {
        return this.player;
    }

    /**
     * Getter for the future imaginary_state
     * @return imaginary_state
     */
    public GameBoardState getImaginaryState() {
        return this.imaginary_state;
    }

    /**
     * Getter for actual node the Thread is in
     * @return MoveNode
     */
    public MoveNode getActualNode() {
        return this.actual_node;
    }

    /**
     * Getter for the Tree of thr Thread
     *  @return move_tree
     */
    public MoveTree getMoveTree() {
        return this.move_tree;
    }

    /**
     * Calculates a level deeper from actual MoveNode
     * @return 
     */
    public abstract Move calculateNextMove();
    

}