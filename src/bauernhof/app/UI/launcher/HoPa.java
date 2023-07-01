package bauernhof.app.UI.launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class HoPa {
    private BaFr BasicFrame;
    private JPanel panel;
    private int heigth, width;


    public HoPa(BaFr BasicFrame, int width, int heigth) throws IOException {
        this.panel = new JPanel();
        this.heigth = heigth;
        this.width = width;
        this.BasicFrame = BasicFrame;

        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Zeichne den Hintergrund
                ImageIcon backgroundImage = new ImageIcon("graphics/home.jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        this.panel.setPreferredSize(new Dimension(this.width, this.heigth));
        this.panel.setLayout(null);

        JButton buttonSinglePlayer = this.addButtonSinglePlayer();
        this.panel.add(buttonSinglePlayer);

        JButton buttonMultiPlayer = this.addButtonMultiPlayer();
        this.panel.add(buttonMultiPlayer);

        JButton buttonSettings = this.addButtonSettings();
        this.panel.add(buttonSettings);
        
    }

    public JPanel getButtons() {
        JPanel buttonPanal = new JPanel();
        buttonPanal.setPreferredSize(new Dimension(this.width, this.heigth));

        return buttonPanal;
    }

    public JButton addButtonSinglePlayer() {
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                BasicFrame.setPanelSinglePlayer();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBounds(199, 520, 280, 125);  

        return button;
    }

    public JButton addButtonMultiPlayer() {
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                BasicFrame.setPanelMultiPlayer();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBounds(500, 520, 280, 125);  

        return button;
    }

    public JButton addButtonSettings() {
        BaFr BasicFrame = this.BasicFrame;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                BasicFrame.setPanelSettings();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBounds(803, 520, 280, 125);  

        return button;
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
