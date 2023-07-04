package bauernhof.app.player;

import bauernhof.preset.Either;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.CardColor;
import bauernhof.preset.card.Effect;

import java.util.HashSet;
import java.util.Set;

/**
 * Klasse für um die Player Karten zu managen
 *
 * @auth#cor Ramon Cemil  Kimyon
 * @date 12.06.2023 00:40
 */
public abstract class PlayerCards implements CardSetHandler {
    private Set<Card> cards, blocked_cards, active_cards;
    protected int score = 0;
    @Override
    public void add(final Card added_card) {
        /*
        TODO: Karten removen updaten
         */
        for (final Card card : cards)
            for (Effect effect : card.getEffects())
                switch (effect.getType()) {
                    case POINTS_FOREACH:
                        effect.getEffectValue();
                        for (final Either<Card, CardColor> either : effect.getSelector());
                        break;
                    case POINTS_SUM_BASEVALUES:
                        break;
                    case POINTS_FLAT_DISJUNCTION:
                        break;
                    case POINTS_FLAT_CONJUNCTION:
                        break;
                    case BLOCKED_IF_WITH:
                        break;
                    case BLOCKED_IF_WITHOUT:
                        break;
                    case BLOCKS_EVERY:
                        break;
                }
    }

    @Override
    public boolean remove(final Card removed_card) {
        /*
        TODO: Karten removen
         */
        if (cards.contains(removed_card)) {
            cards.remove(removed_card);
            for (final Card card : cards)
                for (final Effect effect : card.getEffects())
                    for (final Either<Card, CardColor> either : effect.getSelector())
                        if (either.get().equals(removed_card) || either.get().equals(removed_card.getColor()))
                            switch (effect.getType()) {
                                case POINTS_FOREACH:
                                    score -= effect.getEffectValue();
                                    break;
                                case POINTS_SUM_BASEVALUES:
                                    score -= removed_card.getBaseValue();
                                    break;
                                case POINTS_FLAT_DISJUNCTION:
                                    break;
                    }
            return true;
        } else return false;
    }
    @Override
    public int getAddScore(final Card card) {

        return 0;
    }
    // Methode um die geblockten Karten bei einem ADD zurückzugeben.
    private void updateOnAddBlockedCards(final Card card) {
        cards.add(card);
        for (final Card hand_card : cards)
            for (final Effect effect : hand_card.getEffects())
                for (final Either<Card, CardColor> either : effect.getSelector()) {

                    if (either.get() instanceof Card) {
                        if (cards.contains(either.get()))
                            blocked_cards.add(hand_card);
                    } //else if () blocked_cards.add(hand_card);
                }
    }
    private Set<Card> getCardColorCardsInHand(final CardColor color) {
        Set<Card> colorcards = new HashSet<>();
        for (final Card card : cards)
            if (card.getColor().equals(color))
                colorcards.add(card);
        return colorcards;
    }
    /*
    card die bei den removeblockedcards hinzugefügt wird ist der Parameter Card card

    addafter - boolean um zu bestimmen ob die Karte danach wieder hinzugefügt werden soll
     */
    private void updateOnRemoveBlockedCards(final Card card, final boolean addafter) {
        for (final Card card_in_hand : cards)
            for (final Effect effect : card_in_hand.getEffects())
                for (final Either<Card, CardColor> either : effect.getSelector())
                    switch (effect.getType()) {
                        case BLOCKED_IF_WITH:
                            if (either.get() instanceof Card) {
                                if (cards.contains(either.get()))
                                    blocked_cards.add(card_in_hand);
                            } else {

                            }
                            break;
                        case BLOCKED_IF_WITHOUT:
                            break;
                        case BLOCKS_EVERY:
                            break;
                        default:

                    }
    }

    @Override
    public int getRemoveScore(final Card card) {
        return 0;
    }

    @Override
    public Set<Card> getCards() {
        return null;
    }

    @Override
    public Set<Card> getBlockedCards() {
        return null;
    }

    @Override
    public Set<Card> getActiveCards() {
        return null;
    }


}
