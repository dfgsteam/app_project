package bauernhof.app.ui.launcher.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bauernhof.app.ui.launcher.panel.PanelLocal;

/**
 * The ListenerLocalDeleteUser class represents an action listener for the "Delete Player" button in the local game settings panel of the game launcher.
 * When the button is pressed, it triggers the removal of a player from the local game session by setting the player's information to inactive.
 *
 * The class implements the ActionListener interface and overrides the actionPerformed method to define the action when the button is clicked.
 * It calls the removePlayer method of the associated PanelLocal object to remove the specified player from the local game settings view.
 *
 * @see PanelLocal
 * @author Julius Hunold
 * @version 1.0
 */

public class ListenerLocalDeleteUser implements ActionListener {

    private PanelLocal panelLocal;
    private int playerId;

    /**
     * Constructs a new ListenerLocalDeleteUser instance with the associated PanelLocal object and playerId.
     *
     * @param panelLocal The PanelLocal object associated with the local game settings panel in the game launcher.
     * @param playerId   The ID of the player to be deleted from the local game settings view.
     */

    public ListenerLocalDeleteUser (PanelLocal panelLocal, int playerId) {
        this.panelLocal = panelLocal;
        this.playerId = playerId;
    }

    /**
     * Invoked when the "Delete Player" button is pressed.
     * It calls the removePlayer method of the associated PanelLocal object to remove the specified player from the local game settings view.
     *
     * @param e The ActionEvent representing the button click event.
     */
    
    @Override
    public void actionPerformed(ActionEvent e){
        this.panelLocal.removePlayer(playerId);
    }
    
}
