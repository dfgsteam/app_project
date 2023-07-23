package bauernhof.app.ui.game.listener.card;

import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.card.GCard;
import sag.elements.GElement;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

/**
 * This class implements a MouseEventListener for adding cards in the game UI.
 * It is designed to be used with the UiGame class.
 * 
 * The CardAddListener class provides the following functionality:
 * - Listens for mouse click events on GElements (elements) in the game UI.
 * - When a valid move is allowed by the UiGame instance, it adds the clicked GElement as a GCard to the game.
 * - The clicked GElement is then scaled and passed to the UiGame instance for processing.
 * - It also handles mouse enter and mouse exit events to adjust the scale of the GElement for visual feedback.
 * 
 * @author [Your Name]
 * @version 1.0
 * @since 2023-07-14
 */

public class ListenerCardAdd implements MouseEventListener {
    private UiGame UiGame;

    /**
     * Constructs a new CardAddListener object.
     * The object represents a mouse event listener for adding cards in the game UI.
     * 
     * @param UiGame The UiGame object that represents the game UI.
     */
    public ListenerCardAdd(GElement element, UiGame UiGame) {
        element.setScale(1.0F);
        this.UiGame = UiGame;
    }

    /**
     * Handles the mouse click event on a GElement.
     * If a valid move is allowed by the UiGame instance, it adds the clicked GElement as a GCard to the game.
     * The GElement is then scaled and passed to the UiGame instance for processing.
     * 
     * @param var1 The MouseButtonEvent that represents the mouse click event.
     * @param var2 The GElement that was clicked.
     */
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
    
    /**
     * Handles the mouse press event on a GElement.
     * This method is not implemented.
     * 
     * @param var1 The MouseButtonEvent that represents the mouse press event.
     * @param var2 The GElement on which the mouse press event occurred.
     */
    @Override
    public void mousePressed(MouseButtonEvent var1, GElement var2) {}

    /**
     * Handles the mouse release event on a GElement.
     * This method is not implemented.
     * 
     * @param var1 The MouseButtonEvent that represents the mouse release event.
     * @param var2 The GElement on which the mouse release event occurred.
     */
    @Override
    public void mouseReleased(MouseButtonEvent var1, GElement var2) {}

    /**
     * Handles the mouse enter event on a GElement.
     * Adjusts the scale of the GElement to provide visual feedback.
     * 
     * @param var1 The MouseMotionEvent that represents the mouse enter event.
     * @param var2 The GElement on which the mouse enter event occurred.
     */
    @Override
    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1.1f);
    }

    /**
     * Handles the mouse exit event on a GElement.
     * Adjusts the scale of the GElement to provide visual feedback.
     * 
     * @param var1 The MouseMotionEvent that represents the mouse exit event.
     * @param var2 The GElement on which the mouse exit event occurred.
     */
    @Override
    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1f);
    }

    /**
     * Handles the mouse move event on a GElement.
     * This method is not implemented.
     * 
     * @param var1 The MouseMotionEvent that represents the mouse move event.
     * @param var2 The GElement on which the mouse move event occurred.
     */
    @Override
    public void mouseMoved(MouseMotionEvent var1, GElement var2) {}

    /**
     * Handles the mouse wheel move event on a GElement.
     * This method is not implemented.
     * 
     * @param var1 The MouseWheelEvent that represents the mouse wheel move event.
     * @param var2 The GElement on which the mouse wheel move event occurred.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {}
}