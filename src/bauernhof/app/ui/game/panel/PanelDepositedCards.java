package bauernhof.app.ui.game.panel;

import bauernhof.app.card.Ca;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerBackButton;
import bauernhof.app.ui.game.listener.card.CardListener;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;


import java.awt.Color;
import java.util.ArrayList;

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

        // Bitte schreibe die Kartenerstellung in die update()-Methode. So müssen wir nicht mehr das Panel komplett neuladen.

        // Es muss mittels for-loop alle Karten nach System (x/y) in die GroupCards mittels addChild(gcard, x, y) geladen werden. Die GGroup ist immer leer, wenn diese geladen wird.

        // G-Cards bekommst du mit ((Ca) this.uiGame.getGameBoardState().getDepositedCards().get(index)).getGCard()
        

        int size = this.uiGame.getGameBoardState().getDepositedCards().size();
        groupCards = this.panel.addLayer(LayerPosition.CENTER_CENTER);
        groupCards.setScale(0.68f);
        this.setLayout(null);
        this.setVisible(true);
        GCard card;
        card = ((Ca) this.uiGame.getGameBoardState().getDepositedCards().get(0)).getGCard();
        card.setMouseEventListener(new CardListener());
        int x = (int)(-210*((float)this.uiGame.getGameBoardState().getDepositedCards().size()/8)),y=110,i=1;
        
        groupCards.addChild(card, 0, -200);
            
        for(; i< size; i++ ){
            if(x+400 >= this.panel.VIEWPORT_WIDTH){break;}
            card =((Ca) this.uiGame.getGameBoardState().getDepositedCards().get(i)).getGCard();
            card.setMouseEventListener(new CardListener());
            groupCards.addChild(card,x,y);
            x+=210;
        
        }
        x = (int)(-210*((float)this.uiGame.getGameBoardState().getDepositedCards().size()/8));y=-3450;
        for(; i< size; i++ ){
            if(x+400 >= this.panel.VIEWPORT_WIDTH){break;}
            card =((Ca) this.uiGame.getGameBoardState().getDepositedCards().get(i)).getGCard();
            card.setMouseEventListener(new CardListener());
            groupCards.addChild(card,x,y);
            x+=210;

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
