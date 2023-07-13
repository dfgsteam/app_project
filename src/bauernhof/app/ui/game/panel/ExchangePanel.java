package bauernhof.app.ui.game.panel;

import java.util.ArrayList;

import bauernhof.app.ui.game.GameBoard;
import bauernhof.app.ui.game.listener.card.CardPopListener;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;

public class ExchangePanel extends SAGPanel{

    private GameBoard gameBoard;
    private GCard card;
    private SAGPanel panel;

    public ExchangePanel(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        this.panel = new SAGPanel(GameBoard.WIDTH, GameBoard.HEIGTH);

        GGroup Top = this.panel.addLayer(LayerPosition.TOP_CENTER);
        Top.addChild(new GText("Welche Karte soll abgelegt werden?").setAlignment(GText.TextAnchor.MIDDLE), 0f,100f);

        GGroup Mid = this.panel.addLayer(LayerPosition.CENTER_CENTER);
        
        ArrayList<Card> cards = this.gameBoard.getGameBoardState().getPlayers()[this.gameBoard.getPlayerId()].getCards();
        
        
        Mid.setScale(1.1f - (0.05f*cards.size()));
        int x = (int)(-210*((float)cards.size()/2)), y = 0;
        System.out.println(x);

        for (int index=0; index < cards.size(); index++) {
            card = new GCard(cards.get(index));
            card.setMouseEventListener(new CardPopListener(this.gameBoard));
            Mid.addChild(card,x, y);
            x+=210;
        }
    }

    public SAGPanel getPanel() {
        return this.panel;
    }
}
