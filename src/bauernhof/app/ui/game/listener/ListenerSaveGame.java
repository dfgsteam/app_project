package bauernhof.app.ui.game.listener;

import bauernhof.app.ui.game.UiGame;
import sag.elements.GElement;
import sag.elements.GText;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

public class ListenerSaveGame implements MouseEventListener{
    
    private UiGame UiGame;
    private GText headline;

    public ListenerSaveGame(UiGame UiGame, GText headline) {
        this.UiGame = UiGame;
        this.headline = headline;
    }

    @Override
    public void mouseClicked(MouseButtonEvent arg0, GElement arg1) {
        this.UiGame.saveGame();
    }

    @Override
    public void mouseEntered(MouseMotionEvent arg0, GElement arg1) {
        arg1.setScale(1.1f);
        this.headline.setFontSize(17f);
    }

    @Override
    public void mouseExited(MouseMotionEvent arg0, GElement arg1) {
        arg1.setScale(1f);
        this.headline.setFontSize(15f);
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
    
