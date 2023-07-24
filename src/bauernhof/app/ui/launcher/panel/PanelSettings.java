package bauernhof.app.ui.launcher.panel;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bauernhof.app.ui.launcher.UiLauncher;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * The PanelSettings class represents the settings panel of the game launcher.
 * It provides a graphical interface for the user to configure various game settings, such as sound volume and AI difficulty.
 *
 * The settings panel allows the user to adjust the game configurations before starting a new game.
 * It includes options to change the selected card deck, adjust sound volume, and set the AI difficulty level.
 *
 * The class includes methods to add buttons, sliders, and combo boxes to the panel for user interaction and configuration.
 *
 * @see UiLauncher
 * @author Julius Hunold
 * @version 1.0
 */

public class PanelSettings {
    private UiLauncher launcher;
    private JPanel panel;

    /**
     * Constructs a new PanelSettings instance.
     * It initializes the settings panel and creates UI components such as buttons, combo boxes, and sliders for user interaction.
     *
     * @param launcher The UiLauncher object associated with the settings panel.
     * @throws IOException If there is an I/O error while loading graphics resources or updating settings.
     */

    public PanelSettings(UiLauncher launcher) throws IOException {
        this.launcher = launcher;

        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Zeichne den Hintergrund
                ImageIcon backgroundImage = new ImageIcon("graphics/launcher/settings.jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        this.panel.setPreferredSize(new Dimension(UiLauncher.WIDTH, UiLauncher.HEIGTH));
        this.panel.setLayout(null);

        JButton buttonReturn = this.addButtonReturn();
        this.panel.add(buttonReturn);

        JComboBox<?> comboBoxCards = this.addComboBoxCards();
        this.panel.add(comboBoxCards);

        JSlider sound = this.addSoundSlider();
        this.panel.add(sound);

        JSlider ai = this.addAiSlider();
        this.panel.add(ai);

    }

    /**
     * Creates and returns a JButton to navigate back to the home panel.
     * The button is configured with an ActionListener to handle the return action.
     *
     * @return The JButton for returning to the home panel.
     */

    public JButton addButtonReturn() {
        UiLauncher launcher = this.launcher;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                launcher.setPanelHome();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(994, 532, 260, 125);  

        return button;
    }

    /**
     * Creates and returns a JComboBox for selecting different card decks.
     * The combo box allows the user to choose from available game configurations.
     * It includes an ItemListener to handle the selection change event.
     *
     * @return The JComboBox for selecting card decks.
     */

    public JComboBox<?> addComboBoxCards() {
        JComboBox<String> comboBoxCards = new JComboBox<>(launcher.getSettings().getGameConfs().toArray(new String[0]));

        comboBoxCards.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    // Get the selected item
                    String selectedItem = comboBoxCards.getSelectedItem().toString();
                    
                    // Show the selected item in a label
                    System.out.println("Selected Item: " + selectedItem);
                }
            }
        });
        comboBoxCards.setBounds(315, 265, 250, 50);


        return comboBoxCards;
    }

    /**
     * Creates and returns a JSlider for adjusting the sound volume.
     * The slider allows the user to set the sound volume level.
     * It includes a ChangeListener to handle the slider value changes and update the sound settings.
     *
     * @return The JSlider for adjusting sound volume.
     */

    public JSlider addSoundSlider() {
        JSlider slider = new JSlider(0, 10, launcher.getSettings().getSound());
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.setBounds(315, 420, 250, 50);

        // Add a change listener to handle slider value changes
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                try {
                    launcher.getSettings().setSound(value, true);
                } catch (Exception ex) {
                    System.out.print(ex);
                }
            }
        });

        return slider;
    }

    /**
     * Creates and returns a JSlider for adjusting the AI difficulty level.
     * The slider allows the user to set the AI difficulty from 1 to 4.
     * It includes a ChangeListener to handle the slider value changes and update the AI settings.
     *
     * @return The JSlider for adjusting AI difficulty.
     */

    public JSlider addAiSlider() {
        JSlider slider = new JSlider(1, 4, launcher.getSettings().getAi());
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.setBounds(500, 570, 250, 50);

        // Add a change listener to handle slider value changes
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                try {
                    launcher.getSettings().setAi(value, true);
                } catch (Exception ex) {
                    System.out.print(ex);
                }
            }
        });

        return slider;
    }

    /**
     * Retrieves the settings panel.
     *
     * @return The JPanel representing the settings panel.
     */
    
    public JPanel getPanel() {
        return this.panel;
    }
}
