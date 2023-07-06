package bauernhof.app.launcher;

import bauernhof.app.player.AbstractGamePlayer;
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
    public AbstractGamePlayer actual_player;
    public Iterator<AbstractGamePlayer> player_iterator;
    private ArrayList<Card> deposited_cards = new ArrayList<>();
    private Stack<Card> drawpile_cards = new Stack<>();
    private AbstractGamePlayer[] players;
    private GameConfiguration configuration;

    // For new Game
    public GameBoardState(final GameConfiguration configuration, final AbstractGamePlayer[] players, final ImmutableList<Card> drawpile_cards) throws Exception {
        this.configuration = configuration;
        this.players = players;
        for (final Card card : drawpile_cards)
            this.drawpile_cards.add(card);

    }
    public GameBoardState(final String[] playernames, final PlayerType[] types, GameConfiguration configuration, final ImmutableList<Card> cards) throws Exception {
        final AbstractGamePlayer[] players = new AbstractGamePlayer[playernames.length];
        this.players = players;
        for (int i = 0; i < players.length; i++)
            switch (types[i]) {
                case ADVANCED_AI:
                    players[i] = new AbstractGamePlayer(playernames[i], types[i]);
                    break;
                case HUMAN:
                    players[i] = new AbstractGamePlayer(playernames[i], types[i]);
                    break;
                case RANDOM_AI:
                    players[i] = new AbstractGamePlayer(playernames[i], types[i]);
                    break;
                case REMOTE:
                    players[i] = new AbstractGamePlayer(playernames[i], types[i]);
                    break;
                case SIMPLE_AI:
                    players[i] = new AbstractGamePlayer(playernames[i], types[i]);
                    break;
                default:
            }
        byte playerid = -1;
        for (final Player player : players)
            player.init(configuration, cards, playernames.length, playerid++);
        for (final Card card : cards)
            this.drawpile_cards.add(card);
        for (int i = 0; i < configuration.getNumCardsPerPlayerHand() * players.length; i++)
            this.drawpile_cards.pop();
        this.round = 0;
        actual_player = players[0];
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
    public AbstractGamePlayer[] getPlayers() {
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
        for (final AbstractGamePlayer player : players)
            if(!player.equals(actual_player))
                player.update(move);
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
            player.init(configuration, drawpilecard_list, players.length, count);
            count++;
        }
    }

    @Override
    public GameConfiguration getGameConfiguration() {
        return this.configuration;
    }

    //@Override
    public GameBoardState getState() {
        return this;
    }

    @Override
    public Stack<Card> getDrawPileStack() {
        return this.drawpile_cards;
    }
}
