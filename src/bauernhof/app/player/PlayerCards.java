package bauernhof.app.player;

import bauernhof.app.card.Ca;
import bauernhof.preset.Either;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.CardColor;
import bauernhof.preset.card.Effect;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Klasse für um die Player Karten zu managen
 *
 * @auth#cor Ramon Cemil  Kimyon
 * @date 12.06.2023 00:40
 */
public abstract class PlayerCards implements CardSetHandler {
    protected Set<Card> cards = new HashSet<>(), blocked_cards = new HashSet<>(), active_cards = new HashSet<>();
    protected int score = 0;
    @Override
    public void add(final Card added_card) {
        if(!cards.contains(added_card)) {
            cards.add((Ca)added_card);
            updateBlockedCards();
            updateScore();
        }
    }

    @Override
    public boolean remove(final Card removed_card) {
        cards.remove(removed_card);
        updateBlockedCards();
        updateScore();
        return true;
    }
    @Override
    public int getAddScore(final Card card) {
        add(card);
        int score_state = score;
        remove(card);
        return score_state;
    }
    private Set<Card> getCardColorCardsInHand(final CardColor color) {
        Set<Card> colorcards = new HashSet<>();
        for (final Card card : cards)
            if (card.getColor().equals(color))
                colorcards.add(card);
        return colorcards;
    }
    private void updateScore() {
        score = 0;
        for (final Card card : active_cards) {
            score += card.getBaseValue();
            for (final Effect effect : card.getEffects())
                switch (effect.getType()) {
                    case POINTS_FOREACH:
                        for (final Either<Card, CardColor> either : effect.getSelector())
                            if (either.get() instanceof Card)
                                score += active_cards.contains(either.getLeft()) ? effect.getEffectValue() : 0;
                            else
                                score += getCardColorCardsInHand(either.getRight()).size() * effect.getEffectValue();
                        break;
                    case POINTS_SUM_BASEVALUES:
                        for (final Either<Card, CardColor> either : effect.getSelector())
                            if (either.get() instanceof Card)
                                score += active_cards.contains(either.getLeft()) ? either.getLeft().getBaseValue() : 0;
                            else
                                for (final Card color_card : getCardColorCardsInHand(either.getRight())) score += color_card.getBaseValue();
                        break;
                    case POINTS_FLAT_CONJUNCTION:
                        final Set<Card> selector_cards = new HashSet<>();
                        for (final Either<Card, CardColor> either : effect.getSelector())
                            if (either.get() instanceof Card)
                                selector_cards.add(either.getLeft());
                            else
                                for (final Card color_card : getCardColorCardsInHand(either.getRight()))
                                    selector_cards.add(color_card);
                        if (active_cards.containsAll(selector_cards))
                            score += card.getBaseValue();
                        break;
                    case POINTS_FLAT_DISJUNCTION:
                        for (final Either<Card, CardColor> either : effect.getSelector())
                            if (either.get() instanceof Card) {
                                if (active_cards.contains(either.getLeft())) {
                                    score += either.getLeft().getBaseValue();
                                    break;
                                }
                            } else
                                for (final Card color_card : getCardColorCardsInHand(either.getRight()))
                                    if (active_cards.contains(color_card)) {
                                        score += color_card.getBaseValue();
                                        break;
                                    }
                        break;
                    default:
                }
        }
    }
    public final void setScore(final int score) {
        this.score = score;
    }
     private void updateBlockedCards() {
        blocked_cards.clear();
        active_cards.clear();
        for (final Card hand_card : cards)
            for (final Effect effect : hand_card.getEffects())
                switch (effect.getType()) {                             // switch - case für jedes Event
                    case BLOCKED_IF_WITH:
                        for (final Either<Card, CardColor> either : effect.getSelector())
                            if (either.get() instanceof Card) {
                                if (cards.contains(either.get()))
                                    blocked_cards.add((Ca) hand_card);
                            } else if (getCardColorCardsInHand(either.getRight()).size() != 0) blocked_cards.add((Ca) hand_card);
                        break;
                    case BLOCKED_IF_WITHOUT:
                        boolean is_contained = false;
                        for (final Either<Card, CardColor> either : effect.getSelector())
                            if (either.get() instanceof Card)
                                is_contained |= (cards.contains(either.getLeft())) ? true : false;
                            else is_contained |= getCardColorCardsInHand(either.getRight()).size() != 0 ? true : false;
                        if (!is_contained)
                            blocked_cards.add((Ca) hand_card);
                        break;
                    case BLOCKS_EVERY:
                        for (final Either<Card, CardColor> either : effect.getSelector())
                            if (either.get() instanceof Card) {
                                if (cards.contains(either.get()))
                                    blocked_cards.add((Ca) either.getLeft());
                            } else
                                for (final Card card : getCardColorCardsInHand(either.getRight()))
                                    blocked_cards.add((Ca) card);
                        break;
                    default:
                }
        for (final Card card : cards)
            if (!blocked_cards.contains(card))
                active_cards.add((Ca) card);
    }

    @Override
    public int getRemoveScore(final Card card) {
        remove(card);
        int score_state = score;
        add(card);
        return score_state;
    }

    @Override
    public ArrayList<Card> getCards() {
        return new ArrayList<>(this.cards);
    }

    @Override
    public ArrayList<Card> getBlockedCards() {
        return new ArrayList<>(this.blocked_cards);
    }

    @Override
    public ArrayList<Card> getActiveCards() {
        return new ArrayList<>(this.active_cards);
    }
}
