package bauernhof.app.ui.game.listener.card;

import sag.elements.GElement;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

/**
 * This class represents a mouse event listener for card elements in the game UI.
 * It implements the MouseEventListener interface from the sag.events package.
 * The listener provides functionality for handling various mouse events on card elements.
 * 
 * The CardListener class provides the following functionality:
 * - Handling of mouse click events on card elements.
 * - Handling of mouse press events on card elements, which scales the card element to a larger size.
 * - Handling of mouse release events on card elements, which resets the card element's scale to the original size.
 * - Handling of mouse enter events on card elements, which scales the card element to a slightly smaller size.
 * - Handling of mouse exit events on card elements, which resets the card element's scale to the original size.
 * - Empty implementations for mouse move and mouse wheel events.
  * 
 * @author [Your Name]
 * @version 1.0
 * @since 2023-07-14
 */

public class ListenerCard implements MouseEventListener {
    public ListenerCard(GElement element) {
        element.setScale(1.0F);
    }
    @Override
    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
        // Empty implementation
    }
    
    @Override
    public void mousePressed(MouseButtonEvent var1, GElement var2) {
        var2.setScale(1.2f);
    }

    @Override
    public void mouseReleased(MouseButtonEvent var1, GElement var2) {
        var2.setScale(1f);
    }

    @Override
    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(0.95f);
    }

    @Override
    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1f);
    }

    @Override
    public void mouseMoved(MouseMotionEvent var1, GElement var2) {
        // Empty implementation
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {
        // Empty implementation
    }
}
