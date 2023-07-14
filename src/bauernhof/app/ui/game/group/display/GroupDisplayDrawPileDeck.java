package bauernhof.app.ui.game.group.display;

import bauernhof.app.card.Ca;

import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerDrawPile;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.elements.GGroup;

/**
 * This class represents a group for displaying the draw pile deck in the game UI.
 * It manages the visual representation of the draw pile deck and provides methods for updating and clearing it.
 * The class is designed to be used within the UiGame class.
 * 
 * The GroupDisplayDrawPileDeck class provides the following functionality:
 * - Creates a group for displaying the draw pile deck.
 * - The group is added to the main panel of the UiGame instance.
 * - The group is initially populated with the first card from the draw pile.
 * - The group provides a method to update its contents with the latest card from the draw pile.
 * - The group provides a method to clear its contents and reset the position of the cards.
 * 
 * @author [Your Name]
 * @version 1.0
 * @since 2023-07-14
 */

public class GroupDisplayDrawPileDeck {

    GGroup panel;
    UiGame UiGame;

    /**
     * Constructs a new GroupDisplayDrawPileDeck object.
     * The object represents a group for displaying the draw pile deck in the game UI.
     * 
     * @param UiGame The UiGame object that represents the game UI.
     * @throws InterruptedException If an error occurs during initialization.
     */
    public GroupDisplayDrawPileDeck(UiGame UiGame) throws InterruptedException {
        // Initialize class variables
        this.UiGame = UiGame;
        this.panel = this.UiGame.getMainPanel().addLayer(LayerPosition.CENTER_CENTER);

        // Display the first card from the draw pile
        this.update();
    }

    /**
     * Updates the display of the draw pile deck with the latest card.
     * 
     * @throws InterruptedException If an error occurs during the update process.
     */
    public void update() throws InterruptedException {
        // Add the first card from the draw pile and attach the appropriate listener
        GCard gCard = ((Ca) this.UiGame.getGameBoardState().getDrawPileCards().lastElement()).getGCard();
        gCard.setMouseEventListener(new ListenerDrawPile(this.UiGame));
        this.panel.addChild(gCard, -180, 0);
    }

    /**
     * Clears the display of the draw pile deck and resets the position of the cards.
     * 
     * @throws ChildNotFoundException If the child element is not found in the panel.
     */
    public void clear() throws ChildNotFoundException {
        // Remove the old cards and reset their positions
        for (int cardIndex = 0; cardIndex < this.panel.getNumChildren(); cardIndex++) {
            this.panel.removeChild(this.panel.getChildByRenderingIndex(cardIndex));
        }
    }
}