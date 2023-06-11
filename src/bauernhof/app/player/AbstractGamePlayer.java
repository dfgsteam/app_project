package bauernhof.app.player;

/**
 * Die Abtrakte Klasse {@link bauernhof.app.player.AbstractGamePlayer}
 * ist die Klasse von der jede Instanz eines {@link bauernhof.preset.PlayerType}
 * erben wird.
 *
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 00:55
 */

import bauernhof.app.launcher.GameState;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;


public abstract class AbstractGamePlayer extends PlayerCards implements GamePlayer {
    private String name;
    private ImmutableList<Card> initialDrawPile;
    protected Move move;
    private int playerid;
    private GameConfiguration configuration;
    private GameState state;
    private PlayerType type;
    public AbstractGamePlayer(final String name, final GameState status, final PlayerType type) {
        this.name = name;
        this.state = status;
        this.type = type;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public void initNextMove(final Move move) {
        this.move = move;
    }

    @Override
    public void executeMove() {

    }

    @Override
    public String getName() {
        return this.name;
    }

    public GameState getState() {
        return this.state;
    }
    public void setGameState(final GameState state) {
        this.state = state;
    }

    public PlayerType getPlayerType() {
        return this.type;
    }

    @Override
    public void init(GameConfiguration configuration, ImmutableList<Card> initialDrawPile, int numplayers, int playerid) throws Exception {
        this.initialDrawPile = initialDrawPile;
        this.playerid = playerid;
        this.configuration = configuration;
    }

    @Override
    public Move request() throws Exception {
        return this.move;
    }

    @Override
    public void update(Move opponentMove) throws Exception {
        /*
        TODO:
         Send Update of the Move at the GameState.
         Update initalDrawPileStack
         */
        initialDrawPile.remove(opponentMove.getDeposited());
        initialDrawPile.add(opponentMove.getTaken());
        this.move = opponentMove;
    }

    @Override
    // ello
    public void verifyGame(ImmutableList<Integer> scores) throws Exception {
        /*
        TODO: Verify all games Scores at the end of the Game
         */
    }

    public int getPlayerID() {
        // returns the PlayerID from Player#init
        return playerid;
    }

    public GameConfiguration getGameConfiguration() {
        // returns the GameConfiguration from the Player#init
        return configuration;
    }

    @Override
    public GameState getStatus() {
        return this.state;
    }

    @Override
    public ImmutableList<Card> getDrawPileStack() {
        return null;
    }

}

