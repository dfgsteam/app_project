package bauernhof.app.ui.launcher.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bauernhof.app.ui.launcher.panel.PanelLocal;

public class ListenerStartLocalGame implements ActionListener {

    private PanelLocal panelLocal;

    public ListenerStartLocalGame (PanelLocal panelLocal) {
        this.panelLocal = panelLocal;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.panelLocal.readAllPlayer();
    }
    
}
