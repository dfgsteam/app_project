package bauernhof.app.ui.game.panel;

import java.awt.Color;

import bauernhof.app.launcher.GameBoardState;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class PlayerNamePanel extends GGroup{

    private GRect groupPlayerBg[] = new GRect[4];
    private GText groupPlayerName[] = new GText[4];

    private GameBoardState gameBoardState;

    private Color colorActive = new Color(144,238,144);
    private Color colorInactive = new Color(255, 255, 255);
    private Color colorStroke = new Color(100, 100, 100);

    public PlayerNamePanel (SAGPanel mainPanel, GameBoardState gameBoardState) throws Exception {
        this.gameBoardState = gameBoardState;
        GGroup panel;
        GRect area = new GRect(0f, 0f, 0f, 0f, false);
        GText text = new GText(null);
        for (int counter=0; counter<this.gameBoardState.getPlayers().length; counter++) {
            switch (counter) {
                case 0: {
                    // panel erzeugen (mit Startpunkt)
                    panel = mainPanel.addLayer(LayerPosition.BOTTOM_CENTER); 

                    // playerBg
                    area = new GRect(0f, 0f, 400f, 50f, true, 0f, 0f);
                    area.setFill(this.colorActive);
                    area.setStroke(this.colorStroke, 2f);
                    panel.addChild(area, 0f, -210f);

                    // playerName
                    text = new GText(this.gameBoardState.getPlayers()[counter].getName() + " - " + Integer.toString(this.gameBoardState.getPlayers()[counter].getScore()));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    panel.addChild(text, 0f, -200f);

                    break;
                }
                case 1: {
                    // panel erzeugen (mit Startpunkt)
                    panel = mainPanel.addLayer(LayerPosition.CENTER_LEFT); 

                    // playerBg
                    area = new GRect(0f, 0f, 50f, 400f, true, 0f, 0f);
                    area.setFill(this.colorInactive);
                    area.setStroke(this.colorStroke, 2f);
                    panel.addChild(area, 280f, 0f);

                    // playerName
                    text = new GText(this.gameBoardState.getPlayers()[counter].getName() + " - " + Integer.toString(this.gameBoardState.getPlayers()[counter].getScore()));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    text.rotate(-90f);
                    panel.addChild(text, 290f, 0f);

                    break;
                }
                case 2: {
                    // panel erzeugen (mit Startpunkt)
                    panel = mainPanel.addLayer(LayerPosition.TOP_CENTER); 

                    // playerBg
                    area = new GRect(0f, 0f, 400f, 50f, true, 0f, 0f);
                    area.setFill(this.colorInactive);
                    area.setStroke(this.colorStroke, 2f);
                    panel.addChild(area, 0f, 210f);

                    // playerName
                    text = new GText(this.gameBoardState.getPlayers()[counter].getName() + " - " + Integer.toString(this.gameBoardState.getPlayers()[counter].getScore()));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    panel.addChild(text, 0f, 220f);

                    break;
                }
                case 3: {
                    // panel erzeugen (mit Startpunkt)
                    panel = mainPanel.addLayer(LayerPosition.CENTER_RIGHT); 

                    // playerBg
                    area = new GRect(0f, 0f, 50f, 400f, true, 0f, 0f);
                    area.setFill(this.colorInactive);
                    area.setStroke(this.colorStroke, 2f);
                    panel.addChild(area, -280f, 0f);

                    // playerName
                    text = new GText(this.gameBoardState.getPlayers()[counter].getName() + " - " + Integer.toString(this.gameBoardState.getPlayers()[counter].getScore()));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    text.rotate(90f);
                    panel.addChild(text, -290f, 0f);

                    break;
                }
            }
            this.groupPlayerBg[counter] = area;
            this.groupPlayerName[counter] = text;
        }
    }

    public void updatePlayerName(int playerId) throws Exception {       
        this.groupPlayerName[playerId].setText(this.gameBoardState.getPlayers()[playerId].getName() + " - " + Integer.toString(this.gameBoardState.getPlayers()[playerId].getScore()));
    }

    public void updatePlayerBgInactive(int playerId) {
        this.groupPlayerBg[playerId].setFill(this.colorInactive);
    }

    public void updatePlayerBgActive(int playerId) {
        this.groupPlayerBg[playerId].setFill(this.colorActive);
    }

}
