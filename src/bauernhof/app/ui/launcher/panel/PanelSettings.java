package bauernhof.app.ui.launcher.panel;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bauernhof.app.ui.launcher.UiLauncher;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class PanelSettings {
    private UiLauncher launcher;
    private JPanel panel;

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

    public JPanel getPanel() {
        return this.panel;
    }
}
