package bauernhof.app.ui.game;

import bauernhof.app.player.types.HumanPlayer;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.*;
import bauernhof.app.launcher.GameBoardState;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.listener.DepositedDrawListener;
import bauernhof.app.ui.game.listener.DepositedListener;
import bauernhof.app.ui.game.listener.DrawPileListener;
import bauernhof.app.ui.game.listener.card.CardAddListener;
import bauernhof.app.ui.game.listener.card.CardListener;
import bauernhof.app.ui.game.listener.card.CardPopListener;
import bauernhof.app.ui.game.panel.*;
import bauernhof.app.ui.game.panel.deposited.DepositedDeckPanel;
import bauernhof.app.ui.game.panel.deposited.DepositedPanel;
import bauernhof.app.ui.game.panel.draw.DrawPileDeckPanel;
import bauernhof.app.ui.game.panel.draw.DrawPilePanel;
import bauernhof.app.ui.game.panel.player.PlayerNamePanel;
import bauernhof.app.ui.game.panel.player.PlayerPanel;
import sag.SAGFrame;
import sag.SAGPanel;
import sag.elements.GGroup;


import java.util.concurrent.TimeUnit;

public class GameBoard { 

    public static int WIDTH = 1600;
    public static int HEIGTH = 900;

    private final SAGFrame FRAME = new SAGFrame("Hofbauern", 30, GameBoard.WIDTH, GameBoard.HEIGTH);
    private SAGPanel mainPanel = new SAGPanel(GameBoard.WIDTH, GameBoard.HEIGTH);

    private GCard drawPileDeck;
    private GCard depositedDeck;

    

    private PlayerPanel panelPlayer;
    private PlayerNamePanel panelPlayerName;
    private RoundPanal panelRound;
    private DrawPilePanel panelDrawPile;
    private DrawPileDeckPanel panelDrawPileDeck;
    private DepositedPanel panelDeposited;
    private DepositedDeckPanel panelDepositedDeck;
    
    private GameBoardState gameBoardState;

    private CardPopListener cardListenetr;

    private int playerId = 0;

    /**
     * Constructor
     * @param gameconf
     * @param gameBoardState 
     * @throws Exception
     */

    public GameBoard(GameConfiguration gameconf, GameBoardState gameBoardState) throws Exception{
        this.gameBoardState = gameBoardState;

        //init Frame

        this.FRAME.setSAGPanel(this.mainPanel);
        this.FRAME.setVisible(true);

        // init Panels
        this.panelRound = new RoundPanal(this.mainPanel, this.gameBoardState);
        this.panelPlayer = new PlayerPanel(this.mainPanel, this.gameBoardState.getPlayers().length, gameconf.getNumCardsPerPlayerHand(), this);
        this.panelPlayerName = new PlayerNamePanel(this.mainPanel, this.gameBoardState);
        this.panelDrawPile = new DrawPilePanel(this, gameBoardState.getDrawPileCards());
        this.panelDrawPileDeck = new DrawPileDeckPanel(this, this.mainPanel, this.playerId, this.gameBoardState);
        this.panelDeposited = new DepositedPanel(this, gameBoardState.getDepositedCards());
        this.panelDepositedDeck = new DepositedDeckPanel(this, this.mainPanel, this.playerId, this.gameBoardState);
        new SreenshotPanal(this.mainPanel, this);
        new SaveGamePanel(this.mainPanel, this);

        // init load playerCards
        for (int index=0; index < this.gameBoardState.getPlayers().length; index++)
            this.panelPlayer.updatePlayer(index, this.gameBoardState.getPlayers()[index]);

        // test = 10 gui moves
        this.test();
    }

    public void move(boolean last) throws Exception { 
        // Spieler inaktiv setzten
        this.panelPlayerName.updatePlayerBgInactive(this.playerId);

        // Karten + Punkte updaten
        this.panelPlayer.updatePlayer(this.playerId, this.gameBoardState.getPlayers()[this.playerId]);
        this.panelPlayerName.updatePlayerName(this.playerId);

        // (Nach)ziehstapel update
        this.panelDrawPileDeck.update();
        this.panelDepositedDeck.update();

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
        return this.gameBoardState.getPlayers()[this.playerId].getPlayerType() == PlayerType.HUMAN;
    }

    public void moveAddCard(GCard gCard) {
        ((HumanPlayer) this.gameBoardState.getActualPlayer()).setAdd(gCard.getCard());
        this.createExchangePanel();
        //-> Schnittstelle zum Game

        System.out.print("addCard: ");
        System.out.println(gCard.getCard().getName());
    }

    public void movePopCard(GCard gCard) throws Exception {
       // this.setMainPanel();
        //

        System.out.print("popCard: ");
        System.out.println(gCard.getCard().getName());
        //Thread.sleep(1000);
        ((HumanPlayer) this.gameBoardState.getActualPlayer()).doMove(gCard.getCard());
        this.panelDepositedDeck.update();
        this.panelDrawPileDeck.update();
        this.setMainPanel();
    }

    public void createDrawPilePanel() {
        System.out.println("createDrawPilePanel");
        this.FRAME.setSAGPanel(new DrawPilePanel(this, this.gameBoardState.getDrawPileCards()));
    }

    public void createDepositedPanel() {
        System.out.println("createDepositedPanel");
        this.FRAME.setSAGPanel(new DepositedPanel(this, this.gameBoardState.getDepositedCards()));
    }

    public void createExchangePanel() {
        this.FRAME.setSAGPanel(new ExchangePanel(this));
    }

    public void createScorePanal() throws Exception {
        new ScorePanal(this.mainPanel, this.gameBoardState);
    }

    public void createCheaterPanal(AbstractGamePlayer player) throws Exception {
        new CheaterPanel(this.mainPanel, this.gameBoardState, player);
    }

    public void setMainPanel() {
        this.FRAME.setSAGPanel(this.mainPanel);
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

    /**
     * prepare the main panel
     * initialize the card panels of two decks in the middle, draws the DrawPile deck
     */


    

    /**
     * redraws the the DrawPile and Deposited decks
     * puts the top element from the DrawPile stack and the last element of the Deposited list on the top of their deck
     */

    

    /**
     * get the frame
     * @return this frame
     */


    public SAGFrame getFrame(){
        return this.FRAME;
    }

    /**
     * get the main panel
     * @return main panel
     */
    public SAGPanel getMain(){
        return this.mainPanel;
    } 

    /**
     * get first card of DrawPile stack
     * @return DrawPileDeck
     */

    public DrawPileDeckPanel getDrawPileDeck(){
        return this.panelDrawPileDeck;
    }

    /**
     * get last card of Deposited list
     * @return DepositedFeck
     */

    public DepositedDeckPanel getDepositedDeck(){
        return this.panelDepositedDeck;
    }

    /**
     * get the card panel
     * @return the panel of cards in DrawPile stack 
     */

    public SAGPanel getDrawPilePanel(){
        return this.panelDrawPile;
    }

    /**
     * get the card panel
     * @return the panel of cards in Deposited list
     */

    public SAGPanel getDepositedPanel(){
        return this.panelDeposited;
    }

    /**
     * get the gameboard state
     * @return gameBoardState
     */

    public GameBoardState getGameBoardState(){
        return this.gameBoardState;
    }

    /**
     * get the CardListener
     * @return this implementation of CardListener
     */

    public int getPlayerId(){
        return playerId;
    }


}
