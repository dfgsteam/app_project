package bauernhof.app.ui.launcher.panel;

import javax.swing.*;

import bauernhof.app.ui.launcher.UiLauncher;
import bauernhof.preset.PlayerType;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * The PanelNetwork class represents the network panel of the game launcher.
 * It provides a graphical interface for the user to set up network game options, such as creating a game server or joining a server.
 *
 * The network panel allows the user to choose between creating a game server or joining an existing server.
 * It includes options to input server address and port number for joining a server, and it displays the local IP address and port for the game server.
 *
 * The class includes methods to add buttons, input fields, and labels to the panel for user interaction and configuration.
 *
 * @see UiLauncher
 * @author Julius Hunold
 * @version 1.0
 */

public class PanelNetwork {
    private UiLauncher uiLauncher;
    private JPanel panel;
    private int status;

    final private String[] examPlayerNames = {"Florian", "Cemil", "Viktor", "Kirill"};
    final private String[] playerTypeName = {"Computer Einfach", "Computer Mittel", "Computer Schwer", "Lokaler Spieler", "Remote"};
    final private PlayerType[] playerTypeType = {PlayerType.RANDOM_AI, PlayerType.SIMPLE_AI, PlayerType.ADVANCED_AI, PlayerType.HUMAN, PlayerType.REMOTE};
    final private String[] playerColorName = {"Rot", "Magenta", "Blau", "Orange", "Cyan", "Pink"};
    final private Color[] playerColorType = {Color.RED, Color.MAGENTA, Color.BLUE, Color.ORANGE, Color.CYAN, Color.PINK};

    private JComboBox<String> playerTypeCombo[] = new JComboBox[4];
    private JTextField playerNameField[] = new JTextField[4];
    private JComboBox<String> playerColorCombo[] = new JComboBox[4];

    private JButton buttonCreateServer, buttonJoinServer, buttonStartServer;
    
    /**
     * Constructs a new PanelNetwork instance.
     * It initializes the network panel and creates UI components such as buttons, input fields, and labels for user interaction.
     *
     * @param uiLauncher The UiLauncher object associated with the network panel.
     * @throws IOException If there is an I/O error while loading graphics resources or retrieving local IP address.
     */

    public PanelNetwork(UiLauncher uiLauncher) throws IOException {
        this.panel = new JPanel();
        this.uiLauncher = uiLauncher;

        this.panel.setPreferredSize(new Dimension(UiLauncher.HEIGTH, UiLauncher.WIDTH));
        this.panel.setLayout(null);

        this.setSelect(false);
    }

    private void createNewPanal(String bg) {
        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Zeichne den Hintergrund
                ImageIcon backgroundImage = new ImageIcon("graphics/launcher/" + bg + ".jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }  
        };
        this.panel.setPreferredSize(new Dimension(UiLauncher.WIDTH, UiLauncher.HEIGTH));
        this.panel.setLayout(null);
        this.addButtonReturn();
    }

    // Different views //

    /**
     * Sets the network panel to the "select" view.
     * In the "select" view, the user can choose between creating a game server or joining a server.
     * This method also updates the frame to display the network panel.
     */

    public void setSelect(boolean updateFrame) {
        this.status = 0;
        this.createNewPanal("multiSelect");
        this.addButtonCreateServer();
        this.addButtonCreateClient();
        this.addInputAdress();
        if (updateFrame)
            this.uiLauncher.setPanelNetworkPlayer();
    }

    /**
     * Sets the network panel to the "server" view.
     * In the "server" view, the user can start the game server and view the server address and port.
     * This method also updates the frame to display the network panel.
     */

    public void setServer() {
        this.status = 1;
        this.createNewPanal("multiGameServer");
        this.addButtonStartGame();
        this.createServerView();
        this.uiLauncher.setPanelNetworkPlayer();
    }

    /**
     * Sets the network panel to the "client" view.
     * In the "client" view, the user can input the server address and port to join an existing server.
     * This method also updates the frame to display the network panel.
     */

    public void setClient() {
        this.status = 2;
        this.createNewPanal("multiGameClient");
        this.uiLauncher.setPanelNetworkPlayer();
    }

    // Move //

    /**
     * Handles the action of returning to the previous view when the return button is pressed.
     * If the current view is the "select" view, it navigates back to the home panel.
     * Otherwise, it sets the network panel back to the "select" view.
     */

