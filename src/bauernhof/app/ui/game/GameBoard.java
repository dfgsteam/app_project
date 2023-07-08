package bauernhof.app.ui.game;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.card.GCard;
import bauernhof.app.card.Ca;
import bauernhof.app.launcher.GameBoardState;
import sag.LayerPosition;
import bauernhof.app.player.AbstractGamePlayer;
import sag.SAGFrame;
import sag.SAGPanel;
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
    private PlayerNamePanel panelPlayerName;
    private GameBoardState gameBoardState;

    private int playerId = 0;

    GameBoardState GaBoS;
    ArrayList<AbstractGamePlayer> playerSet = new ArrayList<>();

    public GameBoard(GameConfiguration gameconf, GameBoardState gameBoardState) throws Exception{
        this.gameBoardState = gameBoardState;

        //init Frame
        this.Frame.setSAGPanel(this.mainPanel);
        this.Frame.setVisible(true);

        // init Panels
        this.panelPlayer = new PlayerPanel(mainPanel, this.gameBoardState.getPlayers().length, gameconf.getNumCardsPerPlayerHand(), this);
        this.panelPlayerName = new PlayerNamePanel(mainPanel, gameBoardState);


        // init load playerCards
        for (int index=0; index < this.gameBoardState.getPlayers().length; index++)
            this.panelPlayer.updatePlayer(index, this.gameBoardState.getPlayers()[index], true);


        // test = 10 gui moves
        this.test();
    }

    public void move(boolean last) throws Exception { 
        // Spieler inaktiv setzten
        this.panelPlayerName.updatePlayerBgInactive(this.playerId);

        // Karten + Punkte updaten
        this.panelPlayer.updatePlayer(this.playerId, this.gameBoardState.getPlayers()[this.playerId], false);
        this.panelPlayerName.updatePlayerName(this.playerId);

        // Wenn nicht letzer Zug
        if (!last) {
            // Nächsten Spieler aktiv setzen
            this.playerId = (this.playerId+1)%4;
            this.panelPlayerName.updatePlayerBgActive(this.playerId);
        } else {
            // -> Spielende Panel
            this.playerId = 5;
        }
    }

    public boolean check_move(int playerId) {
        return this.playerId == playerId;
    }

    private void test() throws Exception {
        int index2 = 0;
        while (index2++ < 10) {
            System.out.println(this.playerId);
            TimeUnit.SECONDS.sleep(4);
            this.move(false);
        }
    }




    private void prepareMain(){
         //String path = "graphics/player_view"+i+".jpg";

        this.mainPanel = new SAGPanel();

        mainPanel.setBgColor(new Color(0f, 0f, 0f, 0.5f));

        
        //GCard card = new GCard(playerSet.get(0).getCards().iterator().next());

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
