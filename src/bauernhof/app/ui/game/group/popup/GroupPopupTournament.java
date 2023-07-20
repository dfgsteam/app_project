package bauernhof.app.ui.game.group.popup;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerHomeButton;
import bauernhof.app.ui.game.listener.ListenerNextTournament;
import bauernhof.preset.Player;
import sag.LayerPosition;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;


public class GroupPopupTournament extends GGroup {

    public GroupPopupTournament(UiGame uiGame, boolean last) throws Exception {
        // Panel
        GGroup panel = uiGame.getMainPanel().addLayer(LayerPosition.CENTER);

        // Feld
        GRect area = new GRect(0f, 0f, 700f, 500f, true, 0f, 0f);
        area.setFill(new Color(255, 255, 255));
        area.setStroke(new Color(0, 0, 0), 5f);
        panel.addChild(area, 0f, 0f);

        // Überschrift
        GText headline = new GText("Auswertung");
        headline.setBold(true);
        headline.setAlignment(GText.TextAnchor.MIDDLE);
        headline.setFontSize(55f);
        panel.addChild(headline, 0f, -150f);

        // Spielerreihenfolge berechnen
        HashMap<String, Integer> playerWins = new HashMap<>();
        ArrayList<String> scores = new ArrayList<>();

        for (Player player : uiGame.getGameSystem().getPlayers()) {
            playerWins.put(player.getName(), player.getScore());
            scores.add(player.getName());
            String temp;
            for (int index = 0; index < playerWins.size() - 1; index++) {
                if (playerWins.get(scores.get(index)) < playerWins.get(scores.get(index + 1))) {
                    temp = scores.get(index);
                    scores.set(index, scores.get(index + 1));
                    scores.set(index + 1, temp);
                }
            }
        }

        // Spielerabstufung
        int position = 1;
        GText scorePlayerPosition;

        for (var item : scores) {
            scorePlayerPosition = new GText(Integer.toString(position) + ". " + item + " [" + playerWins.get(scores.get(position - 1)) + "]");
            scorePlayerPosition.setAlignment(GText.TextAnchor.MIDDLE);
            scorePlayerPosition.setFontSize((position == 1) ? 35f : 25f);
            panel.addChild(scorePlayerPosition, 0f, (-115f + 50f * position));
            position++;
        }

        // Game Button
        GRect gameButton = new GRect(0f, 0f, 450f, 75f, true, 0f, 0f);
        gameButton.setFill(new Color(144, 238, 155, 50));
        gameButton.setStroke(new Color(0, 0, 0), 5f);
        panel.addChild(gameButton, 0f, 175f);

        // Game Überschrift
        GText gameHeadline = new GText(last ? "Zurück zum Hauptmenü" : "Next Game");
        gameHeadline.setBold(true);
        gameHeadline.setAlignment(GText.TextAnchor.MIDDLE);
        gameHeadline.setFontSize(25f);
        panel.addChild(gameHeadline, 0f, 183f);

        gameButton.setMouseEventListener(last ? new ListenerHomeButton(uiGame, gameHeadline) : new ListenerNextTournament(uiGame, gameHeadline));
    }
}
