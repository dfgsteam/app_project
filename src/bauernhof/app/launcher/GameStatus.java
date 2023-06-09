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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GameStatus implements GameHandler{
    private int round;
    private Player actual_player;
    private Iterator<Player> player_iterator;
    private Set<Card> deposited_cards, drawpile_cards;
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
        this.drawpile_cards = drawpile_cards;
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
    public Set<Card> getDrawPileCards() {
        return this.drawpile_cards;
    }

    @Override
    public Set<Player> getPlayers() {
        return this.players;
    }

    @Override
    public void doMove(final Move move, final boolean drawpile) throws Exception {
        if (drawpile) drawpile_cards.remove(move.getTaken());
        else deposited_cards.remove(move.getTaken());
        deposited_cards.add(move.getDeposited());
        getActualPlayer().update(move);
        if (!player_iterator.hasNext()) {
            round++;
            player_iterator = players.iterator();
        }
        actual_player = player_iterator.next();

    }

    @Override
    public Player getActualPlayer() {
        return this.actual_player;
    }

    @Override
    public int getRound() {
        return this.round;
    }

 /*   private void removeTopCard() {
        this.drawpile_cards.remove();
    }
    private void addCard(Card card) {
        this.drawpile_cards.add(card);
    }
  */

    @Override
    public void initNewGame() throws Exception {
        byte count = 0;
        final ImmutableList<Card> drawpilecard_list = new ImmutableList<>();
        for (byte i = 0; i < configuration.getNumCardsPerPlayerHand(); i++) {
            //removeCard(drawpilecard_list.get());
        }
        for (final Player player : players) {
            player.init(configuration, drawpilecard_list, players.size(), count);
            count++;
        }

    }

    @Override
    public void mixCards() {

    }
}
