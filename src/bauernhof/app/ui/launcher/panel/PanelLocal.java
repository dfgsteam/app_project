package bauernhof.app.ui.launcher.panel;

import javax.swing.*;

import bauernhof.app.ui.launcher.UiLauncher;
import bauernhof.app.ui.launcher.listener.ListenerLocalAddUser;
import bauernhof.app.ui.launcher.listener.ListenerLocalDeleteUser;
import bauernhof.app.ui.launcher.listener.ListenerToHome;
import bauernhof.preset.PlayerType;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class PanelLocal {
    private UiLauncher uiLauncher;
    private JPanel panel;
    private int heigth, width, playerCounter = 0;

    final private String[] examPlayerNames = {"Florian", "Cemil", "Viktor", "Julius"};
    final private String[] playerTypeName = {"MacBook Pro", "Windows PC", "Viktors Laptop", "Mensch"};
    final private PlayerType[] playerTypeType = {PlayerType.ADVANCED_AI, PlayerType.SIMPLE_AI, PlayerType.RANDOM_AI, PlayerType.HUMAN};

    private JComboBox<String> playerTypeCombo[] = new JComboBox[4];
    private JTextField playerNameField[] = new JTextField[4];


    public PanelLocal(UiLauncher uiLauncher, int width, int heigth) throws IOException {
        this.panel = new JPanel();
        this.heigth = heigth;
        this.width = width;
        this.uiLauncher = uiLauncher;

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

        this.createPlayerView();
        //this.addButtonAddPlayer();
    }

    public void createPlayerView() {
        int xPos = 247;
        
        for (int index=0; index < 4; index ++) {
            // JLabel (Spielertyp)
            JLabel addPlayerTypeLabel = new JLabel("Spielertyp:");
            addPlayerTypeLabel.setForeground(Color.white);
            addPlayerTypeLabel.setBounds(50, xPos, 85, 45); 
            this.panel.add(addPlayerTypeLabel);

            // JComboBox -> Dropdown (Spielertyp)
            JComboBox<String> addPlayerTypeCombo = new JComboBox<>(this.playerTypeName);
            addPlayerTypeCombo.setBounds((int)(addPlayerTypeLabel.getX()+addPlayerTypeLabel.getSize().getWidth()+10), xPos, 200, 45);
            if (index > 1) {
                addPlayerTypeCombo.addItem("Keiner");
                addPlayerTypeCombo.addItemListener(new ListenerLocalAddUser(this, index));
            }
            this.panel.add(addPlayerTypeCombo);
            this.playerTypeCombo[index] = addPlayerTypeCombo;

            JLabel addPlayerNameLabel = new JLabel("Name:");
            addPlayerNameLabel.setForeground(Color.white);
            addPlayerNameLabel.setBounds((int)(addPlayerTypeCombo.getX()+addPlayerTypeCombo.getSize().getWidth()+20), xPos, 50, 45);
            this.panel.add(addPlayerNameLabel);

            JTextField addPlayerNameField = new JTextField(this.examPlayerNames[index]);
            addPlayerNameField.setBounds((int)(addPlayerNameLabel.getX()+addPlayerNameLabel.getSize().getWidth()+10), xPos, 200, 45);
            this.panel.add(addPlayerNameField);
            this.playerNameField[index] = addPlayerNameField;

            if (index > 1) {
                JButton addPlayerDelButton = new JButton("Löschen");
                addPlayerDelButton.setBounds((int)(addPlayerNameField.getX()+addPlayerNameField.getSize().getWidth()+20), xPos, 100, 45);
                addPlayerDelButton.addActionListener(new ListenerLocalDeleteUser(this, index));
                this.panel.add(addPlayerDelButton);
            }
            xPos += 112;
        }  
    }

    public void addPlayer(int playerId) {
        if (playerId < 2) 
            return;

        // Textfeld Prev + aktivieren
        this.playerNameField[playerId].setText(this.examPlayerNames[playerId]);
        this.playerNameField[playerId].setEditable(true);
    }

    public void removePlayer(int playerId) {
        if (playerId < 2) 
            return;

        // Auswahlbox auf Keine
        this.playerTypeCombo[playerId].setSelectedItem("Keiner");

        // Textfeld leer + deaktivieren
        this.playerNameField[playerId].setText("");
        this.playerNameField[playerId].setEditable(false);
    }

    public void removeButtonAddPlayer() {
        if (this.playerCounter-1 > 2) 
            return;

    }

    public void addButtonReturn() {
        // Erstelle den JButton
        JButton button = new JButton();

        // Füge Listener hinzu
        button.addActionListener(new ListenerToHome(this.uiLauncher));
        
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
