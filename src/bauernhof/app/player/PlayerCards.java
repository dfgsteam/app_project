package bauernhof.app.player;

import bauernhof.preset.Either;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.CardColor;
import bauernhof.preset.card.Effect;

import java.util.ArrayList;

/**
 * Klasse für um die Player Karten zu managen
 *
 * @auth#cor Ramon Cemil Kimyon
 * @date 12.06.2023 00:40
 */
public abstract class PlayerCards implements CardSetHandler {
    private ImmutableList<Card> cards, blocked_cards, active_cards;
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

    @Override
    public int getRemoveScore(final Card card) {
        return 0;
    }

    @Override
    public ArrayList<Card> getCards() {
        return null;
    }

    @Override
    public ArrayList<Card> getBlockedCards() {
        return null;
    }

    @Override
    public ArrayList<Card> getActiveCards() {
        return null;
    }


}
