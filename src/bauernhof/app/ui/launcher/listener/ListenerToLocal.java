package bauernhof.app.ui.launcher.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bauernhof.app.ui.launcher.UiLauncher;

/**
 * This class represents an action listener for the "Local" button in the launcher UI.
 * It implements the ActionListener interface.
 * @author Julius Hunold
 * @version 1.0
 * @since 2023-07-14
 */

public class ListenerToLocal implements ActionListener {

    private UiLauncher uiLauncher;

    /**
     * Constructs a new ListenerToLocal object.
     *
     * @param uiLauncher The UiLauncher object that represents the launcher UI.
     */
    public ListenerToLocal(UiLauncher uiLauncher) {
        this.uiLauncher = uiLauncher;
    }

    /**
     * Handles the action performed event when the "Local" button is clicked.
     *
     * @param e The ActionEvent object representing the action performed event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.uiLauncher.setPanelLocalPlayer();
    }
}