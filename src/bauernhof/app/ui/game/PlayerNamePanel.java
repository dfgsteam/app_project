package bauernhof.app.ui.game;

import java.awt.Color;

import bauernhof.app.launcher.GameBoardState;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

public class PlayerNamePanel extends GGroup{

    private GText groupPlayerName[] = new GText[4];

    private GameBoardState gameBoardState;


    public PlayerNamePanel (SAGPanel mainPanel, GameBoardState gameBoardState) throws Exception {
        this.gameBoardState = gameBoardState;
        GGroup panel;
        GRect area;
        GText text = new GText(null);
        for (int counter=0; counter<this.gameBoardState.getPlayers().length; counter++) {
            switch (counter) {
                case 0: {
                    panel = mainPanel.addLayer(LayerPosition.BOTTOM_CENTER); 
                    area = new GRect(0f, 0f, 400f, 50f, true, 0f, 0f);
                    area.setFill(new Color(255, 255, 255));
                    area.setStroke(new Color(100, 100, 100), 2f);
                    text = new GText(this.gameBoardState.getPlayers()[counter].getName() + " - " + Integer.toString(this.gameBoardState.getPlayers()[counter].getScore()));
                    text.setAlignment(GText.TextAnchor.MIDDLE);

                    panel.addChild(area, 0f, -210f);
                    panel.addChild(text, 0f, -200f);
                    break;
                }
                case 1: {
                    panel = mainPanel.addLayer(LayerPosition.CENTER_LEFT); 
                    area = new GRect(0f, 0f, 50f, 400f, true, 0f, 0f);
                    area.setFill(new Color(255, 255, 255));
                    area.setStroke(new Color(100, 100, 100), 2f);
                    text = new GText(this.gameBoardState.getPlayers()[counter].getName() + " - " + Integer.toString(this.gameBoardState.getPlayers()[counter].getScore()));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    text.rotate(-90f);


                    panel.addChild(area, 280f, 0f);
                    panel.addChild(text, 290f, 0f);
                    break;
                }
                case 2: {
                    panel = mainPanel.addLayer(LayerPosition.TOP_CENTER); 
                    area = new GRect(0f, 0f, 400f, 50f, true, 0f, 0f);
                    area.setFill(new Color(255, 255, 255));
                    area.setStroke(new Color(100, 100, 100), 2f);
                    text = new GText(this.gameBoardState.getPlayers()[counter].getName() + " - " + Integer.toString(this.gameBoardState.getPlayers()[counter].getScore()));
                    text.setAlignment(GText.TextAnchor.MIDDLE);


                    panel.addChild(area, 0f, 210f);
                    panel.addChild(text, 0f, 220f);
                    break;
                }
                case 3: {
                    panel = mainPanel.addLayer(LayerPosition.CENTER_RIGHT); 
                    area = new GRect(0f, 0f, 50f, 400f, true, 0f, 0f);
                    area.setFill(new Color(255, 255, 255));
                    area.setStroke(new Color(100, 100, 100), 2f);
                    text = new GText(this.gameBoardState.getPlayers()[counter].getName() + " - " + Integer.toString(this.gameBoardState.getPlayers()[counter].getScore()));
                    text.setAlignment(GText.TextAnchor.MIDDLE);
                    text.rotate(90f);


                    panel.addChild(area, -280f, 0f);
                    panel.addChild(text, -290f, 0f);
                    break;
                }
            }
            this.groupPlayerName[counter] = text;
        }
    }

    public void updatePlayerName(int playerId) throws Exception {
        this.groupPlayerName[playerId].setText(this.gameBoardState.getPlayers()[playerId].getName() + " - " + Integer.toString(this.gameBoardState.getPlayers()[playerId].getScore()));
    }

}
