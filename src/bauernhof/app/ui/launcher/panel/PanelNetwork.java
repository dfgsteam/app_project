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

    // Different views //

    public void setSelect(boolean updateFrame) {
        this.status = 0;
        this.createNewPanal("multiSelect");
        this.addButtonCreateServer();
        this.addButtonCreateClient();
        this.addInputAdress();
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

    // Move //

    public void doReturn() {
        if (this.status == 0)
            this.uiLauncher.setPanelHome();
        else
            this.setSelect(true);
    }

    // Buttons //

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

    // Inputs//

    public void addInputAdress() {
        // IP Part 1
        JSpinner addInputAdressField1 = new JSpinner(new SpinnerNumberModel(127, 0, 255, 1));
        addInputAdressField1.setBounds(352, 405, 55, 22);
        this.panel.add(addInputAdressField1);


        // IP Part 2
        JSpinner addInputAdressField2 = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        addInputAdressField2.setBounds(419, 405, 55, 22);
        this.panel.add(addInputAdressField2);

        // IP Part 3
        JSpinner addInputAdressField3 = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        addInputAdressField3.setBounds(486, 405, 55, 22);
        this.panel.add(addInputAdressField3);

        // IP Part 4
        JSpinner addInputAdressField4 = new JSpinner(new SpinnerNumberModel(1, 0, 255, 1));
        addInputAdressField4.setBounds(553, 405, 55, 22);
        this.panel.add(addInputAdressField4);

        // IP Part 5 (Port)
        JSpinner addInputAdressField5 = new JSpinner(new SpinnerNumberModel(5000, 1, 10000, 1));
        addInputAdressField5.setBounds(620, 405, 70, 22);
        this.panel.add(addInputAdressField5);
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
