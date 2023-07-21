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
                if (game.getRound() == 1) {
                    if (playerid == game.getNumPlayers())
                        game.setRound(game.getRound() + 1);
                    else {
                        game.initBeginnerCards(playerid);
                        playerid++;
                    }
                    if (game instanceof GameSystem) {
                    } else if (!(game.getCurrentPlayer() instanceof HumanPlayer || game.getCurrentPlayer() instanceof RemotePlayer))
                        game.executeMove(game.getCurrentPlayer().request());
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
