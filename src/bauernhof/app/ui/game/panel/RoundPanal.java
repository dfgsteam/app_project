package bauernhof.app.ui.game.panel;

import java.awt.Color;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.ui.game.GameBoard;
import sag.LayerPosition;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class RoundPanal extends GGroup{

    GText roundCounter = new GText("1");

    GameBoardState gameBoardState;

    public RoundPanal (GameBoard gameBoard) throws Exception {
        // Klassenvariablen erstellen
        this.gameBoardState = gameBoard.getGameBoardState();

        // Panel
        GGroup panel = gameBoard.getMainPanel().addLayer(LayerPosition.CENTER);

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

    public void update() {
        if (this.roundCounter != null)
            this.roundCounter.setText(Integer.toString(this.gameBoardState.getRound()));
    }

}
