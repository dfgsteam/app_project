package bauernhof.app.ui.game;

import java.awt.Color;

import bauernhof.app.card.Ca;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.listener.CardListener;
import bauernhof.app.ui.game.listener.HoverListener;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GElement;
import sag.elements.GGroup;

public class PlayerPanel extends GGroup{

    private GGroup groupPlayer[] = new GGroup[4];
    private float pos[][][];

    private GameBoard gameBoard;


    public PlayerPanel (SAGPanel mainPanel, int players, int maxCards, GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.pos = new float[players][maxCards][2];
        for (int counter=0; counter<players; counter++) {
            switch (counter) {
                case 0: this.groupPlayer[0] = mainPanel.addLayer(LayerPosition.BOTTOM_CENTER); break;
                case 1: this.groupPlayer[1] = mainPanel.addLayer(LayerPosition.CENTER_LEFT); break;
                case 2: this.groupPlayer[2] = mainPanel.addLayer(LayerPosition.TOP_CENTER); break;
                case 3: this.groupPlayer[3] = mainPanel.addLayer(LayerPosition.CENTER_RIGHT); break;
            }
            this.groupPlayer[counter].setScale(0.65f-(0.01f*maxCards));

            this.initPlayer(counter, maxCards);
        }
        return;
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

    public void updatePlayer(int playerId, AbstractGamePlayer Player) throws ChildNotFoundException {
        this.clearPlayerPanel(playerId);
        Object[] cards = Player.getCards().toArray();
        //System.out.println("Karten von Spieler " + playerId);
        for (int item=0; item<cards.length; item++) {
        //    System.out.println("Length : " + cards.length);
            GCard gCard = ((Ca) cards[item]).getGCard();
            gCard.setPosition(0f, 0f); // Setzte X/Y zurück

            CardListener cardListener = new CardListener(this.gameBoard, playerId);
            gCard.setMouseEventListener(cardListener);


           // System.out.print("*" + ((Ca) cards[item]).getName() + "*, ");
            
            this.groupPlayer[playerId].addChild(gCard, this.pos[playerId][item][0], this.pos[playerId][item][1]);
        }
    }

    private void clearPlayerPanel(int playerId) throws ChildNotFoundException {
        for (int cardIndex=0; cardIndex < this.groupPlayer[playerId].getNumChildren(); cardIndex++) {
            this.groupPlayer[playerId].getChildByRenderingIndex(cardIndex).setMouseEventListener(null);
            this.groupPlayer[playerId].removeChild(this.groupPlayer[playerId].getChildByRenderingIndex(cardIndex));
        }
    }

}
