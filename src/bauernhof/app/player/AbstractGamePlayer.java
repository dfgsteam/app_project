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

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class AbstractGamePlayer extends PlayerCards implements GamePlayer {
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
    public AbstractGamePlayer(final String name, final PlayerType type, final Set<Card> cards) {
        this.name = name;
        this.type = type;
        this.cards = cards;
    }

    public AbstractGamePlayer(final String name, final PlayerType type) {
        this.name = name;
        this.type = type;
    }
    /**
     * Führt den {@link Move} aus der durch die Methode {@link PlayerProperties#initNextMove(Move)}
     * initialisiert wurde.
     * Dabei wird der Nachziehen-Stapel und der Stapel der hingelegten Karten
     * updated und an den aktuellen {@link GameBoardState} gesendet.
     */
    public void executeMove() throws Exception {

    }
    @Override
    public AbstractGamePlayer clone() {
        final Set<Card> cards = new HashSet<>();
        for (final Card card : getCards())
            cards.add(card);
        AbstractGamePlayer player = new AbstractGamePlayer(this.name, this.type, cards);
        player.setPlayerID(this.playerid);
        player.setDrawPileCards((Stack<Card>) initialDrawPile.clone());
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

    /**8
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
    public void init(final GameConfiguration configuration, final ImmutableList<Card> cards, final int numplayers, final int playerid) throws Exception {
        this.playerid = playerid;
        this.numplayers = numplayers;
        this.configuration = configuration;
        this.initialDrawPile = new Stack<>();
        for (final Card card : initialDrawPile)
            this.initialDrawPile.add(card);

        AbstractGamePlayer[] players = new AbstractGamePlayer[numplayers];
        for(int i = 0; i < numplayers; i++) {
            if (i == playerid)
                players[i] = this;
            else
                players[i] = new AbstractGamePlayer("", PlayerType.HUMAN);
        }
        for (int playeridcounter = 0; playerid < numplayers; playeridcounter++)
            for (int card_count = 0; playerid < configuration.getNumCardsPerPlayerHand(); card_count++) {
                players[playerid].add(cards.get(cards.size() - 1));
                cards.remove(cards.size() - 1);
            }
        this.state = new GameBoardState(configuration, players, cards);
    }

    public void setDrawPileCards(final Stack<Card> initialDrawPile) {
        this.initialDrawPile = initialDrawPile;
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
        if (state.getDepositedCards().contains(move.getTaken()))
            state.getDepositedCards().remove(move.getTaken());
        else if(this.getDrawPileStack().get(0).equals(move.getTaken()))
            this.getDrawPileStack().pop();
        else System.exit(1);
        if (!state.getActualPlayer().getCards().contains(move.getDeposited()))
            System.exit(1);
        state.getDepositedCards().add(move.getDeposited());
        state.getActualPlayer().remove(move.getDeposited());
        state.getActualPlayer().add(move.getTaken());
        this.state.actual_player = this.state.player_iterator.next();
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
    public Stack<Card> getDrawPileStack() {
        return null;
    }
    @Override
    public int getScore() throws Exception {
        return this.score;
    }
}