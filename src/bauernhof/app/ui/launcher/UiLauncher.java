package bauernhof.app.ui.launcher;

import javax.swing.*;

import bauernhof.app.settings.Se;
import bauernhof.app.ui.launcher.panel.*;

import java.io.IOException;


public class UiLauncher {

    // Button outline
    public static boolean debug = true;

    public final static int WIDTH = 1280;
    public final static int HEIGTH = 720;

    private JFrame frame;

    protected Se Settings;

    private PanelHome homePanel;
    private PanelSettings settingsPanel;
    private PanelLocal localPlayer;
    private PanelNetwork networkPanel;


    public UiLauncher(Se Settings) throws IOException, InterruptedException {
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
        // Thread.sleep(2000);
        //this.setPanelSettings();
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
        System.out.println("ok");
        this.frame.setContentPane(this.networkPanel.getPanel());
        this.updateFrame();
    }


    public Se getSettings() {
        return this.Settings;
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