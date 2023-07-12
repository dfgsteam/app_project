package bauernhof.app.ui.game.listener;

import bauernhof.app.ui.game.GameBoard;
import sag.SAGPanel;
import sag.elements.GElement;
import sag.elements.GText;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

/**
 * MouseEventListener for Deposited and DrawPilePanel
 */

public class DepositedDrawListener implements MouseEventListener {

    private GameBoard gameBoard;
    private GText back;

    public DepositedDrawListener(GameBoard gameBoard){
            this.gameBoard = gameBoard;
            back = new GText("Zurück");
    }
    

    @Override
    public void mouseClicked(MouseButtonEvent arg0, GElement arg1) {
        if(arg1.getGElement() == back);
            gameBoard.getFrame().setSAGPanel(gameBoard.getMain());
    }

    @Override
    public void mouseEntered(MouseMotionEvent arg0, GElement arg1) {
        arg1.setScale(1.3f);
    }

    @Override
    public void mouseExited(MouseMotionEvent arg0, GElement arg1) {
        arg1.setScale(1f);
    }

    @Override
    public void mouseMoved(MouseMotionEvent arg0, GElement arg1) {}

    @Override
    public void mousePressed(MouseButtonEvent arg0, GElement arg1) {}

    @Override
    public void mouseReleased(MouseButtonEvent arg0, GElement arg1) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent arg0, GElement arg1) {}
    
}
