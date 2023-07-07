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

public class AbstractGamePlayer extends PlayerCards implements GamePlayer {
    private String name;
    protected Move move;
    private int playerid;
    private GameConfiguration configuration;
    private PlayerGameBoard state;
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
    }

    @Override
    public AbstractGamePlayer clone() {
        final Set<Card> cards = new HashSet<>();
        for (final Card card : getCards())
            cards.add(card);
        final AbstractGamePlayer player = new AbstractGamePlayer(this.name, this.type, cards);
        player.setPlayerID(this.playerid);
        player.setGameConfiguration(configuration);
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
        this.configuration = configuration;
        Stack<Card> initialDrawPile = new Stack<>();
        for (final Card card : cards)
            initialDrawPile.add(card);
        this.state = new PlayerGameBoard(numplayers, configuration, (Stack<Card>) initialDrawPile.clone());
        for (int i = 0; i < numplayers; i++)
            for (int x = 0; x < configuration.getNumCardsPerPlayerHand(); x++)
                if (i == playerid)
                    this.add(initialDrawPile.pop());
                else
                    initialDrawPile.pop();
    }


    public void setGameConfiguration(final GameConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Move request() throws Exception {
        return this.move;
    }

    @Override
    public void update(Move opponentMove) throws Exception {
        if (!state.doMove(opponentMove)) {
            System.out.println("Jemand hat geschummelt!");
            System.exit(0);
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


    @Override
    public int getScore() throws Exception {
        return this.score;
    }
}