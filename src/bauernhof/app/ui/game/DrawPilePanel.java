package bauernhof.app.ui.game;

import java.util.Stack;
import bauernhof.app.ui.game.listener.DepositedDrawListener;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;

public class DrawPilePanel extends SAGPanel{
    
     private GameBoard gameBoard;

    public DrawPilePanel(GameBoard gameBoard, Stack<Card> stack){

        this.gameBoard = gameBoard;

        //3 groups 
        
        GGroup top = this.addLayer(LayerPosition.TOP_LEFT);
        top.setScale(0.5f);
        GGroup cen = this.addLayer(LayerPosition.CENTER_LEFT);
        cen.setScale(0.5f);
        GGroup bot = this.addLayer(LayerPosition.BOTTOM_LEFT);
        bot.setScale(0.5f);
        this.setLayout(null);
        int x=100,y=120,i=0; // start values

        this.setVisible(true);

        GCard card;

        //filling the groups with cards

        for(; i< stack.size(); i++ ){
            if(x+200 >= gameBoard.getFrame().getWidth()*1.5){break;}
            card = new GCard(stack.get(i));
            card.setMouseEventListener(gameBoard.getCardListener());
            top.addChild(card,x,y);
            x+=200;
            
        }
        x=100;y=-150;
        for(; i< stack.size(); i++ ){
            if(x+200 >= gameBoard.getFrame().getWidth()*1.5){break;}
            card = new GCard(stack.get(i));
            card.setMouseEventListener(gameBoard.getCardListener());
            cen.addChild(card,x,y);
            x+=200;
        
        }
        x=100;y=-350;
        for(; i< stack.size(); i++ ){
            card = new GCard(stack.get(i));
            card.setMouseEventListener(gameBoard.getCardListener());
            bot.addChild(card,x,y);
            x+=200;

        }

        //button to go back to main panel

        GText Back = new GText("Zurück");
        Back.setMouseEventListener(new DepositedDrawListener(gameBoard));
        bot.addChild(Back, 1700 , -350);


    }


}