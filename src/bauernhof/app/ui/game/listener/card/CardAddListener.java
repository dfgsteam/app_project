package bauernhof.app.ui.game.listener.card;

import bauernhof.app.ui.game.GameBoard;
import bauernhof.preset.card.GCard;
import sag.elements.GElement;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

public class CardAddListener implements MouseEventListener {
    private GameBoard gameBoard;
    private int playerId;

    public CardAddListener(GameBoard gameBoard, int playerId) {
        this.gameBoard = gameBoard;
        this.playerId = playerId;
    }

    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
        if (this.gameBoard.check_move()) {
            try {
                var2.setScale(1f);
                this.gameBoard.moveAddCard((GCard) var2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
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

    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {}
}
