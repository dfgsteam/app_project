package bauernhof.app.ui.game.group.display;

import bauernhof.app.card.Ca;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.card.CardListener;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.elements.GGroup;

public class GroupDisplayPlayerCards extends GGroup{

    private GGroup groupPlayer[] = new GGroup[4];
    private float pos[][][];

    private UiGame UiGame;


    public GroupDisplayPlayerCards (UiGame UiGame) {
        this.UiGame = UiGame;
        int maxCards = this.UiGame.getGameBoardState().getConfiguration().getNumCardsPerPlayerHand();
        this.pos = new float[this.UiGame.getGameBoardState().getPlayers().length][maxCards][2];
        for (int counter=0; counter<this.UiGame.getGameBoardState().getPlayers().length; counter++) {
            switch (counter) {
                case 0: this.groupPlayer[0] = this.UiGame.getMainPanel().addLayer(LayerPosition.BOTTOM_CENTER); break;
                case 1: this.groupPlayer[1] = this.UiGame.getMainPanel().addLayer(LayerPosition.CENTER_LEFT); break;
                case 2: this.groupPlayer[2] = this.UiGame.getMainPanel().addLayer(LayerPosition.TOP_CENTER); break;
                case 3: this.groupPlayer[3] = this.UiGame.getMainPanel().addLayer(LayerPosition.CENTER_RIGHT); break;
            }
            this.groupPlayer[counter].setScale(0.65f-(0.01f*maxCards));

            this.initPlayer(counter, maxCards);
        }
    }

    private void initPlayer(int playerId, int maxCards) {
        switch (playerId%2) {
            case 0: {
                int counter = 0;
                int startpointX, startpointY;

                if (playerId == 0) // Position ob oben/unten 
                    startpointY = -150;
                else
                    startpointY = 150; 
                
                while (counter < maxCards) {
                    // Ermittel startpunkt
                    startpointX = 0 - 180*(maxCards/2);
                    
                    this.pos[playerId][counter][0] = startpointX + 200f*counter; // X-Pos von Karte
                    this.pos[playerId][counter][1] = startpointY; // Y-Pos von Karte

                    counter++;
                }
                break;
            } case 1: {
                int counter = 0;
                int startpointX, startpointY, side; // side = links/rechts

                startpointY = -120 * (maxCards/2); // 1, weil immer 300, anderes um anzahl an zusätzlichen zeilen zu bestimmen

                if (playerId == 1)
                    side = 1;
                else
                    side = -1;
                
                while (counter < maxCards) {

                    // Ermittel startpunkt
                    startpointX = side*100 + side*(200*(counter%2));
                    
                    this.pos[playerId][counter][0] = startpointX; // X-Pos von Karte
                    this.pos[playerId][counter][1] = startpointY; // Y-Pos von Karte

                    if (counter++ % 2 != 0)
                        startpointY += 280; // nach zwei Karten, wird die Y-Achse um eine Zeile nach unten korigiert. 
                }
                break;
            }
        }
    }

    public void updatePlayer(int playerId) throws ChildNotFoundException, InterruptedException {
        AbstractGamePlayer player = this.UiGame.getGameBoardState().getPlayers()[playerId];
        this.clearPlayerPanel(playerId);
        Object[] cards = player.getCards().toArray();

        for (int item=0; item<cards.length; item++) {
            GCard gCard = ((Ca) cards[item]).getGCard();
            
            gCard.setMouseEventListener(new CardListener());

            // Wenn Karte blockiert = rote umrandung
            if (player.getBlockedCards().contains(cards[item]))
                System.out.println(gCard.getCard().getName());

            // Karte der Gruppe hinzufügen
            this.groupPlayer[playerId].addChild(gCard, this.pos[playerId][item][0], this.pos[playerId][item][1]);
        }
    }

    private void clearPlayerPanel(int playerId) throws ChildNotFoundException {
        for (int cardIndex=this.groupPlayer[playerId].getNumChildren()-1; cardIndex >= 0 ; cardIndex--) {
            this.groupPlayer[playerId].getChildByRenderingIndex(cardIndex).unsetStrokeWidth();
            this.groupPlayer[playerId].getChildByRenderingIndex(cardIndex).setMouseEventListener(null);
            this.groupPlayer[playerId].removeChild(this.groupPlayer[playerId].getChildByRenderingIndex(cardIndex));
        }
    }

}