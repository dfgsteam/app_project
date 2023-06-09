package bauernhof.app.player;

/**
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 00:55
 */

import bauernhof.preset.Player;
import bauernhof.preset.card.Card;

import java.util.Set;

public interface PlayerHandler extends Player {
    /**
     * getter - Cards in PlayerHand
     * @return
     */
    public Set<Card> getCards();
    public Set<Card> getActiveCards();
    public Set<Card> getBlockedCards();
    public int getPlayerID();
}
