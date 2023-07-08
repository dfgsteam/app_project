package bauernhof.app.ui.game.listener;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.ui.game.GameBoard;
import sag.elements.GElement;
import sag.elements.GText;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

public class HomeButtonListener implements MouseEventListener {
    GameBoardState gameBoardState;
    GText homeHeadline;

    public HomeButtonListener(GameBoardState GameBoardState, GText homeHeadline) {
        this.gameBoardState = GameBoardState;
        this.homeHeadline = homeHeadline;
    }

    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
        System.out.println("zurück zum Hauptmenü");
    }

    public void mousePressed(MouseButtonEvent var1, GElement var2) {}

    public void mouseReleased(MouseButtonEvent var1, GElement var2) {}

    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(0.95f);
        this.homeHeadline.setScale(0.95f);
    }

    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1f);
        this.homeHeadline.setScale(1f);
    }

    public void mouseMoved(MouseMotionEvent var1, GElement var2) {}

    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {}
}
