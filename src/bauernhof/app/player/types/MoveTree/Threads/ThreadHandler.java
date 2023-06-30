package bauernhof.app.player.types.MoveTree.Threads;


/**
 * This interface was written by
 * @author Viktor Tevosyan
 * @date 30.06.2023
 * This interface is used for implementation of Threads, which will calculate the Moves in my MoveTree
 */
public interface ThreadHandler {
    
    /**
     * Getter for the number of card (own hand), which will be put
     * @return putNumber
     */
    public int getPutNumber();

    /**
     * Getter for the number of the card, which has to be taken
     * @return takeNUmber
     */
    public int getTakeNumber();
}
