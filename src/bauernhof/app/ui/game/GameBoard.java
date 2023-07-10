package bauernhof.app.ui.game;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import bauernhof.app.launcher.GameBoardState;
import sag.LayerPosition;
import bauernhof.app.player.AbstractGamePlayer;
import sag.SAGFrame;
import sag.SAGPanel;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameBoard implements ActionListener{ 

    final private int WIDTH = 1600;
    final private int HEIGTH = 900;

    private final SAGFrame FRAME = new SAGFrame("Hofbauern", 30, this.WIDTH, this.HEIGTH);
    private SAGPanel mainPanel = new SAGPanel(this.WIDTH, this.HEIGTH);


    private JButton DrawPileButton;
    private JButton DipositedButton;

    private NachziehPanel nachziehPanel;
    private AblagePanel ablagePanel;

    private PlayerPanel panelPlayer;
    private PlayerNamePanel panelPlayerName;
    private GameBoardState gameBoardState;

    JButton Back;

    private int playerId = 0;

    GameBoardState GaBoS;
    ArrayList<AbstractGamePlayer> playerSet = new ArrayList<>();

    public GameBoard(GameConfiguration gameconf, GameBoardState gameBoardState) throws Exception{
        this.gameBoardState = gameBoardState;

        prepareMain();

        //init Frame
        this.FRAME.setSAGPanel(this.mainPanel);
        this.FRAME.setVisible(true);

        // init Panels
        this.panelPlayer = new PlayerPanel(mainPanel, this.gameBoardState.getPlayers().length, gameconf.getNumCardsPerPlayerHand(), this);
        this.panelPlayerName = new PlayerNamePanel(mainPanel, gameBoardState);


        // init load playerCards
        for (int index=0; index < this.gameBoardState.getPlayers().length; index++)
            this.panelPlayer.updatePlayer(index, this.gameBoardState.getPlayers()[index], true);

        // test = 10 gui moves
        this.test();
    }

    public void move() throws Exception {
        this.panelPlayer.updatePlayer(this.playerId, this.gameBoardState.getPlayers()[this.playerId], false);
        this.panelPlayerName.updatePlayerName(this.playerId);
        this.playerId = (this.playerId+1)%4;
    }

    public boolean check_move(int playerId) {
        return this.playerId == playerId;
    }

    private void test() throws Exception {
        int index2 = 0;
        while (index2++ < 10) {
            System.out.println(this.playerId);
            TimeUnit.SECONDS.sleep(4);
            this.move();
        }
    }


    private void prepareMain(){
         //String path = "graphics/player_view"+i+".jpg";

        this.mainPanel = new SAGPanel(this.WIDTH, this.HEIGTH);
        mainPanel.setLayout(new GridLayout(3,1));
        
        initDrawPileButton();
        initAblagestapelButton();

        mainPanel.add(DrawPileButton);
        mainPanel.add(DipositedButton);

        GGroup Mid = mainPanel.addLayer(LayerPosition.CENTER_CENTER);
        Mid.setScale(1.15f);
        Mid.addChild(new GCard(gameBoardState.getDrawPileCards().iterator().next()), -200, 0);
        //Mid.addChild( new GCard(playerSet.get(0).getCards().iterator().next()), 150, 0);
        
    }


    private void initDrawPileButton(){
        
        DrawPileButton = new JButton();
        DrawPileButton.setBounds((WIDTH/2)-(WIDTH/10*2),(HEIGTH/2)-(HEIGTH/5),WIDTH/9,HEIGTH/10*3);
        DrawPileButton.addActionListener(this::actionPerformed);
        DrawPileButton.setOpaque(false);
        DrawPileButton.setContentAreaFilled(false);
        DrawPileButton.setBorderPainted(false);
        DrawPileButton.setFocusable(false);
        DrawPileButton.setRolloverEnabled(false);

    }

    private void initAblagestapelButton(){
        
        DipositedButton = new JButton();
        DipositedButton.setBounds((WIDTH/2),(HEIGTH/2)-(HEIGTH/5),WIDTH/9,HEIGTH/10*3);
        DipositedButton.addActionListener(this::actionPerformed);
        DipositedButton.setOpaque(false);
        DipositedButton.setContentAreaFilled(false);
        DipositedButton.setBorderPainted(false);
        DipositedButton.setFocusable(false);
        DipositedButton.setRolloverEnabled(false);
    
    }

    public SAGFrame getFrame(){
        return this.FRAME;
    }
    public SAGPanel getMain(){
        return this.mainPanel;
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==DrawPileButton){
            nachziehPanel = new NachziehPanel(this.FRAME,gameBoardState.getDrawPileCards());
            FRAME.setSAGPanel(nachziehPanel);
        }

        if(e.getSource()==DipositedButton){
            ablagePanel = new AblagePanel(gameBoardState.getDepositedCards());
        }

        if(e.getSource()==Back){
            FRAME.setSAGPanel(mainPanel);
        }
        
           
    }

    
}
