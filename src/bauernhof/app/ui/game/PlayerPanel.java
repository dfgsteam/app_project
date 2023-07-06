package bauernhof.app.ui.game;

import java.awt.GridLayout;

import javax.swing.ImageIcon;

import bauernhof.preset.Player;
import bauernhof.preset.card.Card;
import sag.SAGPanel;

public class PlayerPanel extends SAGPanel{

    Player player;
    
    public PlayerPanel(Player player){

        this.player = player;
        
    }

}
