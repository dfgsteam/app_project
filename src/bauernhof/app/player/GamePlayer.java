package bauernhof.app.player;

/**
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 00:55
 */

import bauernhof.app.launcher.GameStatus;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.Player;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

import java.util.Set;

public interface GamePlayer extends Player {
    /**
     * gets Cards in PlayerHand
     *
     * @return all_cards_in_hand
     */
    public Set<Card> getCards();

    /**
     * gets the unblocked cards in
     *
     * @return active_cards
     */
    public Set<Card> getActiveCards();

    /**
     * gets all blocked Cards in PlayerHand
     *
     * @return blocked_cards
     */
    public Set<Card> getBlockedCards();

    /**
     * playerid delivered by init() Method
     *
     * @return playerid
     */
    public int getPlayerID();

    /**
     * gets the GameConfiguration
     *
     * @return configuration
     */
    public GameConfiguration getGameConfiguration();

    /**
     * returns actual GameStatus
     *
     * @return gamestatus
     */
    public GameStatus getStatus();

    /**
     *
     *
     * @return playertype
     */
    public PlayerType getPlayerType();

}
