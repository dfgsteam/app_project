package bauernhof.app.player;

import bauernhof.app.utils.ScoreCalculator;
import bauernhof.preset.Either;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.CardColor;
import bauernhof.preset.card.Effect;

import java.util.Set;

/**
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 10:13
 */

public class PlayerScore implements ScoreCalculator {
    private int score = 0;
    private ImmutableList<Card> cards;
    public PlayerScore(final ImmutableList<Card> cards) {
        this.cards = cards;
        for (final Card card : cards) add(card);
    }

    @Override
    public void add(Card card) {
        cards.add(card);
        updateCardValues();
    }
    private void updateCardValues() {
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
    public void remove(Card card) {
        cards.remove(card);
        updateCardValues();
    }

    @Override
    public int getScore() {
        return score;
    }

}
