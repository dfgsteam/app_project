package bauernhof.app.ui.launcher.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bauernhof.app.ui.launcher.panel.PanelLocal;

/**
 * The ListenerStartLocalGame class represents an action listener for the "Start Local Game" button in the game launcher.
 * When the button is pressed, it triggers the start of a local game session with the selected players and settings.
 *
 * The class implements the ActionListener interface and overrides the actionPerformed method to define the action when the button is clicked.
 * It calls the readAllPlayer method of the associated PanelLocal object to read and gather player information and start the local game accordingly.
 *
 * @see PanelLocal
 * @author Julius Hunold
 * @version 1.0
 */

public class ListenerStartLocalGame implements ActionListener {

    private PanelLocal panelLocal;

    /**
     * Constructs a new ListenerStartLocalGame instance with the associated PanelLocal object.
     *
     * @param panelLocal The PanelLocal object associated with the local game settings panel in the game launcher.
     */

    public ListenerStartLocalGame (PanelLocal panelLocal) {
        this.panelLocal = panelLocal;
    }

    /**
     * Invoked when the "Start Local Game" button is pressed.
     * It calls the readAllPlayer method of the associated PanelLocal object to gather player information and start the local game accordingly.
     *
     * @param e The ActionEvent representing the button click event.
     */
    
    @Override
    public void actionPerformed(ActionEvent e){
        this.panelLocal.readAllPlayer();
    }
    
}
