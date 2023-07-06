package bauernhof.app.ui.game;

import java.util.Set;

import bauernhof.app.card.Ca;
import sag.SAGPanel;

public class Stapel extends SAGPanel {

    Set<Ca> cards;
    
    public Stapel(Set<Ca> set){
            this.cards = set;
            
    }

}
