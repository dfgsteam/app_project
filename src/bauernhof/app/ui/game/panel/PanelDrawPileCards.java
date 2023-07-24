package bauernhof.app.ui.game.panel;

import java.awt.Color;

import bauernhof.app.card.Ca;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerBackButton;
import bauernhof.app.ui.game.listener.SwipeListener;
import bauernhof.app.ui.game.listener.card.ListenerCard;
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
    private GGroup topcard;

    public PanelDrawPileCards(UiGame uiGame) {
        // Definiere uiGame
        this.uiGame = uiGame;

        // Panel
        this.panel = new SAGPanel();
        GGroup headlinePanel = this.panel.addLayer(LayerPosition.TOP_CENTER);
        this.groupCards = this.panel.addLayer(LayerPosition.CENTER);
        GGroup buttonBackPanel = this.panel.addLayer(LayerPosition.BOTTOM_CENTER);
        topcard = this.panel.addLayer(LayerPosition.CENTER_LEFT);

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
        buttonBackBG.setMouseEventListener(new ListenerBackButton(this.uiGame, buttonBackHeadline, 1));
    }

    public void update() throws ChildNotFoundException {
        clear();
        // Bitte schreibe die Kartenerstellung in die update()-Methode. So müssen wir nicht mehr das Panel komplett neuladen.
        // Es muss mittels for-loop alle Karten nach System (x/y) in die GroupCards mittels addChild(gcard, x, y) geladen werden. Die GGroup ist immer leer, wenn diese geladen wird.
        // Platziere bitte die erste Karte in einer Zeile in der mitte alleine (keine anderen Karten). Gib dieser den CardPopListener, alle anderen den CardListener
        // G-Cards bekommst du mit ((Ca) this.uiGame.getGame().getDepositedCards().get(index)).getGCard()
        groupCards = this.panel.addLayer(LayerPosition.CENTER_LEFT);
        groupCards.setScale(0.68f);
        int size = this.uiGame.getGame().getDrawPileCards().size();
        this.setLayout(null);
        this.setVisible(true);

        /*GCard card;
        card = ((Ca) this.uiGame.getGame().getDrawPileCards().get(size - 1)).getGCard();
        card.setMouseEventListener(new ListenerCard(card.getGElement()));
        card.setScale(1.5F);
        card.setStroke(Color.GREEN, 20);
        System.out.println(card.getGElement().getScaleY());
        topcard.addChild(card, 180, 100);
        GText text = new GText("TOP CARD");
        topcard.setFill(Color.BLACK);
        text.setScale(1.5F);
        text.setBold(true);
        text.setAlignment(GText.TextAnchor.MIDDLE);
        topcard.addChild(text, 180, -150);


        int rows = 0;
        int columns = 0;
        size--;
        for (int i = 0; i < 5; i++) {
            columns = size/i;
            if (columns/i <= 2) {
                rows = i;
                i = size;
            }
            if (rows * size != size) columns++;
        }
        */

        groupCards.setMouseEventListener(new SwipeListener(uiGame));
        if (size < 11) {
            for (int i = size - 1; i >= 0; i--) {
                GCard card = ((Ca) uiGame.getGame().getDrawPileCards().get(i)).getGCard();
                groupCards.addChild(card, (size - i) * 225, 0);

                card.setScale(1F);
            }
        } else if (size < 21) {
            for (int i = size - 1; i >= 10; i--) {
                GCard card = ((Ca) uiGame.getGame().getDrawPileCards().get(i)).getGCard();
                groupCards.addChild(card, (size - i) * 225, -200);
                card.setScale(1.1F);
            }
            for (int i = 10; i >= 0; i--) {
                GCard card = ((Ca) uiGame.getGame().getDrawPileCards().get(i)).getGCard();
                groupCards.addChild(card, (size - i - (size - 10)) * 225, 200);
                card.setScale(1.1F);
            }

        } else {

        }

        /*
        int x = (int)(-210*((float)this.uiGame.getGame().getDrawPileCards().size()/8)), y=110,i=size - 2;

        //groupCards.addChild(card, 0f, -200f);

        for (; i >= 0;i--) { // Füge alle Karten aus der Hand hinzu
            if(x+400 >= this.panel.VIEWPORT_WIDTH){break;}
            card = ((Ca) this.uiGame.getGame().getDrawPileCards().get(i)).getGCard();
            card.setMouseEventListener(new ListenerCard(card.getGElement()));
            groupCards.addChild(card, x, y);
            x+=210;
        }

        x = (int)(-210*((float)this.uiGame.getGame().getDrawPileCards().size()/8));y=345;
        for(; i >= 0; i-- ){
            if(x+400 >= this.panel.VIEWPORT_WIDTH){break;}
            card = ((Ca) this.uiGame.getGame().getDrawPileCards().get(i)).getGCard();
            card.setMouseEventListener(new ListenerCard(card.getGElement()));
            groupCards.addChild(card, x, y);
            x+=210;
        }
*/
        /*
        this.gameBoard = gameBoard;
        CardListener cardListener = new ListenerCard();
        
        GGroup top = this.addLayer(LayerPosition.TOP_LEFT);
        top.setScale(0.5f);
        GGroup cen = this.addLayer(LayerPosition.CENTER_LEFT);
        cen.setScale(0.5f);
        GGroup bot = this.addLayer(LayerPosition.BOTTOM_LEFT);
        bot.setScale(0.5f);
        this.setLayout(null);
        int x=100,y=120,i=0;

        this.setVisible(true);

        GCard card;

        for(; i< stack.size(); i++ ){
            if(x+200 >= this.gameBoard.WIDTH*2){break;}
            card = new GCard(stack.get(i));
            card.setMouseEventListener(cardListener);
            top.addChild(card,x,y);
            x+=200;
            
        }
        x=100;y=-150;
        for(; i< stack.size(); i++ ){
            if(x+200 >= this.gameBoard.WIDTH*2){break;}
            card = new GCard(stack.get(i));
            card.setMouseEventListener(cardListener);
            cen.addChild(card,x,y);
            x+=200;
        
        }
        x=100;y=-350;
        for(; i< stack.size(); i++ ){
            card = new GCard(stack.get(i));
            card.setMouseEventListener(cardListener);
            bot.addChild(card,x,y);
            x+=200;

        }
        try {
            new BackButton(this, gameBoard);
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
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