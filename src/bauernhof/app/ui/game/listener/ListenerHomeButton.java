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

/**
 * This class represents a mouse event listener for the home button in the game UI.
 * It implements the MouseEventListener interface from the sag.events package.
 * The listener handles various mouse events associated with the home button.
 * 
 * The ListenerHomeButton class provides the following functionality:
 * - Handles the mouseClicked event for the home button.
 *   - Disposes the current JFrame.
 *   - Launches the StartLauncher to return to the home menu.
 * - Handles the mouseEntered event for the home button.
 *   - Increases the scale of the home button and its associated headline.
 * - Handles the mouseExited event for the home button.
 *   - Resets the scale of the home button and its associated headline.
 * 
 * @author [Your Name]
 * @version 1.0
 * @since 2023-07-14
 */

public class ListenerHomeButton implements MouseEventListener {
    private GameBoardState gameBoardState;
    private GText homeHeadline;

    /**
     * Constructs a new ListenerHomeButton object.
     * The object represents a mouse event listener for the home button in the game UI.
     * 
     * @param gameBoardState The GameBoardState object representing the game board state.
     * @param homeHeadline The GText object representing the home button headline.
     */
    public ListenerHomeButton(GameBoardState gameBoardState, GText homeHeadline) {
        this.gameBoardState = gameBoardState;
        this.homeHeadline = homeHeadline;
    }

    @Override
    public void mouseClicked(MouseButtonEvent event, GElement element) {
        System.out.println("->home menu");
        JFrame.getFrames()[0].dispose();
        try {
            StartLauncher.main(new String[]{});
        } catch (IOException | InterruptedException | SeEx e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void mousePressed(MouseButtonEvent event, GElement element) {}

    @Override
    public void mouseReleased(MouseButtonEvent event, GElement element) {}

    @Override
    public void mouseEntered(MouseMotionEvent event, GElement element) {
        element.setScale(1.1f);
        this.homeHeadline.setScale(1.1f);
    }

    @Override
    public void mouseExited(MouseMotionEvent event, GElement element) {
        element.setScale(1f);
        this.homeHeadline.setScale(1f);
    }

    @Override
    public void mouseMoved(MouseMotionEvent event, GElement element) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent event, GElement element) {}
}
