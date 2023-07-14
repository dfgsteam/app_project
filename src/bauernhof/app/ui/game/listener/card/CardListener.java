package bauernhof.app.ui.game.listener.card;

import sag.elements.GElement;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

public class CardListener implements MouseEventListener {

    @Override
    public void mouseClicked(MouseButtonEvent var1, GElement var2) {}
    
    @Override
    public void mousePressed(MouseButtonEvent var1, GElement var2) {
        var2.setScale(1.2f);
    }

    @Override
    public void mouseReleased(MouseButtonEvent var1, GElement var2) {
        var2.setScale(1f);
    }

    @Override
    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(0.95f);
    }

    @Override
    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1f);
    }

    @Override
    public void mouseMoved(MouseMotionEvent var1, GElement var2) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {}
}
