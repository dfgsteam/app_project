package bauernhof.app.ui.launcher.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import bauernhof.app.ui.launcher.panel.PanelLocal;

/**
 * The ListenerLocalAddUser class represents an item listener for the player type selection dropdown in the local game settings panel of the game launcher.
 * When a player type is selected from the dropdown, this listener detects the change in selection.
 * If the selected player type is set to "Keiner" (None), it triggers the addition of a new player to the local game session by enabling the corresponding player input fields.
 *
 * The class implements the ItemListener interface and overrides the itemStateChanged method to define the action when the player type selection is changed.
 * If the selected player type is "Keiner", it calls the addPlayer method of the associated PanelLocal object to add a new player to the local game settings view.
 *
 * @see PanelLocal
 * @author Julius Hunold
 * @version 1.0
 */

public class ListenerLocalAddUser implements ItemListener {


    private PanelLocal panelLocal;
    private int playerId;

    /**
     * Constructs a new ListenerLocalAddUser instance with the associated PanelLocal object and playerId.
     *
     * @param panelLocal The PanelLocal object associated with the local game settings panel in the game launcher.
     * @param playerId   The ID of the player to be added to the local game settings view.
     */

    public ListenerLocalAddUser (PanelLocal panelLocal, int playerId) {
        this.panelLocal = panelLocal;
        this.playerId = playerId;
    }

    /**
     * Invoked when the player type selection is changed in the dropdown.
     * If the selected player type is "Keiner", it calls the addPlayer method of the associated PanelLocal object to add a new player to the local game settings view.
     *
     * @param e The ItemEvent representing the change in player type selection.
     */
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED && e.getItem().toString() == "Keiner") {
            this.panelLocal.addPlayer(this.playerId);
        }
    }
    
}
