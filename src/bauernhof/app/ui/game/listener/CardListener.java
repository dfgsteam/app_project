package bauernhof.app.ui.game.listener;

import javax.swing.JLabel;

import bauernhof.app.ui.game.ExchangePanel;
import bauernhof.app.ui.game.GameBoard;
import sag.SAGPanel;
import sag.elements.GElement;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

public class CardListener implements MouseEventListener {
    GameBoard gameBoard;
    int playerId;

    public CardListener(GameBoard gameBoard, int playerId) {
        this.gameBoard = gameBoard;
        this.playerId = playerId;
    }

    public CardListener() {
    }

    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
        //System.out.println("click");
        if (this.gameBoard.check_move(this.playerId))
            System.out.println("click");

        if(var2.getGElement() == gameBoard.getDrawPileDeck() && var1.getClickCount()==1){
          
            ExchangePanel sagMyPanel = new ExchangePanel(this.gameBoard);
            gameBoard.getFrame().setSAGPanel(sagMyPanel);

            }
        
    }
    

    public void mousePressed(MouseButtonEvent var1, GElement var2) {}

    public void mouseReleased(MouseButtonEvent var1, GElement var2) {}

    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1.25f);
    }

    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1f);
    }

    public void mouseMoved(MouseMotionEvent var1, GElement var2) {}

    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {
        if(var2.getGElement() == gameBoard.getDrawPileDeck()){// && var1.getClickCount()==1){
            gameBoard.getFrame().setSAGPanel(gameBoard.getDrawPilePanel());
        }

        if(var2.getGElement() == gameBoard.getDepositedDeck()){// && var1.getClickCount()==1){
            gameBoard.getFrame().setSAGPanel(gameBoard.getDrawPilePanel());
        }

    }
}
