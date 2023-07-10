package bauernhof.app.ui.game;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;

import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGFrame;
import sag.SAGPanel;
import sag.elements.GElement;
import sag.elements.GGroup;

public class DrawPilePanel extends SAGPanel implements ActionListener{
    
     JButton Back;
     GameBoard gameBoard;

    public DrawPilePanel(GameBoard gameBoard, Stack<Card> stack){

        this.gameBoard = gameBoard;
        
        GGroup top = this.addLayer(LayerPosition.TOP_LEFT);
        top.setScale(0.75f);
        GGroup cen = this.addLayer(LayerPosition.CENTER_LEFT);
        cen.setScale(0.75f);
        GGroup bot = this.addLayer(LayerPosition.BOTTOM_LEFT);
        bot.setScale(0.75f);
        this.setLayout(null);
        int x=100,y=120,i=0;

        this.setVisible(true);

        for(; i< stack.size(); i++ ){
            if(x+200 >= this.VIEWPORT_WIDTH){break;}
            top.addChild(new GCard(stack.get(i)),x,y);
            x+=200;
            
        }
        x=100;y=-150;
        for(; i< stack.size(); i++ ){
            if(x+200 >= this.VIEWPORT_WIDTH){break;}
            cen.addChild(new GCard(stack.get(i)),x,y);
            x+=200;
        
        }
        x=100;y=-350;
        for(; i< stack.size(); i++ ){
            bot.addChild(new GCard(stack.get(i)),x,y);
            x+=200;

        }

        Back = new JButton("Zuruck");
        Back.addActionListener(this::actionPerformed);
        Back.setBounds(this.VIEWPORT_WIDTH-250, this.VIEWPORT_HEIGHT-130, 150, 30);
        this.add(Back);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Back){
            gameBoard.getFrame().setSAGPanel(gameBoard.getMain());
            
        }
    
}

}