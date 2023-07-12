package bauernhof.app.ui.game;


import java.util.ArrayList;

import bauernhof.app.ui.game.listener.DepositedDrawListener;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;

public class DepositedPanel extends SAGPanel{
    
    private GameBoard gameBoard;

    public DepositedPanel(GameBoard gameBoard, ArrayList<Card> list){

        this.gameBoard = gameBoard;

        //create 3 groups 
        
        GGroup top = this.addLayer(LayerPosition.TOP_LEFT);
        top.setScale(0.75f);
        GGroup cen = this.addLayer(LayerPosition.CENTER_LEFT);
        cen.setScale(0.75f);
        GGroup bot = this.addLayer(LayerPosition.BOTTOM_LEFT);
        bot.setScale(0.75f);
        this.setLayout(null);
        int x=100,y=120,i=0;

        this.setVisible(true);

        GCard card;

        //draw about 8 cards on the top droup

        for(; i< list.size(); i++ ){
            if(x+200 >= this.VIEWPORT_WIDTH){break;}
            card = new GCard(list.get(i));
            card.setMouseEventListener(gameBoard.getCardListener());
            top.addChild(card,x,y);
            x+=200;
            
        }

        //same 8 cards in the middle

        x=100;y=-150;
        for(; i< list.size(); i++ ){
            if(x+200 >= this.VIEWPORT_WIDTH){break;}
            card = new GCard(list.get(i));
            card.setMouseEventListener(gameBoard.getCardListener());
            cen.addChild(card,x,y);
            x+=200;
        
        }

        //rest comes at the bottom

        x=100;y=-350;
        for(; i< list.size(); i++ ){
            card = new GCard(list.get(i));
            card.setMouseEventListener(gameBoard.getCardListener());
            bot.addChild(card,x,y);
            x+=200;

        }

        //the back button to carry the player back to the main panel 

        GText Back = new GText("Zurück");
        Back.setMouseEventListener(new DepositedDrawListener(gameBoard));
        bot.addChild(Back, 1700 , -350);
        

    }

   
    
}

