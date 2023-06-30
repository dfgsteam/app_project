package bauernhof.app.ui.launcher;

import javax.swing.*;
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
        JComboBox<String> comboBoxCards = new JComboBox<>(BasicFrame.Settings.getCardSets().toArray(new String[0]));

        // comboBoxCards.setSelectedIndex(4);
        // comboBoxCards.addActionListener((ActionListener ) new ActionListener() {
        //     public void actionPerformed(ActionEvent e){
        //         System.out.println(e);
        //     }
        // });
        comboBoxCards.setBounds(315, 265, 250, 50);


        return comboBoxCards;
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
