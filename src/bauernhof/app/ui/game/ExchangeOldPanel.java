package bauernhof.app.ui.game;

import bauernhof.app.ui.game.listener.ExchangeListener;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;


public class ExchangeOldPanel extends SAGPanel {

    private GameBoard gameBoard;
    private GCard card;

    public ExchangeOldPanel(GameBoard gameBoard){
            this.gameBoard = gameBoard;
            GGroup Top = this.addLayer(LayerPosition.TOP_CENTER);
            Top.addChild(new GText("Click on the Card You want to get rid of:"), -350,100);

            GGroup Mid = this.addLayer(LayerPosition.CENTER_CENTER);
            int x = -750, y = 0;
            Mid.setScale(0.9f);

            //draw players cards in the middle

            for(int i = 0; i < 7; i++){
                card = new GCard(gameBoard.getGameBoardState().getPlayers()[0].getCards().get(i));
                card.setMouseEventListener(new ExchangeListener(gameBoard));
                Mid.addChild(card,x, y);
                x+=200;
            }

            // bottom text

            GGroup Bot = this.addLayer(LayerPosition.TOP_CENTER);
            Bot.addChild(new GText("Instead you'll get this card :"), -350,100);



    }

    
}
