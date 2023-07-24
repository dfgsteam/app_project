package bauernhof.app.ui.launcher.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bauernhof.app.ui.launcher.UiLauncher;

/**
 * The ListenerStartNetworkClientGame class represents an action listener for the "Start Network Client Game" button in the game launcher.
 * When the button is pressed, it triggers the start of a network client game session.
 *
 * The class implements the ActionListener interface and overrides the actionPerformed method to define the action when the button is clicked.
 * It calls the startNetworkClientGame method of the associated UiLauncher object to initiate the process of joining a network game as a client.
 *
 * @see UiLauncher
 * @author Julius Hunold
 * @version 1.0
 */

public class ListenerStartNetworkClientGame implements ActionListener {

    private UiLauncher uiLauncher;

    /**
     * Constructs a new ListenerStartNetworkClientGame instance with the associated UiLauncher object.
     *
     * @param uiLauncher The UiLauncher object associated with the game launcher.
     */

    public ListenerStartNetworkClientGame (UiLauncher uiLauncher) {
        this.uiLauncher = uiLauncher;
    }

    /**
     * Invoked when the "Start Network Client Game" button is pressed.
     * It calls the startNetworkClientGame method of the associated UiLauncher object to initiate joining a network game as a client.
     *
     * @param e The ActionEvent representing the button click event.
     */
    
    @Override
    public void actionPerformed(ActionEvent e){
        this.uiLauncher.startNetworkClientGame();
    }
    
}
