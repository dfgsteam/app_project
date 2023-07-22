package bauernhof.app.ui.game.panel;

import bauernhof.app.card.Ca;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerBackButton;
import bauernhof.app.ui.game.listener.ListenerDeposited;
import bauernhof.app.ui.game.listener.card.ListenerCard;
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

    public void update() throws ChildNotFoundException {

        // Bitte schreibe die Kartenerstellung in die update()-Methode. So müssen wir nicht mehr das Panel komplett neuladen.

        // Es muss mittels for-loop alle Karten nach System (x/y) in die GroupCards mittels addChild(gcard, x, y) geladen werden. Die GGroup ist immer leer, wenn diese geladen wird.

        // G-Cards bekommst du mit ((Ca) this.uiGame.getGameBoardState().getDepositedCards().get(index)).getGCard()
        

        int size = this.uiGame.getGame().getDepositedCards().size();
        groupCards = this.panel.addLayer(LayerPosition.CENTER_CENTER);
        this.setLayout(null);
        this.setVisible(true);
        GCard card = null;
        if (size <= uiGame.getGame().getConfiguration().getNumDepositionAreaSlots() / 2) {
            groupCards.setScale(1.25F);
            for (int i = 0; i < size; i++) {
                card = ((Ca) uiGame.getGame().getDepositedCards().get(i)).getGCard();
                groupCards.addChild(card, (size % 2 == 0 ? 105 : 0) + 210 * (-(size / 2) + i), 0);
            }
        } else {
            groupCards.setScale(1.0F);
            for (int i = 0; i < size; i++) {
                card = ((Ca) uiGame.getGame().getDepositedCards().get(i)).getGCard();
                groupCards.addChild(card, (size % 2 == 0 ? i % 2 == 0 ? 110 : 0 : 0 ) + 110*(-(size/2) + i), i % 2 == 0 ? -150 : 150); // Vllt nicht soviel elvis
            }
        }
        if(card != null) card.setMouseEventListener(new ListenerCard());
        /*card = ((Ca) this.uiGame.getGame().getDepositedCards().get(0)).getGCard();
        card.setMouseEventListener(new ListenerCard());

        int x = (int)(-210*((float)this.uiGame.getGame().getDepositedCards().size()/8)),y=110,i=1;

        groupCards.addChild(card, 0, -200);

        for(; i< size; i++ ){
            if(x+400 >= this.panel.VIEWPORT_WIDTH){
                break;
            }
            card =((Ca) this.uiGame.getGame().getDepositedCards().get(i)).getGCard();
            card.setMouseEventListener(new ListenerCard());
            groupCards.addChild(card,x,y);
            x+=210;

        }
        x = (int)(-210*((float)this.uiGame.getGame().getDepositedCards().size()/8));
        y=-3450;
        for(; i< size; i++ ){
            if(x+400 >= this.panel.VIEWPORT_WIDTH){break;}
            card =((Ca) this.uiGame.getGame().getDepositedCards().get(i)).getGCard();
            card.setMouseEventListener(new ListenerCard());
            groupCards.addChild(card,x,y);
            x+=210;

        }*/
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
