package bauernhof.app.ui;

import com.kitfox.svg.app.beans.SVGPanel;
import sag.SAGPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {

    SAGPanel MainPanel,TopPanel,MidPanel,BotPanel;

    public GamePanel(int width, int heigth){
        init(width, heigth);
    }

    public void init(int width, int heigth){

        setTopPanel();
        setMidPanel();
        setBotPanel();

        setMainPanel(width, heigth);
    }

    private void setMainPanel(int width, int heigth){
        MainPanel = new SAGPanel(width, heigth);
        MainPanel.setLayout(new GridLayout(3,1));
        MainPanel.add(TopPanel);
        MainPanel.add(MidPanel);
        MainPanel.add(BotPanel);
        MainPanel.setVisible(true);
    }

    private void setTopPanel(){
        TopPanel = new SAGPanel();
        TopPanel.setLayout(new GridLayout(3,1));
        TopPanel.setBackground(Color.ORANGE);

        JPanel TopTop = new SAGPanel();
        TopTop.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
        JPanel TopMid = new SAGPanel();
        TopMid.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
        JPanel TopBot = new SAGPanel();
        TopBot.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));

        JLabel playerOne = new JLabel("Player Nr.1");
        playerOne.setIcon(new ImageIcon("graphics/player.jpg"));
        TopTop.add(playerOne);
        JLabel playerTwo = new JLabel("Player Nr.2");
        JLabel playerThree = new JLabel("Player Nr.3");

        switch(StartFrame.getPlayers()){
            case 4 :
                TopPanel.add(playerThree);
            case 3 :
                TopPanel.add(playerTwo);
            case 2 :
                TopPanel.add(TopTop);
                break;

        }
    }

    private void setMidPanel(){
        MidPanel = new SAGPanel();
        MidPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
        MidPanel.setBackground(Color.ORANGE);

        JButton Nachziehstapel = new JButton("Nachziehstapel");
        Nachziehstapel.setBackground(Color.CYAN);
        JButton Ablagestapel = new JButton("Ablagestapel");
        Ablagestapel.setBackground(Color.CYAN);

        JPanel MidPanelRight = new JPanel();
        MidPanelRight.add(Ablagestapel);
        JPanel MidPanelLeft = new JPanel();
        MidPanelLeft.add(Nachziehstapel);

        MidPanel.add(MidPanelRight);
        MidPanel.add(MidPanelLeft);
    }

    private void setBotPanel(){
        BotPanel = new SAGPanel();
        BotPanel.setLayout(new FlowLayout());
        BotPanel.setBackground(Color.ORANGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
