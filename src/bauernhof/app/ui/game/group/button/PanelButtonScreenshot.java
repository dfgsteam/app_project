package bauernhof.app.ui.game.group.button;

import java.awt.Color;

import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerScreenshot;
import sag.LayerPosition;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

/**

 * The {@code PanelButtonScreenshot} class represents a button panel for taking screenshots in the game UI.
 * It extends the {@code GGroup} class from the {@code sag.elements} package.
 * The panel contains a rectangular button with text and an event listener for capturing screenshots.

 * @author Julius Hunold
 * @version 1.0
*/

public class PanelButtonScreenshot extends GGroup {
/**
 * Constructs a new {@code PanelButtonScreenshot} object.
 * The object represents a button panel for taking screenshots in the game UI.
 * 
 * @param UiGame The {@code UiGame} object that represents the game UI.
 * @throws Exception If an error occurs during initialization.
 */
public PanelButtonScreenshot(UiGame UiGame) throws Exception {
    // Panel
    GGroup panel = UiGame.getMainPanel().addLayer(LayerPosition.BOTTOM_RIGHT);

    // Feld (Button)
    GRect area = new GRect(0f, 0f, 140f, 35f, true, 0f, 0f);
    area.setFill(new Color(255, 255, 255, 255));
    area.setStroke(new Color(0, 0, 0), 2f);
    panel.addChild(area, -90f, -40f);

    // Button text
    GText headline = new GText("+ Screenshot");
    headline.setAlignment(GText.TextAnchor.MIDDLE);
    headline.setFontSize(15f);
    panel.addChild(headline, -90f, -33f);

    // Eventlistener
    area.setMouseEventListener(new ListenerScreenshot(UiGame, headline));
  }
}
