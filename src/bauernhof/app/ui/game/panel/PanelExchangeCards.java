package bauernhof.app.ui.game.panel;

import java.util.ArrayList;

import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerBackButton;
import bauernhof.app.ui.game.listener.card.CardPopListener;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

/**
 * This class represents a panel for exchanging cards in the game UI.
 * It extends the SAGPanel class from the sag package.
 * @author Julius Hunold
 * @version 1.0
 * @since 2023-07-14
*/

public class PanelExchangeCards extends SAGPanel {

    private SAGPanel panel;
    private UiGame uiGame;

    /**
     * Constructs a new PanelExchangeCards object.
     *
     * @param UiGame The UiGame object that represents the game UI.
     */
    public PanelExchangeCards(UiGame UiGame) {
        this.uiGame = uiGame;

        this.panel = new SAGPanel(bauernhof.app.ui.game.UiGame.WIDTH, bauernhof.app.ui.game.UiGame.HEIGTH);
        GGroup headlineGroup = this.panel.addLayer(LayerPosition.TOP_CENTER);
        GGroup cardGroup = this.panel.addLayer(LayerPosition.CENTER_CENTER);


        // Überschrift
        GText headlineHeadline = new GText("Welche Karte soll von dir abgelegt werden?");
        headlineHeadline.setAlignment(GText.TextAnchor.MIDDLE);
        headlineHeadline.setFontSize(40f);
        headlineHeadline.setBold(true);
        headlineGroup.addChild(headlineHeadline, 0f, 100f);

        // Ablagestapel
        //cardGroup.setScale(1.1f - (0.05f * this.uiGam)); //-> aus Settings hand pro player+1

        
    }


    public void update() {
        int x = (int) (-210 * ((float) cards.size() / 2)); // Bestimme Startposition

        for (int index = 0; index < cards.size(); index++) { // Füge alle Karten aus der Hand hinzu
            card = new GCard(cards.get(index));
            card.setMouseEventListener(new CardPopListener(UiGame));
            cardGroup.addChild(card, x, 0);
            x += 210;
        }
    }

    /**
     * Returns the panel object.
     *
     * @return The SAGPanel object representing the panel.
     */
    public SAGPanel getPanel() {
        return this.panel;
    }
}
