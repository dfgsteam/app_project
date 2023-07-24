package bauernhof.app.ui.game.group.display;

import java.awt.Color;

import bauernhof.app.system.Game;
import bauernhof.app.ui.game.UiGame;
import sag.LayerPosition;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

/**
 * This class represents a group for displaying player names in the game UI.
 * It extends the GGroup class from the sag.elements package.
 * The group consists of multiple player panels, each displaying a player's name and score.
 * The appearance of the panels can be updated to indicate active or inactive players.
 * The class is designed to be used within the UiGame class.
 *
 * The GroupDisplayPlayerName class provides the following functionality:
 * - Creates player panels for displaying player names and scores.
 * - The player panels are added to the main panel of the UiGame instance.
 * - The appearance of the player panels can be updated to indicate active or inactive players.
 * - The player panels are positioned at different locations on the game board.
 * - The player panels are initially populated with player names and scores from the game state.
 * - The player names and scores can be updated dynamically using the provided update methods.
 *
 * @author Julius Hunold
 * @version 1.0
 * @since 2023-07-14
 */

public class GroupDisplayPlayerName extends GGroup {

    private GRect groupPlayerBg[] = new GRect[4];
    private GText groupPlayerName[] = new GText[4];

    private Game game;

    private Color colorActive = new Color(144, 238, 144);
    private Color colorInactive = new Color(250, 250, 250);

    /**
     * Constructs a new GroupDisplayPlayerName object.
     * The object represents a group for displaying player names in the game UI.
     *
     * @param uiGame The UiGame object that represents the game UI.
     * @throws Exception If an error occurs during initialization.
     */
    public GroupDisplayPlayerName(UiGame uiGame) throws Exception {
        this.game = uiGame.getGame();
        GGroup panel;
        GRect area = new GRect(0f, 0f, 0f, 0f, false);
        GText text = new GText(null);
        // Erstelle für den jeweiligen Spieler das Panel an der richtigen Position 
        for (int counter = 0; counter<this.game.getNumPlayers(); counter++) {
            switch (counter) {
                case 0: {
                    // panel erzeugen (mit Startpunkt)
                    panel = uiGame.getMainPanel().addLayer(LayerPosition.BOTTOM_CENTER); 

                    // playerBg
                    area = new GRect(0f, 0f, 400f, 50f, true, 0f, 0f);
                    area.setStroke(this.colorActive, 3f);
                    panel.addChild(area, 0f, -210f);

                    // playerName
                    text = new GText(this.game.getName(counter) + " - " + this.game.getScore(counter));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    panel.addChild(text, 0f, -200f);
                    break;
                }
                case 1: {
                    // creates panel (with startpoint)
                    panel = uiGame.getMainPanel().addLayer(LayerPosition.CENTER_LEFT); 
                    // playerBg
                    area = new GRect(0f, 0f, 50f, 400f, true, 0f, 0f);
                    area.setStroke(this.colorInactive, 2f);
                    panel.addChild(area, 280f, 0f);

                    // playerName
                    text = new GText(this.game.getName(counter) + " - " + this.game.getScore(counter));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    text.rotate(-90f);
                    panel.addChild(text, 290f, 0f);

                    break;
                }
                case 2: {
                    // creates panel (with startpoint)
                    panel = uiGame.getMainPanel().addLayer(LayerPosition.TOP_CENTER); 
                  
                    // playerBg
                    area = new GRect(0f, 0f, 400f, 50f, true, 0f, 0f);
                    area.setStroke(this.colorInactive, 2f);
                    panel.addChild(area, 0f, 210f);

                    // playerName
                    text = new GText(this.game.getName(counter) + " - " + this.game.getScore(counter));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    panel.addChild(text, 0f, 220f);

                    break;
                }
                case 3: {
                    // creates panel (with startpoint)
                    panel = uiGame.getMainPanel().addLayer(LayerPosition.CENTER_RIGHT); 

                    // playerBg
                    area = new GRect(0f, 0f, 50f, 400f, true, 0f, 0f);
                    area.setStroke(this.colorInactive, 2f);
                    panel.addChild(area, -280f, 0f);

                    // playerName
                    text = new GText(this.game.getName(counter) + " - " + this.game.getScore(counter));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    text.rotate(90f);
                    panel.addChild(text, -290f, 0f);

                    break;
                }
            }
            area.setFill(this.game.getSettings().playerColors.get(counter));
            text.setStroke(this.colorInactive, 0.7f);

            this.groupPlayerBg[counter] = area;
            this.groupPlayerName[counter] = text;
        }
    }

    /**
     * Updates the player name and score displayed in the player panel.
     *
     * @param playerId The ID of the player whose name and score should be updated.
     * @throws Exception If an error occurs during the update process.
     */
    public void updatePlayerName(int playerId) throws Exception {
        this.groupPlayerName[playerId].setText(this.game.getName(playerId) + " - " + Integer.toString(this.game.getScore(playerId)));
    }

    /**
     * Updates the background color of the player panel to indicate an inactive player.
     *
     * @param playerId The ID of the player whose panel background color should be updated.
     */
    public void updatePlayerBgInactive(int playerId) {
        this.groupPlayerBg[playerId].setStroke(this.colorInactive, 5f);
    }

    /**
     * Updates the background color of the player panel to indicate an active player.
     *
     * @param playerId The ID of the player whose panel background color should be updated.
     */
    public void updatePlayerBgActive(int playerId) {
        this.groupPlayerBg[playerId].setStroke(this.colorActive, 10f);
    }
}
