package bauernhof.app.player;

/**
 * Die Abtrakte Klasse {@link bauernhof.app.player.AbstractGamePlayer}
 * ist die Klasse von der jede Instanz eines {@link bauernhof.preset.PlayerType}
 * erben wird.
 *
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 00:55
 */

import bauernhof.preset.*;
import bauernhof.preset.card.Card;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public abstract class AbstractGamePlayer extends PlayerCards implements GamePlayer {
    private String name;
    protected Move move;
    private int playerid;
    protected GameConfiguration configuration;
    protected PlayerGameBoard state;
    private PlayerType type;

    /**
     * Setzt den aktuellen namen des Spielers.
     * Und definiert den Spieler Typen {@link PlayerType}
     *
     * @param name
     * @param type
     */

    public AbstractGamePlayer(final String name, final PlayerType type) {
        this.name = name;
        this.type = type;
    }

    public AbstractGamePlayer(final String name, final PlayerType type, final Set<Card> cards) {
        this.name = name;
        this.type = type;
        this.cards = cards;
    }
    public AbstractGamePlayer() {}

    @Override
    public AbstractGamePlayer clone() {
        final Set<Card> cards = new HashSet<>();
        for (final Card card : getCards())
            cards.add(card);
        final AbstractGamePlayer player = new AbstractGamePlayer(this.name, this.type, cards) {
            @Override
            public Move request() {
                return null;
            }
        };
        player.setPlayerID(this.playerid);
        player.setGameConfiguration(configuration);
        player.setPlayerGameBoard(state.clone());
        player.setScore(this.score);
        return player;
    }
    @Override
    public void setName(final String name) {
        this.name = name;
    }
    public void setPlayerID(final int playerid) {
        this.playerid = playerid;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public PlayerType getPlayerType() {
        return this.type;
    }

    @Override
    public void init(final GameConfiguration configuration, final ImmutableList<Card> cards, final int numplayers, final int playerid) throws Exception {
        this.playerid = playerid;
        System.out.println("THIS 1");
        this.configuration = configuration;
        Stack<Card> initialDrawPile = new Stack<>();
        System.out.println("THIS 2");
        System.out.println(cards.size());
        for (final Card card : cards)
            initialDrawPile.add(card);
        this.state = new PlayerGameBoard(numplayers, configuration, (Stack<Card>) initialDrawPile.clone());
        System.out.println("THIS 3");
        for (int i = 0; i < numplayers; i++) {
            System.out.println(configuration.getNumCardsPerPlayerHand());
            for (int x = 0; x < configuration.getNumCardsPerPlayerHand(); x++) {
                if (i == playerid) {
                    System.out.println(i + " = i,  " + x + " = x: " + "FUNKTIONIERT");
                    System.out.println("I am here " + initialDrawPile.lastElement());
                    this.add(initialDrawPile.pop());
                }
                else {
                    System.out.println(i + " = i, " + x + " = x; " + " FUNKTIONIERT ELSE");
                    initialDrawPile.pop();
                }
            }
        }
        System.out.println("THIS 4");
    }


    public void setGameConfiguration(final GameConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void update(Move opponentMove) throws Exception {
        if (!state.doMove(opponentMove)) {
            //System.out.println("Jemand hat geschummelt!");
            //System.exit(0);
        }
    }

    public boolean doMove(Move move) {
        this.move = move;
        this.add(move.getTaken());
        this.remove(move.getDeposited());
        return state.doMove(move);
    }

    @Override
    public void verifyGame(ImmutableList<Integer> scores) throws Exception {
        scores.add(getScore());
    }

    public int getPlayerID() {
        return playerid;
    }
    public void setPlayerGameBoard(final PlayerGameBoard state) {
        this.state = state;
    }


    @Override
    public int getScore() throws Exception {
        return this.score;
    }
}