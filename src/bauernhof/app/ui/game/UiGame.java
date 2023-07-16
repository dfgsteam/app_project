package bauernhof.app.ui.game;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.*;
import bauernhof.app.Init;
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
import javax.swing.JFrame;
/**
 * This class represents the game UI for the Hofbauern game.
 * @author Julius Hunold
 * @version 1.0
 * @since 2023-07-14
 */
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

    /**
     * Constructs a new UiGame object.
     *
     * @param gameconf      The GameConfiguration object representing the game configuration.
     * @param gameBoardState The GameBoardState object representing the game board state.
     * @throws Exception If an error occurs during initialization.
     */
    public UiGame(GameConfiguration gameconf, GameBoardState gameBoardState) throws Exception {
        this.gameBoardState = gameBoardState;

        // Initialize Frame
        this.FRAME.setSAGPanel(this.mainPanel);

        // Initialize Panels
        this.FRAME.setVisible(true);
        this.panelDepositedCards = new PanelDepositedCards(this);
        this.panelDrawPileCards = new PanelDrawPileCards(this);
        //this.panelExchangeCards = new PanelExchangeCards(this);

        // Initialize GGroups
        this.groupDisplayRound = new GroupDisplayRound(this);
        this.groupDisplayPlayerCards = new GroupDisplayPlayerCards(this);
        this.groupDisplayPlayerName = new GroupDisplayPlayerName(this);
        this.groupDisplayDrawPileDeck = new GroupDisplayDrawPileDeck(this);
        this.groupDisplayDepositedDeck = new GroupDisplayDepositedDeck(this);
        new PanelButtonScreenshot(this);
        new PanelButtonSaveGame(this);

        // Initialize playerCards
        for (int index = 0; index < this.gameBoardState.getPlayers().length; index++)
            this.groupDisplayPlayerCards.updatePlayer(index);
    }

    /**
     * Moves to the next turn in the game.
     *
     * @param last Determines if it is the last turn.
     * @throws Exception If an error occurs during the move.
     */
    public void move(boolean last) throws Exception {
        // Set current player as inactive
        this.groupDisplayPlayerName.updatePlayerBgInactive(this.playerId);

        // Clear Draw Pile and Deposited Deck
        this.groupDisplayDepositedDeck.clear();
        this.groupDisplayDrawPileDeck.clear();

        // Update player cards and points
        this.groupDisplayPlayerCards.updatePlayer(this.playerId);
        this.groupDisplayPlayerName.updatePlayerName(this.playerId);

        // Update Draw Pile and Deposited Deck
        this.groupDisplayDepositedDeck.update();
        this.groupDisplayDrawPileDeck.update();

        // If not the last turn
        if (!last) {
            // Set next player as active
            this.playerId = (this.playerId + 1) % this.gameBoardState.getPlayers().length;
            this.groupDisplayPlayerName.updatePlayerBgActive(this.playerId);
            this.groupDisplayRound.update();
        } else {
            // Show end of game panel
            this.playerId = 5;
            new GroupPopupScore(this);
        }
    }

    /**
     * Moves the selected card from the draw pile to the player's hand.
     *
     * @param gCard The GCard object representing the selected card.
     */
    public void moveAddCard(GCard gCard) {
       // ((HumanPlayer) this.gameBoardState.getActualPlayer()).setAdd(gCard.getCard());
        this.createExchangePanel();
    }

    /**
     * Moves the selected card from the player's hand to the discarded pile.
     *
     * @param gCard The GCard object representing the selected card.
     * @throws Exception If an error occurs during the move.
     */
    public void movePopCard(GCard gCard) throws Exception {
        this.setMainPanel(3); // Does not update correctly. Panel is only displayed properly during HUMAN move
     //   ((HumanPlayer) this.gameBoardState.getActualPlayer()).doMove(gCard.getCard());
    }

    /**
     * Shows the panel for the draw pile cards.
     *
     * @throws ChildNotFoundException If a child element is not found in the group.
     */
    public void showPanelDrawPileCards() throws ChildNotFoundException {
        this.groupDisplayDepositedDeck.clear(); // Clear reference to card in the deck
        this.FRAME.setSAGPanel(this.panelDrawPileCards.getPanel());
    }

    /**
     * Shows the panel for the discarded pile cards.
     *
     * @throws ChildNotFoundException If a child element is not found in the group.
     */
    public void showPanelDepositedCards() throws ChildNotFoundException {
        this.groupDisplayDepositedDeck.clear(); // Clear reference to card in the deck
        this.FRAME.setSAGPanel(this.panelDepositedCards.getPanel());
    }

    /**
     * Creates the exchange panel for exchanging cards between players.
     */
    public void createExchangePanel() {
        this.FRAME.setSAGPanel(new PanelExchangeCards(this).getPanel());
    }

    /**
     * Creates the score panel for displaying the final scores.
     *
     * @throws Exception If an error occurs during the creation of the panel.
     */
    public void createScorePanel() throws Exception {
        new GroupPopupScore(this);
    }

    /**
     * Creates the cheater panel for displaying the cheater information.
     *
     * @param player The AbstractGamePlayer object representing the cheater player.
     * @throws Exception If an error occurs during the creation of the panel.
     */
    public void createCheaterPanel(AbstractGamePlayer player) throws Exception {
        new GroupPopupCheater(this, player);
    }

    /**
     * Sets the main panel based on the specified value.
     *
     * @param v The value representing the panel to set. (1: DrawPile, 2: Deposited, 3: Exchange)
     */
    public void setMainPanel(int v) {
        System.out.println(v);
        try {
            if (v == 1) { // DrawPile
                this.panelDrawPileCards.clear(); // Clear reference to card in the panel
                this.groupDisplayDrawPileDeck.update();
            } else if (v == 2) { // Deposited
                this.panelDepositedCards.clear(); // Clear reference to card in the panel
                this.groupDisplayDepositedDeck.update();
            } else if (v == 3) { // Exchange
                System.out.println("test");
                //this.panelExchangeCards.clear();
            }
            this.FRAME.setSAGPanel(this.mainPanel); // Set the SAGPanel correctly

        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    /**
     * Creates a screenshot of the game UI and saves it as an image file.
     */
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

    /**
     * Saves the current game state.
     */
    public void saveGame() {
        System.out.println("saveGame");
    }

    /**
     * Checks if it is a human player's turn to make a move.
     *
     * @return True if it is a human player's turn, false otherwise.
     */
    public boolean check_move() {
        return this.gameBoardState.getPlayers()[this.playerId].getPlayerType() == PlayerType.HUMAN;
    }

    /**
     * Returns the current player ID.
     *
     * @return The player ID.
     */
    public int getPlayerId() {
        return this.playerId;
    }

    /**
     * Returns the main panel.
     *
     * @return The main SAGPanel object.
     */
    public SAGPanel getMainPanel() {
        return this.mainPanel;
    }

    /**
     * Returns the game board state.
     *
     * @return The GameBoardState object representing the game board state.
     */
    public GameBoardState getGameBoardState() {
        return this.gameBoardState;
    }


    public void closeUiGame() {
        JFrame.getFrames()[0].dispose();
        try {
            Init.main(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
