package bauernhof.app.ui.launcher.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bauernhof.app.ui.launcher.UiLauncher;

public class ListenerStartLocalGame implements ActionListener {

    private UiLauncher uiLauncher;

    public ListenerStartLocalGame (UiLauncher uiLauncher) {
        this.uiLauncher = uiLauncher;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.uiLauncher.setPanelLocalPlayer();
    }
    
}
