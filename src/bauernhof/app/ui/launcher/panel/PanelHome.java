package bauernhof.app.ui.launcher.panel;

import javax.swing.*;

import bauernhof.app.ui.launcher.UiLauncher;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class PanelHome {
    private UiLauncher uiLauncher;
    private JPanel panel;
    private int heigth, width;


    public PanelHome(UiLauncher uiLauncher, int width, int heigth) throws IOException {
        this.panel = new JPanel();
        this.heigth = heigth;
        this.width = width;
        this.uiLauncher = uiLauncher;

        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Zeichne den Hintergrund
                ImageIcon backgroundImage = new ImageIcon("graphics/launcher/main.jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        this.panel.setPreferredSize(new Dimension(this.width, this.heigth));
        this.panel.setLayout(null);

        
        JButton buttonLocalPlayer = this.addButtonLocalPlayer();
        this.panel.add(buttonLocalPlayer);

        JButton buttonNetworkPlayer = this.addButtonNetworkPlayer();
        this.panel.add(buttonNetworkPlayer);

        JButton buttonSettings = this.addButtonSettings();
        this.panel.add(buttonSettings);
        
    }

    public JPanel getButtons() {
        JPanel buttonPanal = new JPanel();
        buttonPanal.setPreferredSize(new Dimension(this.width, this.heigth));

        return buttonPanal;
    }

    public JButton addButtonLocalPlayer() {
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                uiLauncher.setPanelLocalPlayer();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(199, 520, 280, 125);  

        return button;
    }

    public JButton addButtonNetworkPlayer() {
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener) new ActionListener() {
            private UiLauncher uiLauncher;

            public void actionPerformed(ActionEvent e){
                this.uiLauncher.setPanelNetworkPlayer();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(500, 520, 280, 125);  

        return button;
    }

    public JButton addButtonSettings() {
        UiLauncher launcher = this.uiLauncher;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                launcher.setPanelSettings();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(803, 520, 280, 125);  

        return button;
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