    public void doReturn() {
        if (this.status == 0)
            this.uiLauncher.setPanelHome();
        else
            this.setSelect(true);
    }

    // Buttons //

    /**
     * Creates and adds a "return" button to the network panel.
     * The button allows the user to navigate back to the previous view when pressed.
     */

    public void addButtonReturn() {
        PanelNetwork networkPanel = this;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                networkPanel.doReturn();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(994, 532, 260, 125);  

        this.panel.add(button);
    }

    /**
     * Creates and adds a "Create Server" button to the network panel.
     * The button allows the user to set up a game server when pressed, leading to the "server" view.
     */

    public void addButtonCreateServer() {
        PanelNetwork networkPanel = this;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                networkPanel.setServer();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(265, 248, 752, 100);

        this.panel.add(button);
    }

    /**
     * Creates and adds a "Join Server" button to the network panel.
     * The button allows the user to join an existing server when pressed, leading to the "client" view.
     */

    public void addButtonCreateClient() {
        PanelNetwork networkPanel = this;
        // Erstelle den JButton
        JButton button = new JButton();

        button.addActionListener((ActionListener ) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                networkPanel.setClient();
            }
        });
        
        button.setContentAreaFilled(false);
        button.setBorderPainted(UiLauncher.debug);
        button.setBounds(773, 361, 245, 100);

        this.panel.add(button);
    }

    /**
     * Creates and adds a "Start Game" button to the network panel in the "server" view.
     * The button allows the user to start the game when pressed.
     */

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

    // Inputs//

    /**
     * Adds input fields for server address and port to the network panel in the "client" view.
     * The user can input the server address and port number to join an existing server.
     */

    public void addInputAdress() {
        // IP Part 1
        JSpinner addInputAdressField1 = new JSpinner(new SpinnerNumberModel(127, 0, 255, 1));
        addInputAdressField1.setBounds(352, 405, 55, 22);
        this.panel.add(addInputAdressField1);


        // IP Part 2
        JSpinner addInputAdressField2 = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        addInputAdressField2.setBounds(419, 405, 55, 22);
        this.panel.add(addInputAdressField2);

        // IP Part 3
        JSpinner addInputAdressField3 = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        addInputAdressField3.setBounds(486, 405, 55, 22);
        this.panel.add(addInputAdressField3);

        // IP Part 4
        JSpinner addInputAdressField4 = new JSpinner(new SpinnerNumberModel(1, 0, 255, 1));
        addInputAdressField4.setBounds(553, 405, 55, 22);
        this.panel.add(addInputAdressField4);

        // IP Part 5 (Port)
        JSpinner addInputAdressField5 = new JSpinner(new SpinnerNumberModel(5000, 1, 10000, 1));
        addInputAdressField5.setBounds(620, 405, 70, 22);
        this.panel.add(addInputAdressField5);
    }

    /**
     * Retrieves the network panel.
     *
     * @return The JPanel representing the network panel.
     */

    public JPanel getPanel() {
        return this.panel;
    }

    /**
     * Creates and displays the server view with player options and server information.
     * The server view shows the local IP address and port number for the game server.
     * It allows the user to set player types, names, and colors for the game.
     */
    
    public void createServerView() {
        InetAddress IP = null;
        try {
            IP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        JLabel serverAdress= new JLabel(IP.getHostAddress() + ":8000");
        serverAdress.setForeground(Color.white);
        serverAdress.setFont(new Font("Arial", Font.PLAIN, 30));
        serverAdress.setBounds(200, 150, 300, 45); 
        this.panel.add(serverAdress);

        int xPos = 218; // The xPos is statically changed for each player. The yPos is calculated based on the individual object lengths + 5px/15px spacing
        int yPos = 50;
        // Create inputs for each player
        for (int index=0; index < 4; index ++) { // playerId = 0, 1, 2, 3
            
            // playertip //

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
            
            if (index > 1) { // 2 players are mandatory -> Player 1, 2 can be deleted.
                JButton addPlayerDelButton = new JButton("Löschen");
                addPlayerDelButton.setBounds(yPos, xPos+17, 100, 68);
                addPlayerDelButton.addActionListener(null);
                this.panel.add(addPlayerDelButton);
            }

            // next collum
            yPos = 50;
            xPos += 112;
        }  
    }
}
