package bauernhof.app.ui.game.group.button;

import java.awt.Color;

import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerSaveGame;
import sag.LayerPosition;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

/**
 * This class represents a button panel for saving the game in the game UI.
 * It extends the GGroup class from the sag.elements package.
 * The panel contains a rectangular button with text and an event listener for saving the game.
 * The class is designed to be used within the UiGame class.

 * @author Julius Hunold
 * @version 1.0
 * @since 2023-07-14
 */

public class PanelButtonSaveGame extends GGroup {

/**
 * Constructs a new PanelButtonSaveGame object.
 * The object represents a button panel for saving the game in the game UI.
 * 
 * @param UiGame The UiGame object that represents the game UI.
 * @throws Exception If an error occurs during initialization.
 */
    public PanelButtonSaveGame(UiGame UiGame) throws Exception {
        GGroup panel = UiGame.getMainPanel().addLayer(LayerPosition.BOTTOM_LEFT);

        // Feld (Button)
        GRect area = new GRect(0f, 0f, 140f, 35f, true, 0f, 0f);
        area.setFill(new Color(255, 255, 255, 255));
        area.setStroke(new Color(0, 0, 0), 2f);
        panel.addChild(area, 90f, -40f);

        // Button text

        GText headline = new GText("Spiel sichern");
        headline.setAlignment(GText.TextAnchor.MIDDLE);
        headline.setFontSize(15f);
        panel.addChild(headline, 90f, -33f);

        // Eventlistener
        area.setMouseEventListener(new ListenerSaveGame(UiGame, headline));
    }
}
