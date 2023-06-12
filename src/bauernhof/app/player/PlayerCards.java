package bauernhof.app.player;

import bauernhof.preset.Player;
import bauernhof.preset.card.Card;

import java.util.Set;

/**
 * Klasse für um die Player Karten zu managen
 *
 * @author Ramon Cemil Kimyon
 * @date 12.06.2023 00:40
 */
public abstract class PlayerCards implements CardSetHandler, Player {

    @Override
    public void add(Card card) {

    }

    @Override
    public void remove(Card card) {

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

    @Override
    public int getScore() throws Exception {
        /*
        TODO: Calculate the Score with the ScoreManager
         */
        return 0;
    }
}
