package bauernhof.app.ui.launcher.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import bauernhof.app.ui.launcher.panel.PanelLocal;

public class ListenerLocalAddUser implements ItemListener {

    private PanelLocal panelLocal;
    private int playerId;

    public ListenerLocalAddUser (PanelLocal panelLocal, int playerId) {
        this.panelLocal = panelLocal;
        this.playerId = playerId;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED && e.getItem().toString() == "Keiner") {
            this.panelLocal.addPlayer(this.playerId);
        }
    }
    
}
