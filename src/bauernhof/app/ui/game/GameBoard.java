package bauernhof.app.ui.game;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.card.GCard;
import bauernhof.app.launcher.GameBoardState;
import sag.LayerPosition;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.listener.CardListener;
import sag.SAGFrame;
import sag.SAGPanel;
import sag.elements.GElement;
import sag.elements.GGroup;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameBoard { 

    final private int WIDTH = 1600;
    final private int HEIGTH = 900;

    private final SAGFrame FRAME = new SAGFrame("Hofbauern", 30, this.WIDTH, this.HEIGTH);
    private SAGPanel mainPanel = new SAGPanel(this.WIDTH, this.HEIGTH);

    private GCard drawPileDeck;
    private GCard depositedDeck;

    private GGroup Mid; 

    private DrawPilePanel drawPilePanel;
    private DepositedPanel depositedPanel;

    private PlayerPanel panelPlayer;
    private PlayerNamePanel panelPlayerName;
    private GameBoardState gameBoardState;

    private CardListener cardListenetr;

    private int playerId = 0;

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
        //this.test();
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
        mainPanel.setLayout(null);
        
        cardListenetr = new CardListener(this, playerId);

        Mid = mainPanel.addLayer(LayerPosition.CENTER_CENTER);
        Mid.setScale(1.15f);
        drawPileDeck = new GCard(gameBoardState.getDrawPileCards().iterator().next());
        drawPileDeck.setMouseEventListener(cardListenetr);
        Mid.addChild(drawPileDeck, -200, 0);
        drawPilePanel = new DrawPilePanel(this, gameBoardState.getDrawPileCards());
        depositedPanel = new DepositedPanel(this, gameBoardState.getDepositedCards());
    
        
    }

    public void updateMain(){
        System.out.println("hi");
        drawPileDeck = new GCard(gameBoardState.getDrawPileCards().iterator().next());
        drawPileDeck.setMouseEventListener(cardListenetr);
        Mid.addChild(drawPileDeck, -200, 0);
        drawPilePanel = new DrawPilePanel(this, gameBoardState.getDrawPileCards());

        depositedDeck = new GCard(gameBoardState.getDepositedCards().iterator().next());
        depositedDeck.setMouseEventListener(cardListenetr);
        Mid.addChild(depositedDeck, 150,0);
        depositedPanel = new DepositedPanel(this, gameBoardState.getDepositedCards());

    }


    public SAGFrame getFrame(){
        return this.FRAME;
    }
    public SAGPanel getMain(){
        return this.mainPanel;
    } 

    public GCard getDrawPileDeck(){
        return this.drawPileDeck;
    }

    public GCard getDepositedDeck(){
        return this.depositedDeck;
    }

    public SAGPanel getDrawPilePanel(){
            return drawPilePanel;
    }
    public SAGPanel getDepositedPanel(){
        return depositedPanel;
    }
    public GameBoardState getGameBoardState(){
        return this.gameBoardState;
    }
    public CardListener getCardListener(){
        return this.cardListenetr;
    }

    
}
