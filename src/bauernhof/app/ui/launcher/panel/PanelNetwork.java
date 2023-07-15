package bauernhof.app.ui.launcher.panel;

import javax.swing.*;

import bauernhof.app.ui.launcher.UiLauncher;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class PanelNetwork {
    private UiLauncher uiLauncher;
    private JPanel panel;
    private int status;

    private JButton buttonCreateServer, buttonJoinServer, buttonStartServer;

    public PanelNetwork(UiLauncher uiLauncher) throws IOException {
        this.panel = new JPanel();
        this.uiLauncher = uiLauncher;

        this.panel.setPreferredSize(new Dimension(UiLauncher.HEIGTH, UiLauncher.WIDTH));
        this.panel.setLayout(null);

        this.setSelect(false);
    }

    private void createNewPanal(String bg) {
        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Zeichne den Hintergrund
                ImageIcon backgroundImage = new ImageIcon("graphics/launcher/" + bg + ".jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }  
        };
        this.panel.setPreferredSize(new Dimension(UiLauncher.WIDTH, UiLauncher.HEIGTH));
        this.panel.setLayout(null);
        this.addButtonReturn();
    }

    public void setSelect(boolean updateFrame) {
        this.status = 0;
        this.createNewPanal("multiSelect");
        this.addButtonCreateServer();
        this.addButtonCreateClient();
        if (updateFrame)
            this.uiLauncher.setPanelNetworkPlayer();
    }

    public void setServer() {
        this.status = 1;
        this.createNewPanal("multiGameServer");
        this.addButtonStartGame();
        this.uiLauncher.setPanelNetworkPlayer();
    }

    public void setClient() {
        this.status = 2;
        this.createNewPanal("multiGameClient");
        this.uiLauncher.setPanelNetworkPlayer();
    }

    public void doReturn() {
        if (this.status == 0)
            this.uiLauncher.setPanelHome();
        else
            this.setSelect(true);
    }

    public void addButtonReturn() {
        PanelNetwork networkPanel = this;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                networkPanel.doReturn();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(994, 532, 260, 125);  

        this.panel.add(button);
    }

    public void addButtonCreateServer() {
        PanelNetwork networkPanel = this;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                networkPanel.setServer();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(265, 248, 752, 100);

        this.panel.add(button);
    }

    public void addButtonCreateClient() {
        PanelNetwork networkPanel = this;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                networkPanel.setClient();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(773, 361, 245, 100);

        this.panel.add(button);
    }

    public void addButtonStartGame() {
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Start");
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(995, 280, 261, 125);

        this.panel.add(button);
    }


    public JPanel getPanel() {
        return this.panel;
    }
}
