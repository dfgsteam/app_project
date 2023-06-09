package bauernhof.app.launcher;

/**
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 00:55
 */

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.Move;
import bauernhof.preset.Player;
import bauernhof.preset.card.Card;

import java.util.*;

public class GameStatus implements Table{
    private int round;
    private Player actual_player;
    private Iterator<Player> player_iterator;
    private Set<Card> deposited_cards;
    private ArrayList<Card> drawpile_cards = new ArrayList<>();
    private Set<Player> players;
    private GameConfiguration configuration;

    // For new Game
    public GameStatus(final GameConfiguration configuration, final Set<Player> players) throws Exception {
        new GameStatus(0, configuration, players, configuration.getCards(), new HashSet<>());
    }

    // For saved Game Status
    public GameStatus(final int round,
                      final GameConfiguration configuration,
                      final Set<Player> players,
                      final Set<Card> drawpile_cards,
                      final Set<Card> deposited_cards) throws Exception {
        this.round = round;
        for (final Card card : drawpile_cards)
            this.drawpile_cards.add(card);
        this.deposited_cards = deposited_cards;
        this.players = players;
        this.actual_player = player_iterator.next();
        this.player_iterator = players.iterator();
        this.configuration = configuration;
    }

    @Override
    public Set<Card> getDepositedCards() {
        return this.deposited_cards;
    }

    @Override
    public ArrayList<Card> getDrawPileCards() {
        return this.drawpile_cards;
    }

    @Override
    public Set<Player> getPlayers() {
        return this.players;
    }

    @Override
    public boolean doMove(final Move move, final boolean drawpile) throws Exception {
        if (drawpile) removeTopCard();
        else deposited_cards.remove(move.getTaken());
        deposited_cards.add(move.getDeposited());
        getActualPlayer().update(move);
        if (!player_iterator.hasNext()) {
            round++;
            player_iterator = players.iterator();
        }
        actual_player = player_iterator.next();
        return false;

    }

    @Override
    public Player getActualPlayer() {
        return this.actual_player;
    }

    @Override
    public int getRound() {
        return this.round;
    }

    private Card removeTopCard() {
        final Card card = drawpile_cards.get(drawpile_cards.size() - 1);
        this.drawpile_cards.remove(card);
        return card;
    }

    @Override
    public void initNewGame() throws Exception {
        byte count = 0;
        mixCards();
        for (final Player player : players) {
            final ImmutableList<Card> drawpilecard_list = new ImmutableList<>();
            for (byte i = 0; i < configuration.getNumCardsPerPlayerHand(); i++) {
                drawpilecard_list.add(removeTopCard());
            }
            player.init(configuration, drawpilecard_list, players.size(), count);
            count++;
        }
    }

    @Override
    public void mixCards() {
        Collections.shuffle(drawpile_cards);
    }

    @Override
    public GameConfiguration getGameConfiguration() {
        return this.configuration;
    }
}
