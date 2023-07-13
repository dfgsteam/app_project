package bauernhof.app.ui.game.panel;

import java.awt.Color;

import bauernhof.app.ui.game.GameBoard;
import bauernhof.app.ui.game.listener.SaveGameListener;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class SaveGamePanel extends GGroup{

    public SaveGamePanel (GameBoard gameBoard) throws Exception {
        // Panel
        GGroup panel = gameBoard.getMainPanel().addLayer(LayerPosition.BOTTOM_LEFT);

        // Feld (Button)
        GRect area = new GRect(0f, 0f, 140f, 35f, true, 0f, 0f);
        area.setFill(new Color(255, 255, 255, 255));
        area.setStroke(new Color(0, 0, 0), 2f);
        panel.addChild(area, 90f, -40f);

        // Button-Schrift
        GText headline = new GText("Spiel sichern");
        headline.setAlignment(GText.TextAnchor.MIDDLE);
        headline.setFontSize(15f);
        panel.addChild(headline, 90f, -33f);

        // Eventlistener
        area.setMouseEventListener(new SaveGameListener(gameBoard, headline));
    }
}
