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

/**
 * The PanelLocal class represents the local player panel of the game launcher.
 * It provides a graphical interface for the user to configure local player options, such as player types, names, and colors.
 *
 * The local player panel allows the user to set up and configure local players for a single-player or local multiplayer game.
 * It includes options to choose player types (e.g., computer AI or human) and set player names and colors.
 *
 * The class includes methods to add buttons, input fields, and labels to the panel for user interaction and configuration.
 *
 * Note: The PanelLocal may throw IOException when reading graphics resources.
 *
 * @see UiLauncher
 * @author Julius Hunold
 * @version 1.0
 */

public class PanelLocal {
    private UiLauncher uiLauncher;
    private JPanel panel;

    final private String[] examPlayerNames = {"Florian", "Cemil", "Viktor", "Kirill"};
    final private String[] playerTypeName = {"Computer Einfach", "Computer Mittel", "Computer Schwer", "Mensch"};
    final private PlayerType[] playerTypeType = {PlayerType.RANDOM_AI, PlayerType.SIMPLE_AI, PlayerType.ADVANCED_AI, PlayerType.HUMAN};
    final private String[] playerColorName = {"Rot", "Magenta", "Blau", "Orange", "Cyan", "Pink"};
    final private Color[] playerColorType = {Color.RED, Color.MAGENTA, Color.BLUE, Color.ORANGE, Color.CYAN, Color.PINK};

    @SuppressWarnings("unchecked")
    private JComboBox<String> playerTypeCombo[] = new JComboBox[4];
    private JTextField playerNameField[] = new JTextField[4];
    @SuppressWarnings("unchecked")
    private JComboBox<String> playerColorCombo[] = new JComboBox[4];

    /**
     * Constructs a new PanelLocal instance.
     * It initializes the local player panel and creates UI components such as buttons, input fields, and labels for player configuration.
     *
     * @param uiLauncher The UiLauncher object associated with the local player panel.
     * @throws IOException If there is an I/O error while loading graphics resources.
     */

