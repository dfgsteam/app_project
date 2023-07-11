package bauernhof.app.ui.game.panel;

import bauernhof.app.ui.game.GameBoard;
import bauernhof.app.ui.game.listener.CardPopListener;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;

public class ExchangePanel extends SAGPanel{

    private GameBoard gameBoard;
    private GCard card;

    public ExchangePanel(GameBoard gameBoard){
            this.gameBoard = gameBoard;
            GGroup Top = this.addLayer(LayerPosition.TOP_CENTER);
            Top.addChild(new GText("Welche Karte soll abgelegt werden?"), -350,100);

            GGroup Mid = this.addLayer(LayerPosition.CENTER_CENTER);
            int x = -750, y = 0;
            Mid.setScale(0.9f);

            for(int i = 0; i < 7; i++){
                card = new GCard(gameBoard.getGameBoardState().getPlayers()[0].getCards().get(i));
                card.setMouseEventListener(new CardPopListener(this.gameBoard, this.gameBoard.getPlayerId()));
                Mid.addChild(card,x, y);
                x+=200;
            }

            card = new GCard(gameBoard.getGameBoardState().getDrawPileCards().elementAt(0));
            card.setMouseEventListener(new CardPopListener(this.gameBoard, this.gameBoard.getPlayerId()));

            Mid.addChild(card, x, y);

    }

    public GCard getCard(){
        return this.card;
    }
}
