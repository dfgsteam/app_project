package bauernhof.app.ui.launcher.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bauernhof.app.ui.launcher.UiLauncher;

/**
 * The ListenerStartNetworkHostGame class represents an action listener for the "Start Network Host Game" button in the game launcher.
 * When the button is pressed, it triggers the start of a network-hosted game session.
 *
 * The class implements the ActionListener interface and overrides the actionPerformed method to define the action when the button is clicked.
 * It calls the startNetworkHostGame method of the associated UiLauncher object to initiate the process of hosting a network game.
 *
 * @see UiLauncher
 * @author Julius Hunold
 * @version 1.0
 */

public class ListenerStartNetworkHostGame implements ActionListener {

    private UiLauncher uiLauncher;

    /**
     * Constructs a new ListenerStartNetworkHostGame instance with the associated UiLauncher object.
     *
     * @param uiLauncher The UiLauncher object associated with the game launcher.
     */

    public ListenerStartNetworkHostGame (UiLauncher uiLauncher) {
        this.uiLauncher = uiLauncher;
    }

    /**
     * Invoked when the "Start Network Host Game" button is pressed.
     * It calls the startNetworkHostGame method of the associated UiLauncher object to initiate hosting a network game.
     *
     * @param e The ActionEvent representing the button click event.
     */
    
    @Override
    public void actionPerformed(ActionEvent e){
        this.uiLauncher.startNetworkHostGame();
    }
    
}
