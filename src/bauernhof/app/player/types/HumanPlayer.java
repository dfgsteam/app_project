package bauernhof.app.player.types;

import bauernhof.app.player.PlayerCards;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.system.GameBoard;
import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.Move;
import bauernhof.preset.Settings;

/**
 * This class represents a human player in the Hofbauern game.
 * It extends the AbstractGamePlayer class and implements the request() method
 * to allow human players to make moves during the game.
 *
 * The HumanPlayer class holds references to the game settings, player cards,
 * and the game board system, enabling human players to interact with the game state.
 * The class uses the UiGame object from the GameBoard class to request moves from the human player
 * through the graphical user interface.
 *
 * The HumanPlayer class is a concrete implementation of a player and provides the necessary methods
 * to participate in the game and make valid moves.
 * 
 * @see AbstractGamePlayer
 * @see GameBoard
 * @see Move
 * @see Settings
 * @see PlayerCards
 * @see UiGame
 * @author Ramon Cemil Kimyon
 */
public class HumanPlayer extends AbstractGamePlayer {
        
    /**
     * Constructs a new HumanPlayer object with the given game settings, player cards, and game board system.
     *
     * @param settings     The Settings object representing game settings.
     * @param playercards  The PlayerCards object representing the player's hand.
     * @param gamesystem   The GameBoard object representing the game board system.
     */

    public HumanPlayer(final Settings settings, final PlayerCards playercards, final GameBoard gamesystem) {
        super(settings, playercards, gamesystem);
    }

    /**
     * Requests a move from the human player during their turn.
     *
     * @return The Move object representing the move made by the human player.
     * @throws Exception If an error occurs during the move request.
     */
    
    @Override
    public Move request() throws Exception {
        return GameBoard.getGraphics().requestMoveFromCurrentHumanPlayer();
    }
}