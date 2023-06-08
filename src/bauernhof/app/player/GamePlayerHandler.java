package bauernhof.app.player;// This class was created by PaintableToast

import bauernhof.preset.Player;
import bauernhof.preset.card.Card;

import java.util.Set;

public interface GamePlayerHandler extends Player {
    /**
     * getter - Cards in PlayerHand
     * @return
     */
    public Set<Card> getCards();
    public Set<Card> getActiveCards();
    public Set<Card> getBlockedCards();
    public int getPlayerID();
}
