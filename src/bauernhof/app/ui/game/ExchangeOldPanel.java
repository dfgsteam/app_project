package bauernhof.app.ui.game;

import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GElement;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

public class ExchangeOldPanel extends SAGPanel {

    private GameBoard gameBoard;
    private GCard card;

    public ExchangeOldPanel(GameBoard gameBoard){
            this.gameBoard = gameBoard;
            GGroup Top = this.addLayer(LayerPosition.TOP_CENTER);
            Top.addChild(new GText("Insert the Card You want to get rid of:"), -350,100);

            GGroup Mid = this.addLayer(LayerPosition.CENTER_CENTER);
            int x = -750, y = 0;
            Mid.setScale(0.9f);

            for(int i = 0; i < 7; i++){
                card = new GCard(gameBoard.getGameBoardState().getPlayers()[0].getCards().get(i));
                card.setMouseEventListener(new MyLIstener());
                Mid.addChild(card,x, y);
                x+=200;
            }

            Mid.addChild(card, x, y);

    }

    public GCard getCard(){
        return this.card;
    }


    class MyLIstener implements MouseEventListener{

        @Override
        public void mouseClicked(MouseButtonEvent arg0, GElement arg1) {
                                //gameBoard.getGameBoardState().getActualPlayer().doMove(new Move((Card)gameBoard.getGameBoardState().getDrawPileCards().pop(),
                                //(Card)arg1.getGElement()));
                                //gameBoard.updateMain();
                                gameBoard.getFrame().setSAGPanel(gameBoard.getMain());
                       
                        
        }

        @Override
        public void mouseEntered(MouseMotionEvent arg0, GElement arg1) {
                arg1.setScale(1.3f);
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

    
}
