package bauernhof.app.ui.game.group.display;

import java.awt.Color;

import bauernhof.app.launcher.GameBoardState;
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
 * @author [Your Name]
 * @version 1.0
 * @since 2023-07-14
 */

public class GroupDisplayPlayerName extends GGroup {

    private GRect groupPlayerBg[] = new GRect[4];
    private GText groupPlayerName[] = new GText[4];

    private GameBoardState gameBoardState;

    private Color colorActive = new Color(144, 238, 144);
    private Color colorInactive = new Color(255, 255, 255);
    private Color colorStroke = new Color(100, 100, 100);

    /**
     * Constructs a new GroupDisplayPlayerName object.
     * The object represents a group for displaying player names in the game UI.
     *
     * @param UiGame The UiGame object that represents the game UI.
     * @throws Exception If an error occurs during initialization.
     */
    public GroupDisplayPlayerName(UiGame UiGame) throws Exception {
        this.gameBoardState = UiGame.getGameBoardState();
        GGroup panel;
        GRect area = new GRect(0f, 0f, 0f, 0f, false);
        GText text = new GText(null);

        // Create player panels at the appropriate positions for each player
        for (int counter = 0; counter < this.gameBoardState.getPlayers().length; counter++) {
            switch (counter) {
                case 0: {
                    // Create panel (with starting position)
                    panel = UiGame.getMainPanel().addLayer(LayerPosition.BOTTOM_CENTER);

                    // playerBg
                    area = new GRect(0f, 0f, 400f, 50f, true, 0f, 0f);
                    area.setFill(this.colorActive);
                    area.setStroke(this.colorStroke, 2f);
                    panel.addChild(area, 0f, -210f);

                    // playerName
                    text = new GText(this.gameBoardState.getPlayers()[counter].getName() + " - " +
                            Integer.toString(this.gameBoardState.getPlayers()[counter].getScore()));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    panel.addChild(text, 0f, -200f);

                    break;
                }
                case 1: {
                    // Create panel (with starting position)
                    panel = UiGame.getMainPanel().addLayer(LayerPosition.CENTER_LEFT);

                    // playerBg
                    area = new GRect(0f, 0f, 50f, 400f, true, 0f, 0f);
                    area.setFill(this.colorInactive);
                    area.setStroke(this.colorStroke, 2f);
                    panel.addChild(area, 280f, 0f);

                    // playerName
                    text = new GText(this.gameBoardState.getPlayers()[counter].getName() + " - " +
                            Integer.toString(this.gameBoardState.getPlayers()[counter].getScore()));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    text.rotate(-90f);
                    panel.addChild(text, 290f, 0f);

                    break;
                }
                case 2: {
                    // Create panel (with starting position)
                    panel = UiGame.getMainPanel().addLayer(LayerPosition.TOP_CENTER);

                    // playerBg
                    area = new GRect(0f, 0f, 400f, 50f, true, 0f, 0f);
                    area.setFill(this.colorInactive);
                    area.setStroke(this.colorStroke, 2f);
                    panel.addChild(area, 0f, 210f);

                    // playerName
                    text = new GText(this.gameBoardState.getPlayers()[counter].getName() + " - " +
                            Integer.toString(this.gameBoardState.getPlayers()[counter].getScore()));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    panel.addChild(text, 0f, 220f);

                    break;
                }
                case 3: {
                    // Create panel (with starting position)
                    panel = UiGame.getMainPanel().addLayer(LayerPosition.CENTER_RIGHT);

                    // playerBg
                    area = new GRect(0f, 0f, 50f, 400f, true, 0f, 0f);
                    area.setFill(this.colorInactive);
                    area.setStroke(this.colorStroke, 2f);
                    panel.addChild(area, -280f, 0f);

                    // playerName
                    text = new GText(this.gameBoardState.getPlayers()[counter].getName() + " - " +
                            Integer.toString(this.gameBoardState.getPlayers()[counter].getScore()));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    text.rotate(90f);
                    panel.addChild(text, -290f, 0f);

                    break;
                }
            }
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
        this.groupPlayerName[playerId].setText(this.gameBoardState.getPlayers()[playerId].getName() + " - " +
                Integer.toString(this.gameBoardState.getPlayers()[playerId].getScore()));
    }

    /**
     * Updates the background color of the player panel to indicate an inactive player.
     *
     * @param playerId The ID of the player whose panel background color should be updated.
     */
    public void updatePlayerBgInactive(int playerId) {
        this.groupPlayerBg[playerId].setFill(this.colorInactive);
    }

    /**
     * Updates the background color of the player panel to indicate an active player.
     *
     * @param playerId The ID of the player whose panel background color should be updated.
     */
    public void updatePlayerBgActive(int playerId) {
        this.groupPlayerBg[playerId].setFill(this.colorActive);
    }
}
