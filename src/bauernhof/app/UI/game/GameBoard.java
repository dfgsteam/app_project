package bauernhof.app.UI.game;

import bauernhof.app.GaCo;
import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.Advanced_AI;
import bauernhof.app.player.types.Random_AI;
import bauernhof.app.player.types.Simple_AI;
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

    private Simple_AI SA;
    private Random_AI RA;
    private Advanced_AI AA;

    GameBoardState GaBoS;
    Set<AbstractGamePlayer> playerSet;

    public GameBoard(GaCo gameconf, Set<AbstractGamePlayer> set){

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
    /*
        private void preparePlayers(int player, int ai, int aiGrade){

            for(int i = 0; i < player;i++){
                playerSet.add( new HumanPlayer("Player"+(i+2),GaBoS));
            }
            if(aiGrade == 0) {
                for (int i = 0; i < ai; i++) {
                    playerSet.add(new Random_AI("AiPlayer" + (i + 2), GaBoS));
                }
            }
            if(aiGrade == 1) {
                for (int i = 0; i < ai; i++) {
                    playerSet.add(new Simple_AI("AiPlayer" + (i + 2), GaBoS));
                }
            }
            if(aiGrade == 2) {
                for (int i = 0; i < ai; i++) {
                    playerSet.add(new Advanced_AI("AiPlayer" + (i + 2), GaBoS));
                }
            }

        }
    */
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

                /*JPanel mid = new SAGPanel();
                MainPanel.add(mid, BorderLayout.CENTER);
                mid.setBackground(new Color(255,0,0));*/

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
        JPanel panel3 = new SAGPanel();
        panel3.setLayout(new FlowLayout());//new GridLayout(1,10));
        System.out.println(5);
        panel3.setBackground(new Color(255,0,0));

        JPanel panel31 = new SAGPanel();panel3.add(panel31);
        JPanel panel32 = new SAGPanel();panel3.add(panel32);
        JPanel panel33 = new SAGPanel();panel3.add(panel33);
        JPanel panel34 = new SAGPanel();panel3.add(panel34);
        JPanel panel35 = new SAGPanel();panel3.add(panel35);
        JPanel panel36 = new SAGPanel();panel3.add(panel36);
        JPanel panel37 = new SAGPanel();panel3.add(panel37);
        JPanel panel38 = new SAGPanel();panel3.add(panel38);
        JPanel panel39 = new SAGPanel();panel3.add(panel39);
        JPanel panel310 = new SAGPanel();panel3.add(panel310);


        MainPanel.add(panel3);
    }

    private void setPlayer2n4Panel(){
        JPanel panel2 = new SAGPanel();
        panel2.setLayout(new GridLayout(1,10));
        JPanel panel21 = new SAGPanel();panel2.add(panel21);
        JPanel panel22 = new SAGPanel();panel2.add(panel22);
        JPanel panel23 = new SAGPanel();panel2.add(panel23);
        JPanel panel24 = new SAGPanel();panel2.add(panel24);
        JPanel panel25 = new SAGPanel();panel2.add(panel25);
        JPanel panel26 = new SAGPanel();panel2.add(panel26);
        JPanel panel27 = new SAGPanel();panel2.add(panel27);
        JPanel panel28 = new SAGPanel();panel2.add(panel28);
        JPanel panel29 = new SAGPanel();panel2.add(panel29);
        JPanel panel210 = new SAGPanel();panel2.add(panel210);

        JPanel panel4 = new SAGPanel();panel2.add(panel21);

        panel4.setLayout(new GridLayout(1,10));
        JPanel panel41 = new SAGPanel();panel4.add(panel41);
        JPanel panel42 = new SAGPanel();panel4.add(panel42);
        JPanel panel43 = new SAGPanel();panel4.add(panel43);
        JPanel panel44 = new SAGPanel();panel4.add(panel44);
        JPanel panel45 = new SAGPanel();panel4.add(panel45);
        JPanel panel46 = new SAGPanel();panel4.add(panel46);
        JPanel panel47 = new SAGPanel();panel4.add(panel47);
        JPanel panel48 = new SAGPanel();panel4.add(panel48);
        JPanel panel49 = new SAGPanel();panel4.add(panel49);
        JPanel panel410 = new SAGPanel();panel4.add(panel410);

        MainPanel.add(panel2, BorderLayout.LINE_START);
        MainPanel.add(panel4, BorderLayout.LINE_END);
    }



}
