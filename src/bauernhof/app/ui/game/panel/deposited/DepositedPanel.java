package bauernhof.app.ui.game.panel.deposited;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import bauernhof.app.ui.game.GameBoard;
import bauernhof.app.ui.game.listener.DepositedListener;
import bauernhof.app.ui.game.listener.card.CardListener;
import bauernhof.app.ui.game.listener.card.CardPopListener;
import bauernhof.app.ui.game.panel.BackButton;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGFrame;
import sag.SAGPanel;
import sag.elements.GElement;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class DepositedPanel extends SAGPanel{
    
     private JButton Back;
     private GameBoard gameBoard;

    public DepositedPanel(GameBoard gameBoard, ArrayList<Card> list){

        this.gameBoard = gameBoard;
        DepositedListener cardListener = new DepositedListener(this.gameBoard,this.gameBoard.getPlayerId());
        
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

        for(; i< list.size(); i++ ){
            if(x+200 >= this.gameBoard.WIDTH*2){break;}
            card = new GCard(list.get(i));
            card.setMouseEventListener(cardListener);
            top.addChild(card,x,y);
            x+=200;
            
        }
        x=100;y=-150;
        for(; i< list.size(); i++ ){
            if(x+200 >= this.gameBoard.WIDTH*2){break;}
            card = new GCard(list.get(i));
            card.setMouseEventListener(cardListener);
            cen.addChild(card,x,y);
            x+=200;
        
        }
        x=100;y=-350;
        for(; i< list.size(); i++ ){
            card = new GCard(list.get(i));
            card.setMouseEventListener(cardListener);
            bot.addChild(card,x,y);
            x+=200;

        }

        try {
            new BackButton(this, gameBoard);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
}

