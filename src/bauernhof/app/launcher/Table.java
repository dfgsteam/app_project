package bauernhof.app.launcher;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.Move;
import bauernhof.preset.Player;
import bauernhof.preset.card.Card;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

/**
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 01:05
 */
public interface Table extends GameProperties {
    /**
     * Deposited Cards on the table
     * Cards which are deposited by {@link Player#request()} --> {@link Move#getDeposited()}
     *
     * @return deposited_cards
     */
    public ArrayList<Card> getDepositedCards();
    /**
     * Cards on Card stack
     * @return
     */
    public Stack<Card> getDrawPileCards();

    /**
     * get Players sorted by ID
     * @return
     */
    public AbstractGamePlayer[] getPlayers();

    /**
     * move - Actual Player Move executed by ActualPlayer
     * drawpile - checks if player gets cards by DrawPileCards
     * @param move, drawpile
     *
     * @return True - If Move can be executed
     * @return False - If Move can't be executed
     */
    public boolean doMove(final Move move) throws Exception;

    /**
     * @param move - Actual Move Player executed by ActualPlayer
     *
     * @return Copy of GameBoardState todo a preMove again.
     */
    //public GameBoardState preMove(final Move move);

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
   // public void mixCards();

}
