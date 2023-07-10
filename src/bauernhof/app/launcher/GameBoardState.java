package bauernhof.app.launcher;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.Advanced_AI;
import bauernhof.app.player.types.HumanPlayer;
import bauernhof.app.player.types.Random_AI;
import bauernhof.app.player.types.Simple_AI;
import bauernhof.app.ui.game.GameBoard;
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
    private int round;
    private boolean run;
    public AbstractGamePlayer actual_player;
    private int activeplayerid = 0;
    private ArrayList<Card> deposited_cards = new ArrayList<>();
    private Stack<Card> drawpile_cards = new Stack<>();
    private AbstractGamePlayer[] players;
    private GameBoard graphics;
    private String[] playernames;
    private PlayerType[] types;
    private GameConfiguration configuration;
    public GameBoardState(final String[] playernames, final PlayerType[] types, GameConfiguration configuration, final ImmutableList<Card> cards) throws Exception {

        this.playernames = playernames;
        this.run = true;
        //Collections.shuffle(cards);
        this.round = 1;
        final AbstractGamePlayer[] players = new AbstractGamePlayer[playernames.length];
        this.players = players;
        for (int i = 0; i < players.length; i++)
            switch (types[i]) {
                case ADVANCED_AI:
                    players[i] = new Advanced_AI(playernames[i]);
                    break;
                case HUMAN:
                    players[i] = new HumanPlayer(playernames[i])
                    break;
                case RANDOM_AI:
                    players[i] = new Random_AI(playernames[i]);
                    break;
                case REMOTE:
                    players[i] = new AbstractGamePlayer(playernames[i], types[i]);
                    break;
                case SIMPLE_AI:
                    players[i] = new Simple_AI(playernames[i]);
                    break;
                default:
            }
        for (byte playerid = 0; playerid < playernames.length; playerid++)
            players[playerid].init(configuration, cards, playernames.length, playerid);
        for (final Card card : cards)
            this.drawpile_cards.add(card);
        for (int i = 0; i < configuration.getNumCardsPerPlayerHand() * players.length; i++)
            this.drawpile_cards.pop();
        this.round = 0;
        actual_player = players[0];
        this.configuration = configuration;
        for (final AbstractGamePlayer player : players)
            if (player.getPlayerType().equals(PlayerType.ADVANCED_AI))
                ((Advanced_AI)player).setGameBoardState(this.clone());


    }
    public GameBoardState() {

    }
    public void initGame(final GameBoard graphics) throws Exception {
        this.graphics = graphics;
        System.out.println("GAME WIRD GESTARTET");
        switch (actual_player.getPlayerType()) {
            case RANDOM_AI:
                this.doMove(((Random_AI) actual_player).calculateNextMove());
                break;
            case ADVANCED_AI:
                this.doMove(((Advanced_AI) actual_player).calculateNextMove());
                break;
            case SIMPLE_AI:
                this.doMove(((Simple_AI) actual_player).calculateNextMove());
                break;
            default:
        }

    }

    @Override
    public GameBoardState clone() {
        final GameBoardState state = new GameBoardState();
        state.setActiveplayerid(activeplayerid);
        final AbstractGamePlayer[] players = new AbstractGamePlayer[this.players.length];
        for (int i = 0; i < players.length; i++)
            players[i] = this.players[i].clone();
        state.setPlayers(players);
        state.setConfiguration(configuration);
        state.setRound(round);
        state.setRun(false);
        final ArrayList<Card> deposited_cards = new ArrayList<>();
        for (final Card card : this.deposited_cards)
            deposited_cards.add(card);
        state.setDeposited_cards(deposited_cards);
        final Stack<Card> drawpile_cards = new Stack<>();
        for (final Card card : this.drawpile_cards)
            drawpile_cards.add(card);
        state.setDrawpile_cards(drawpile_cards);
        return state;
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
        if (!drawpile_cards.isEmpty())
        /*System.out.println("DRAWPILE_CARDS : " + drawpile_cards.lastElement().getName());
        System.out.print("DEPOSITED_CARDS: ");
        for (final Card card : deposited_cards)
            System.out.print(card.getName() + ", ");
        System.out.println("\n");
        System.out.println("ACTIVEPLAYER: " + getActualPlayer().getName() + " " + activeplayerid);
        System.out.println(activeplayerid + " TAKEN : " + move.getTaken().getName() + "    DEPOSITED : " + move.getDeposited().getName());*/
        if (deposited_cards.contains(move.getTaken()))
            deposited_cards.remove(move.getTaken());
        if(!(drawpile_cards.isEmpty()) && drawpile_cards.lastElement().equals(move.getTaken()))
            drawpile_cards.pop();
        //else return false;
        /*if (!getActualPlayer().getCards().contains(move.getDeposited()))
            return false; */
        deposited_cards.add(move.getDeposited());
        getActualPlayer().add(move.getTaken());
        getActualPlayer().remove(move.getDeposited());
       /* for (final AbstractGamePlayer gameplayer : this.getPlayers()) {
            System.out.print(gameplayer.getPlayerID() + " > " + gameplayer.getName() + "\t|| ");
            for (final Card card : gameplayer.getCards()) {
                System.out.print(card.getName() + ", ");
            }
            System.out.println( "\t  [" + gameplayer.getCards().size() + "]");
        }
        */
        for (final AbstractGamePlayer player : players)
            if(!player.equals(getActualPlayer()))
                player.update(move);
            else
                getActualPlayer().doMove(move);
            activeplayerid++;
            if(activeplayerid == players.length) {
                activeplayerid = 0;
                this.round++;
            }

            if (graphics != null)
            if (round > 30) {
                graphics.move(true);
                run = false;
            }else graphics.move(false);
        //System.out.println("===================");
        if (run) {
            Thread.sleep(50);
            this.doMove(getActualPlayer().request());
            switch (getActualPlayer().getPlayerType()) {
                case RANDOM_AI:
                    this.doMove(((Random_AI) getActualPlayer()).calculateNextMove());
                    break;
                case SIMPLE_AI:
                    this.doMove(((Simple_AI) getActualPlayer()).calculateNextMove());
                    break;
                case REMOTE:
                    this.doMove(getActualPlayer().request());
                    break;
                case ADVANCED_AI:
                    this.doMove(((Advanced_AI) getActualPlayer()).calculateNextMove());
                    break;
                default:
                    break;
            }
        }


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
    public void setRound(int round) {
        this.round = round;
    }
    public void setActual_player(AbstractGamePlayer player) {
        this.actual_player = player;
    }
    public void setRun(final boolean run) {
        this.run = run;
    }
    public void setActiveplayerid(final int activeplayerid) {
        this.activeplayerid = activeplayerid;
    }
    public void setDeposited_cards(final ArrayList<Card> deposited_cards) {
        this.deposited_cards = deposited_cards;
    }
    public void setDrawpile_cards(final Stack<Card> drawpile_cards) {
        this.drawpile_cards = drawpile_cards;
    }
    public void setPlayers(final AbstractGamePlayer[] players) {
        this.players = players;
    }
    public void setConfiguration(final GameConfiguration configuration) {
        this.configuration = configuration;
    }


}
