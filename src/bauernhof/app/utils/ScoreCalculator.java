package bauernhof.app.utils;

import bauernhof.preset.card.Card;

/**
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 09:29
 */
public interface ScoreCalculator {
    /**
     * adds Card to current Score
     *
     * @param card
     */
    public void add(Card card);

    /**
     * removes Card by current Score
     *
     * @param card
     */
    public void remove(Card card);

    /**
     * current Score as an int value
     *
     * @return score
     */
    public int getScore();
}
