package bauernhof.app.ui.game.listener.card;

import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.card.GCard;
import sag.elements.GElement;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

public class CardAddListener implements MouseEventListener {
    private UiGame UiGame;

    public CardAddListener(UiGame UiGame) {
        this.UiGame = UiGame;
    }

    @Override
    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
        if (this.UiGame.check_move()) {
            try {
                var2.setScale(1f);
                this.UiGame.moveAddCard((GCard) var2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    @Override
    public void mousePressed(MouseButtonEvent var1, GElement var2) {}

    @Override
    public void mouseReleased(MouseButtonEvent var1, GElement var2) {}

    @Override
    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1.1f);
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
