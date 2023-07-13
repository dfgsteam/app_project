package bauernhof.app.ui.game.listener;

import javax.swing.JLabel;

import bauernhof.app.ui.game.GameBoard;
import bauernhof.app.ui.game.panel.ExchangePanel;
import bauernhof.preset.card.GCard;
import sag.SAGPanel;
import sag.elements.GElement;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

public class DepositedListener implements MouseEventListener {
    private GameBoard gameBoard;

    public DepositedListener(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
            this.gameBoard.moveAddCard((GCard) var2);
    }
    
    public void mousePressed(MouseButtonEvent var1, GElement var2) {}

    public void mouseReleased(MouseButtonEvent var1, GElement var2) {}

    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1.1f);
    }

    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1f);
    }

    public void mouseMoved(MouseMotionEvent var1, GElement var2) {}

    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {
        if (this.gameBoard.check_move())
            this.gameBoard.createDepositedPanel();

    }
}
