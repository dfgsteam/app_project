package bauernhof.app.ui.game.listener;

import bauernhof.app.StartLauncher;
import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.settings.SeEx;
import sag.elements.GElement;
import sag.elements.GText;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

import javax.swing.*;
import java.io.IOException;

public class ListenerHomeButton implements MouseEventListener {
    GameBoardState gameBoardState;
    GText homeHeadline;

    public ListenerHomeButton(GameBoardState GameBoardState, GText homeHeadline) {
        this.gameBoardState = GameBoardState;
        this.homeHeadline = homeHeadline;
    }

    @Override
    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
        System.out.println("->home menu");
        JFrame.getFrames()[0].dispose();
        try {
            StartLauncher.main(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (SeEx e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void mousePressed(MouseButtonEvent var1, GElement var2) {}

    @Override
    public void mouseReleased(MouseButtonEvent var1, GElement var2) {}

    @Override
    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1.1f);
        this.homeHeadline.setScale(1.1f);
    }

    @Override
    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1f);
        this.homeHeadline.setScale(1f);
    }

    @Override
    public void mouseMoved(MouseMotionEvent var1, GElement var2) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {}
}
