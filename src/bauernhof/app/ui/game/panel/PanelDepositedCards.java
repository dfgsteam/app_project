package bauernhof.app.ui.game.panel;

import bauernhof.app.card.Ca;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerBackButton;
import bauernhof.app.ui.game.listener.ListenerDeposited;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;


import java.awt.Color;

/**
 * PanelDepositedCards class represents a panel displaying deposited cards in the game UI.
 * It handles the display and updates of the deposited cards.
 * The panel also contains a "Back" button that allows the user to return to the previous screen.
 * 
 * @author Kirill Pokhilenko
 * @version 1.0
 */

public class PanelDepositedCards extends SAGPanel {

    private SAGPanel panel;
    private UiGame uiGame;
    private GGroup groupCards;

    /**
     * Constructs a new PanelDepositedCards object.
     *
     * @param uiGame The UiGame instance associated with this panel.
     */

    public PanelDepositedCards(UiGame uiGame) {
        // Definiere uiGame
        this.uiGame = uiGame;

        // Panel
        this.panel = new SAGPanel();
        GGroup headlinePanel = this.panel.addLayer(LayerPosition.TOP_CENTER);
        this.groupCards = this.panel.addLayer(LayerPosition.CENTER);
        GGroup buttonBackPanel = this.panel.addLayer(LayerPosition.BOTTOM_CENTER);

        // Überschrift
        GText headlineHeadline = new GText("Ablagestapel");
        headlineHeadline.setAlignment(GText.TextAnchor.MIDDLE);
        headlineHeadline.setFontSize(40f);
        headlineHeadline.setBold(true);
        headlinePanel.addChild(headlineHeadline, 0f, 100f);

        // Button (Button)
        GRect buttonBackBG = new GRect(0f, 0f, 140f, 35f, true, 0f, 0f);
        buttonBackBG.setFill(new Color(255, 255, 255, 255));
        buttonBackBG.setStroke(new Color(0, 0, 0), 2f);
        buttonBackPanel.addChild(buttonBackBG, 0f, -85f);

        // Button-Schrift
        GText buttonBackHeadline = new GText("Zurück");
        buttonBackHeadline.setAlignment(GText.TextAnchor.MIDDLE);
        buttonBackHeadline.setFontSize(15f);
        buttonBackPanel.addChild(buttonBackHeadline, 0f, -80f);
        
        // Eventlistener
        buttonBackBG.setMouseEventListener(new ListenerBackButton(this.uiGame, buttonBackHeadline, 2));
    }

    /**
     * Updates the panel to display the deposited cards.
     *
     * @throws ChildNotFoundException If a child element is not found in the panel.
     */

    public void update() throws ChildNotFoundException {

        int size = this.uiGame.getGame().getDepositedCards().size();
        clear();
        groupCards = this.panel.addLayer(LayerPosition.CENTER_CENTER);
        this.setLayout(null);
        this.setVisible(true);
        GCard card;
        if (size <= uiGame.getGame().getConfiguration().getNumDepositionAreaSlots() / 2) {
            groupCards.setScale(1.25F);
            for (int i = 0; i < size; i++) {
                card = ((Ca) uiGame.getGame().getDepositedCards().get(i)).getGCard();
                card.setMouseEventListener(new ListenerDeposited(card.getGElement(), uiGame));
                groupCards.addChild(card, (size % 2 == 0 ? 105 : 0) + 210 * (-(size / 2) + i), 0);
            }
        } else {
            groupCards.setScale(1.0F);
            for (int i = 0; i < size; i++) {
                card = ((Ca) uiGame.getGame().getDepositedCards().get(i)).getGCard();
                card.setMouseEventListener(new ListenerDeposited(card.getGElement(), uiGame));
                groupCards.addChild(card, (size % 2 == 0 ? i % 2 == 0 ? 110 : 0 : 0 ) + 110*(-(size/2) + i), i % 2 == 0 ? -150 : 150); // Vllt nicht soviel elvis
            }
        }

    }

    /**
     * Clears the panel by removing all child elements.
     *
     * @throws ChildNotFoundException If a child element is not found in the panel.
     */

    public void clear() throws ChildNotFoundException {
        for (int cardIndex=this.groupCards.getNumChildren()-1; cardIndex >= 0 ; cardIndex--) {
            this.groupCards.getChildByRenderingIndex(cardIndex).setMouseEventListener(null);
            this.groupCards.removeChild(this.groupCards.getChildByRenderingIndex(cardIndex));
        }
    }

    /**
     * Gets the SAGPanel associated with this PanelDepositedCards object.
     *
     * @return The SAGPanel associated with this object.
     */
    
    public SAGPanel getPanel() {
        return this.panel;
    }
}
