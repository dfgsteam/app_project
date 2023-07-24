package bauernhof.app.ui.launcher.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bauernhof.app.ui.launcher.UiLauncher;

/**
 * This class represents an action listener for switching to the settings panel in the launcher UI.
 * It implements the ActionListener interface from the java.awt.event package.
 * @author Julius Hunold
 * @version 1.0
 * @since 2023-07-14
 */
public class ListenerToSettings implements ActionListener {

    private UiLauncher uiLauncher;

    /**
     * Constructs a new ListenerToSettings object.
     *
     * @param uiLauncher The UiLauncher object that represents the launcher UI.
     */
    public ListenerToSettings(UiLauncher uiLauncher) {
        this.uiLauncher = uiLauncher;
    }

    /**
     * Performs the action of switching to the settings panel when the corresponding event occurs.
     *
     * @param e The ActionEvent object representing the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.uiLauncher.setPanelSettings();
    }
}

