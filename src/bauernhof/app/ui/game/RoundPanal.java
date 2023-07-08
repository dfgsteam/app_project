package bauernhof.app.ui.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.listener.HomeButtonListener;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class RoundPanal extends GGroup{

    GText headline;
    GameBoardState gameBoardState;

    public RoundPanal (SAGPanel mainPanel, GameBoardState gameBoardState) throws Exception {
        // Klassenvariablen erstellen
        this.gameBoardState = gameBoardState;

        // Panel
        GGroup panel = mainPanel.addLayer(LayerPosition.TOP_RIGHT);

        // Feld
        GRect area = new GRect(0f, 0f, 50f, 20f, true, 0f, 0f);
        area.setFill(new Color(255, 255, 255));
        area.setStroke(new Color(0, 0, 0), 2f);
        panel.addChild(area, 0f, 0f);

        // Zähler
        this.headline = new GText(Integer.toString(this.gameBoardState.getRound()));
        headline.setBold(true);
        headline.setAlignment(GText.TextAnchor.MIDDLE);
        headline.setFontSize(55f);
        panel.addChild(headline, 0f, -150f);
    }

    public void update() {
        this.headline.setText(Integer.toString(this.gameBoardState.getRound()));
    }

}
