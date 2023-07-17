package bauernhof.app.ui.game.listener;

import bauernhof.app.player.types.HumanPlayer;
import bauernhof.app.system.GameBoard;
import bauernhof.app.system.GameSystem;
import bauernhof.preset.networking.RemotePlayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Ramon Cemil Kimyon
 * @date 17.07.2023 02:14
 */
public class KeyboardListener implements KeyListener {
    private GameSystem system;
    private int playerid = 0;
    public KeyboardListener(final GameSystem system) {
        this.system = system;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            try {
                if (system.getRound() == 1) {
                    if (playerid == system.getNumPlayers()) system.setRound(system.getRound() + 1);
                    else {
                        system.initBeginnerCards(playerid);
                        playerid++;
                    }
                } else if (!(system.getActualPlayer() instanceof HumanPlayer || system.getActualPlayer() instanceof RemotePlayer))
                    system.executeMove(system.getActualPlayer().request());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
