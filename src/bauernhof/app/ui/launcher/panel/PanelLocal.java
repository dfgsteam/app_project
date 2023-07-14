package bauernhof.app.ui.launcher.panel;

import javax.swing.*;

import bauernhof.app.ui.launcher.UiLauncher;
import bauernhof.preset.PlayerType;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class PanelLocal {
    private UiLauncher launcher;
    private JPanel panel;
    private int heigth, width, playerCounter;

    final private String[] examPlayerNames = {"Florian", "Cemil", "Viktor", "Julius"};
    final private String[] playerTypeName = {"MacBook Pro", "Windows PC", "Viktors Laptop", "Mensch"};
    final private PlayerType[] playerTypeType = {PlayerType.ADVANCED_AI, PlayerType.SIMPLE_AI, PlayerType.RANDOM_AI, PlayerType.HUMAN};

    private JLabel playerTypeLabel[] = new JLabel[4];
    private JComboBox<String> playerTypeCombo[] = new JComboBox[4];
    private JLabel playerNameLabel[] = new JLabel[4];
    private JTextField playerNameField[] = new JTextField[4];

    private JButton playerDel[] = new JButton[4];

    private JButton playerAddButton = null;


    public PanelLocal(UiLauncher launcher, int width, int heigth) throws IOException {
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

        this.addButtonReturn();
        this.addButtonStartGame();

        this.addPlayer();
        this.addPlayer();
        this.addPlayer();
        this.addPlayer();
        this.addButtonAddPlayer();
    }




    public void addPlayer() {
        this.playerCounter ++;
        int xPos = 247+112*(this.playerCounter-1);
        
        
        JLabel addPlayerTypeLabel = new JLabel("Spielertyp:");
        addPlayerTypeLabel.setForeground(Color.white);
        addPlayerTypeLabel.setBounds(50, xPos, 85, 45); 
        this.panel.add(addPlayerTypeLabel);
        this.playerTypeLabel[this.playerCounter-1] = addPlayerTypeLabel;

        JComboBox<String> addPlayerTypeCombo = new JComboBox<>(this.playerTypeName);
        addPlayerTypeCombo.setBounds((int)(addPlayerTypeLabel.getX()+addPlayerTypeLabel.getSize().getWidth()+10), xPos, 200, 45);
        this.panel.add(addPlayerTypeCombo);
        this.playerTypeCombo[this.playerCounter-1] = addPlayerTypeCombo;

        JLabel addPlayerNameLabel = new JLabel("Name:");
        addPlayerNameLabel.setForeground(Color.white);
        addPlayerNameLabel.setBounds((int)(addPlayerTypeCombo.getX()+addPlayerTypeCombo.getSize().getWidth()+20), xPos, 50, 45);
        this.panel.add(addPlayerNameLabel);
        this.playerNameLabel[this.playerCounter-1] = addPlayerNameLabel;

        JTextField addPlayerNameField = new JTextField(this.examPlayerNames[this.playerCounter-1]);
        addPlayerNameField.setBounds((int)(addPlayerNameLabel.getX()+addPlayerNameLabel.getSize().getWidth()+10), xPos, 200, 45);
        this.panel.add(addPlayerNameField);
        this.playerNameField[this.playerCounter-1] = addPlayerNameField;

        if (this.playerCounter > 2) {
            JButton addPlayerDel = new JButton("Löschen");
            addPlayerDel.setBounds((int)(addPlayerNameField.getX()+addPlayerNameField.getSize().getWidth()+20), xPos, 100, 45);
            this.panel.add(addPlayerDel);
            this.playerDel[this.playerCounter-1] = addPlayerDel;
        }

        //this.addButtonAddPlayer();
    }

    public void removePlayer() {
        if (playerCounter == 1) 
            return;
        return;
    }

    public void addButtonAddPlayer() {
        return;
        // if (this.playerAddButton != null)
        //     this.playerAddButton.setVisible(false);
        // if (this.playerCounter + 1 != 5) {
        //     int xPos = 247+112*(this.playerCounter);
        //     this.playerAddButton = new JButton("Spieler hinzufügen");
        //     this.playerAddButton.setBounds(50, xPos, 575, 45);
        //     this.panel.add(this.playerAddButton);
        // }
        // this.launcher.setPanelLocalPlayer();
    }

    public void removeButtonAddPlayer() {
        if (this.playerCounter-1 > 2) 
            return;

    }

    public void addButtonReturn() {
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

    public JPanel getPanel() {
        return this.panel;
    }
}
