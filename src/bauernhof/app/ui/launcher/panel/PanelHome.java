package bauernhof.app.ui.launcher.panel;

import javax.swing.*;

import bauernhof.app.ui.launcher.UiLauncher;
import bauernhof.app.ui.launcher.listener.ListenerToHome;
import bauernhof.app.ui.launcher.listener.ListenerToLocal;
import bauernhof.app.ui.launcher.listener.ListenerToNetwork;
import bauernhof.app.ui.launcher.listener.ListenerToSettings;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * This class represents the home panel in the launcher UI.
 * @author [Your Name]
 * @version 1.0
 * @since 2023-07-14
 */

public class PanelHome {
    private UiLauncher uiLauncher;
    private JPanel panel;

    /**
     * Constructs a new PanelHome object.
     *
     * @param uiLauncher The UiLauncher object that represents the launcher UI.
     * @throws IOException If an I/O error occurs.
     */
    public PanelHome(UiLauncher uiLauncher) throws IOException {
        this.uiLauncher = uiLauncher;

        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Draw the background image
                ImageIcon backgroundImage = new ImageIcon("graphics/launcher/main.jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        this.panel.setPreferredSize(new Dimension(UiLauncher.WIDTH, UiLauncher.HEIGTH));
        this.panel.setLayout(null);

        JButton buttonLocalPlayer = this.addButtonLocalPlayer();
        this.panel.add(buttonLocalPlayer);

        JButton buttonNetworkPlayer = this.addButtonNetworkPlayer();
        this.panel.add(buttonNetworkPlayer);

        JButton buttonSettings = this.addButtonSettings();
        this.panel.add(buttonSettings);
    }

    /**
     * Adds a button for the local player.
     *
     * @return The JButton object representing the local player button.
     */
    public JButton addButtonLocalPlayer() {
        JButton button = new JButton();

        button.addActionListener(new ListenerToLocal(this.uiLauncher));
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(199, 520, 280, 125);

        return button;
    }

    /**
     * Adds a button for the network player.
     *
     * @return The JButton object representing the network player button.
     */
    public JButton addButtonNetworkPlayer() {
        JButton button = new JButton();

        button.addActionListener(new ListenerToNetwork(this.uiLauncher));
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(500, 520, 280, 125);

        return button;
    }

    /**
     * Adds a button for the settings.
     *
     * @return The JButton object representing the settings button.
     */
    public JButton addButtonSettings() {
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener(new ListenerToSettings(this.uiLauncher));
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(803, 520, 280, 125);

        return button;
    }

    /**
     * Returns the panel object.
     *
     * @return The JPanel object representing the panel.
     */
    public JPanel getPanel() {
        return this.panel;
    }
}
