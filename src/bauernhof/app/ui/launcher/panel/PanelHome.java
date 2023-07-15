package bauernhof.app.ui.launcher.panel;

import javax.swing.*;

import bauernhof.app.ui.launcher.UiLauncher;

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
    private int height, width;

    /**
     * Constructs a new PanelHome object.
     *
     * @param uiLauncher The UiLauncher object that represents the launcher UI.
     * @param width      The width of the panel.
     * @param height     The height of the panel.
     * @throws IOException If an I/O error occurs.
     */
    public PanelHome(UiLauncher uiLauncher, int width, int height) throws IOException {
        this.panel = new JPanel();
        this.height = height;
        this.width = width;
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

        this.panel.setPreferredSize(new Dimension(this.width, this.height));
        this.panel.setLayout(null);

        JButton buttonLocalPlayer = this.addButtonLocalPlayer();
        this.panel.add(buttonLocalPlayer);

        JButton buttonNetworkPlayer = this.addButtonNetworkPlayer();
        this.panel.add(buttonNetworkPlayer);

        JButton buttonSettings = this.addButtonSettings();
        this.panel.add(buttonSettings);
    }

    /**
     * Creates a panel for buttons.
     *
     * @return The JPanel object representing the button panel.
     */
    public JPanel getButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(this.width, this.height));
        return buttonPanel;
    }

    /**
     * Adds a button for the local player.
     *
     * @return The JButton object representing the local player button.
     */
    public JButton addButtonLocalPlayer() {
        JButton button = new JButton();

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uiLauncher.setPanelLocalPlayer();
            }
        });

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

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uiLauncher.setPanelNetworkPlayer();
            }
        });

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
        UiLauncher launcher = this.uiLauncher;
        JButton button = new JButton();

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                launcher.setPanelSettings();
            }
        });

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
