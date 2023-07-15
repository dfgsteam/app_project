package bauernhof.app.ui.game.panel;

import java.awt.Color;
import java.util.Stack;

import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerBackButton;
import bauernhof.app.ui.game.listener.card.CardListener;
import bauernhof.app.ui.game.listener.card.CardPopListener;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class PanelDrawPileCards extends SAGPanel{
    
    private SAGPanel panel;
    private UiGame uiGame;
    private GGroup groupCards;

    public PanelDrawPileCards(UiGame uiGame) {
        // Definiere uiGame
        this.uiGame = uiGame;

        // Panel
        this.panel = new SAGPanel();
        GGroup headlinePanel = this.panel.addLayer(LayerPosition.TOP_CENTER);
        this.groupCards = this.panel.addLayer(LayerPosition.CENTER);
        GGroup buttonBackPanel = this.panel.addLayer(LayerPosition.BOTTOM_CENTER);

        // Überschrift
        GText headlineHeadline = new GText("Ziehstapel");
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
        buttonBackBG.setMouseEventListener(new ListenerBackButton(this.uiGame, buttonBackHeadline));
        
    }

    public void update() {
        System.out.println("Test");

        // Bitte schreibe die Kartenerstellung in die update()-Methode. So müssen wir nicht mehr das Panel komplett neuladen.

        // Es muss mittels for-loop alle Karten nach System (x/y) in die GroupCards mittels addChild(gcard, x, y) geladen werden. Die GGroup ist immer leer, wenn diese geladen wird.

        // Platziere bitte die erste Karte in einer Zeile in der mitte alleine (keine anderen Karten). Gib dieser den CardPopListener, alle anderen den CardListener

        // G-Cards bekommst du mit ((Ca) this.uiGame.getGameBoardState().getDepositedCards().get(index)).getGCard()
        
        Stack<Card> stack = this.uiGame.getGameBoardState().getDrawPileCards();
        
        GGroup cen_cen = this.panel.addLayer(LayerPosition.CENTER_CENTER);
        cen_cen.setScale(0.75f);
        GGroup cen = this.panel.addLayer(LayerPosition.CENTER_LEFT);
        cen.setScale(0.68f);
        GGroup bot = this.panel.addLayer(LayerPosition.BOTTOM_LEFT);
        bot.setScale(0.68f);

        this.setLayout(null);
        this.setVisible(true);
        GCard card;
        card = new GCard(stack.get(0));
        card.setMouseEventListener(new CardListener());
        int x = 100, y=110,i=1;
        
        cen_cen.addChild(card, 0, -200);
            
        for (; i < stack.size();i++) { // Füge alle Karten aus der Hand hinzu
            if(x+400 >= this.panel.VIEWPORT_WIDTH*1.8){break;}
            card = new GCard(stack.get(i));
            card.setMouseEventListener(new CardListener());
            cen.addChild(card, x, y);
            x+=210;
        }
        
        x=100;y=-300;
        for(; i< stack.size(); i++ ){
            if(x+400 >= this.panel.VIEWPORT_WIDTH*1.8){break;}
            card = new GCard(stack.get(i));
            card.setMouseEventListener(new CardListener());
            bot.addChild(card,x,y);
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