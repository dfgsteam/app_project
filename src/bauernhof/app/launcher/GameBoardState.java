package bauernhof.app.launcher;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.HumanPlayer;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;

import java.util.*;

/**
 * Diese Klasse ist der Generelle Main Handler für das gesamte Spielbrett.
 * Sie gibt über jeden Status des aktuellen Spiels bescheid.
 * Auch die Instanzen der aktuellen {@link Player} sind enthalten.
 * Zudem dient die Klasse auch zum Laden von gespeicherten Spielständen
 *
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 00:55
 */

public class GameBoardState implements Table {
    /*
    TO-DO: Laden von Spielständen durch eventuellen SaveGameLoader
     */
    private int round;
    private AbstractGamePlayer actual_player;
    private Iterator<AbstractGamePlayer> player_iterator;
    private ArrayList<Card> deposited_cards;
    private Stack<Card> drawpile_cards = new Stack<>();
    private Set<AbstractGamePlayer> players;
    private GameConfiguration configuration;

    // For new Game
    public GameBoardState(final GameConfiguration configuration, final Set<AbstractGamePlayer> players) throws Exception {
        new GameBoardState(0, configuration, players, configuration.getCards(), new ArrayList<>());
    }

    public GameBoardState(final GameConfiguration configuration, final ImmutableList<Card> drawpile_cards, final int numplayers) {
        final AbstractGamePlayer[] players = new AbstractGamePlayer[numplayers];
    }
    
    public GameBoardState(final String[] playernames, final PlayerType[] types, GameConfiguration configuration, final ImmutableList<Card> cards) {
        AbstractGamePlayer[] players = new AbstractGamePlayer[playernames.length];
    }

    // For saved Game Status
    public GameBoardState(final int round,
                     final GameConfiguration configuration,
                     final Set<AbstractGamePlayer> players,
                     final Set<Card> drawpile_cards,
                     final ArrayList<Card> deposited_cards) throws Exception {
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
    public Object clone() {
        //
       /* final Set<AbstractGamePlayer> players = new HashSet<>();
        for (final AbstractGamePlayer player : getPlayers())
            players.add(player.)
        return new GameBoardState(round, getGameConfiguration(), getPlayers(), getDrawPileCards().clone(), getDepositedCards().clone()); */
        return null;
    }

    @Override
    public ArrayList<Card> getDepositedCards() {
        return this.deposited_cards;
    }

    @Override
    public Stack<Card> getDrawPileCards() {
        return this.drawpile_cards;
    }

    @Override
    public Set<AbstractGamePlayer> getPlayers() {
        return this.players;
    }

    @Override
    public boolean doMove(final Move move) throws Exception {
        if (deposited_cards.contains(move.getTaken()))
            deposited_cards.remove(move.getTaken());
        else if(drawpile_cards.get(0).equals(move.getTaken()))
            drawpile_cards.pop();
        else return false;
        if (!getActualPlayer().getCards().contains(move.getDeposited()))
            return false;
        deposited_cards.add(move.getDeposited());
        getActualPlayer().remove(move.getDeposited());
        getActualPlayer().add(move.getTaken());
        this.actual_player = player_iterator.next();
        return true;
    }

    @Override
    public AbstractGamePlayer getActualPlayer() {
        return this.actual_player;
    }

    @Override
    public int getRound() {
        return this.round;
    }

    @Override
    public void initNewGame() throws Exception {
        byte count = 0;
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
    public GameConfiguration getGameConfiguration() {
        return this.configuration;
    }

    @Override
    public GameBoardState getState() {
        return this;
    }

    @Override
    public Stack<Card> getDrawPileStack() {
        return this.drawpile_cards;
    }
}
