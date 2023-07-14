package bauernhof.app.ui.game.group.popup;

import java.awt.Color;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerHomeButton;
import sag.LayerPosition;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class GroupPopupCheater extends GGroup{
    public GroupPopupCheater (UiGame UiGame, AbstractGamePlayer player) throws Exception {
        // Panel
        GGroup panel = UiGame.getMainPanel().addLayer(LayerPosition.CENTER);

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

        // Cheaternamen
        GText playerName = new GText("\u2620\uFE0F " + player.getName() + " \u2620\uFE0F");
        playerName.setAlignment(GText.TextAnchor.MIDDLE);
        playerName.setFontSize(40f);
        panel.addChild(playerName, 0f, 0f);
 
        // Hauptmenü Button
        GRect homeButton = new GRect(0f, 0f, 450f, 75f, true, 0f, 0f);
        homeButton.setFill(new Color(255, 255, 0, 70));
        homeButton.setStroke(new Color(0, 0, 0), 5f);
        panel.addChild(homeButton, 0f, 175f);

        // Hauptmenü Überschrift
        GText homeHeadline = new GText("Zurück zum Hauptmenü");
        homeHeadline.setBold(true);
        homeHeadline.setAlignment(GText.TextAnchor.MIDDLE);
        homeHeadline.setFontSize(25f);
        panel.addChild(homeHeadline, 0f, 183f);

        homeButton.setMouseEventListener(new ListenerHomeButton(UiGame.getGameBoardState(), homeHeadline));
    }
}
