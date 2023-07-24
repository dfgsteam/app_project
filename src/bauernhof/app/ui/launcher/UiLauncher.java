package bauernhof.app.ui.launcher;

import javax.swing.*;

import bauernhof.app.Init;
import bauernhof.app.launcher.LauncherSettings;
import bauernhof.app.ui.launcher.panel.*;
import bauernhof.preset.LogLevel;
import bauernhof.preset.PlayerType;
import bauernhof.preset.Settings;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The UiLauncher class represents the user interface for the game launcher.
 * It provides a graphical interface for the user to configure game settings, select player types and colors,
 * and start different game modes (local or network-based).
 *
 * The launcher allows the user to set various configurations before starting the game, such as player types,
 * player names, player colors, sound settings, and other game-specific options.
 *
 * The class includes methods to update the frame, set different panels for different launcher states (e.g., home, settings, local player, network player),
 * and start the game with the selected configurations.
 *
 * @see LauncherSettings
 * @see PanelHome
 * @see PanelSettings
 * @see PanelLocal
 * @see PanelNetwork
 * @see bauernhof.preset.Settings
 * @see Init
 * @author Julius Hunold
 * @version 1.0
 */

public class UiLauncher {

    // Button outline
    public static boolean debug = false;

    public final static int WIDTH = 1280;
    public final static int HEIGTH = 720;

    private JFrame frame;

    protected LauncherSettings Settings;

    private PanelHome homePanel;
    private PanelSettings settingsPanel;
    private PanelLocal localPlayer;
    private PanelNetwork networkPanel;

    /**
     * Constructs a new UiLauncher instance.
     * It initializes the game launcher by creating the main frame, setting its properties, and creating the panels for different launcher states.
     * The launcher settings are provided as input to configure the game launcher.
     *
     * @param Settings The LauncherSettings object containing configurations for the game launcher.
     * @throws IOException If there is an I/O error while reading game configurations or creating the main frame.
     * @throws InterruptedException If the current thread is interrupted during the execution.
     */

    public UiLauncher(LauncherSettings Settings) throws IOException, InterruptedException {
        // Erzeuge Frame
        this.frame = new JFrame("Hofbauern");
        
        // Größe Frame
        this.frame.setResizable(false);
        this.frame.setSize(UiLauncher.WIDTH, UiLauncher.HEIGTH);

        // Appicon
        ImageIcon img = new ImageIcon("graphics/bauernhof_logo.png");
        this.frame.setIconImage(img.getImage());
        this.frame.setLocationRelativeTo(null);  
        
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.frame.setVisible(true);  

        this.Settings = Settings;

        this.homePanel = new PanelHome(this);
        this.settingsPanel = new PanelSettings(this);
        this.localPlayer = new PanelLocal(this);
        this.networkPanel = new PanelNetwork(this);
        
        this.setPanelHome(); 
        
        // Wenn GameConfiguration aus Settingsparser = null -> Warnung -> setze eine neue GameConfiguration
        if (this.Settings.getGameConf() == null) {
            this.setDeckInvalid();
        }
    }

    /**
     * Updates the frame of the game launcher.
     * This method is used to refresh the UI components and apply any changes made during runtime.
     */

    public void updateFrame() {
        SwingUtilities.updateComponentTreeUI(this.frame);
    }

    /**
     * Sets the home panel as the current content of the game launcher frame.
     * The home panel displays the main menu of the launcher, where the user can start a new game or configure settings.
     */

    public void setPanelHome() {
        this.frame.setContentPane(this.homePanel.getPanel());
        this.updateFrame();
    }

    /**
     * Sets the settings panel as the current content of the game launcher frame.
     * The settings panel allows the user to configure various game settings, such as sound and log level.
     */

    public void setPanelSettings() {
        this.frame.setContentPane(this.settingsPanel.getPanel());
        this.updateFrame();
    }

    /**
     * Sets the local player panel as the current content of the game launcher frame.
     * The local player panel allows the user to select player types and colors for a local game.
     */

    public void setPanelLocalPlayer() {
        this.frame.setContentPane(this.localPlayer.getPanel());
        this.updateFrame();
    }

    /**
     * Sets the network player panel as the current content of the game launcher frame.
     * The network player panel allows the user to set up a network game as a host or client.
     */

    public void setPanelNetworkPlayer() {
        this.frame.setContentPane(this.networkPanel.getPanel());
        this.updateFrame();
    }

    /**
     * Displays a message dialog indicating that the current deck is invalid or missing.
     * This method informs the user that a default deck will be used, and the user's settings will not be overridden.
     */

    public void setDeckInvalid() {
        JOptionPane.showMessageDialog(null, "Dein gespeichertes Kartendeck ist nicht im Ornder. Solange wird ein anderes Deck verwendet.\nDeine Einstellungen wurden nicht überschrieben.", "Fehler", JOptionPane.INFORMATION_MESSAGE);
        this.Settings.setGameConf(this.Settings.getGameConfs().iterator().next()); // setze eine neue GameConfiguration
    }

    /**
     * Retrieves the launcher settings from the game launcher.
     *
     * @return The LauncherSettings object containing configurations for the game launcher.
     */

    public LauncherSettings getSettings() {
        return this.Settings;
    }

   /**
     * Starts the game with the specified configurations.
     * This method initiates the game initialization and launches the game based on the selected player types, names, and colors.
     *
     * @param type The type of game to start (e.g., local or network-based).
     * @param playerTypes An array of PlayerType representing the selected player types.
     * @param playerNames An array of Strings representing the names of the players.
     * @param playerColors An array of Colors representing the selected colors for each player.
     */

    public void startGame(String type, PlayerType[] playerTypes, String[] playerNames, Color[] playerColors) {
        try {
            this.frame.setVisible(false);
            this.frame.dispose();
            bauernhof.preset.Settings settings = new Settings();
            settings.delay = 1000;
            settings.showGUI = true;
            settings.volume = Settings.getSound();
            settings.logLevel = LogLevel.INFO;
            settings.playerNames = List.of(playerNames);
            settings.playerColors = List.of(playerColors);
            settings.playerTypes = List.of(playerTypes);
            settings.gameConfigurationFile = new File("gameconfigs/bauernhof.xml");
            settings.delay = 1000L;
            settings.showGUI = true;
            settings.connectToHostname = null;
            settings.port = 6600;
            settings.loadSaveGameFile = null;
            settings.shouldLauncherLaunch = false;
            settings.numTournamentRounds = 0;
            settings.waitAfterTournamentRound = false;
            settings.volume = 0;
            Init.initGame(settings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts a local game with the specified configurations.
     * This method is called when the user selects to start a local game from the launcher.
     */

    public void startLocalGame() {
        System.out.println("startLocalGame");
    }

    /**
     * Starts a network-hosted game with the specified configurations.
     * This method is called when the user selects to start a network game as the host from the launcher.
     */

    public void startNetworkHostGame() {
        System.out.println("startNetworkHostGame");
    }

    /**
     * Starts a network client game with the specified configurations.
     * This method is called when the user selects to start a network game as a client from the launcher.
     */
    
    public void startNetworkClientGame() {
        System.out.println("startNetworkClientGame");
    }

}