package bauernhof.app.ui.game.group.display;

import bauernhof.app.card.Ca;

import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerDeposited;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.elements.GGroup;

/**
 * This class represents a group for displaying the deposited deck of cards in the game UI.
 * It provides methods to update and clear the displayed cards.
 * The class is designed to be used within the UiGame class.
 *
 * The GroupDisplayDepositedDeck class provides the following functionality:
 * - Creates a group for displaying the deposited deck of cards.
 * - The group is added to the main panel of the UiGame instance.
 * - The group can be updated to display the first card from the deposited deck.
 * - The displayed card can have an event listener attached to it.
 * - The group can be cleared, removing all displayed cards.
 *
 * @author Julius Hunold
 * @version 1.0
 * @since 2023-07-14
 */
public class GroupDisplayDepositedDeck {

    GGroup panel;
    UiGame UiGame;

    /**
     * Constructs a new GroupDisplayDepositedDeck object.
     * The object represents a group for displaying the deposited deck of cards in the game UI.
     *
     * @param UiGame The UiGame object that represents the game UI.
     */
    public GroupDisplayDepositedDeck(UiGame UiGame) {
        // Initialize class variables
        this.UiGame = UiGame;
        this.panel = this.UiGame.getMainPanel().addLayer(LayerPosition.CENTER_CENTER); // Create a new GGroup on the main panel
    }

    /**
     * Updates the displayed cards in the group.
     * It adds the first card from the deposited cards and attaches a listener to it.
     *
     * @throws InterruptedException If the thread is interrupted while updating the group.
     */
    public void update() throws InterruptedException {
        System.out.println(this.UiGame.getGameSystem().getDepositedCards().isEmpty());
        // Add the first card from the deposited cards and assign the appropriate listener to it
        GCard gCard;
        if(!this.UiGame.getGame().getDepositedCards().isEmpty()) {
            gCard = ((Ca) this.UiGame.getGame().getDepositedCards().iterator().next()).getGCard();
            gCard.setMouseEventListener(new ListenerDeposited(gCard.getGElement(), this.UiGame));
            this.panel.addChild(gCard, 180f, 0f);
        }
    }

    /**
     * Clears the displayed cards in the group.
     * It removes all child elements from the group and resets their positions.
     *
     * @throws ChildNotFoundException If a child element is not found within the group.
     */
    public void clear() throws ChildNotFoundException {
        // Remove old cards and reset their positions (move)
        for (int cardIndex = this.panel.getNumChildren()-1; cardIndex >= 0 ; cardIndex--) {
            this.panel.removeChild(this.panel.getChildByRenderingIndex(cardIndex));
            System.out.println(this.panel.getNumChildren());
        }
    }
}
