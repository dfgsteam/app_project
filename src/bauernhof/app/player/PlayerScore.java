package bauernhof.app.player;

import bauernhof.app.utils.ScoreCalculator;
import bauernhof.preset.card.Card;

import java.util.Set;

/**
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 10:13
 */

public class PlayerScore implements ScoreCalculator {
    private int score = 0;
    public PlayerScore(final Set<Card> cards) {
        for (final Card card : cards) {

        }
    }

    @Override
    public void add(Card card) {

    }

    @Override
    public void remove(Card card) {

    }

    @Override
    public int getScore() {
        return 0;
    }
}
