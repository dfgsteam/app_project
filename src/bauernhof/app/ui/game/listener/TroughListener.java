package bauernhof.app.ui.game.listener;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.ui.game.GameBoard;
import sag.elements.GElement;
import sag.elements.GText;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

public class TroughListener implements MouseEventListener{
    
    private GameBoard gameBoard;

    public TroughListener(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void mouseClicked(MouseButtonEvent arg0, GElement arg1) {

        if(arg1.getGElement() != card){
            System.out.println("hi");
        
                //gameBoard.getGameBoardState().getActualPlayer().doMove(new Move((Card)gameBoard.getGameBoardState().getDrawPileCards().pop(),
                //(Card)arg1.getGElement()));
                //gameBoard.updateMain();
                this.gameBoard.getFrame().setSAGPanel(this.gameBoard.getMain());
        }
        if(arg1.getGElement() == card){
            System.out.println("not hi");
                this.gameBoard.getGameBoardState().getDepositedCards().add(this.gameBoard.getGameBoardState().getDrawPileCards().iterator().next());
                this.gameBoard.getGameBoardState().getDrawPileCards().remove(this.gameBoard.getGameBoardState().getDepositedCards().get(this.gameBoard.getGameBoardState().getDepositedCards().size()-1));
                this.gameBoard.updateMain();
                this.gameBoard.getFrame().setSAGPanel(this.gameBoard.getMain());
        }
    }

    @Override
    public void mouseEntered(MouseMotionEvent arg0, GElement arg1) {
            arg1.setScale(1.1f);
    }

    @Override
    public void mouseExited(MouseMotionEvent arg0, GElement arg1) {
        arg1.setScale(1f);
    }

    @Override
    public void mouseMoved(MouseMotionEvent arg0, GElement arg1) {}

    @Override
    public void mousePressed(MouseButtonEvent arg0, GElement arg1) {}

    @Override
    public void mouseReleased(MouseButtonEvent arg0, GElement arg1) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent arg0, GElement arg1) {}

}
    
