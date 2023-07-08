package bauernhof.app.ui.game;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.card.GCard;
import bauernhof.app.card.Ca;
import bauernhof.app.launcher.GameBoardState;
import sag.LayerPosition;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.panel.*;
import sag.SAGFrame;
import sag.SAGPanel;
import sag.elements.GGroup;
import javax.swing.*;
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
    private RoundPanal panelRound;
    private GameBoardState gameBoardState;

    private int playerId = 0;

    GameBoardState GaBoS;
    ArrayList<AbstractGamePlayer> playerSet = new ArrayList<>();

    public GameBoard(GameConfiguration gameconf, GameBoardState gameBoardState) throws Exception{
        this.gameBoardState = gameBoardState;

        prepareMain();

        //init Frame
        this.Frame.setSAGPanel(this.mainPanel);
        this.Frame.setVisible(true);

        // init Panels
        this.panelRound = new RoundPanal(this.mainPanel, this.gameBoardState);
        this.panelPlayer = new PlayerPanel(this.mainPanel, this.gameBoardState.getPlayers().length, gameconf.getNumCardsPerPlayerHand(), this);
        this.panelPlayerName = new PlayerNamePanel(this.mainPanel, this.gameBoardState);


        // init load playerCards
        for (int index=0; index < this.gameBoardState.getPlayers().length; index++)
            this.panelPlayer.updatePlayer(index, this.gameBoardState.getPlayers()[index]);

        // test = 10 gui moves
        //this.test();
    }

    public void move(boolean last) throws Exception { 
        // Spieler inaktiv setzten
        this.panelPlayerName.updatePlayerBgInactive(this.playerId);

        // Karten + Punkte updaten
        this.panelPlayer.updatePlayer(this.playerId, this.gameBoardState.getPlayers()[this.playerId]);
        this.panelPlayerName.updatePlayerName(this.playerId);

        // Wenn nicht letzer Zug
        if (!last) {
            // Nächsten Spieler aktiv setzen
            this.playerId = (this.playerId+1)%this.gameBoardState.getPlayers().length;
            this.panelPlayerName.updatePlayerBgActive(this.playerId);
            this.panelRound.update();
        } else {
            // -> Spielende Panel
            this.playerId = 5;
            new ScorePanal(mainPanel, gameBoardState);
        }
    }

    public boolean check_move(int playerId) {
        return this.playerId == playerId;
    }

    public void createScorePanal() throws Exception {
        new ScorePanal(this.mainPanel, this.gameBoardState);
    }

    public void createCheaterPanal(AbstractGamePlayer player) throws Exception {
        new CheaterPanel(this.mainPanel, this.gameBoardState, player);
    }

    private void test() throws Exception {
        int maxTestRounds = 3;
        int index2 = 0;
        while (index2++ < maxTestRounds) {
            System.out.println(this.playerId);
            TimeUnit.SECONDS.sleep(1);
            this.move(index2 == maxTestRounds);
        }
        this.createScorePanal();
    }





    private void prepareMain(){
         //String path = "graphics/player_view"+i+".jpg";

        this.mainPanel = new SAGPanel(this.WIDTH, this.HEIGTH);

        GGroup Mid = mainPanel.addLayer(LayerPosition.CENTER_CENTER);
        Mid.addChild( new GCard(gameBoardState.getDrawPileCards().lastElement()), -150, 0);
       // Mid.addChild( new GCard(gameBoardState.getDepositedCards().get(gameBoardState.getDepositedCards().size())), 150, 0);
    }


    private void initNachziehstapel(){
        
        NachziehstapelCards = new HashSet<>();
        for(int i = 0; i < 10; i++){
             NachziehstapelCards.add(new GCard(new Ca("",0,null,null,null)));
        }
        Nachziehstapel = new JButton();
        Nachziehstapel.addActionListener(this::actionPerformed);


    }

    private void initAblagestapel(){
        
        AblagestapelCards = new HashSet<>();
        
        Ablagestapel = new JButton();
        Ablagestapel.addActionListener(this::actionPerformed);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
           
    }

}
