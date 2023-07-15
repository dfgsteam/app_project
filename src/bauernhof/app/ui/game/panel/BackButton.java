package bauernhof.app.ui.game.panel;

import java.awt.Color;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerBackButton;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class BackButton extends GGroup{

    
    UiGame gameBoard;

    public BackButton (SAGPanel panel, UiGame gameBoard) throws Exception {
        // Klassenvariablen erstellen
        this.gameBoard = gameBoard;

        // Panel
        GGroup bot = panel.addLayer(LayerPosition.BOTTOM_RIGHT);

        // Feld
        GRect area = new GRect(0f, 0f, 140f, 35f, true, 0f, 0f);
        area.setFill(new Color(255, 255, 255, 255));
        area.setStroke(new Color(0, 0, 0), 2f);
        bot.addChild(area, -90f, -80f);

        // Zähler
        GText headline = new GText("Zurück");
        headline.setAlignment(GText.TextAnchor.MIDDLE);
        headline.setFontSize(15f);
        bot.addChild(headline, -90f, -73f);


        area.setMouseEventListener(new ListenerBackButton(this.gameBoard,new GText("Zurück")));
    }
}

