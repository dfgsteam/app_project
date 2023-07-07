package bauernhof.app.launcher;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;

import java.util.*;

/**
 * Diese Klasse ist der  Generelle Main Handler für das gesamte Spielbrett.
 * Sie gibt über jeden Status des aktuellen Spiels bescheid.
 * Auch die Instanzen der aktuellen {@link Player} sind enthalten.
 * Zudem dient die Klasse auch zum Laden von gespeicherten Spielständen
 *
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 00:55
 */

public class GameBoardState implements Table {
    private int round;
    public AbstractGamePlayer actual_player;
    private int activeplayerid = 0;
    private ArrayList<Card> deposited_cards = new ArrayList<>();
    private Stack<Card> drawpile_cards = new Stack<>();
    private AbstractGamePlayer[] players;
    private GameConfiguration configuration;
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
        this.configuration = configuration;
    }

    @Override
    public Object clone() {
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
            else
                actual_player.doMove(move);
        if (!(activeplayerid < players.length)) activeplayerid = 0;
        else activeplayerid++;
        return true;
    }

    @Override
    public AbstractGamePlayer getActualPlayer() {
        return players[activeplayerid];
    }

    @Override
    public int getRound() {
        return this.round;
    }

}
