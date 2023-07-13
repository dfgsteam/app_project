package bauernhof.app.ui.game.panel.draw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import bauernhof.app.ui.game.GameBoard;
import bauernhof.app.ui.game.listener.card.CardAddListener;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;

public class DrawPilePanel extends SAGPanel implements ActionListener{
    
    private JButton Back;
    private GameBoard gameBoard;
    private SAGPanel panel;

    public DrawPilePanel(GameBoard gameBoard){
        this.panel = new SAGPanel(GameBoard.WIDTH, GameBoard.HEIGTH);
        Stack<Card> stack = gameBoard.getGameBoardState().getDrawPileCards();
        // Muss neu
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

        GCard card;

        for(; i< stack.size(); i++ ){
            if(x+200 >= this.VIEWPORT_WIDTH){break;}
            card = new GCard(stack.get(i));
            card.setMouseEventListener(new CardAddListener(this.gameBoard, this.gameBoard.getPlayerId()));
            top.addChild(card,x,y);
            x+=200;
            
        }
        x=100;y=-150;
        for(; i< stack.size(); i++ ){
            if(x+200 >= this.VIEWPORT_WIDTH){break;}
            card = new GCard(stack.get(i));
            card.setMouseEventListener(new CardAddListener(this.gameBoard, this.gameBoard.getPlayerId()));
            cen.addChild(card,x,y);
            x+=200;
        
        }
        x=100;y=-350;
        for(; i< stack.size(); i++ ){
            card = new GCard(stack.get(i));
            card.setMouseEventListener(new CardAddListener(this.gameBoard, this.gameBoard.getPlayerId()));
            bot.addChild(card,x,y);
            x+=200;

        }


        // Muss neu -> Kein JButton
        Back = new JButton("Zuruck");
        Back.addActionListener(this::actionPerformed);
        Back.setBounds(this.VIEWPORT_WIDTH-250, this.VIEWPORT_HEIGHT-130, 150, 30);
        this.add(Back);

    }

    public SAGPanel getPanel() {
        return null;
        //this.panel;
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Back){
            //gameBoard.getFrame().setSAGPanel(gameBoard.getMain());
            
        }
    
}

}