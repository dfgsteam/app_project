package bauernhof.app.ui.game.panel;

import java.awt.Color;

import bauernhof.app.ui.game.GameBoard;
import bauernhof.app.ui.game.listener.ScreenshotListener;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class SreenshotPanal extends GGroup{

    
    GameBoard gameBoard;

    public SreenshotPanal (SAGPanel mainPanel, GameBoard gameBoard) throws Exception {
        // Klassenvariablen erstellen
        this.gameBoard = gameBoard;

        // Panel
        GGroup panel = mainPanel.addLayer(LayerPosition.BOTTOM_RIGHT);

        // Feld
        GRect area = new GRect(0f, 0f, 140f, 35f, true, 0f, 0f);
        area.setFill(new Color(255, 255, 255, 255));
        area.setStroke(new Color(0, 0, 0), 2f);
        panel.addChild(area, -90f, -40f);

        // Zähler
        GText headline = new GText("+ Screenshot");
        headline.setAlignment(GText.TextAnchor.MIDDLE);
        headline.setFontSize(15f);
        panel.addChild(headline, -90f, -33f);


        area.setMouseEventListener(new ScreenshotListener(this.gameBoard, headline));
    }
}
