package bauernhof.app.launcher;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.Move;
import bauernhof.preset.Player;
import bauernhof.preset.card.Card;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 01:05
 */
public interface Table {
    /**
     * Deposited Cards on the table
     * @return
     */
    public Set<Card> getDepositedCards();
    /**
     * Cards on Card stack
     * @return
     */
    public ArrayList<Card> getDrawPileCards();

    /**
     * get Players sorted by ID
     * @return
     */
    public Set<Player> getPlayers();

    /**
     * move - Actual Player Move executed by ActualPlayer
     * drawpile - checks if player gets cards by DrawPileCards
     * @param move, drawpile
     */
    public void doMove(final Move move, final boolean drawpile) throws Exception;

    /**
     * gets the Player who has to play now
     * @return
     */
    public Player getActualPlayer();

    /**
     * Returns the Actual Round Count.
     * A Round is finished if the first Players has to play again.
     * @return actual_roundcount;
     */
    public int getRound();

    /**
     * initNewGame();
     */
    public void initNewGame() throws Exception;
    /**
     * Mix all Cards on the DrawPileStack
     */
    public void mixCards();

    /**
     *
     * @return gameconfiguration;
     */
    public GameConfiguration getGameConfiguration();
}
