package bauernhof.app.ui.game.listener;

import bauernhof.app.system.GameBoard;
import bauernhof.app.system.GameSystem;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Ramon Cemil Kimyon
 * @date 17.07.2023 02:14
 */
public class SpaceListener implements KeyListener {
    private GameSystem system;
    private int playerid = 0;
    public SpaceListener(final GameSystem system) {
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
                    system.initBeginnerCards(playerid);
                    GameBoard.getGraphics().move(false);
                    playerid++;
                    if (playerid == system.getNumPlayers()) system.setRound(system.getRound() + 1);
                } else
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