    public PanelLocal(UiLauncher uiLauncher) throws IOException {
        // Init classvariables
        this.uiLauncher = uiLauncher;

        // create JPanel
        this.panel = new JPanel() {

            // create background
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // draw background
                ImageIcon backgroundImage = new ImageIcon("graphics/launcher/local.jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // JPanel settings
        this.panel.setPreferredSize(new Dimension(UiLauncher.WIDTH, UiLauncher.HEIGTH));
        this.panel.setLayout(null);

        // add buttons
        this.addButtonReturn();
        this.addButtonStartGame();

        this.createPlayerView();
        //this.addButtonAddPlayer();
    }

    /**
     * Creates and displays the player view in the local player panel.
     * The player view includes options to set player types, names, and colors for the local players.
     */

    public void createPlayerView() {
        int xPos = 218; // The xPos is statically changed for each player. The yPos is calculated based on the individual object lengths plus a 5px/15px spacing.
        int yPos = 50;
        // creates inputs for every player
        for (int index=0; index < 4; index ++) { // playerId = 0, 1, 2, 3
            
            // playertype //

            // -> JLabel
            JLabel addPlayerTypeLabel = new JLabel("Spielertyp:");
            addPlayerTypeLabel.setForeground(Color.white);
            addPlayerTypeLabel.setBounds(yPos, xPos, 180, 45); 
            this.panel.add(addPlayerTypeLabel);
            
            // -> JComboBox - Dropdown
            JComboBox<String> addPlayerTypeCombo = new JComboBox<>(this.playerTypeName);
            addPlayerTypeCombo.setBounds(yPos, xPos+40, 180, 45);
            if (index > 1) {
                addPlayerTypeCombo.addItem("Keiner");
                addPlayerTypeCombo.addItemListener(new ListenerLocalAddUser(this, index));
            }
            this.panel.add(addPlayerTypeCombo);
            this.playerTypeCombo[index] = addPlayerTypeCombo;

            yPos += 200;

            // playernames //

            // -> Label
            JLabel addPlayerNameLabel = new JLabel("Name:");
            addPlayerNameLabel.setForeground(Color.white);
            addPlayerNameLabel.setBounds(yPos, xPos, 180, 45);
            this.panel.add(addPlayerNameLabel);

            // -> JComboBox - Dropdown
            JTextField addPlayerNameField = new JTextField(this.examPlayerNames[index]);
            addPlayerNameField.setBounds(yPos, xPos+40, 180, 45);
            this.panel.add(addPlayerNameField);
            this.playerNameField[index] = addPlayerNameField;

            yPos += 200;

            // playercolor

            // -> Label
            JLabel addPlayerColoLabel = new JLabel("Farbe:");
            addPlayerColoLabel.setForeground(Color.white);
            addPlayerColoLabel.setBounds(yPos, xPos, 180, 45);
            this.panel.add(addPlayerColoLabel);

            // -> JComboBox - Dropdown
            JComboBox<String> addPlayerColorCombo = new JComboBox<>(this.playerColorName);
            addPlayerColorCombo.setSelectedIndex(index);
            addPlayerColorCombo.setBounds(yPos, xPos+40, 180, 45);
            this.panel.add(addPlayerColorCombo);
            this.playerColorCombo[index] = addPlayerColorCombo;

            yPos += 200;

            // Delete Button //
            
            if (index > 1) { // 2 Spieler sind verpflichtend -> Spieler 1, 2 kann man löschen
                JButton addPlayerDelButton = new JButton("Löschen");
                addPlayerDelButton.setBounds(yPos, xPos+17, 100, 68);
                addPlayerDelButton.addActionListener(new ListenerLocalDeleteUser(this, index));
                this.panel.add(addPlayerDelButton);
            }

            // next collum
            yPos = 50;
            xPos += 112;
        }  
    }

    /**
     * Reads all player configurations from the local player panel and starts the game with the selected players.
     * It retrieves the player types, names, and colors for the local players and initiates the game with the provided configurations.
     */

    public void readAllPlayer() {
        // ArrayLists (since there can be an empty player in between).
        ArrayList<PlayerType> playerTypes = new ArrayList<>(4);
        ArrayList<String> playerNames = new ArrayList<>(4);
        ArrayList<Color> playerColors = new ArrayList<>(4);

        // fill list for every player up to 4
        for (int index=0; index < 4; index++) { 
            if (!(this.playerTypeCombo[index].getSelectedItem() == "Keiner") && this.playerNameField[index].getText() != "") { // Überprüfen, ob Spieler leer/aktiv ist
                playerTypes.add(this.playerTypeType[this.playerTypeCombo[index].getSelectedIndex()]);
                playerNames.add(this.playerNameField[index].getText());
                playerColors.add(this.playerColorType[this.playerColorCombo[index].getSelectedIndex()]);
            }
        }

        // start the game
        this.uiLauncher.startGame("local", playerTypes.toArray(new PlayerType[playerTypes.size()]), playerNames.toArray(new String[playerNames.size()]), playerColors.toArray(new Color[playerColors.size()]));        
    }
    
    /**
     * Adds a new player to the local player panel with the specified player ID.
     * It enables input fields for the new player to set player type, name, and color.
     *
     * @param playerId The ID of the new player to be added.
     */

    public void addPlayer(int playerId) {
        // only player 2 and 3
        if (playerId < 2) 
            return;

        // textfield prev + activate
        this.playerNameField[playerId].setText(this.examPlayerNames[playerId]);
        this.playerNameField[playerId].setEditable(true);

        // activate color
        this.playerColorCombo[playerId].setEditable(true);
    }

    /**
     * Removes a player from the local player panel with the specified player ID.
     * It resets the input fields for the removed player and disables them.
     *
     * @param playerId The ID of the player to be removed.
     */

    public void removePlayer(int playerId) {
        // Only player 2 and 3 
        if (playerId < 2) 
            return;

        // The selection box is set to "None"
        this.playerTypeCombo[playerId].setSelectedItem("Keiner");

        // The text field empty and disable
        this.playerNameField[playerId].setText("");
        this.playerNameField[playerId].setEditable(false);

        // deactivate color
        this.playerColorCombo[playerId].setEditable(false);
    }

    /**
     * Adds a "return" button to the local player panel.
     * The button allows the user to navigate back to the home panel when pressed.
     */

    public void addButtonReturn() {
        // Create the JButton
        JButton button = new JButton();

        // Add a Listener
        button.addActionListener(new ListenerToHome(this.uiLauncher));
        
        // Styles
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(994, 532, 260, 125);  

        // Add the JButton to the Button Panel
        this.panel.add(button);
    }

    /**
     * Adds a "Start Game" button to the local player panel.
     * The button allows the user to start the game with the selected local player configurations when pressed.
     */

    public void addButtonStartGame() {
        // Create the JButton
        JButton button = new JButton();

        // Add a Listener
        button.addActionListener(new ListenerStartLocalGame(this));
        
        // Styles
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(995, 280, 261, 125);

        // Add the JButton
        this.panel.add(button);
    }

    /**
     * Retrieves the local player panel.
     *
     * @return The JPanel representing the local player panel.
     */
    
    public JPanel getPanel() {
        return this.panel;
    }
}
