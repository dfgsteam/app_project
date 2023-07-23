package bauernhof.app.ui.game.group.popup;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import bauernhof.app.system.GameBoard;
import bauernhof.app.system.Tournament;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerHomeButton;
import bauernhof.app.ui.game.listener.ListenerNextTournament;
import bauernhof.preset.Settings;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;


public class GroupPopupTournament extends GGroup {
    private ArrayList<Integer> winScores = new ArrayList<>();
    private GRect area, gameButton;
    private GText headline, gameHeadline, scorePlayerPosition;
    private Tournament tournament;
    public GroupPopupTournament(Tournament tournament, Settings settings) {
        this.tournament = tournament;
        for (final String name : settings.playerNames)
            winScores.add(0);
        winScores.add(0);

    }
    public void popupTournamentScore(final boolean last) throws ChildNotFoundException {

        area = new GRect(0f, 0f, 700f, 500f, true, 0f, 0f);
        area.setFill(new Color(255, 255, 255));
        area.setStroke(new Color(0, 0, 0), 5f);
        headline = new GText("Auswertung");
        headline.setBold(true);
        headline.setAlignment(GText.TextAnchor.MIDDLE);
        headline.setFontSize(55f);
        gameButton = new GRect(0f, 0f, 450f, 75f, true, 0f, 0f);
        gameButton.setFill(new Color(144, 238, 155, 50));
        gameButton.setStroke(new Color(0, 0, 0), 5f);
        UiGame uiGame = GameBoard.getGraphics();
        GGroup panel = uiGame.getMainPanel().addLayer(LayerPosition.CENTER);
        for (int i = 0; i < panel.getNumChildren(); i++) {
            System.out.println("CHILDS");
            panel.removeChild(panel.getChildByRenderingIndex(i));
        }
        panel.addChild(area, 0f, 0f);
        panel.addChild(headline, 0f, -150f);

        // Spielerreihenfolge berechnen
        /*HashMap<String, Integer> playerWins = new HashMap<>();
        ArrayList<String> scores = new ArrayList<>();
        for (Player player : uiGame.getGame().getPlayers()) {
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
        } */
        winScores.set(uiGame.getGame().getWinnerID(), winScores.get(uiGame.getGame().getWinnerID()) + 1);
        ArrayList<Integer> winsSorted = new ArrayList<Integer>(winScores);
        Collections.sort(winsSorted, Collections.reverseOrder());
        
        // ArrayList<Integer> scorescopy = (ArrayList<Integer>) scores.clone();
        // Collections.sort(scorescopy);
        // final ArrayList<Integer> positions = new ArrayList<>();
        // int position = 1;
        // for (int i = scorescopy.size() - 1; i >= 0; i--) {
        //     int score = scorescopy.get(i);
        //     for (int x = 0; x < uiGame.getGame().getNumPlayers(); x++) {
        //         if (score == scores.get(x) && !positions.contains(x)) {
        //             scorePlayerPosition = new GText(position + ". " + uiGame.getGame().getSettings().playerNames.get(x) + " Wins " + scores.get(x) + "   [" + uiGame.getGame().getAllScores().get(x) + "]");
        //             scorePlayerPosition.setAlignment(GText.TextAnchor.MIDDLE);
        //             scorePlayerPosition.setFontSize((position == 1) ? 35f : 25f);
        //             if (x == uiGame.getGame().getWinnerID()) scorePlayerPosition.setFill(Color.RED);
        //             panel.addChild(scorePlayerPosition, 0f, (-115f + 50f * position));
        //             positions.add(x);
        //             position++;
        //         }
        //     }
        // }
        // Game Button
        panel.addChild(gameButton, 0f, 175f);
        // Game Überschrift

        gameHeadline = new GText(last ? "Zurück zum Hauptmenü" : "Next Game");
        gameHeadline.setBold(true);
        gameHeadline.setAlignment(GText.TextAnchor.MIDDLE);
        gameHeadline.setFontSize(25f);
        panel.addChild(gameHeadline, 0f, 183f);
        gameButton.setMouseEventListener(last ? new ListenerHomeButton(uiGame, gameHeadline) : new ListenerNextTournament(tournament, uiGame, gameHeadline));
    }
}
