package bauernhof.app.ui.launcher.panel;

import javax.swing.*;

import bauernhof.app.ui.launcher.UiLauncher;
import bauernhof.preset.PlayerType;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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

    public void setSelect(boolean updateFrame) {
        this.status = 0;
        this.createNewPanal("multiSelect");
        this.addButtonCreateServer();
        this.addButtonCreateClient();
        this.addInputAdress();
        if (updateFrame)
            this.uiLauncher.setPanelNetworkPlayer();
    }

    public void setServer() {
        this.status = 1;
        this.createNewPanal("multiGameServer");
        this.addButtonStartGame();
        this.createServerView();
        this.uiLauncher.setPanelNetworkPlayer();
    }

    public void setClient() {
        this.status = 2;
        this.createNewPanal("multiGameClient");
        this.uiLauncher.setPanelNetworkPlayer();
    }

    // Move //

    public void doReturn() {
        if (this.status == 0)
            this.uiLauncher.setPanelHome();
        else
            this.setSelect(true);
    }

    // Buttons //

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

    public JPanel getPanel() {
        return this.panel;
    }

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

        int xPos = 218; // xPos wird pro Player statisch verändert. Die yPos wird anhand deinzelnen Objektlängen + 5px/15px Abstand berechnet
        int yPos = 50;
        // Erstelle für jeden Spieler inputs
        for (int index=0; index < 4; index ++) { // playerId = 0, 1, 2, 3
            
            // Spielertyp //

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
                //addPlayerTypeCombo.addItemListener(new ListenerLocalAddUser(this, index));
            }
            this.panel.add(addPlayerTypeCombo);
            this.playerTypeCombo[index] = addPlayerTypeCombo;

            yPos += 200;

            // Spielernamen //

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

            // Spielerfarbe

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
                addPlayerDelButton.addActionListener(null);
                this.panel.add(addPlayerDelButton);
            }

            // Nächste Zeile
            yPos = 50;
            xPos += 112;
        }  
    }
}
