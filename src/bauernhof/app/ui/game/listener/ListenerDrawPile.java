package bauernhof.app.ui.game.listener;

import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.elements.GElement;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

/**
 * This class represents a mouse event listener for the draw pile in the game UI.
 * It implements the MouseEventListener interface from the sag.events package.
 * @author [Your Name]
 * @version 1.0
 * @since 2023-07-14
 */

public class ListenerDrawPile implements MouseEventListener {
    private UiGame UiGame;

    /**
     * Constructs a new ListenerDrawPile object.
     *
     * @param UiGame The UiGame object that represents the game UI.
     */
    public ListenerDrawPile(UiGame UiGame) {
        this.UiGame = UiGame;
    }

    /**
     * Handles the mouse click event on the draw pile.
     *
     * @param var1 The MouseButtonEvent object representing the mouse click event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
        if (this.UiGame.check_move()) {
            try {
                this.UiGame.moveAddCard((GCard) var2);
            } catch (ChildNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Handles the mouse press event on the draw pile.
     *
     * @param var1 The MouseButtonEvent object representing the mouse press event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mousePressed(MouseButtonEvent var1, GElement var2) {}

    /**
     * Handles the mouse release event on the draw pile.
     *
     * @param var1 The MouseButtonEvent object representing the mouse release event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseReleased(MouseButtonEvent var1, GElement var2) {}

    /**
     * Handles the mouse enter event on the draw pile.
     *
     * @param var1 The MouseMotionEvent object representing the mouse enter event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        if (this.UiGame.check_move())
            var2.setScale(1.1f);
    }

    /**
     * Handles the mouse exit event on the draw pile.
     *
     * @param var1 The MouseMotionEvent object representing the mouse exit event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        if (this.UiGame.check_move())
            var2.setScale(1f);
    }

    /**
     * Handles the mouse move event on the draw pile.
     *
     * @param var1 The MouseMotionEvent object representing the mouse move event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseMoved(MouseMotionEvent var1, GElement var2) {}

    /**
     * Handles the mouse wheel move event on the draw pile.
     *
     * @param var1 The MouseWheelEvent object representing the mouse wheel move event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {
        try {
            this.UiGame.showPanelDrawPileCards();
        } catch (ChildNotFoundException e) {
            System.out.println(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}