package bauernhof.app.ui.game.listener;

import bauernhof.app.ui.game.GameBoard;
import bauernhof.preset.card.GCard;
import sag.elements.GElement;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

/**
 * MouseEventListener implementation for ExchangePanel
 */

public class ExchangeListener implements MouseEventListener {

    private GameBoard gameBoard;

    /**
     * constructor
     * @param gameBoard
     */

    public ExchangeListener(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    /**
     * implementation of MouseClicked
     */

    @Override
        public void mouseClicked(MouseButtonEvent arg0, GElement arg1) {

            //a card what was in the hand 
            //and was clicked should be dropped to the deposited 
            //and the top card from the drawpile should land in the players hand

            if(gameBoard.getGameBoardState().getPlayers()[0].getCards().contains(arg1.getGElement())){
                //TODO place chosen card into the hand and put the second chosen card in the Drawpile deck
                //gameBoard.updateMain();
                gameBoard.getFrame().setSAGPanel(gameBoard.getMain());
            }

            //the card that was just taken lands in the deposited

            if(!gameBoard.getGameBoardState().getPlayers()[0].getCards().contains(arg1.getGElement()) && !gameBoard.getGameBoardState().getDepositedCards().contains(arg1.getGElement())){
                    System.out.println("hi");
                gameBoard.getGameBoardState().getDepositedCards().add(gameBoard.getGameBoardState().getDrawPileCards().iterator().next());
                gameBoard.getGameBoardState().getDrawPileCards().remove(gameBoard.getGameBoardState().getDepositedCards().get(gameBoard.getGameBoardState().getDepositedCards().size()-1));
                gameBoard.updateMain();
                gameBoard.getFrame().setSAGPanel(gameBoard.getMain());
            }
            //TODO taking cards from deposited
             if(arg1.getGElement() == gameBoard.getGameBoardState().getDepositedCards().get(gameBoard.getGameBoardState().getDepositedCards().size()-1)){
                //TODO place chosen card into the hand and put the second chosen card in the Drawpile deck
                gameBoard.updateMain();
                gameBoard.getFrame().setSAGPanel(gameBoard.getMain());
            }


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

