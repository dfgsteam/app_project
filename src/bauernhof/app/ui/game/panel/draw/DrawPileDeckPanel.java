package bauernhof.app.ui.game.panel.draw;

import bauernhof.app.card.Ca;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.ui.game.GameBoard;
import bauernhof.app.ui.game.listener.DrawPileListener;
import bauernhof.app.ui.game.listener.card.CardPopListener;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GElement;
import sag.elements.GGroup;

public class DrawPileDeckPanel {

    GGroup panel;
    GameBoardState gameBoardState;
    GameBoard gameBoard;

    public DrawPileDeckPanel(GameBoard gameBoard, SAGPanel mainPanel, int playerId, GameBoardState gameBoardState) throws ChildNotFoundException {
        this.gameBoardState = gameBoardState;
        this.gameBoard = gameBoard;
        this.panel = mainPanel.addLayer(LayerPosition.CENTER_CENTER);
        //this.panel.setScale(1.15f);

        this.update();

    }

    public void update() throws ChildNotFoundException {
        for (int cardIndex=0; cardIndex < this.panel.getNumChildren(); cardIndex++)
            this.panel.removeChild(this.panel.getChildByRenderingIndex(cardIndex));

        GCard gCard = ((Ca) this.gameBoardState.getDrawPileCards().iterator().next()).getGCard();
        //GCard gCard = new GCard(this.gameBoardState.getDrawPileCards().iterator().next());
        gCard.setMouseEventListener(new DrawPileListener(this.gameBoard, this.gameBoard.getPlayerId()));
        this.panel.addChild(gCard, -150, 0);
    }

}
