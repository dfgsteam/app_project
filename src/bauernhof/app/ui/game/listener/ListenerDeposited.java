package bauernhof.app.ui.game.listener;

import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.elements.GElement;
import sag.events.*;

/**
 * This class represents a mouse event listener for deposited cards in the game UI.
 * It implements the MouseEventListener interface from the sag.events package.
 * 
 * @author Julius Hunold
 * @version 1.0
 * @since 2023-07-14
 */

public class ListenerDeposited implements MouseEventListener {
    private UiGame UiGame;

    /**
     * Constructs a new ListenerDeposited object.
     *
     * @param UiGame The UiGame object that represents the game UI.
     */
    public ListenerDeposited(GElement element, UiGame UiGame) {
        element.setScale(1.0F);
        this.UiGame = UiGame;
    }

    /**
     * {@inheritDoc}
     * Handles the mouse click event for the deposited card.
     * If the move is allowed, it adds the card to the game.
     *
     * @param var1 The MouseButtonEvent object representing the mouse button event.
     * @param var2 The GElement object representing the deposited card.
     */
    @Override
    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
        if (var1.getMouseButton().equals(MouseButton.LEFT)) {
            if (this.UiGame.check_move()) {
                try {
                    this.UiGame.moveAddCard((GCard) var2);
                } catch (ChildNotFoundException e) {
                    throw new RuntimeException(e);
                }
        }
        } else
                try {
                    this.UiGame.showPanelDepositedCards();
                } catch (ChildNotFoundException e) {
                    System.out.println(e);
                }

    }

    /**
     * {@inheritDoc}
     * Handles the mouse press event for the deposited card.
     *
     * @param var1 The MouseButtonEvent object representing the mouse button event.
     * @param var2 The GElement object representing the deposited card.
     */
    @Override
    public void mousePressed(MouseButtonEvent var1, GElement var2) {}

    /**
     * {@inheritDoc}
     * Handles the mouse release event for the deposited card.
     *
     * @param var1 The MouseButtonEvent object representing the mouse button event.
     * @param var2 The GElement object representing the deposited card.
     */
    @Override
    public void mouseReleased(MouseButtonEvent var1, GElement var2) {}

    /**
     * {@inheritDoc}
     * Handles the mouse enter event for the deposited card.
     * If the move is allowed, it scales the card to a larger size.
     *
     * @param var1 The MouseMotionEvent object representing the mouse motion event.
     * @param var2 The GElement object representing the deposited card.
     */
    @Override
    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        if (this.UiGame.check_move())
            var2.setScale(1.1f);
    }

    /**
     * {@inheritDoc}
     * Handles the mouse exit event for the deposited card.
     * It scales the card back to its original size.
     *
     * @param var1 The MouseMotionEvent object representing the mouse motion event.
     * @param var2 The GElement object representing the deposited card.
     */
    @Override
    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1f);
    }

    /**
     * {@inheritDoc}
     * Handles the mouse move event for the deposited card.
     *
     * @param var1 The MouseMotionEvent object representing the mouse motion event.
     * @param var2 The GElement object representing the deposited card.
     */
    @Override
    public void mouseMoved(MouseMotionEvent var1, GElement var2) {}

    /**
     * {@inheritDoc}
     * Handles the mouse wheel move event for the deposited card.
     * It shows the panel of deposited cards in the game UI.
     *
     * @param var1 The MouseWheelEvent object representing the mouse wheel event.
     * @param var2 The GElement object representing the deposited card.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {
        try {
            this.UiGame.showPanelDepositedCards();
        } catch (ChildNotFoundException e) {
            System.out.println(e);
        }
    }
}
