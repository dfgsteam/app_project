package bauernhof.app.ui.game.panel;

import java.awt.Color;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.listener.HomeButtonListener;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class CheaterPanel extends GGroup{
    public CheaterPanel (SAGPanel mainPanel, GameBoardState gameBoardState, AbstractGamePlayer player) throws Exception {
        // Panel
        GGroup panel = mainPanel.addLayer(LayerPosition.CENTER);

        // Feld
        GRect area = new GRect(0f, 0f, 700f, 500f, true, 0f, 0f);
        area.setFill(new Color(255, 0, 0, 70));
        area.setStroke(new Color(0, 0, 0), 5f);
        panel.addChild(area, 0f, 0f);

        // Überschrift
        GText headline = new GText("Cheater erkannt!");
        headline.setBold(true);
        headline.setAlignment(GText.TextAnchor.MIDDLE);
        headline.setFontSize(55f);
        panel.addChild(headline, 0f, -150f);

        // Spielerabstufung
        GText playerName = new GText("\u2620\uFE0F " + player.getName() + " \u2620\uFE0F");
        playerName.setAlignment(GText.TextAnchor.MIDDLE);
        playerName.setFontSize(40f);
        panel.addChild(playerName, 0f, 0f);

 
        // Hauptmenü Button
        GRect homeButton = new GRect(0f, 0f, 450f, 75f, true, 0f, 0f);
        homeButton.setFill(new Color(255, 255, 0, 70));
        homeButton.setStroke(new Color(0, 0, 0), 5f);
        panel.addChild(homeButton, 0f, 175f);

        // Überschrift
        GText homeHeadline = new GText("Zurück zum Hauptmenü");
        homeHeadline.setBold(true);
        homeHeadline.setAlignment(GText.TextAnchor.MIDDLE);
        homeHeadline.setFontSize(25f);
        panel.addChild(homeHeadline, 0f, 183f);


        homeButton.setMouseEventListener(new HomeButtonListener(gameBoardState, homeHeadline));
    }
}
