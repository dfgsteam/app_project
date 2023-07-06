package bauernhof.app.ui.game;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.border.Border;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.Player;
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


    private void updatePlayer(int playerId, AbstractGamePlayer Player) {
        
        return;
    }



    public void updatePlayer(int playerId) {

    }


    public GGroup getPanal() {
        return this.Panel;
    }

}
