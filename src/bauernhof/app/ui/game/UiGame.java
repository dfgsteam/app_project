package bauernhof.app.ui.game;

import bauernhof.app.player.types.HumanPlayer;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.*;
import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.group.button.PanelButtonSaveGame;
import bauernhof.app.ui.game.group.button.PanelButtonScreenshot;
import bauernhof.app.ui.game.group.display.GroupDisplayDepositedDeck;
import bauernhof.app.ui.game.group.display.GroupDisplayDrawPileDeck;
import bauernhof.app.ui.game.group.display.GroupDisplayPlayerCards;
import bauernhof.app.ui.game.group.display.GroupDisplayPlayerName;
import bauernhof.app.ui.game.group.display.GroupDisplayRound;
import bauernhof.app.ui.game.group.popup.GroupPopupCheater;
import bauernhof.app.ui.game.group.popup.GroupPopupScore;
import bauernhof.app.ui.game.panel.*;
import sag.ChildNotFoundException;
import sag.SAGFrame;
import sag.SAGPanel;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class UiGame { 

    public static int WIDTH = 1920;
    public static int HEIGTH = 1080;

    // Frame
    private final SAGFrame FRAME = new SAGFrame("Hofbauern", 30, UiGame.WIDTH, UiGame.HEIGTH);
    
    // Panels
    private SAGPanel mainPanel = new SAGPanel();
    private PanelDepositedCards panelDepositedCards;
    private PanelDrawPileCards panelDrawPileCards;
    private PanelExchangeCards panelExchangeCards;

    // GGroups
    private GroupDisplayPlayerCards groupDisplayPlayerCards;
    private GroupDisplayPlayerName groupDisplayPlayerName;
    private GroupDisplayRound groupDisplayRound;
    private GroupDisplayDrawPileDeck groupDisplayDrawPileDeck;
    private GroupDisplayDepositedDeck groupDisplayDepositedDeck;
    


    private GameBoardState gameBoardState;

    private int playerId = 0;

    public UiGame(GameConfiguration gameconf, GameBoardState gameBoardState) throws Exception{
        this.gameBoardState = gameBoardState;

        //init Frame
        this.FRAME.setSAGPanel(this.mainPanel);

        // init Frames
        this.FRAME.setVisible(true);
        this.panelDepositedCards = new PanelDepositedCards(this);
        this.panelDrawPileCards = new PanelDrawPileCards(this);
        //this.panelExchangeCards = new PanelExchangeCards(this);


        // init Groups
        this.groupDisplayRound = new GroupDisplayRound(this);
        this.groupDisplayPlayerCards = new GroupDisplayPlayerCards(this);
        this.groupDisplayPlayerName = new GroupDisplayPlayerName(this);
        this.groupDisplayDrawPileDeck = new GroupDisplayDrawPileDeck(this);
        this.groupDisplayDepositedDeck = new GroupDisplayDepositedDeck(this);
        new PanelButtonScreenshot(this);
        new PanelButtonSaveGame(this);

        // init load playerCards
        for (int index=0; index < this.gameBoardState.getPlayers().length; index++)
            this.groupDisplayPlayerCards.updatePlayer(index);

    }

    public void move(boolean last) throws Exception { 
        // Spieler inaktiv setzten
        this.groupDisplayPlayerName.updatePlayerBgInactive(this.playerId);


        // (Nach)Ziehstapel löschen
        this.groupDisplayDepositedDeck.clear();
        this.groupDisplayDrawPileDeck.clear();


        // Karten + Punkte updaten
        this.groupDisplayPlayerCards.updatePlayer(this.playerId);
        this.groupDisplayPlayerName.updatePlayerName(this.playerId);


        // (Nach)Ziehstapel update
        this.groupDisplayDepositedDeck.update();
        this.groupDisplayDrawPileDeck.update();

        // Wenn nicht letzer Zug
        if (!last) {
            // Nächsten Spieler aktiv setzen
            this.playerId = (this.playerId+1)%this.gameBoardState.getPlayers().length;
            this.groupDisplayPlayerName.updatePlayerBgActive(this.playerId);
            this.groupDisplayRound.update();
        } else {
            // -> Spielende Panel
            this.playerId = 5;
            new GroupPopupScore(this);
        }
    }

    public boolean check_move() {
        return this.gameBoardState.getPlayers()[this.playerId].getPlayerType() == PlayerType.HUMAN;
    }

    public void moveAddCard(GCard gCard) {
        ((HumanPlayer) this.gameBoardState.getActualPlayer()).setAdd(gCard.getCard());
        this.createExchangePanel();
    }

    public void movePopCard(GCard gCard) throws Exception {
        this.setMainPanel(); //aktuallisiert nicht richtig. Panel wird erst bei HUMAN move wieder angezeigt
        ((HumanPlayer) this.gameBoardState.getActualPlayer()).doMove(gCard.getCard());
    }

    public void showPanelDrawPileCards() {
        this.FRAME.setSAGPanel(this.panelDrawPileCards.getPanel());
        this.panelDrawPileCards.update();
    }

    public void showPanelDepositedCards() {
        this.FRAME.setSAGPanel(this.panelDepositedCards.getPanel());
        this.panelDepositedCards.update();
    }

    public void createExchangePanel() {
        this.FRAME.setSAGPanel(new PanelExchangeCards(this).getPanel());
    }

    public void createScorePanal() throws Exception {
        new GroupPopupScore(this);
    }

    public void createCheaterPanal(AbstractGamePlayer player) throws Exception {
        new GroupPopupCheater(this, player);
    }

    public void setMainPanel() {
        try {
            this.panelDrawPileCards.clear();
            this.panelDepositedCards.clear();
            //this.panelExchangeCards.clear();
            this.FRAME.setSAGPanel(this.mainPanel);
        } catch (ChildNotFoundException e) {
            System.out.println(e);
            System.exit(0);
        }
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
