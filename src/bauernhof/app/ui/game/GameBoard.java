package bauernhof.app.ui.game;

import bauernhof.app.player.types.HumanPlayer;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.*;
import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.panel.*;
import bauernhof.app.ui.game.panel.deposited.DepositedDeckPanel;
import bauernhof.app.ui.game.panel.deposited.DepositedPanel;
import bauernhof.app.ui.game.panel.draw.DrawPileDeckPanel;
import bauernhof.app.ui.game.panel.draw.DrawPilePanel;
import bauernhof.app.ui.game.panel.player.PlayerNamePanel;
import bauernhof.app.ui.game.panel.player.PlayerPanel;
import sag.ChildNotFoundException;
import sag.SAGFrame;
import sag.SAGPanel;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class GameBoard { 

    public static int WIDTH = 1920;
    public static int HEIGTH = 1080;

    private final SAGFrame FRAME = new SAGFrame("Hofbauern", 30, GameBoard.WIDTH, GameBoard.HEIGTH);
    private SAGPanel mainPanel = new SAGPanel();

    private PlayerPanel panelPlayer;
    private PlayerNamePanel panelPlayerName;
    private RoundPanal panelRound;
    private DrawPileDeckPanel panelDrawPileDeck;
    private DepositedDeckPanel panelDepositedDeck;
    
    private GameBoardState gameBoardState;

    private int playerId = 0;

    public GameBoard(GameConfiguration gameconf, GameBoardState gameBoardState) throws Exception{
        this.gameBoardState = gameBoardState;

        //init Frame
        this.FRAME.setSAGPanel(this.mainPanel);
        this.FRAME.setVisible(true);

        // init Panels
        this.panelRound = new RoundPanal(this);
        this.panelPlayer = new PlayerPanel(this);
        this.panelPlayerName = new PlayerNamePanel(this);
        this.panelDrawPileDeck = new DrawPileDeckPanel(this);
        this.panelDepositedDeck = new DepositedDeckPanel(this);
        new SreenshotPanal(this);
        new SaveGamePanel(this);

        // init load playerCards
        for (int index=0; index < this.gameBoardState.getPlayers().length; index++)
            this.panelPlayer.updatePlayer(index);

        // test = 10 gui moves
        //this.test();
    }

    public void move(boolean last) throws Exception { 
        //this.FRAME.setSAGPanel(this.mainPanel);

        // Spieler inaktiv setzten
        this.panelPlayerName.updatePlayerBgInactive(this.playerId);


        // (Nach)Ziehstapel löschen
        this.panelDepositedDeck.clear();
        this.panelDrawPileDeck.clear();


        // Karten + Punkte updaten
        this.panelPlayer.updatePlayer(this.playerId);
        this.panelPlayerName.updatePlayerName(this.playerId);


        // (Nach)Ziehstapel update
        this.panelDepositedDeck.update();
        this.panelDrawPileDeck.update();

        // Wenn nicht letzer Zug
        if (!last) {
            // Nächsten Spieler aktiv setzen
            this.playerId = (this.playerId+1)%this.gameBoardState.getPlayers().length;
            this.panelPlayerName.updatePlayerBgActive(this.playerId);
            this.panelRound.update();
        } else {
            // -> Spielende Panel
            this.playerId = 5;
            new ScorePanal(this);
        }
    }

    public boolean check_move() {
        return this.gameBoardState.getPlayers()[this.playerId].getPlayerType() == PlayerType.HUMAN;
    }

    public void moveAddCard(GCard gCard) {
        this.createExchangePanel();
        //((HumanPlayer) this.gameBoardStat8e.getActualPlayer()).setAdd(gCard.getCard());
        System.out.print("addCard: ");
        System.out.println(gCard.getCard().getName());
    }

    public void movePopCard(GCard gCard) throws Exception {
        this.setMainPanel(); //aktuallisiert nicht richtig. Panel wird erst bei HUMAN move wieder angezeigt
        ((HumanPlayer) this.gameBoardState.getActualPlayer()).doMove(gCard.getCard());
    }

    public void createDrawPilePanel() {
        System.out.println("createDrawPilePanel");
        this.FRAME.setSAGPanel(new DrawPilePanel(this));
    }

    public void createDepositedPanel() {
        System.out.println("createDepositedPanel");
        this.FRAME.setSAGPanel(new DepositedPanel(this));
    }

    public void createExchangePanel() {
        this.FRAME.setSAGPanel(new ExchangePanel(this).getPanel());
    }

    public void createScorePanal() throws Exception {
        new ScorePanal(this);
    }

    public void createCheaterPanal(AbstractGamePlayer player) throws Exception {
        new CheaterPanel(this, player);
    }

    public void setMainPanel() {
        System.out.println("pop1");
        System.out.println(this.FRAME.setSAGPanel(this.mainPanel));
        this.FRAME.requestRepaint();
        // Thread.sleep(2000);
        System.out.println("pop2");
    }

    // screenshot
    public void createScreenshot() {
        BufferedImage img = new BufferedImage(this.FRAME.getWidth(), this.FRAME.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.FRAME.paint(img.getGraphics());
        File outputfile = new File("saved.png");
        try {
            ImageIO.write(img, "png", outputfile);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    // saveGame
    public void saveGame() {
        System.out.println("saveGame");
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public SAGPanel getMainPanel() {
        return this.mainPanel;
    }

    public GameBoardState getGameBoardState() {
        return this.gameBoardState;
    }
}
