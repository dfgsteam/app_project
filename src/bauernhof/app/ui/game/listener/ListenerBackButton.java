package bauernhof.app.ui.game.listener;

import bauernhof.app.ui.game.UiGame;
import sag.elements.GElement;
import sag.elements.GText;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

/**
 * This class represents a mouse event listener for the back button in the game UI.
 * It implements the MouseEventListener interface from the sag.events package.
 * The listener is responsible for handling various mouse events related to the back button.
 *
 * The ListenerBackButton class requires an instance of UiGame, a GText object representing the button headline,
 * and an update version number to be constructed.
 *
 * @author Julius Hunold
 * @version 1.0
 * @since 2023-07-14
 */

public class ListenerBackButton implements MouseEventListener {

    private UiGame uiGame;
    private GText headline;
    private int updateVersion;

    /**
     * Constructs a new ListenerBackButton object.
     * The object represents a mouse event listener for the back button in the game UI.
     *
     * @param uiGame        The UiGame object that represents the game UI.
     * @param headline      The GText object representing the button headline.
     * @param updateVersion The update version number to set the main panel to when the button is clicked.
     */
    public ListenerBackButton(UiGame uiGame, GText headline, int updateVersion) {
        this.uiGame = uiGame;
        this.headline = headline;
        this.updateVersion = updateVersion;
    }

    @Override
    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
        this.uiGame.setMainPanel(updateVersion);
    }

    /**
     * This method is called when a mouse button is pressed on the back button.
     *
     * @param var1 The MouseButtonEvent object representing the mouse button event.
     * @param var2 The GElement object representing the back button.
     */
    @Override
    public void mousePressed(MouseButtonEvent var1, GElement var2) {
    }

    /**
     * This method is called when a mouse button is released on the back button.
     *
     * @param var1 The MouseButtonEvent object representing the mouse button event.
     * @param var2 The GElement object representing the back button.
     */
    @Override
    public void mouseReleased(MouseButtonEvent var1, GElement var2) {
    }

    /**
     * This method is called when the mouse cursor enters the back button.
     *
     * @param var1 The MouseMotionEvent object representing the mouse motion event.
     * @param var2 The GElement object representing the back button.
     */
    @Override
    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1.25f);
        this.headline.setScale(1.25f);
    }

    /**
     * This method is called when the mouse cursor exits the back button.
     *
     * @param var1 The MouseMotionEvent object representing the mouse motion event.
     * @param var2 The GElement object representing the back button.
     */
    @Override
    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1f);
        this.headline.setScale(1f);
    }

    /**
     * This method is called when the mouse cursor moves within the back button.
     *
     * @param var1 The MouseMotionEvent object representing the mouse motion event.
     * @param var2 The GElement object representing the back button.
     */
    @Override
    public void mouseMoved(MouseMotionEvent var1, GElement var2) {
    }

    /**
     * This method is called when the mouse wheel is moved while the cursor is on the back button.
     *
     * @param var1 The MouseWheelEvent object representing the mouse wheel event.
     * @param var2 The GElement object representing the back button.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {
    }
}
