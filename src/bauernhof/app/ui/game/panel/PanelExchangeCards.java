package bauernhof.app.ui.game.panel;

import java.util.ArrayList;

import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.card.CardPopListener;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;

public class PanelExchangeCards extends SAGPanel{

    private SAGPanel panel;

    public PanelExchangeCards(UiGame UiGame){
        // Panel
        this.panel = new SAGPanel(UiGame.WIDTH, UiGame.HEIGTH);

        // Überschrift
        GGroup headline = this.panel.addLayer(LayerPosition.TOP_CENTER);
        headline.addChild(new GText("Welche Karte soll von dir abgelegt werden?").setAlignment(GText.TextAnchor.MIDDLE), 0f,100f);
        
        // Ablagestapel
        ArrayList<Card> cards = UiGame.getGameBoardState().getPlayers()[UiGame.getPlayerId()].getCards();
        GGroup cardGroup = this.panel.addLayer(LayerPosition.CENTER_CENTER);
        cardGroup.setScale(1.1f - (0.05f*cards.size()));
        GCard card; 
        
        int x = (int)(-210*((float)cards.size()/2)); // Bestimme Startposition
        
        for (int index=0; index < cards.size(); index++) { // Füge alle Karten aus der Hand hinzu
            card = new GCard(cards.get(index));
            card.setMouseEventListener(new CardPopListener(UiGame));
            cardGroup.addChild(card, x, 0);
            x+=210;
        }
    }

    public SAGPanel getPanel() {
        return this.panel;
    }
}
