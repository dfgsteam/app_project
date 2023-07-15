package bauernhof.app.ui.launcher.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bauernhof.app.ui.launcher.panel.PanelLocal;

public class ListenerLocalDeleteUser implements ActionListener {

    private PanelLocal panelLocal;
    private int playerId;

    public ListenerLocalDeleteUser (PanelLocal panelLocal, int playerId) {
        this.panelLocal = panelLocal;
        this.playerId = playerId;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.panelLocal.removePlayer(playerId);
    }
    
}
