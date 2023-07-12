package bauernhof.app.ui.game.panel.draw;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;

import com.kitfox.svg.app.beans.SVGPanel;

import bauernhof.app.ui.game.GameBoard;
import bauernhof.app.ui.game.listener.BackButtonListener;
import bauernhof.app.ui.game.listener.card.CardListener;
import bauernhof.app.ui.game.listener.card.CardPopListener;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGFrame;
import sag.SAGPanel;
import sag.elements.GElement;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class DrawPilePanel extends SAGPanel{
    
    private JButton Back;
    private GameBoard gameBoard;
    private SAGPanel panel;

    public DrawPilePanel(GameBoard gameBoard, Stack<Card> stack){
        this.panel = new SAGPanel(GameBoard.WIDTH, GameBoard.HEIGTH);

        // Muss neu
        this.gameBoard = gameBoard;
        CardListener cardListener = new CardListener();
        
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

        for(; i< stack.size(); i++ ){
            if(x+200 >= this.VIEWPORT_WIDTH){break;}
            card = new GCard(stack.get(i));
            card.setMouseEventListener(cardListener);
            top.addChild(card,x,y);
            x+=200;
            
        }
        x=100;y=-150;
        for(; i< stack.size(); i++ ){
            if(x+200 >= this.VIEWPORT_WIDTH){break;}
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

        GText back = new GText("Zurück");
        GRect backRect = new GRect(x, y+80, 70, 30, getFocusTraversalKeysEnabled());
        bot.addChild(back,x,y);
        bot.addChild(backRect);

    }

    public SAGPanel getPanel() {
        return null;
        //this.panel;
    } 


}