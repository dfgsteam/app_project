package bauernhof.app.ui.game;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.Player;
import bauernhof.app.launcher.GameBoardState;
import sag.SAGFrame;
import sag.SAGPanel;
import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class GameBoard{ 

    private SAGFrame Frame;
    private SAGPanel MainPanel;

    final private int WIDTH = 1280;
    final private int HEIGTH = 720;


    GameBoardState GaBoS;
    Set<Player> playerSet;

    public GameBoard(GameConfiguration gameconf, Set<Player> set){

        Frame = new SAGFrame("Hofbauern",30,1280,720);
        playerSet = set;

        /*try {
            GaBoS = new GameBoardState(gameconf,set);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        //playerSet.add( new HumanPlayer("Player1",GaBoS));

        prepareMain(playerSet.size());
        Frame.setSAGPanel(MainPanel);
        Frame.setLayout(null);
        Frame.setVisible(true);

    }

    private void drawCards(){

    }

    private void prepareMain(int i){
        String path = "graphics/player_view"+i+".jpg";

                this.MainPanel = new SAGPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        // Zeichne den Hintergrund
                        ImageIcon backgroundImage = new ImageIcon(path);
                        Image image = backgroundImage.getImage();
                        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                    }
                };

                MainPanel.setLayout(new FlowLayout());

                JPanel mid = new SAGPanel();
                MainPanel.add(mid, BorderLayout.CENTER);
                mid.setBackground(new Color(255,0,0));

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

