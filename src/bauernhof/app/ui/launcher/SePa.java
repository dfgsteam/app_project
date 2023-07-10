package bauernhof.app.ui.launcher;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class SePa {
    private BaFr BasicFrame;
    private JPanel panel;
    private int heigth, width;

    public SePa(BaFr BasicFrame, int width, int heigth) throws IOException {
        this.panel = new JPanel();
        this.heigth = heigth;
        this.width = width;
        this.BasicFrame = BasicFrame;

        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Zeichne den Hintergrund
                ImageIcon backgroundImage = new ImageIcon("graphics/settings.jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        this.panel.setPreferredSize(new Dimension(this.width, this.heigth));
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
        BaFr BasicFrame = this.BasicFrame;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                BasicFrame.setPanelHome();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBounds(993, 530, 260, 125);  

        return button;
    }

    public JComboBox<?> addComboBoxCards() {
        JComboBox<String> comboBoxCards = new JComboBox<>(BasicFrame.Settings.getGameConfs().toArray(new String[0]));

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
        JSlider slider = new JSlider(0, 10, BasicFrame.Settings.getSound());
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
                    BasicFrame.Settings.setSound(value, true);
                } catch (Exception ex) {
                    System.out.print(ex);
                }
            }
        });

        return slider;
    }

    public JSlider addAiSlider() {
        JSlider slider = new JSlider(1, 4, BasicFrame.Settings.getAi());
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
                    BasicFrame.Settings.setAi(value, true);
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
