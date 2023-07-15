package bauernhof.app.ui.launcher.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bauernhof.app.ui.launcher.UiLauncher;

/**
 * This class represents an action listener for switching to the network player panel in the launcher UI.
 * It implements the ActionListener interface from the java.awt.event package.
 * @author [Your Name]
 * @version 1.0
 * @since 2023-07-14
 */
public class ListenerToNetwork implements ActionListener {

    private UiLauncher uiLauncher;

    /**
     * Constructs a new ListenerToNetwork object.
     *
     * @param uiLauncher The UiLauncher object that represents the launcher UI.
     */
    public ListenerToNetwork(UiLauncher uiLauncher) {
        this.uiLauncher = uiLauncher;
    }

    /**
     * Handles the action event when the network player button is clicked.
     *
     * @param e The ActionEvent object representing the action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.uiLauncher.setPanelNetworkPlayer();
    }
}
