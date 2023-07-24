package bauernhof.app.ui.game.panel;

import bauernhof.app.card.Ca;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.card.ListenerCardPop;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;

import java.awt.*;

/**
 * This class represents a panel for exchanging cards in the game UI.
 * It extends the SAGPanel class from the sag package.
 * @author Julius Hunold
 * @version 1.0
 * @since 2023-07-14
*/

public class PanelExchangeCards extends SAGPanel {

    private SAGPanel panel;
    private GGroup cardGroup;
    private UiGame uiGame;
    private int[][] positions;

    /**
     * Constructs a new PanelExchangeCards object.
     *
     * @param uiGame The UiGame object that represents the game UI.
     */
    public PanelExchangeCards(UiGame uiGame) {
        this.uiGame = uiGame;

        this.panel = new SAGPanel(bauernhof.app.ui.game.UiGame.WIDTH, bauernhof.app.ui.game.UiGame.HEIGTH);
        GGroup headlineGroup = this.panel.addLayer(LayerPosition.TOP_CENTER);
        this.cardGroup = this.panel.addLayer(LayerPosition.CENTER_CENTER);


        // Überschrift
        GText headlineHeadline = new GText("Welche Karte soll von dir abgelegt werden?");
        headlineHeadline.setAlignment(GText.TextAnchor.MIDDLE);
        headlineHeadline.setFontSize(40f);
        headlineHeadline.setBold(true);
        headlineGroup.addChild(headlineHeadline, 0f, 100f);

        // Positionen errechnen
        int cardSize = this.uiGame.getGame().getConfiguration().getNumCardsPerPlayerHand()+1;
        cardGroup.setScale(1.1f - (0.01f * cardSize)); //-> aus Settings hand pro player+1

        this.positions = new int[cardSize][2];
        for (int index = 0; index < cardSize-1; index+=(cardSize/2)) {
            float maxCards = index<cardSize/2 ? cardSize/2 : cardSize-cardSize/2;
            int xPos = (int) (-220*(maxCards/2+0.5));
            int yPos = index > 0 ? -150 : 150;
            for (int index2 = 0; index2 < maxCards; index2++) {
                xPos += 220;
                this.positions[(int) index+index2][0] = xPos;
                this.positions[(int) index+index2][1] = yPos;
            }

        }
    }

    /**
     * Updates the panel with the current player's cards and their positions.
     *
     * @throws ChildNotFoundException If there is an error finding a child in the cardGroup.
     */

    public void update() throws ChildNotFoundException {
        for (int index = 0; index < this.uiGame.getGame().getCurrentPlayerCards().getCards().size(); index++) {
            GCard gCard = ((Ca) this.uiGame.getGame().getCurrentPlayerCards().getCards().get(index)).getGCard();
            gCard.setMouseEventListener(new ListenerCardPop(gCard.getGElement(), this.uiGame));
            this.cardGroup.addChild(gCard, this.positions[index][0], this.positions[index][1]);
            if (this.uiGame.getGame().getCurrentPlayerCards().getBlockedCards().contains(gCard.getCard())) {
                gCard.setStroke(Color.RED, 20.0F);
            } else
                gCard.unsetStroke();
        }
            
    }

    /**
     * Clears the panel by removing all cards from the cardGroup.
     *
     * @throws ChildNotFoundException If there is an error finding a child in the cardGroup.
     */
    
    public void clear() throws ChildNotFoundException {
        for (int index=this.cardGroup.getNumChildren()-1; index >= 0 ; index--) {
            this.cardGroup.removeChild(this.cardGroup.getChildByRenderingIndex(index));
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
