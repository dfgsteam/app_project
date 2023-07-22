package bauernhof.app.ui.game.panel;

import bauernhof.app.card.Ca;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerBackButton;
import bauernhof.app.ui.game.listener.card.ListenerCardAdd;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;


import java.awt.Color;

public class PanelDepositedCards extends SAGPanel {

    private SAGPanel panel;
    private UiGame uiGame;
    private GGroup groupCards;

    public PanelDepositedCards(UiGame uiGame) {
        // Definiere uiGame
        this.uiGame = uiGame;

        // Panel
        this.panel = new SAGPanel();
        GGroup headlinePanel = this.panel.addLayer(LayerPosition.TOP_CENTER);
        this.groupCards = this.panel.addLayer(LayerPosition.CENTER);
        GGroup buttonBackPanel = this.panel.addLayer(LayerPosition.BOTTOM_CENTER);

        // Überschrift
        GText headlineHeadline = new GText("Nachziehstapel");
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

    public void update() {
        System.out.println("UPDATE");
        int cardSize = this.uiGame.getGame().getDrawPileCards().size();
        this.groupCards.setScale(1.1f - (0.01f * cardSize));
        for (int index = 0; index < cardSize-1; index+=(cardSize/2)) { // Für obere und untere Reihe
            float maxCards = index<cardSize/2 ? cardSize/2 : cardSize-cardSize/2; // Endpunkt für 2. Forschleife (Ende von Reihe a/b)
            int xPos = (int) (-220*(maxCards/2+0.5)); // Damit Startpunkt variable nach Kartenanzahl
            int yPos = index > 0 ? -150 : 150; // Verschiebung der Reihe auf y-Achse
            for (int index2 = 0; index2 < maxCards; index2++) {
                xPos += 220;
                GCard gCard = ((Ca)this.uiGame.getGame().getDrawPileCards().get(index)).getGCard(); // bekomme gCard
                gCard.setMouseEventListener(new ListenerCardAdd(this.uiGame)); // MouseEventlistener
                this.groupCards.addChild(gCard, xPos, yPos);
            }
        }
       
    }

    public void clear() throws ChildNotFoundException {
        for (int cardIndex=this.groupCards.getNumChildren()-1; cardIndex >= 0 ; cardIndex--) {
            this.groupCards.getChildByRenderingIndex(cardIndex).setMouseEventListener(null);
            this.groupCards.removeChild(this.groupCards.getChildByRenderingIndex(cardIndex));
        }
    }

    public SAGPanel getPanel() {
        return this.panel;
    }
}
