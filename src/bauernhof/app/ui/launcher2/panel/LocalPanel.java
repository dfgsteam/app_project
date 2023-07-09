package bauernhof.app.ui.launcher2.panel;

import javax.swing.*;

import bauernhof.app.ui.launcher2.Launcher;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LocalPanel {
    private Launcher launcher;
    private JPanel panel;
    private int heigth, width;

    public LocalPanel(Launcher launcher, int width, int heigth) throws IOException {
        this.panel = new JPanel();
        this.heigth = heigth;
        this.width = width;
        this.launcher = launcher;

        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Zeichne den Hintergrund
                ImageIcon backgroundImage = new ImageIcon("graphics/launcher/local.jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        this.panel.setPreferredSize(new Dimension(this.width, this.heigth));
        this.panel.setLayout(null);

        JButton buttonReturn = this.addButtonReturn();
        this.panel.add(buttonReturn);
    }





    public JButton addButtonReturn() {
        Launcher launcher = this.launcher;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                launcher.setPanelHome();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(Launcher.debug);
        button.setBounds(994, 532, 260, 125);  

        return button;
    }


    public JPanel getPanel() {
        return this.panel;
    }
}
