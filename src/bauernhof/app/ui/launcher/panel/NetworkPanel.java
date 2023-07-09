package bauernhof.app.ui.launcher.panel;

import javax.swing.*;

import bauernhof.app.ui.launcher.Launcher;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class NetworkPanel {
    private Launcher launcher;
    private JPanel panel;
    private int heigth, width, status;

    private JButton buttonCreateServer, buttonJoinServer, buttonStartServer;

    public NetworkPanel(Launcher launcher, int width, int heigth) throws IOException {
        this.panel = new JPanel();
        this.heigth = heigth;
        this.width = width;
        this.launcher = launcher;

        this.panel.setPreferredSize(new Dimension(this.width, this.heigth));
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
        this.panel.setPreferredSize(new Dimension(this.width, this.heigth));
        this.panel.setLayout(null);
        this.addButtonReturn();
    }

    public void setSelect(boolean updateFrame) {
        this.status = 0;
        this.createNewPanal("multiSelect");
        this.addButtonCreateServer();
        this.addButtonCreateClient();
        if (updateFrame)
            this.launcher.setPanelNetworkPlayer();
    }

    public void setServer() {
        this.status = 1;
        this.createNewPanal("multiGameServer");
        this.launcher.setPanelNetworkPlayer();
    }

    public void setClient() {
        this.status = 2;
        this.createNewPanal("multiGameClient");
        this.launcher.setPanelNetworkPlayer();
    }

    public void doReturn() {
        if (this.status == 0)
            this.launcher.setPanelHome();
        else
            this.setSelect(true);
    }

    public void resetButtons() {
        this.buttonCreateServer.setVisible(false);
        this.buttonJoinServer.setVisible(false);
        this.buttonStartServer.setVisible(false);
    }

    public void addButtonReturn() {
        NetworkPanel networkPanel = this;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                networkPanel.doReturn();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(Launcher.debug);
        button.setBounds(994, 532, 260, 125);  

        this.panel.add(button);
    }

    public void addButtonCreateServer() {
        NetworkPanel networkPanel = this;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                networkPanel.setServer();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(Launcher.debug);
        button.setBounds(265, 248, 752, 100);

        this.panel.add(button);
    }

    public void addButtonCreateClient() {
        NetworkPanel networkPanel = this;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                networkPanel.setClient();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(Launcher.debug);
        button.setBounds(773, 361, 245, 100);

        this.panel.add(button);
    }




    public JPanel getPanel() {
        return this.panel;
    }
}
