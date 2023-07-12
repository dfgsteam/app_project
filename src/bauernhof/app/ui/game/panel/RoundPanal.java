package bauernhof.app.ui.game.panel;

import java.awt.Color;

import bauernhof.app.launcher.GameBoardState;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class RoundPanal extends GGroup{

    GText headline = new GText("R. 1");
    GameBoardState gameBoardState;

    public RoundPanal (SAGPanel mainPanel, GameBoardState gameBoardState) throws Exception {
        // Klassenvariablen erstellen
        this.gameBoardState = gameBoardState;

        // Panel
        GGroup panel = mainPanel.addLayer(LayerPosition.TOP_RIGHT);

        // Feld
        GRect area = new GRect(0f, 0f, 80f, 50f, false, 0f, 0f);
        area.setFill(new Color(255, 255, 255, 255));
        area.setStroke(new Color(0, 0, 0), 3f);
        panel.addChild(area, -90f, 10f);

        // Zähler
        this.headline.setAlignment(GText.TextAnchor.END);
        this.headline.setFontSize(25f);
        panel.addChild(this.headline, -21f, 45f);
        System.out.println("ok");
    }

    public void update() {
        //System.out.println("ok2");
        //System.out.println(this.headline);
        //System.out.println(this.gameBoardState.getRound());
        if (this.headline != null)
            this.headline.setText("R. " + Integer.toString(this.gameBoardState.getRound()));
    }

}
