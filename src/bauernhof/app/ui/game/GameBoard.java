package bauernhof.app.ui.game;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.Player;
import bauernhof.preset.card.GCard;
import bauernhof.app.GaCo;
import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.SAGFrame;
import sag.SAGPanel;
import sag.elements.GGroup;

import javax.swing.*;
import javax.swing.text.LayeredHighlighter.LayerPainter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GameBoard{ 

    final private int WIDTH = 1600;
    final private int HEIGTH = 900;

    private SAGFrame Frame = new SAGFrame("Hofbauern", 30, this.WIDTH, this.HEIGTH);
    private SAGPanel mainPanel = new SAGPanel(this.WIDTH, this.HEIGTH);

    private PlayerPanel panelPlayer;

    private GameBoardState gameBoardState;


    GameBoardState GaBoS;
    ArrayList<AbstractGamePlayer> playerSet;

    public GameBoard(GameConfiguration gameconf, GameBoardState gameBoardState) throws ChildNotFoundException, InterruptedException{
        this.gameBoardState = gameBoardState;

        this.Frame.setSAGPanel(this.mainPanel);
        this.Frame.setVisible(true);

        this.panelPlayer = new PlayerPanel(mainPanel, this.gameBoardState.getPlayers().length, gameconf.getNumCardsPerPlayerHand(), this.gameBoardState.getPlayers());

        int index2 = 0;
        while (index2++ < 10) {
            for (int index=0; index < this.gameBoardState.getPlayers().length; index++){
                this.panelPlayer.updatePlayer(index, this.gameBoardState.getPlayers()[index]);
                System.out.println(this.gameBoardState.getPlayers()[index].getCards());
                TimeUnit.SECONDS.sleep(1);
                this.gameBoardState.getPlayers()[index].add(this.gameBoardState.getDrawPileCards().pop());
            }
        }
        
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

