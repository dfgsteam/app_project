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

    public void updateFrame() {
        SwingUtilities.updateComponentTreeUI(this.frame);
    }

    public void setPanelHome() {
        this.frame.setContentPane(this.homePanel.getPanel());
        this.updateFrame();
    }

    public void setPanelSettings() {
        this.frame.setContentPane(this.settingsPanel.getPanel());
        this.updateFrame();
    }

    public void setPanelLocalPlayer() {
        this.frame.setContentPane(this.localPlayer.getPanel());
        this.updateFrame();
    }

    public void setPanelNetworkPlayer() {
        this.frame.setContentPane(this.networkPanel.getPanel());
        this.updateFrame();
    }

    public void setDeckInvalid() {
        JOptionPane.showMessageDialog(null, "Dein gespeichertes Kartendeck ist nicht im Ornder. Solange wird ein anderes Deck verwendet.\nDeine Einstellungen wurden nicht überschrieben.", "Fehler", JOptionPane.INFORMATION_MESSAGE);
        this.Settings.setGameConf(this.Settings.getGameConfs().iterator().next()); // setze eine neue GameConfiguration
    }

    public LauncherSettings getSettings() {
        return this.Settings;
    }

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

    public void startLocalGame() {
        System.out.println("startLocalGame");
    }

    public void startNetworkHostGame() {
        System.out.println("startNetworkHostGame");
    }

    public void startNetworkClientGame() {
        System.out.println("startNetworkClientGame");
    }

}