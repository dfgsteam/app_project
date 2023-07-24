package bauernhof.app.ui.launcher.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bauernhof.app.ui.launcher.UiLauncher;

/**
 * This class represents an action listener for navigating to the home panel in the launcher UI.
 * It implements the ActionListener interface from the java.awt.event package.
 * @author Julius Hunold
 * @version 1.0
 */

public class ListenerToHome implements ActionListener {

    private UiLauncher uiLauncher;

    /**
     * Constructs a new ListenerToHome object.
     *
     * @param uiLauncher The UiLauncher object that represents the launcher UI.
     */
    public ListenerToHome(UiLauncher uiLauncher) {
        this.uiLauncher = uiLauncher;
    }

    /**
     * Performs the action of navigating to the home panel when the associated event is triggered.
     *
     * @param e The ActionEvent object representing the action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.uiLauncher.setPanelHome();
    }
}

