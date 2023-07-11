package bauernhof.app.ui.game.listener.card;

import bauernhof.app.ui.game.GameBoard;
import bauernhof.preset.card.GCard;
import sag.elements.GElement;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

public class CardListener implements MouseEventListener {

    public void mouseClicked(MouseButtonEvent var1, GElement var2) {}
    

    public void mousePressed(MouseButtonEvent var1, GElement var2) {
        var2.setScale(1.2f);
    }

    public void mouseReleased(MouseButtonEvent var1, GElement var2) {
        var2.setScale(1f);
    }

    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(0.95f);
    }

    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1f);
    }

    public void mouseMoved(MouseMotionEvent var1, GElement var2) {}

    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {}
}
