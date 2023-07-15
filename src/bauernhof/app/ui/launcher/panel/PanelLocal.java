package bauernhof.app.ui.launcher.panel;

import javax.swing.*;

import bauernhof.app.ui.launcher.UiLauncher;
import bauernhof.app.ui.launcher.listener.ListenerLocalAddUser;
import bauernhof.app.ui.launcher.listener.ListenerLocalDeleteUser;
import bauernhof.app.ui.launcher.listener.ListenerStartLocalGame;
import bauernhof.app.ui.launcher.listener.ListenerToHome;
import bauernhof.preset.PlayerType;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class PanelLocal {
    private static final String ArrayList = null;
    private UiLauncher uiLauncher;
    private JPanel panel;

    final private String[] examPlayerNames = {"Florian", "Cemil", "Viktor", "Julius"};
    final private String[] playerTypeName = {"Computer Einfach", "Computer Mittel", "Computer Schwer", "Mensch"};
    final private PlayerType[] playerTypeType = {PlayerType.RANDOM_AI, PlayerType.SIMPLE_AI, PlayerType.ADVANCED_AI, PlayerType.HUMAN};
    final private String[] playerColorName = {"Rot", "Magenta", "Blau", "Orange", "Cyan", "Pink"};
    final private Color[] playerColorType = {Color.RED, Color.MAGENTA, Color.BLUE, Color.ORANGE, Color.CYAN, Color.PINK};

    private JComboBox<String> playerTypeCombo[] = new JComboBox[4];
    private JTextField playerNameField[] = new JTextField[4];
    private JComboBox<String> playerColorCombo[] = new JComboBox[4];


    public PanelLocal(UiLauncher uiLauncher) throws IOException {
        // init Klassenvariablen
        this.uiLauncher = uiLauncher;

        // JPanel erstellen
        this.panel = new JPanel() {

            // Erstelle Hintergrundbild
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Zeichne den Hintergrund
                ImageIcon backgroundImage = new ImageIcon("graphics/launcher/local.jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // JPanel Einstellungen
        this.panel.setPreferredSize(new Dimension(UiLauncher.WIDTH, UiLauncher.HEIGTH));
        this.panel.setLayout(null);

        // Füge Buttons hinzu
        this.addButtonReturn();
        this.addButtonStartGame();

        this.createPlayerView();
        //this.addButtonAddPlayer();
    }

    public void createPlayerView() {
        int xPos = 247; // xPos wird pro Player statisch verändert. Die yPos wird anhand deinzelnen Objektlängen + 5px/15px Abstand berechnet
        
        // Erstelle für jeden Spieler inputs
        for (int index=0; index < 4; index ++) { // playerId = 0, 1, 2, 3
            // Spielertyp JLabel
            JLabel addPlayerTypeLabel = new JLabel("Spielertyp:");
            addPlayerTypeLabel.setForeground(Color.white);
            addPlayerTypeLabel.setBounds(45, xPos, 68, 45); 
            this.panel.add(addPlayerTypeLabel);

            // Spielertyp JComboBox -> Dropdown
            JComboBox<String> addPlayerTypeCombo = new JComboBox<>(this.playerTypeName);
            addPlayerTypeCombo.setBounds((int)(addPlayerTypeLabel.getX()+addPlayerTypeLabel.getSize().getWidth()+5), xPos, 170, 45);
            if (index > 1) {
                addPlayerTypeCombo.addItem("Keiner");
                addPlayerTypeCombo.addItemListener(new ListenerLocalAddUser(this, index));
            }
            this.panel.add(addPlayerTypeCombo);
            this.playerTypeCombo[index] = addPlayerTypeCombo;

            // Spielernamen Label
            JLabel addPlayerNameLabel = new JLabel("Name:");
            addPlayerNameLabel.setForeground(Color.white);
            addPlayerNameLabel.setBounds((int)(addPlayerTypeCombo.getX()+addPlayerTypeCombo.getSize().getWidth()+15), xPos, 40, 45);
            this.panel.add(addPlayerNameLabel);

            // Spielernamen JComboBox -> Dropdown
            JTextField addPlayerNameField = new JTextField(this.examPlayerNames[index]);
            addPlayerNameField.setBounds((int)(addPlayerNameLabel.getX()+addPlayerNameLabel.getSize().getWidth()+5), xPos, 120, 45);
            this.panel.add(addPlayerNameField);
            this.playerNameField[index] = addPlayerNameField;

            // Spielerfarbe Label
            JLabel addPlayerColoLabel = new JLabel("Farbe:");
            addPlayerColoLabel.setForeground(Color.white);
            addPlayerColoLabel.setBounds((int)(addPlayerNameField.getX()+addPlayerNameField.getSize().getWidth()+15), xPos, 40, 45);
            this.panel.add(addPlayerColoLabel);

            // Spielerfarbe JComboBox -> Dropdown
            JComboBox<String> addPlayerColorCombo = new JComboBox<>(this.playerColorName);
            addPlayerColorCombo.setSelectedIndex(index);
            addPlayerColorCombo.setBounds((int)(addPlayerColoLabel.getX()+addPlayerColoLabel.getSize().getWidth()+5), xPos, 120, 45);
            this.panel.add(addPlayerColorCombo);
            this.playerColorCombo[index] = addPlayerColorCombo;

            // Delete Button
            if (index > 1) { // 2 Spieler sind verpflichtend -> Spieler 1, 2 kann man löschen
                JButton addPlayerDelButton = new JButton("Löschen");
                addPlayerDelButton.setBounds((int)(addPlayerColorCombo.getX()+addPlayerColorCombo.getSize().getWidth()+15), xPos, 100, 45);
                addPlayerDelButton.addActionListener(new ListenerLocalDeleteUser(this, index));
                this.panel.add(addPlayerDelButton);
            }

            // Nächste Zeile
            xPos += 112;
        }  
    }

    public void readAllPlayer() {
        ArrayList<PlayerType> playerTypes = new ArrayList<>(4);
        ArrayList<String> playerNames = new ArrayList<>(4);
        ArrayList<Color> playerColor = new ArrayList<>(4);
        for (int index=0; index < 4; index++) {
            if (!(this.playerTypeCombo[index].getSelectedItem() == "Keiner")) {
                playerTypes.add(this.playerTypeType[index]);
                playerNames.add(this.playerNameField[index].getText());
                playerColor.add(this.playerColorType[index]);
            }
        }

        for (int index=0; index < playerTypes.size(); index++) {
            System.out.println(Integer.toString(index) + ": " + playerTypes.get(index) + " " + playerNames.get(index) + " " + playerColor.get(index));
        }
    }
    
    public void addPlayer(int playerId) {
        // Nur Spieler 2, 3 
        if (playerId < 2) 
            return;

        // Textfeld Prev + aktivieren
        this.playerNameField[playerId].setText(this.examPlayerNames[playerId]);
        this.playerNameField[playerId].setEditable(true);

        // Farbe aktivieren
        this.playerColorCombo[playerId].setEditable(true);
    }

    public void removePlayer(int playerId) {
        // Nur Spieler 2, 3 
        if (playerId < 2) 
            return;

        // Auswahlbox auf Keine
        this.playerTypeCombo[playerId].setSelectedItem("Keiner");

        // Textfeld leer + deaktivieren
        this.playerNameField[playerId].setText("");
        this.playerNameField[playerId].setEditable(false);

        // Farbe deaktivieren
        this.playerColorCombo[playerId].setEditable(false);
    }

    public void addButtonReturn() {
        // Erstelle den JButton
        JButton button = new JButton();

        // Füge Listener hinzu
        button.addActionListener(new ListenerToHome(this.uiLauncher));
        
        // Styles
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(994, 532, 260, 125);  

        // Füge Button Panel hinzu
        this.panel.add(button);
    }

    public void addButtonStartGame() {
        // Erstelle den JButton
        JButton button = new JButton();

        // Füge Listener hinzu
        button.addActionListener(new ListenerStartLocalGame(this));
        
        // Styles
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(995, 280, 261, 125);

        // Füge Button Panel hinzu
        this.panel.add(button);
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
