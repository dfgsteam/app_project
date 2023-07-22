package bauernhof.app.ui.game.listener.card;

import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.card.GCard;
import sag.elements.GElement;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

/**
 * This class implements a MouseEventListener for card popping functionality in the game UI.
 * It is designed to be used with GCard elements.
 * The listener handles mouse events related to card popping, such as mouse click, mouse enter, and mouse exit.
 * 
 * The CardPopListener class provides the following functionality:
 * - Listens to mouse click events on GCard elements.
 * - If the UiGame's move is valid, it scales the clicked GCard to its original size and triggers the movePopCard() method of UiGame.
 * - Handles mouse enter events to scale the GCard element by a factor of 1.1.
 * - Handles mouse exit events to restore the original scale of the GCard element.
 * 
 * @author [Your Name]
 * @version 1.0
 * @since 2023-07-14
 */

public class ListenerCardPop implements MouseEventListener {
    private UiGame UiGame;
    
    /**
     * Constructs a new ListenerCardPop object.
     * The object represents a mouse event listener for card popping functionality in the game UI.
     * 
     * @param UiGame The UiGame object that represents the game UI.
     */
    public ListenerCardPop (UiGame UiGame) {
        this.UiGame = UiGame;
    }
    
    /**
     * Handles the mouse click event on a GCard element.
     * If the UiGame's move is valid, it scales the clicked GCard to its original size and triggers the movePopCard() method of UiGame.
     * 
     * @param var1 The MouseButtonEvent representing the mouse click event.
     * @param var2 The GElement that triggered the event.
     */
    @Override
    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
        if (this.UiGame.check_move()) {
            var2.setScale(1f);
            try {

                this.UiGame.movePopCard((GCard) var2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    /**
     * Handles the mouse press event.
     * This method is not implemented in this listener.
     * 
     * @param var1 The MouseButtonEvent representing the mouse press event.
     * @param var2 The GElement that triggered the event.
     */
    @Override
    public void mousePressed(MouseButtonEvent var1, GElement var2) {}
    
    /**
     * Handles the mouse release event.
     * This method is not implemented in this listener.
     * 
     * @param var1 The MouseButtonEvent representing the mouse release event.
     * @param var2 The GElement that triggered the event.
     */
    @Override
    public void mouseReleased(MouseButtonEvent var1, GElement var2) {}
    
    /**
     * Handles the mouse enter event.
     * It scales the GCard element by a factor of 1.1.
     * 
     * @param var1 The MouseMotionEvent representing the mouse enter event.
     * @param var2 The GElement that triggered the event.
     */
    @Override
    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1.1f);
    }
    
    /**
     * Handles the mouse exit event.
     * It restores the original scale of the GCard element.
     * 
     * @param var1 The MouseMotionEvent representing the mouse exit event.
     * @param var2 The GElement that triggered the event.
     */
    @Override
    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1f);
    }
    
    /**
     * Handles the mouse move event.
     * This method is not implemented in this listener.
     * 
     * @param var1 The MouseMotionEvent representing the mouse move event.
     * @param var2 The GElement that triggered the event.
     */
    @Override
    public void mouseMoved(MouseMotionEvent var1, GElement var2) {}
    
    /**
     * Handles the mouse wheel move event.
     * This method is not implemented in this listener.
     * 
     * @param var1 The MouseWheelEvent representing the mouse wheel move event.
     * @param var2 The GElement that triggered the event.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {}
}