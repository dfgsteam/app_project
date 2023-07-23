package bauernhof.app.ui.game.group.popup;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerHomeButton;
import bauernhof.preset.Player;
import sag.LayerPosition;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

/**
 * This class represents a popup window for displaying game scores at the end of the game.
 * It extends the GGroup class from the sag.elements package.
 * The popup window contains the scores of each player in the game and a button to return to the main menu.
 * The class is designed to be used within the UiGame class.
 *
 * The GroupPopupScore class provides the following functionality:
 * - Creates a popup window for displaying game scores.
 * - The popup window is added to the main panel of the UiGame instance.
 * - The popup window contains a rectangular area with a title, player scores, and a return to the main menu button.
 * - The player scores are calculated based on the game board state and player scores.
 * - The player scores are displayed in descending order.
 * - Each player's score is shown with their position and name.
 * - The return to the main menu button allows the player to navigate back to the main menu.
 *
 * @author Julius Hunold
 * @version 1.0
 * @since 2023-07-14
 */

public class GroupPopupScore extends GGroup {

    /**
     * Constructs a new GroupPopupScore object.
     * The object represents a popup window for displaying game scores at the end of the game.
     *
     * @param uiGame The UiGame object that represents the game UI.
     * @throws Exception If an error occurs during initialization.
     */
    public GroupPopupScore(UiGame uiGame) throws Exception {
        // Panel
        GGroup panel = uiGame.getMainPanel().addLayer(LayerPosition.CENTER);

        // Feld
        GRect area = new GRect(0f, 0f, 700f, 500f, true, 0f, 0f);
        area.setFill(new Color(255, 255, 255));
        area.setStroke(new Color(0, 0, 0), 5f);
        panel.addChild(area, 0f, 0f);

        // Überschrift
        GText headline = new GText("Spielende");
        headline.setBold(true);
        headline.setAlignment(GText.TextAnchor.MIDDLE);
        headline.setFontSize(55f);
        panel.addChild(headline, 0f, -150f);

        // Spielerreihenfolge berechnen
        ArrayList<String> same_score;
        TreeMap<Integer, ArrayList<String>> playerScores = new TreeMap<>();
        int index_of_player = 0;
        for (Integer score : uiGame.getGame().getAllScores()) { 
            if (playerScores.containsKey(score)) { playerScores.get(score).add(uiGame.getGame().getSettings().playerNames.get(index_of_player)); }
            else {
                same_score = new ArrayList<String>();
                same_score.add(uiGame.getGame().getSettings().playerNames.get(index_of_player));
                playerScores.put(score, same_score); 
            }
            index_of_player++;
        }
        ArrayList<Integer> sortedScores = new ArrayList<>(uiGame.getGame().getAllScores());
        Collections.sort(sortedScores, Collections.reverseOrder());

        // Spielerabstufung
        int position = 1;
        GText scorePlayerPosition;

        for (Integer item : sortedScores) {
            for (String name : playerScores.get(item)) {
            scorePlayerPosition = new GText(Integer.toString(position) + ". " + name + " [" + item + "]");
            scorePlayerPosition.setAlignment(GText.TextAnchor.MIDDLE);
            scorePlayerPosition.setFontSize((position == 1) ? 35f : 25f);
            panel.addChild(scorePlayerPosition, 0f, (-115f + 50f * position));
            position++;
            }
        }

        // Hauptmenü Button
        GRect homeButton = new GRect(0f, 0f, 450f, 75f, true, 0f, 0f);
        homeButton.setFill(new Color(144, 238, 155, 50));
        homeButton.setStroke(new Color(0, 0, 0), 5f);
        panel.addChild(homeButton, 0f, 175f);

        // Hauptmenü Überschrift
        GText homeHeadline = new GText("Zurück zum Hauptmenü");
        homeHeadline.setBold(true);
        homeHeadline.setAlignment(GText.TextAnchor.MIDDLE);
        homeHeadline.setFontSize(25f);
        panel.addChild(homeHeadline, 0f, 183f);

        homeButton.setMouseEventListener(new ListenerHomeButton(uiGame, homeHeadline));
    }
}
