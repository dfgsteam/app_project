package bauernhof.app.UI.launcher;

import javax.swing.*;

import java.io.IOException;


public class BaFr {

    final private int width = 1280;
    final private int heigth = 720;

    private JFrame frame;
    private HoPa homePanel;
    private SePa settingsPanel;
    private SiPl singlePlayer;
    private MuPl multiPlayer;


    public BaFr() throws IOException, InterruptedException {
        // Erzeuge Frame
        this.frame = new JFrame("Hofbauern");
        
        // Größe Frame
        this.frame.setResizable(false);
        this.frame.setSize(this.width, this.heigth);

        // Appicon
        ImageIcon img = new ImageIcon("graphics/bauernhof_logo.png");
        this.frame.setIconImage(img.getImage());
        this.frame.setLocationRelativeTo(null);  
        
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.frame.setVisible(true);  

        this.homePanel = new HoPa(this, this.width, this.heigth);
        this.settingsPanel = new SePa(this, this.width, this.heigth);
        this.singlePlayer = new SiPl(this, this.width, this.heigth);
        this.multiPlayer = new MuPl(this, this.width, this.heigth);
        
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

    public void setPanelSinglePlayer() {
        this.frame.setContentPane(this.singlePlayer.getPanel());
        this.updateFrame();
    }

    public void setPanelMultiPlayer() {
        this.frame.setContentPane(this.multiPlayer.getPanel());
        this.updateFrame();
    }
}