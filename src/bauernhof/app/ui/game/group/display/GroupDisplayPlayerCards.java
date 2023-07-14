package bauernhof.app.ui.game.group.display;

import bauernhof.app.card.Ca;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.card.CardListener;
import bauernhof.preset.card.GCard;
import sag.ChildNotFoundException;
import sag.LayerPosition;
import sag.elements.GGroup;

/**
 * This class represents a group for displaying player cards in the game UI.
 * It extends the GGroup class from the sag.elements package.
 * The group contains multiple layers, each representing a player's hand of cards.
 * The class provides methods for initializing and updating the player cards.
 * 
 * The GroupDisplayPlayerCards class provides the following functionality:
 * - Initializes the group of player cards with the specified UiGame instance.
 * - Creates separate layers for each player's hand of cards based on their position.
 * - Adjusts the scale and position of each player's hand based on the number of cards.
 * - Updates the player cards based on changes in the game state.
 * - Adds event listeners to the cards for interaction.
 * - Supports clearing the player panel and removing cards from the display.
 * 
 * @author [Your Name]
 * @version 1.0
 * @since 2023-07-14
 */

public class GroupDisplayPlayerCards extends GGroup {
    
    private GGroup groupPlayer[] = new GGroup[4];
    private float pos[][][];
    private UiGame UiGame;

    /**
     * Constructs a new GroupDisplayPlayerCards object.
     * The object represents a group for displaying player cards in the game UI.
     * 
     * @param UiGame The UiGame object that represents the game UI.
     */
    public GroupDisplayPlayerCards(UiGame UiGame) {
        this.UiGame = UiGame;
        int maxCards = this.UiGame.getGameBoardState().getConfiguration().getNumCardsPerPlayerHand();
        this.pos = new float[this.UiGame.getGameBoardState().getPlayers().length][maxCards][2];
        for (int counter = 0; counter < this.UiGame.getGameBoardState().getPlayers().length; counter++) {
            switch (counter) {
                case 0:
                    this.groupPlayer[0] = this.UiGame.getMainPanel().addLayer(LayerPosition.BOTTOM_CENTER);
                    break;
                case 1:
                    this.groupPlayer[1] = this.UiGame.getMainPanel().addLayer(LayerPosition.CENTER_LEFT);
                    break;
                case 2:
                    this.groupPlayer[2] = this.UiGame.getMainPanel().addLayer(LayerPosition.TOP_CENTER);
                    break;
                case 3:
                    this.groupPlayer[3] = this.UiGame.getMainPanel().addLayer(LayerPosition.CENTER_RIGHT);
                    break;
            }
            this.groupPlayer[counter].setScale(0.65f - (0.01f * maxCards));

            this.initPlayer(counter, maxCards);
        }
    }

    private void initPlayer(int playerId, int maxCards) {
        switch (playerId % 2) {
            case 0: {
                int counter = 0;
                int startpointX, startpointY;

                if (playerId == 0)
                    startpointY = -150;
                else
                    startpointY = 150;

                while (counter < maxCards) {
                    startpointX = 0 - 180 * (maxCards / 2);

                    this.pos[playerId][counter][0] = startpointX + 200f * counter;
                    this.pos[playerId][counter][1] = startpointY;

                    counter++;
                }
                break;
            }
            case 1: {
                int counter = 0;
                int startpointX, startpointY, side;

                startpointY = -120 * (maxCards / 2);

                if (playerId == 1)
                    side = 1;
                else
                    side = -1;

                while (counter < maxCards) {
                    startpointX = side * 100 + side * (200 * (counter % 2));

                    this.pos[playerId][counter][0] = startpointX;
                    this.pos[playerId][counter][1] = startpointY;

                    if (counter++ % 2 != 0)
                        startpointY += 280;
                }
                break;
            }
        }
    }

    /**
     * Updates the player cards for the specified player.
     * The method clears the player panel and adds the updated cards to the display.
     * 
     * @param playerId The ID of the player whose cards should be updated.
     * @throws ChildNotFoundException If the child card element is not found in the group.
     * @throws InterruptedException If the thread is interrupted during card updates.
     */
    public void updatePlayer(int playerId) throws ChildNotFoundException, InterruptedException {
        AbstractGamePlayer player = this.UiGame.getGameBoardState().getPlayers()[playerId];
        this.clearPlayerPanel(playerId);
        Object[] cards = player.getCards().toArray();

        for (int item = 0; item < cards.length; item++) {
            GCard gCard = ((Ca) cards[item]).getGCard();

            gCard.setMouseEventListener(new CardListener());

            if (player.getBlockedCards().contains(cards[item]))
                System.out.println(gCard.getCard().getName());

            this.groupPlayer[playerId].addChild(gCard, this.pos[playerId][item][0], this.pos[playerId][item][1]);
        }
    }

    private void clearPlayerPanel(int playerId) throws ChildNotFoundException {
        for (int cardIndex = this.groupPlayer[playerId].getNumChildren() - 1; cardIndex >= 0; cardIndex--) {
            this.groupPlayer[playerId].getChildByRenderingIndex(cardIndex).unsetStrokeWidth();
            this.groupPlayer[playerId].getChildByRenderingIndex(cardIndex).setMouseEventListener(null);
            this.groupPlayer[playerId].removeChild(this.groupPlayer[playerId].getChildByRenderingIndex(cardIndex));
        }
    }
}
