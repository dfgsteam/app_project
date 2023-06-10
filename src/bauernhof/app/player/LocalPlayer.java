package bauernhof.app.player;

/**
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 00:55
 */

import bauernhof.app.launcher.GameStatus;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

public class LocalPlayer implements GamePlayer {
    private String name;
    private ImmutableList<Card> initialDrawPile;
    private Move move;
    private int playerid;
    private GameConfiguration configuration;
    private GameStatus status;
    private PlayerType type;

    public LocalPlayer(final String name, final GameStatus status, final PlayerType type) {
        this.name = name;
        this.status = status;
        this.type = type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public GameStatus getStatus() {
        return this.status;
    }

    @Override
    public PlayerType getPlayerType() {
        return this.type;
    }

    @Override
    public void init(GameConfiguration gameConfiguration, ImmutableList<Card> initialDrawPile, int numplayers, int playerid) throws Exception {
        this.initialDrawPile = initialDrawPile;
        this.playerid = playerid;
        this.configuration = gameConfiguration;
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
    public ImmutableList<Card> getCards() {
        return initialDrawPile;
    }

    @Override
    public ImmutableList<Card> getActiveCards() {
        return null;
    }

    @Override
    public ImmutableList<Card> getBlockedCards() {
        return null;
    }

    @Override
    public int getPlayerID() {
        return playerid;
    }

    @Override
    public GameConfiguration getGameConfiguration() {
        return configuration;
    }


}
