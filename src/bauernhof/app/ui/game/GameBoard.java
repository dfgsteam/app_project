package bauernhof.app.ui.game;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.Player;
import bauernhof.preset.card.GCard;
import bauernhof.app.card.Ca;
import bauernhof.preset.card.GCard;
import bauernhof.app.launcher.GameBoardState;
import sag.LayerPosition;
import bauernhof.app.player.AbstractGamePlayer;
import sag.LayerPosition;
import sag.SAGFrame;
import sag.SAGPanel;
import sag.elements.GGroup;

import javax.imageio.ImageIO;
import sag.elements.GGroup;

import javax.swing.*;
import javax.swing.text.LabelView;

import javax.swing.text.LayeredHighlighter.LayerPainter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

public class GameBoard implements ActionListener{ 

    final private int WIDTH = 1600;
    final private int HEIGTH = 900;

    private SAGFrame Frame = new SAGFrame("Hofbauern", 30, this.WIDTH, this.HEIGTH);
    private SAGPanel mainPanel = new SAGPanel(this.WIDTH, this.HEIGTH);
    private SAGPanel CardPanel;


    private JButton Nachziehstapel;
    private JButton Ablagestapel;

    private Set<GCard> NachziehstapelCards; 
    private Set<GCard> AblagestapelCards; 

    private PlayerPanel panelPlayer;


    GameBoardState GaBoS;
    ArrayList<AbstractGamePlayer> playerSet;

    public GameBoard(GameConfiguration gameconf, ArrayList<AbstractGamePlayer> players){

        this.Frame.setSAGPanel(this.mainPanel);
        this.Frame.setVisible(true);

        this.panelPlayer = new PlayerPanel(mainPanel, players.size(), 10, players);
    }
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

    private void prepareMain(int i){
         String path = "graphics/player_view"+i+".jpg";

                this.MainPanel = new SAGPanel();/*  {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        // Zeichne den Hintergrund
                        ImageIcon backgroundImage = new ImageIcon(path);
                        Image image = backgroundImage.getImage();
                        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                    }
                };*/

                initNachziehstapel();

                GGroup Mid = MainPanel.addLayer(LayerPosition.CENTER_CENTER);
                //Mid.addChild(new GCard(new Ca())));


                

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


    private void initNachziehstapel(){
        
        NachziehstapelCards = new HashSet<>();
        for(int i = 0; i < 10; i++){
             NachziehstapelCards.add(new GCard(new Ca("",0,null,null,null)));
        }
        Nachziehstapel = new JButton();//new ImageIcon(NachziehstapelCards.iterator().next().getImage()));
        /*try {
            Nachziehstapel.setIcon(new ImageIcon(ImageIO.read(new File("graphics/bauer.svg"))));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Nachziehstapel.addActionListener(this::actionPerformed);

    }

    private void initAblagestapel(){
        
        AblagestapelCards = new HashSet<>();
        
        Ablagestapel = new JButton();
        Ablagestapel.addActionListener(this::actionPerformed);
    
    }

    private void setPlayer3Panel(){
           // PlayerPanel PP3 = new PlayerPanel();
    }

    private void setPlayer2n4Panel(){
           /*  PlayerPanel PP2 = new PlayerPanel();
            PlayerPanel PP4 = new PlayerPanel();*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
            if(e.getSource()==Nachziehstapel){
                    CardPanel = new SAGPanel();
                    CardPanel.setLayout(new FlowLayout());
                    for(GCard c : NachziehstapelCards){
                       // CardPanel.add(new Label(c.getImage()));
                    }

            }
    }

}

