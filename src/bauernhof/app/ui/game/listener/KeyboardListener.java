package bauernhof.app.ui.game.listener;

import bauernhof.app.player.types.HumanPlayer;
import bauernhof.app.system.Game;
import bauernhof.app.system.GameSystem;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyboardListener class is responsible for handling keyboard input in the game.
 * It listens for key events and responds accordingly, allowing players to interact with the game using the keyboard.
 * In this implementation, the spacebar (VK_SPACE) is used to trigger certain game actions, such as starting the game and executing a move.
 *
 * This class implements the KeyListener interface to listen for keyboard events.
 * When the spacebar is pressed, it checks the game's current state and performs the appropriate action based on the game's rules.
 * For example, if the game is in the initial round (round 0), pressing the spacebar will progress to the next round and initialize beginner cards for the players.
 * During the actual game rounds, pressing the spacebar will execute the move of the current player if the player is a HumanPlayer.
 *
 * @author Ramon Cemil Kimyon
 * @date 17.07.2023 02:14
 */

public class KeyboardListener implements KeyListener {
    private GameSystem game;
    private int playerid = 0;

    /**
     * Constructs a new KeyboardListener instance.
     *
     * @param game The Game instance representing the current game.
     */

    public KeyboardListener(final Game game) {
        this.game = (GameSystem) game;
    }
        
    /**
     * Invoked when a key is typed (pressed and released).
     *
     * @param e The KeyEvent representing the key event.
     */
    
     @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this implementation
    }

    /**
     * Invoked when a key is pressed down.
     * The spacebar key (VK_SPACE) triggers specific game actions based on the current game state.
     *
     * @param e The KeyEvent representing the key event.
     */

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            try {
                if (game.getRound() == 0) {
                        System.out.println("HALLO");
                        if (playerid == game.getNumPlayers()- 1)
                            game.setRound(game.getRound() + 1);
                        System.out.println(game.getRound());
                        game.initBeginnerCards(playerid);
                        playerid++;
                }else if (game.getCurrentPlayer() instanceof HumanPlayer)game.executeMove(game.getCurrentPlayer().request());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    /**
     * Invoked when a key is released.
     *
     * @param e The KeyEvent representing the key event.
     */

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
