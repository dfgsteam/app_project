package bauernhof.app.ui.game.panel;

import java.awt.Color;

import bauernhof.app.card.Ca;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerBackButton;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

/**
 * PanelDrawPileCards represents the panel for displaying the draw pile of cards in the game UI.
 * This panel shows the draw pile cards, and it provides a "Back" button to return to the previous view.
 * It handles card rendering and event listeners for the back button.
 * 
 * The class extends SAGPanel, a custom panel class provided by the SAG library, to create the UI elements.
 * It manages the layout of cards and the "Back" button within the panel.
 * 
 * @author Kiril Pokhilenko
 * @version 1.0
 * @date 23.07
 */

public class PanelDrawPileCards extends SAGPanel{
    
    private SAGPanel panel;
    private UiGame uiGame;
    private GGroup groupCards;

    /**
     * Constructs a new PanelDrawPileCards instance.
     * 
     * @param uiGame The UiGame instance representing the game UI.
     */

    public PanelDrawPileCards(UiGame uiGame) {
        // define uiGame
        this.uiGame = uiGame;

        // Panel
        this.panel = new SAGPanel();
        GGroup headlinePanel = this.panel.addLayer(LayerPosition.TOP_CENTER);
        this.groupCards = this.panel.addLayer(LayerPosition.CENTER);
        GGroup buttonBackPanel = this.panel.addLayer(LayerPosition.BOTTOM_CENTER);

        // Headline
        GText headlineHeadline = new GText("Ziehstapel");
        headlineHeadline.setAlignment(GText.TextAnchor.MIDDLE);
        headlineHeadline.setFontSize(40f);
        headlineHeadline.setBold(true);
        headlinePanel.addChild(headlineHeadline, 0f, 50f);

        // Button (Button)
        GRect buttonBackBG = new GRect(0f, 0f, 140f, 35f, true, 0f, 0f);
        buttonBackBG.setFill(new Color(255, 255, 255, 255));
        buttonBackBG.setStroke(new Color(0, 0, 0), 2f);
        buttonBackPanel.addChild(buttonBackBG, 0f, -85f);

        // Button-Text
        GText buttonBackHeadline = new GText("Zurück");
        buttonBackHeadline.setAlignment(GText.TextAnchor.MIDDLE);
        buttonBackHeadline.setFontSize(15f);
        buttonBackPanel.addChild(buttonBackHeadline, 0f, -80f);

        // Eventlistener
        buttonBackBG.setMouseEventListener(new ListenerBackButton(this.uiGame, buttonBackHeadline, 1));
    }

    /**
     * Updates the panel to display the draw pile cards.
     * This method clears the panel, re-adds the draw pile cards, and renders them within the panel.
     * It sets the card positions and scales them accordingly for display.
     * 
     * @throws ChildNotFoundException If a child element is not found within the panel.
     */

    public void update() throws ChildNotFoundException {
        clear();
        groupCards = this.panel.addLayer(LayerPosition.CENTER_LEFT);
        groupCards.setScale(0.68f);
        int size = this.uiGame.getGame().getDrawPileCards().size();
        this.setLayout(null);
        this.setVisible(true);
        GCard card;
        int x = 1;
        int row = 0;
        for (int i = size - 1; i >= 0; i--) {
            card = ((Ca) uiGame.getGame().getDrawPileCards().get(i)).getGCard();
            if (x % 13 != 0) x++;
            else {
                x = 1;
                row++;
            }
            groupCards.addChild(card, -100 + (x - 1) * 200 , -425 + row * 270);
            card.setScale(1.0F);
        }
    }

    /**
     * Clears the panel by removing all card elements.
     * This method removes all child elements within the groupCards layer to clear the panel's contents.
     * It also sets the card event listeners to null to remove any attached event listeners.
     * 
     * @throws ChildNotFoundException If a child element is not found within the panel.
     */

    public void clear() throws ChildNotFoundException {
        for (int cardIndex=this.groupCards.getNumChildren()-1; cardIndex >= 0 ; cardIndex--) {
            this.groupCards.getChildByRenderingIndex(cardIndex).setMouseEventListener(null);
            this.groupCards.removeChild(this.groupCards.getChildByRenderingIndex(cardIndex));
        }
    }

    /**
     * Gets the SAGPanel representing the panel with the draw pile cards.
     * 
     * @return The SAGPanel representing the draw pile cards panel.
     */

    public SAGPanel getPanel() {
        return this.panel;
    }
}