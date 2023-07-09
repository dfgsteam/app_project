package bauernhof.app.ui;

import sag.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class StartFrame implements ActionListener {

    final private int   FRAMEWIDTH = 1280,
                        FRAMEHEIGTH = 720;

    private JButton Start;
    private JComboBox numberBox, howManyAIs;
    private SAGPanel panelTop, panelMid, panelBot, startPanel;
    private static int Players = 2;
    private SAGFrame frame;
    private JPanel panelBotTop, panelBotBot;

    public StartFrame() {
        init();
    }

    private void init(){
        setTopPanel();setMidPanel();setBotPanel();
        setStartPanel();
        setFrame();

    }

    private void setFrame(){
        frame = new SAGFrame("Bauernhof",30,1280,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSAGPanel(startPanel);
        frame.setBackground(new Color(127,39,21));
        frame.setVisible(true);

    }

    private void setStartPanel(){

        startPanel = new SAGPanel(FRAMEWIDTH,FRAMEHEIGTH);
        startPanel.setLayout(new GridLayout(3,1));
        startPanel.add(panelTop);
        startPanel.add(panelMid);
        startPanel.add(panelBot);

    }

    private void setTopPanel(){
        panelTop = new SAGPanel(FRAMEWIDTH/3,FRAMEHEIGTH/3);
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER,0,50));
        Label PanelName = new Label("*MENU*");
        PanelName.setForeground(new Color(76,195,210));
        PanelName.setFont(new Font("Serif", Font.PLAIN, 25));
        PanelName.setBackground(new Color(127,39,21));
        panelTop.add(PanelName);
    }

    private void setMidPanel(){
        panelMid = new SAGPanel(FRAMEWIDTH/3,FRAMEHEIGTH/3);
        panelMid.setLayout(new GridLayout(1,3));
        panelMid.setBorder(BorderFactory.createMatteBorder(2,0,0,0, new Color(76,195,173)));



        JPanel panelMidRight = new JPanel(new FlowLayout(FlowLayout.CENTER,0,100));
        panelMidRight.setBackground(new Color(127,39,21));

        JPanel panelMidMid = new JPanel(new FlowLayout(1,0,100));
        panelMidMid.setBackground(new Color(127,39,21));

        JPanel panelMidLeft = new JPanel(new FlowLayout(1,0,100));
        panelMidLeft.setBackground(new Color(127,39,21));



        ImageIcon icon = new ImageIcon("graphics/MenuBackground.png");
        BufferedImage midPanelBackgroundImg = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);



        JButton authors = new JButton("View Authors");
        authors.setFont(new Font("Serif", Font.PLAIN, 18));
        authors.setForeground(new Color(76,195,173));
        authors.setBackground(new Color(127,39,21));
        panelMidLeft.add(authors);
        panelMid.add(panelMidLeft);



        Start = new JButton("START");
        Start.setBackground(new Color(221,96,69));
        Start.addActionListener(this::actionPerformed);
        panelMidMid.add(Start);
        panelMid.add(panelMidMid);



        JButton BecomeAPatron = new JButton("Become a patrone");
        BecomeAPatron.setFont(new Font("Serif", Font.PLAIN, 18));
        BecomeAPatron.setForeground(new Color(76,195,173));
        BecomeAPatron.setBackground(new Color(127,39,21));
        panelMidRight.add(BecomeAPatron);
        panelMid.add(panelMidRight);
    }

    private void setBotPanel(){
        setPanelBotTop();
        setPanelBotBot();

        panelBot = new SAGPanel(FRAMEWIDTH/3,FRAMEHEIGTH/3);
        panelBot.setLayout(new GridLayout(2,1));
        panelBot.setBorder(BorderFactory.createMatteBorder(2,0,0,0, new Color(76,195,173)));

        panelBot.add(panelBotTop);
        panelBot.add(panelBotBot);

    }

    private void setPanelBotTop(){
        JLabel gameSettings = new JLabel("*** Gameplay settings ***");
        gameSettings.setForeground(new Color(76,195,210));
        gameSettings.setFont(new Font("Serif", Font.PLAIN, 25));

        panelBotTop = new JPanel(new FlowLayout(FlowLayout.CENTER,50,0));
        panelBotTop.setBackground(new Color(127,39,21));
        panelBotTop.add(gameSettings);
    }

    private void setPanelBotBot(){
        JLabel chosePlayers = new JLabel("Set players*");
        chosePlayers.setForeground(new Color(76,195,173));
        chosePlayers.setFont(new Font("Serif", Font.PLAIN, 18));

        JLabel choseAI = new JLabel("How many AI among players?");
        choseAI.setForeground(new Color(76,195,173));
        choseAI.setFont(new Font("Serif", Font.PLAIN, 18));

        String[] list1 = {"2","3","4"};
        String[] list2 = {"0","1","2","3"};

        numberBox = new JComboBox(list1);
        numberBox.addActionListener(this);
        numberBox.setBackground(new Color(221,96,69));

        howManyAIs = new JComboBox<>(list2);
        howManyAIs.addActionListener(this);
        howManyAIs.setBackground(new Color(221,96,69));

        panelBotBot = new JPanel(new FlowLayout(FlowLayout.CENTER,50,0));
        panelBotBot.setBackground(new Color(127,39,21));

        panelBotBot.add(chosePlayers);
        panelBotBot.add(numberBox);
        panelBotBot.add(choseAI);
        panelBotBot.add(howManyAIs);
    }

    public static int getPlayers() {
        return Players;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Start){
            GamePanel GP = new GamePanel(FRAMEWIDTH,FRAMEHEIGTH);
            frame.setSAGPanel(GP.MainPanel);
        }

        if(e.getSource()==numberBox){
            Players = Integer.parseInt(numberBox.getSelectedItem().toString());

            if(numberBox.getSelectedItem()=="2"){
                panelBotBot.remove(howManyAIs);
                String[] list2 = {"0","1"};
                howManyAIs = new JComboBox<>(list2);
                howManyAIs.addActionListener(this);
                howManyAIs.setBackground(new Color(221,96,69));
                panelBotBot.add(howManyAIs);

            }
            if(numberBox.getSelectedItem()=="3"){
                panelBotBot.remove(howManyAIs);
                String[] list2 = {"0","1","2"};
                howManyAIs = new JComboBox<>(list2);
                howManyAIs.addActionListener(this);
                howManyAIs.setBackground(new Color(221,96,69));
                panelBotBot.add(howManyAIs);

            }
            if(numberBox.getSelectedItem()=="4"){
                panelBotBot.remove(howManyAIs);
                String[] list2 = {"0","1","2","3"};
                howManyAIs = new JComboBox<>(list2);
                howManyAIs.addActionListener(this);
                howManyAIs.setBackground(new Color(221,96,69));
                panelBotBot.add(howManyAIs);

            }

        }
        if(e.getSource()==howManyAIs){
            if(howManyAIs.getSelectedItem()=="0"){

            }
            if(howManyAIs.getSelectedItem()=="1"){

            }
            if(howManyAIs.getSelectedItem()=="2"){

            }
            if(howManyAIs.getSelectedItem()=="3"){

            }

        }

    }
}
