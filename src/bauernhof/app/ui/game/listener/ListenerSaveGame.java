package bauernhof.app.ui.game.listener;

import bauernhof.app.ui.game.UiGame;
import sag.elements.GElement;
import sag.elements.GText;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

/**
 * This class represents a mouse event listener for saving the game in the game UI.
 * It implements the MouseEventListener interface from the sag.events package.
 * The listener is associated with a specific GElement object and responds to mouse events.
 * 
 * The ListenerSaveGame class provides the following functionality:
 * - Saves the game when the associated GElement is clicked.
 * - Changes the scale and font size of the GElement when the mouse enters or exits the element.
 * 
 * @author Julius Hunold
 * @version 1.0
 */

public class ListenerSaveGame implements MouseEventListener {
    
    private UiGame UiGame;
    private GText headline;

    /**
     * Constructs a new ListenerSaveGame object.
     * The object represents a mouse event listener for saving the game in the game UI.
     * 
     * @param UiGame The UiGame object that represents the game UI.
     * @param headline The GText object representing the headline associated with the listener.
     */
    public ListenerSaveGame(UiGame UiGame, GText headline) {
        this.UiGame = UiGame;
        this.headline = headline;
    }

    /**
     * Handles the mouse click event.
     * Saves the game in the UiGame object.
     * 
     * @param arg0 The MouseButtonEvent object representing the mouse click event.
     * @param arg1 The GElement object associated with the event.
     */
    @Override
    public void mouseClicked(MouseButtonEvent arg0, GElement arg1) {
        this.UiGame.saveGame();
    }

    /**
     * Handles the mouse enter event.
     * Changes the scale and font size of the associated GElement.
     * 
     * @param arg0 The MouseMotionEvent object representing the mouse enter event.
     * @param arg1 The GElement object associated with the event.
     */
    @Override
    public void mouseEntered(MouseMotionEvent arg0, GElement arg1) {
        arg1.setScale(1.1f);
        this.headline.setFontSize(17f);
    }

    /**
     * Handles the mouse exit event.
     * Changes the scale and font size of the associated GElement.
     * 
     * @param arg0 The MouseMotionEvent object representing the mouse exit event.
     * @param arg1 The GElement object associated with the event.
     */
    @Override
    public void mouseExited(MouseMotionEvent arg0, GElement arg1) {
        arg1.setScale(1f);
        this.headline.setFontSize(15f);
    }

    /**
     * Handles the mouse move event.
     * This method is not implemented in this listener.
     * 
     * @param arg0 The MouseMotionEvent object representing the mouse move event.
     * @param arg1 The GElement object associated with the event.
     */
    @Override
    public void mouseMoved(MouseMotionEvent arg0, GElement arg1) {}

    /**
     * Handles the mouse press event.
     * This method is not implemented in this listener.
     * 
     * @param arg0 The MouseButtonEvent object representing the mouse press event.
     * @param arg1 The GElement object associated with the event.
     */
    @Override
    public void mousePressed(MouseButtonEvent arg0, GElement arg1) {}

    /**
     * Handles the mouse release event.
     * This method is not implemented in this listener.
     * 
     * @param arg0 The MouseButtonEvent object representing the mouse release event.
     * @param arg1 The GElement object associated with the event.
     */
    @Override
    public void mouseReleased(MouseButtonEvent arg0, GElement arg1) {}

    /**
     * Handles the mouse wheel move event.
     * This method is not implemented in this listener.
     * 
     * @param arg0 The MouseWheelEvent object representing the mouse wheel move event.
     * @param arg1 The GElement object associated with the event.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent arg0, GElement arg1) {}

}
