package bauernhof.app.ui.game.listener;

import bauernhof.app.ui.game.UiGame;
import sag.elements.GElement;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

public class ListenerBackButton implements MouseEventListener {
    private UiGame UiGame;

    public ListenerBackButton(UiGame UiGame) {
        this.UiGame = UiGame;
    }

    @Override
    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
        this.UiGame.setMainPanel();
    }
    
    @Override
    public void mousePressed(MouseButtonEvent var1, GElement var2) {}

    @Override
    public void mouseReleased(MouseButtonEvent var1, GElement var2) {}

    @Override
    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1.25f);
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
