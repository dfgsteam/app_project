package bauernhof.app.ui.game;

import java.util.ArrayList;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;

public class PlayerPanel extends GGroup{

    private GGroup Group;
    private AbstractGamePlayer Player;
    private int position;

    private GGroup groupPlayer[] = new GGroup[4];

    private float pos[][][];


    // private
    // AbstractGamePlayer Player, int position, GGroup groupPlayer
    public PlayerPanel (SAGPanel mainPanel, int players, int maxCards, ArrayList<AbstractGamePlayer> Players) {
        this.pos = new float[players][maxCards][2];
        for (int counter=0; counter<players; counter++) {
            switch (counter) {
                case 0: this.groupPlayer[0] = mainPanel.addLayer(LayerPosition.BOTTOM_CENTER); break;
                case 1: this.groupPlayer[1] = mainPanel.addLayer(LayerPosition.CENTER_LEFT); break;
                case 2: this.groupPlayer[2] = mainPanel.addLayer(LayerPosition.TOP_CENTER); break;
                case 3: this.groupPlayer[3] = mainPanel.addLayer(LayerPosition.CENTER_RIGHT); break;
            }
            this.groupPlayer[counter].setScale(0.5f);

            this.initPlayer(counter, Players.get(0).getCards().iterator().next(), maxCards);
        }
        return;
    }

    private void initPlayer(int playerId, Card cardExamp, int maxCards) {
        switch (playerId%2) {
            case 0: {
                int counter = 0;
                int startpointX, startpointY;

                if (playerId == 0)
                    startpointY = -150;
                else
                    startpointY = 150;

                System.out.println(startpointY);
                
                while (counter < maxCards) {
                    System.out.println(counter);
                    startpointX = 0 - 180*(maxCards/2);
                    
                    this.pos[playerId][counter][0] = startpointX + 200f*counter; // X-Pos von Karte
                    this.pos[playerId][counter][1] = startpointY; // Y-Pos von Karte

                    this.groupPlayer[playerId].addChild(new GCard(cardExamp), this.pos[playerId][counter][0], this.pos[playerId][counter][1]);
                    counter++;
                }
                break;
            } case 1: {
                break;
            }
        }
    }



    public void updatePlayer(int playerId) {

    }


     public GGroup getPanal() {
        return null;//this.Panel;
    }

}
