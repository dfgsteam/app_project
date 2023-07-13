package bauernhof.app.ui.game.panel;

import java.awt.Color;

import bauernhof.app.ui.game.GameBoard;
import bauernhof.app.ui.game.listener.ScreenshotListener;
import sag.LayerPosition;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class SreenshotPanal extends GGroup{
    public SreenshotPanal (GameBoard gameBoard) throws Exception {
        // Panel
        GGroup panel = gameBoard.getMainPanel().addLayer(LayerPosition.BOTTOM_RIGHT);

        // Feld (Button)
        GRect area = new GRect(0f, 0f, 140f, 35f, true, 0f, 0f);
        area.setFill(new Color(255, 255, 255, 255));
        area.setStroke(new Color(0, 0, 0), 2f);
        panel.addChild(area, -90f, -40f);

        // Button-Schrift
        GText headline = new GText("+ Screenshot");
        headline.setAlignment(GText.TextAnchor.MIDDLE);
        headline.setFontSize(15f);
        panel.addChild(headline, -90f, -33f);

        // Eventlistener
        area.setMouseEventListener(new ScreenshotListener(gameBoard, headline));
    }
}
