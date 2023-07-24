package bauernhof.app.ui.game.listener;

import bauernhof.app.ui.game.UiGame;
import sag.elements.GElement;
import sag.elements.GText;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

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
 * @author Julius Hunold
 * @version 1.0
 */
public class ListenerHomeButton implements MouseEventListener {
    private GText homeHeadline;
    private UiGame uiGame;

    /**
     * Constructs a new ListenerHomeButton object.
     * The object represents a mouse event listener for the home button in the game UI.
     *
     * @param uiGame The UiGame object representing the ingame UI.
     * @param homeHeadline   The GText object representing the home button headline.
     */
    public ListenerHomeButton(UiGame uiGame, GText homeHeadline) {
        this.uiGame = uiGame;
        this.homeHeadline = homeHeadline;
    }

    /**
     * Handles the mouseClicked event for the home button.
     * Disposes the current JFrame and launches the StartLauncher to return to the home menu.
     *
     * @param var1 The MouseButtonEvent object representing the mouse click event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
        this.uiGame.closeUiGame();
    }

    /**
     * Handles the mousePressed event for the home button.
     *
     * @param var1 The MouseButtonEvent object representing the mouse press event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mousePressed(MouseButtonEvent var1, GElement var2) {}

    /**
     * Handles the mouseReleased event for the home button.
     *
     * @param var1 The MouseButtonEvent object representing the mouse release event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseReleased(MouseButtonEvent var1, GElement var2) {}

    /**
     * Handles the mouseEntered event for the home button.
     * Increases the scale of the home button and its associated headline.
     *
     * @param var1 The MouseMotionEvent object representing the mouse enter event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1.1f);
        this.homeHeadline.setScale(1.1f);
    }

    /**
     * Handles the mouseExited event for the home button.
     * Resets the scale of the home button and its associated headline.
     *
     * @param var1 The MouseMotionEvent object representing the mouse exit event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1f);
        this.homeHeadline.setScale(1f);
    }

    /**
     * Handles the mouseMoved event for the home button.
     *
     * @param var1 The MouseMotionEvent object representing the mouse move event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseMoved(MouseMotionEvent var1, GElement var2) {}

    /**
     * Handles the mouseWheelMoved event for the home button.
     *
     * @param var1 The MouseWheelEvent object representing the mouse wheel move event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {}
}