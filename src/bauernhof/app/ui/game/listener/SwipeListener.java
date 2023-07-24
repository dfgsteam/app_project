package bauernhof.app.ui.game.listener;

import bauernhof.app.ui.game.UiGame;
import sag.elements.GElement;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * @author Ramon Cemil Kimyon
 * @date 24.07.2023 05:21
 */
public class SwipeListener implements MouseEventListener {
    private UiGame uiGame;
    private double x = 0;
    private boolean pressed = false;
    public SwipeListener(final UiGame uiGame) {
        this.uiGame = uiGame;
    }
    @Override
    public void mouseClicked(MouseButtonEvent event, GElement self) {

    }

    @Override
    public void mousePressed(MouseButtonEvent event, GElement self) {
        pressed = true;
        x = MouseInfo.getPointerInfo().getLocation().getX() - self.getPositionX();
    }

    @Override
    public void mouseReleased(MouseButtonEvent event, GElement self) {
        pressed = false;
    }

    @Override
    public void mouseEntered(MouseMotionEvent event, GElement self) {

    }

    @Override
    public void mouseExited(MouseMotionEvent event, GElement self) {

    }

    @Override
    public void mouseMoved(MouseMotionEvent event, GElement self) {
        if(pressed) {
            double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
            self.move((float) (x- mouseX), self.getPositionY());
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event, GElement self) {

    }
}
