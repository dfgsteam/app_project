package bauernhof.app.player;

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
public class PlayerCards {
    protected Set<Card> cards = new HashSet<>(), blocked_cards = new HashSet<>(), active_cards = new HashSet<>();
    protected int score = 0;

    public PlayerCards() {

    }

    public PlayerCards(final int score, final Set<Card> cards, final Set<Card> blocked_cards, final Set<Card> active_cards) {
        this.score = score;
        this.cards = cards;
        this.blocked_cards = blocked_cards;
        this.active_cards = active_cards;
    }

    public void add(final Card added_card) {
        if (!cards.contains(added_card)) {
            cards.add(added_card);
            updateBlockedCards();
            updateScore();
        }
    }

    public boolean remove(final Card removed_card) {
        cards.remove(removed_card);
        updateBlockedCards();
        updateScore();
        return true;
    }

    public int getAddScore(final Card card) {
        add(card);
        int score_state = score;
        remove(card);
        return score_state;
    }

    public int getAddRemoveScore(final Card add, final Card remove) {
        add(add);
        remove(remove);
        int score_state = score;
        add(remove);
        remove(add);
        return score_state;
    }

    private Set<Card> getCardColorCardsInHand(final CardColor color) {
        Set<Card> colorcards = new HashSet<>();
        for (final Card card : active_cards)
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
                            if (either.get() instanceof Card) {
                                score += active_cards.contains(either.getLeft()) ? effect.getEffectValue() : 0;
                            } else
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
                                    score += effect.getEffectValue();
                                    break;
                                }
                            } else
                                for (final Card color_card : getCardColorCardsInHand(either.getRight()))
                                    if (active_cards.contains(color_card)) {
                                        score += effect.getEffectValue();
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
        active_cards = new HashSet<>(cards);
        HashSet<Card> eithercards;
        for (final Card hand_card : cards)
            for (final Effect effect : hand_card.getEffects()) {
                eithercards = new HashSet<>();
                for (final Either<Card, CardColor> either : effect.getSelector())
                    if (either.get() instanceof Card)
                        eithercards.add(either.getLeft());
                    else
                        eithercards.addAll(getCardColorCardsInHand(either.getRight()));
                switch (effect.getType()) {
                    case BLOCKED_IF_WITH:
                        for (final Card card : eithercards)
                            if(cards.contains(card)) {
                                blocked_cards.add(hand_card);
                                break;
                            }
                        break;
                    case BLOCKED_IF_WITHOUT:
                        if (!cards.containsAll(eithercards))
                            blocked_cards.add(hand_card);
                        break;
                    case BLOCKS_EVERY:
                        for (final Card card : eithercards)
                            if (eithercards.contains(card))
                                blocked_cards.add(card);
                        break;
                    default:
                }
            }
        active_cards.clear();
        for (final Card card : cards)
            if (!blocked_cards.contains(card))
                active_cards.add(card);
    }

    public int getRemoveScore(final Card card) {
        remove(card);
        int score_state = score;
        add(card);
        return score_state;
    }

    public ArrayList<Card> getCards() {
        return new ArrayList<>(this.cards);
    }

    public ArrayList<Card> getBlockedCards() {
        return new ArrayList<>(this.blocked_cards);
    }

    public ArrayList<Card> getActiveCards() {
        return new ArrayList<>(this.active_cards);
    }

    public PlayerCards clone() {
        return new PlayerCards(score, new HashSet<>(cards), new HashSet<>(blocked_cards), new HashSet<>(active_cards));
    }
    public int getScore() {
        return this.score;
    }
}
