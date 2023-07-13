package bauernhof.app.ui.game.panel.draw;

import bauernhof.app.card.Ca;

import bauernhof.app.ui.game.GameBoard;
import bauernhof.app.ui.game.listener.DrawPileListener;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.elements.GGroup;

public class DrawPileDeckPanel {

    GGroup panel;
    GameBoard gameBoard;

    public DrawPileDeckPanel(GameBoard gameBoard) throws InterruptedException {
        // init Klassenvariabeln
    public DrawPileDeckPanel(GameBoard gameBoard) throws InterruptedException {
        // init Klassenvariabeln
        this.gameBoard = gameBoard;
        this.panel = this.gameBoard.getMainPanel().addLayer(LayerPosition.CENTER_CENTER); // neue ggroup auf panel erzeugen
        this.panel = this.gameBoard.getMainPanel().addLayer(LayerPosition.CENTER_CENTER); // neue ggroup auf panel erzeugen

        // für erste Karte, wird this.update() einmal ausgeführt.
        // für erste Karte, wird this.update() einmal ausgeführt.
        this.update();
    }

    public void update() throws InterruptedException {
        // Füge die erste Karte aus dem DrawPile hinzu und gib ihm den passenden Listener
        GCard gCard = ((Ca) this.gameBoard.getGameBoardState().getDrawPileCards().lastElement()).getGCard();
        gCard.setPosition(0, 0);
        gCard.setMouseEventListener(new DrawPileListener(this.gameBoard));
        if (gCard.getPositionX() != 0f || gCard.getPositionY() != 0f) {
            System.out.println(gCard.getCard().getName());
            Thread.sleep(10000000);
        }
        this.panel.addChild(gCard, -180, 0);
    }

    public void clear() throws ChildNotFoundException {
        // Lösche alte Karte und setzte position (move) zurück
        for (int cardIndex=0; cardIndex < this.panel.getNumChildren(); cardIndex++) {
            this.panel.getChildByRenderingIndex(cardIndex).setPosition(0f, 0f);
            this.panel.removeChild(this.panel.getChildByRenderingIndex(cardIndex));
        }
    }
}
