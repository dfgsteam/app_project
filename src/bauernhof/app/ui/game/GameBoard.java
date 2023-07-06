package bauernhof.app.ui.game;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.Player;
import bauernhof.preset.card.GCard;
import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import sag.LayerPosition;
import sag.SAGFrame;
import sag.SAGPanel;
import sag.elements.GGroup;

import javax.swing.*;
import javax.swing.text.LayeredHighlighter.LayerPainter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

public class GameBoard{ 

    final private int WIDTH = 1600;
    final private int HEIGTH = 900;

    private SAGFrame Frame = new SAGFrame("Hofbauern", 30, this.WIDTH, this.HEIGTH);
    private SAGPanel mainPanel = new SAGPanel(this.WIDTH, this.HEIGTH);

    private PlayerPanel panelPlayer;


    GameBoardState GaBoS;
    ArrayList<AbstractGamePlayer> playerSet;

    public GameBoard(GameConfiguration gameconf, ArrayList<AbstractGamePlayer> players){

        this.Frame.setSAGPanel(this.mainPanel);
        this.Frame.setVisible(true);

        this.panelPlayer = new PlayerPanel(mainPanel, players.size(), 10, players);
    }

    private void drawCards(){

    }

    private void prepareMain(int i){
        System.out.println(i);
        switch (i){
            case 2:
                setPlayer3Panel(); break;
            case 3:
                setPlayer2n4Panel(); break;
            case 4:
                setPlayer3Panel();
                setPlayer2n4Panel(); break;
        }
    }

    private void setPlayer3Panel(){
           // PlayerPanel PP3 = new PlayerPanel();
    }

    private void setPlayer2n4Panel(){
           /*  PlayerPanel PP2 = new PlayerPanel();
            PlayerPanel PP4 = new PlayerPanel();*/
    }

    
        


}

