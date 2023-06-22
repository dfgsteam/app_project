package bauernhof.app.player;

/**
 * Die Abtrakte Klasse {@link bauernhof.app.player.AbstractGamePlayer}
 * ist die Klasse von der jede Instanz eines {@link bauernhof.preset.PlayerType}
 * erben wird.
 *
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 00:55
 */

import bauernhof.app.launcher.GameBoardState;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;

import java.util.Stack;


public abstract class AbstractGamePlayer extends PlayerCards implements GamePlayer {
    private String name;
    protected Stack<Card> initialDrawPile = new Stack<>();
    protected Move move;
    private int playerid, numplayers;
    private GameConfiguration configuration;
    private GameBoardState state;
    private PlayerType type;

    /**
     * Setzt den aktuellen namen des Spielers.
     * Übergibt den Spielstatus. {@link GameBoardState}
     * Und definiert den Spieler Typen {@link PlayerType}
     *
     * @param name
     * @param state
     * @param type
     */


    public AbstractGamePlayer(final String name, final GameBoardState state, final PlayerType type) {
        this.name = name;
        this.state = state;
        this.type = type;
    }
    /**
     * Führt den {@link Move} aus der durch die Methode {@link PlayerProperties#initNextMove(Move)}
     * initialisiert wurde.
     * Dabei wird der Nachziehen-Stapel und der Stapel der hingelegten Karten
     * updated und an den aktuellen {@link GameBoardState} gesendet.
     */
    public void executeMove() throws Exception {
        this.remove(request().getDeposited());
        this.add(request().getTaken());
        // pop card from the actual stack
        if (request().getTaken().equals(initialDrawPile.firstElement())) initialDrawPile.pop();
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
    public String getName() {
        return this.name;
    }

    @Override
    public GameBoardState getState() {
        return this.state;
    }

    /**
     * Setzt den aktuellen Spielestand.
     * Fürs Laden von gespeicherten Spielen
     *
     * @param state
     */
    public void setState(final GameBoardState state) {
        this.state = state;
    }

    @Override
    public PlayerType getPlayerType() {
        return this.type;
    }

    @Override
    public void init(final GameConfiguration configuration, final ImmutableList<Card> initialDrawPile, final int numplayers, final int playerid) throws Exception {
         this.playerid = playerid;
        this.numplayers = numplayers;
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
    public void verifyGame(ImmutableList<Integer> scores) throws Exception {
        /*
        TODO: Verify all games Scores at the end of the Game
         */
    }

    public int getPlayerID() {
        // returns the PlayerID from Player#init
        return playerid;
    }

    @Override
    public GameConfiguration getGameConfiguration() {
        // returns the GameConfiguration from the Player#init
        return configuration;
    }

    @Override
    public ImmutableList<Card> getDrawPileStack() {
        return null;
    }
    @Override
    public int getScore() throws Exception {
        return this.score;
    }
}

