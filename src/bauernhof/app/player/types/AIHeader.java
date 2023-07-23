package bauernhof.app.player.types;

import bauernhof.preset.card.Card;

/**
 * This interface was created by
 * @author Viktor Tevosyan
 * @date 26.06.2023
 * This interface helps to implement the AIs. It represents a basic construct:
 * - a method to get a Card, which should be taken from deposit
 * - a method to get a Card, which should be taken from the drawpile stack
 * - a method to get a Card, which should be put from the own hand (incl. taken card)
 */
public interface AIHeader {
    /**
     * Returning a card, which an AI (evtl.) is going to take from the deposit-area
     * @return Card from DepositedPile
     */
    public Card cardFromDeposit();

    /**
     * Returning a card, which an AI (evtl.) is going to take from the general cardStack
     * @return Card from DrawPile Stack
     */
    public Card cardFromStack();
    /**
     * Returning a card, which an AI is going to remove from the own hand
     * @return Card from the own hand
     */
    public Card removeFromOwn(Card to_take);
}
