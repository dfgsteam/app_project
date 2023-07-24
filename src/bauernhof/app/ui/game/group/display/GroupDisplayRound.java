package bauernhof.app.ui.game.group.display;

import java.awt.Color;

import bauernhof.app.system.Game;
import bauernhof.app.ui.game.UiGame;
import sag.LayerPosition;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

/**
 * This class represents a display group for the round counter in the game UI.
 * It extends the GGroup class from the sag.elements package.
 * The group contains a rectangular area with a headline and a round counter text.
 * The class is designed to be used within the UiGame class.
 * 
 * The GroupDisplayRound class provides the following functionality:
 * - Creates a display group for the round counter in the game UI.
 * - The group is added to the main panel of the UiGame instance.
 * - The group contains a rectangular area with visual styling.
 * - The group has a headline text indicating the purpose of the display.
 * - The group has a round counter text that shows the current round number.
 * - The round counter is updated dynamically based on the game state.
 * 
 * @author Julius Hunold
 * @version 1.0
 * @since 2023-07-14
 */

public class GroupDisplayRound extends GGroup {

    private GText roundCounter = new GText("0");
    private Game game;

    /**
     * Constructs a new GroupDisplayRound object.
     * The object represents a display group for the round counter in the game UI.
     * 
     * @param uiGame The UiGame object that represents the game UI.
     * @throws Exception If an error occurs during initialization.
     */
    public GroupDisplayRound(UiGame uiGame) throws Exception {
        // Initialize class variables
        this.game = uiGame.getGame();

        // Panel
        GGroup panel = uiGame.getMainPanel().addLayer(LayerPosition.CENTER);

        // Feld
        GRect area = new GRect(0f, 0f, 110f, 90f, true, 0f, 0f);
        area.setFill(new Color(255, 255, 255, 255));
        area.setStroke(new Color(0, 0, 0), 3f);
        panel.addChild(area, 0f, -10f);

        // Überschrift
        GText headline = new GText("Runde");
        headline.setAlignment(GText.TextAnchor.MIDDLE);
        headline.setFontSize(25f);
        panel.addChild(headline, 0f, -15f);

        // Rundenzähler
        this.roundCounter.setAlignment(GText.TextAnchor.MIDDLE);
        this.roundCounter.setFontSize(25f);
        panel.addChild(this.roundCounter, 0f, 15f);
    }

    /**
     * Updates the round counter based on the current game state.
     */
    public void update() {
        if (this.roundCounter != null) {
            this.roundCounter.setText(Integer.toString(this.game.getRound()));
        }
    }
}
