package bauernhof.app.player;

import bauernhof.app.utils.ScoreCalculator;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.Effect;

import java.util.Set;

/**
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 10:13
 */

public class PlayerScore implements ScoreCalculator {
    private int score = 0;
    private Set<Card> cards;
    public PlayerScore(final Set<Card> cards) {
        this.cards = cards;
        for (final Card card : cards)
            add(card);
    }

    @Override
    public void add(Card card) {

    }

    @Override
    public void remove(Card card) {

    }

    @Override
    public int getScore() {
        return score;
    }

}
