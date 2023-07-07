package bauernhof.app.ui.game;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.Player;
import bauernhof.preset.card.GCard;
import bauernhof.app.card.Ca;
import bauernhof.preset.card.GCard;
import bauernhof.app.GaCo;
import bauernhof.app.launcher.GameBoardState;
import sag.LayerPosition;
import bauernhof.app.player.AbstractGamePlayer;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.SAGFrame;
import sag.SAGPanel;
import sag.elements.GGroup;
import javax.imageio.ImageIO;
import sag.elements.GGroup;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
    private GameBoardState gameBoardState;

    GameBoardState GaBoS;
    ArrayList<AbstractGamePlayer> playerSet = new ArrayList<>();

    public GameBoard(GameConfiguration gameconf, GameBoardState gameBoardState) throws ChildNotFoundException, InterruptedException{
        this.gameBoardState = gameBoardState;

        this.Frame.setSAGPanel(this.mainPanel);
        this.Frame.setVisible(true);

        this.panelPlayer = new PlayerPanel(mainPanel, this.gameBoardState.getPlayers().length, gameconf.getNumCardsPerPlayerHand(), this.gameBoardState.getPlayers());

        int index2 = 0;
        while (index2++ < 10) {
            for (int index=0; index < this.gameBoardState.getPlayers().length; index++){
                this.panelPlayer.updatePlayer(index, this.gameBoardState.getPlayers()[index]);
                //System.out.println(this.gameBoardState.getPlayers()[index].getCards());
                TimeUnit.SECONDS.sleep(1);
                //this.gameBoardState.getPlayers()[index].add(this.gameBoardState.getDrawPileCards().pop());
            }
        }
        
    }

        prepareMain(playerSet.size());
        Frame.setSAGPanel(mainPanel);
        Frame.setLayout(null);
        Frame.setVisible(true);

    }
        /*try {
            GaBoS = new GameBoardState(gameconf,set);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    

    private void prepareMain(int i){
         String path = "graphics/player_view"+i+".jpg";

                this.mainPanel = new SAGPanel(); /*{
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        // Zeichne den Hintergrund
                        ImageIcon backgroundImage = new ImageIcon(path);
                        Image image = backgroundImage.getImage();
                        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                    }
                };
                */
                mainPanel.setBgColor(new Color(0f, 0f, 0f, 0.5f));

                
                //GCard card = new GCard(playerSet.get(0).getCards().iterator().next());

                PlayerPanel playerPanel = new PlayerPanel(this.mainPanel, playerSet.size(), 50, playerSet);
                GGroup Mid = mainPanel.addLayer(LayerPosition.CENTER_CENTER);
                Mid.addChild( new GCard(playerSet.get(0).getCards().iterator().next()), -150, 0);
                Mid.addChild( new GCard(playerSet.get(0).getCards().iterator().next()), 150, 0);
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

