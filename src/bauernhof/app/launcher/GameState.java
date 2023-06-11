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

public class GameState implements Table{
    private int round;
    private Player actual_player;
    private Iterator<Player> player_iterator;
    private Set<Card> deposited_cards;
    private Stack<Card> drawpile_cards = new Stack<>();
    private Set<Player> players;
    private GameConfiguration configuration;

    // For new Game
    public GameState(final GameConfiguration configuration, final Set<Player> players) throws Exception {
        new GameState(0, configuration, players, configuration.getCards(), new HashSet<>());
    }

    // For saved Game Status
    public GameState(final int round,
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
    public Stack<Card> getDrawPileCards() {
        return this.drawpile_cards;
    }

    @Override
    public Set<Player> getPlayers() {
        return this.players;
    }

    @Override
    public boolean doMove(final Move move, final boolean drawpile) throws Exception {
        boolean execution = true;
        if (drawpile && !drawpile_cards.empty())
            drawpile_cards.pop();
        else if (!deposited_cards.isEmpty())
            deposited_cards.remove(move.getTaken());
        else execution = false;
        deposited_cards.add(move.getDeposited());
        getActualPlayer().update(move);
        if (!player_iterator.hasNext()) {
            round++;
            player_iterator = players.iterator();
        }
        actual_player = player_iterator.next();
        return execution;
    }

    @Override
    public Player getActualPlayer() {
        return this.actual_player;
    }

    @Override
    public int getRound() {
        return this.round;
    }

    @Override
    public void initNewGame() throws Exception {
        byte count = 0;
        mixCards();
        for (final Player player : players) {
            final ImmutableList<Card> drawpilecard_list = new ImmutableList<>();
            for (byte i = 0; i < configuration.getNumCardsPerPlayerHand(); i++) {
                drawpilecard_list.add(this.drawpile_cards.pop());
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

    @Override
    public GameState getState() {
        return null;
    }

    @Override
    public void setGameState(GameState state) {

    }

    @Override
    public ImmutableList<Card> getDrawPileStack() {
        return null;
    }
}
