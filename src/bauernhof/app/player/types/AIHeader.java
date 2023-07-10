package bauernhof.app.player.types;

import bauernhof.preset.Move;
import bauernhof.preset.card.Card;

/**
 * This interface was created by
 * @author Viktor Tevosyan
 * It helps to implement every AI
 */
public interface AIHeader {
    /**
     * Returning a card, which an AI (evtl.) is going to take from Deposit
     * @return Card
     */
    public Card cardFromDeposit();

    /**
     * Returning a card, which an AI (evtl.) is going to take from the general cardStack
     * @return Card
     */
    public Card cardFromStack();
    /**
     * Returning a card, which an AI is going to remove from the own hand
     * @return Card
     */
    public Card removeFromOwn();
}
