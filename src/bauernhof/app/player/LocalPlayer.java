package bauernhof.app.player;

/**
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 00:55
 */

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.Move;
import bauernhof.preset.card.Card;

import java.util.HashSet;
import java.util.Set;

public class LocalPlayer implements GamePlayer {

    private String name;
    private ImmutableList<Card> initialDrawPile;
    private Move move;
    private int playerid;

    public LocalPlayer(final String name) {
        this.name = name;
    }

    @Override
    public String getName() throws Exception {
        return this.name;
    }

    @Override
    public void init(GameConfiguration gameConfiguration, ImmutableList<Card> initialDrawPile, int numplayers, int playerid) throws Exception {
        this.initialDrawPile = initialDrawPile;
        this.playerid = playerid;
    }

    @Override
    public Move request() throws Exception {
        return this.move;
    }

    @Override
    public void update(Move opponentMove) throws Exception {
        initialDrawPile.remove(opponentMove.getDeposited());
        initialDrawPile.add(opponentMove.getTaken());
        this.move = opponentMove;
    }

    @Override
    public int getScore() throws Exception {
        return 0;
    }

    @Override
    public void verifyGame(ImmutableList<Integer> scores) throws Exception {

    }

    @Override
    public Set<Card> getCards() {
        final Set<Card> cards = new HashSet<>();
        for (byte i = 0; i < cards.size(); i++) cards.add(initialDrawPile.get(i));
        return cards;
    }

    @Override
    public Set<Card> getActiveCards() {
        return null;
    }

    @Override
    public Set<Card> getBlockedCards() {
        return null;
    }

    @Override
    public int getPlayerID() {
        return playerid;
    }
}
