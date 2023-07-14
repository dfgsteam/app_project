package bauernhof.app.ui.game.group.display;

import bauernhof.app.card.Ca;

import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerDeposited;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.elements.GGroup;

public class GroupDisplayDepositedDeck {

    GGroup panel;
    UiGame UiGame;

    public GroupDisplayDepositedDeck(UiGame UiGame) {
        // init Klassenvariabeln
        this.UiGame = UiGame;
        this.panel = this.UiGame.getMainPanel().addLayer(LayerPosition.CENTER_CENTER); // neue ggroup auf panel erzeugen
    }

    public void update() throws InterruptedException {
        // Füge die erste Karte aus dem DepositedCards hinzu und gib ihm den passenden Listener
        GCard gCard = ((Ca) this.UiGame.getGameBoardState().getDepositedCards().iterator().next()).getGCard();
        gCard.setMouseEventListener(new ListenerDeposited(this.UiGame));
        this.panel.addChild(gCard, 180f, 0f);
    }

    public void clear()  throws ChildNotFoundException {
        // Lösche alte Karte und setzte position (move) zurück
        for (int cardIndex=0; cardIndex < this.panel.getNumChildren(); cardIndex++) {
            this.panel.removeChild(this.panel.getChildByRenderingIndex(cardIndex));
        }
    }
}
