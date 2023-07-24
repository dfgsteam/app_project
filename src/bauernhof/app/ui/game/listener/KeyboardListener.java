package bauernhof.app.ui.game.listener;

import bauernhof.app.player.types.HumanPlayer;
import bauernhof.app.system.Game;
import bauernhof.app.system.GameSystem;
import bauernhof.preset.networking.RemotePlayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Ramon Cemil Kimyon
 * @date 17.07.2023 02:14
 */
public class KeyboardListener implements KeyListener {
    private GameSystem game;
    private int playerid = 0;
    public KeyboardListener(final Game game) {
        this.game = (GameSystem) game;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

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

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
