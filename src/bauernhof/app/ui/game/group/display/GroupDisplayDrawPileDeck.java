package bauernhof.app.ui.game.group.display;

import bauernhof.app.card.Ca;

import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerDrawPile;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.elements.GGroup;

public class GroupDisplayDrawPileDeck {

    GGroup panel;
    UiGame UiGame;

    public GroupDisplayDrawPileDeck(UiGame UiGame) throws InterruptedException {
        // init Klassenvariabeln
        this.UiGame = UiGame;
        this.panel = this.UiGame.getMainPanel().addLayer(LayerPosition.CENTER_CENTER); // neue ggroup auf panel erzeugen

        // für erste Karte, wird this.update() einmal ausgeführt.
        this.update();
    }

    public void update() throws InterruptedException {
        // Füge die erste Karte aus dem DrawPile hinzu und gib ihm den passenden Listener
        GCard gCard = ((Ca) this.UiGame.getGameBoardState().getDrawPileCards().lastElement()).getGCard();
        gCard.setMouseEventListener(new ListenerDrawPile(this.UiGame));
        this.panel.addChild(gCard, -180, 0);
    }

    public void clear() throws ChildNotFoundException {
        // Lösche alte Karte und setzte position (move) zurück
        for (int cardIndex=0; cardIndex < this.panel.getNumChildren(); cardIndex++) {
            this.panel.removeChild(this.panel.getChildByRenderingIndex(cardIndex));
        }
    }
}
