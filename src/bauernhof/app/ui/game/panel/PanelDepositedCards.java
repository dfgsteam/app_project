package bauernhof.app.ui.game.panel;

import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.card.CardAddListener;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGPanel;
import sag.elements.GGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelDepositedCards extends SAGPanel implements ActionListener {

    private JButton Back;
    private UiGame UiGame;

    public PanelDepositedCards(UiGame UiGame) {

        this.UiGame = UiGame;
        ArrayList<Card> list = this.UiGame.getGameBoardState().getDepositedCards();

        GGroup top = this.addLayer(LayerPosition.TOP_LEFT);
        top.setScale(0.75f);
        GGroup cen = this.addLayer(LayerPosition.CENTER_LEFT);
        cen.setScale(0.75f);
        GGroup bot = this.addLayer(LayerPosition.BOTTOM_LEFT);
        bot.setScale(0.75f);
        this.setLayout(null);
        int x = 100, y = 120, i = 0;

        this.setVisible(true);

        GCard card;

        for (; i < list.size(); i++) {
            if (x + 200 >= this.VIEWPORT_WIDTH) {
                break;
            }
            card = new GCard(list.get(i));
            card.setMouseEventListener(new CardAddListener(this.UiGame, this.UiGame.getPlayerId()));
            top.addChild(card, x, y);
            x += 200;

        }
        x = 100;
        y = -150;
        for (; i < list.size(); i++) {
            if (x + 200 >= this.VIEWPORT_WIDTH) {
                break;
            }
            card = new GCard(list.get(i));
            card.setMouseEventListener(new CardAddListener(this.UiGame, this.UiGame.getPlayerId()));
            cen.addChild(card, x, y);
            x += 200;

        }
        x = 100;
        y = -350;
        for (; i < list.size(); i++) {
            card = new GCard(list.get(i));
            card.setMouseEventListener(new CardAddListener(this.UiGame, this.UiGame.getPlayerId()));
            bot.addChild(card, x, y);
            x += 200;

        }

        Back = new JButton("Zuruck");
        Back.addActionListener(this::actionPerformed);
        Back.setBounds(this.VIEWPORT_WIDTH - 250, this.VIEWPORT_HEIGHT - 130, 150, 30);
        this.add(Back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back) {
            //UiGame.getFrame().setSAGPanel(UiGame.getMain());
        }
    }
}
