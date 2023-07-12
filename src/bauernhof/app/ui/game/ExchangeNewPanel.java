package bauernhof.app.ui.game;

import bauernhof.app.ui.game.listener.ExchangeListener;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;

/**
 * a panel where one card of players hand will be placed into the deposited 
 * and top card from the drawpile will take its place
 */

public class ExchangeNewPanel extends SAGPanel{

    private GameBoard gameBoard;
    private GCard card;

    /**
     * constructor
     * @param gameBoard
     */

    public ExchangeNewPanel(GameBoard gameBoard){

            this.gameBoard = gameBoard;

            //top text
            
            GGroup Top = this.addLayer(LayerPosition.TOP_CENTER);
            Top.addChild(new GText("Click on the Card You want to get rid of:"), -350,100);

            GGroup Mid = this.addLayer(LayerPosition.CENTER_CENTER);
            int x = -750, y = 0;
            Mid.setScale(0.9f);

            //placing the cards from players hand in the middle of the panel

            for(int i = 0; i < 7; i++){
                card = new GCard(gameBoard.getGameBoardState().getPlayers()[0].getCards().get(i));
                card.setMouseEventListener(new ExchangeListener(gameBoard));
                Mid.addChild(card,x, y);
                x+=200;
            }

            // last card (first of drawplie)

            card = new GCard(gameBoard.getGameBoardState().getDrawPileCards().iterator().next());
            card.setMouseEventListener(new ExchangeListener(gameBoard));

            Mid.addChild(card, x, y);

    }
}

    

    