package bauernhof.app.ui.game.listener;

import bauernhof.app.ui.game.UiGame;
import sag.elements.GElement;
import sag.elements.GText;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

/**
 * This class implements a mouse event listener for capturing screenshots in the game UI.
 * It provides functionality for handling mouse events such as mouse click, mouse enter, and mouse exit.
 * The listener is designed to be used with the UiGame class and works with GText and GElement objects.
 * 
 * The ListenerScreenshot class provides the following functionality:
 * - Captures a screenshot when the button is clicked.
 * - Enlarges the button and increases the font size when the mouse enters the button area.
 * - Restores the button size and font size when the mouse exits the button area.

 * @author [Your Name]
 * @version 1.0
 * @since 2023-07-14
 */


public class ListenerScreenshot implements MouseEventListener {
    
    private UiGame UiGame;
    private GText headline;

    /**
     * Constructs a new ListenerScreenshot object.
     * The object represents a mouse event listener for capturing screenshots in the game UI.
     * 
     * @param UiGame The UiGame object that represents the game UI.
     * @param headline The GText object that represents the button text.
     */
    public ListenerScreenshot(UiGame UiGame, GText headline) {
        this.UiGame = UiGame;
        this.headline = headline;
    }

    /**
     * Invoked when the button is clicked.
     * This method triggers the capture of a screenshot in the game UI.
     * It also ensures that the button returns to its default state after being clicked.
     * 
     * @param arg0 The MouseButtonEvent object that represents the mouse click event.
     * @param arg1 The GElement object that represents the button element.
     */
    @Override
    public void mouseClicked(MouseButtonEvent arg0, GElement arg1) {
        this.mouseExited(null, arg1); // To reset the button size
        this.UiGame.createScreenshot();
    }

    /**
     * Invoked when the mouse enters the button area.
     * This method enlarges the button and increases the font size of the button text.
     * 
     * @param arg0 The MouseMotionEvent object that represents the mouse enter event.
     * @param arg1 The GElement object that represents the button element.
     */
    @Override
    public void mouseEntered(MouseMotionEvent arg0, GElement arg1) {
        arg1.setScale(1.1f);
        this.headline.setFontSize(17f);
    }

    /**
     * Invoked when the mouse exits the button area.
     * This method restores the button size and font size to their default values.
     * 
     * @param arg0 The MouseMotionEvent object that represents the mouse exit event.
     * @param arg1 The GElement object that represents the button element.
     */
    @Override
    public void mouseExited(MouseMotionEvent arg0, GElement arg1) {
        arg1.setScale(1f);
        this.headline.setFontSize(15f);
    }

    /**
     * This method is not implemented.
     */
    @Override
    public void mouseMoved(MouseMotionEvent arg0, GElement arg1) {}

    /**
     * This method is not implemented.
     */
    @Override
    public void mousePressed(MouseButtonEvent arg0, GElement arg1) {}

    /**
     * This method is not implemented.
     */
    @Override
    public void mouseReleased(MouseButtonEvent arg0, GElement arg1) {}

    /**
     * This method is not implemented.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent arg0, GElement arg1) {}

}