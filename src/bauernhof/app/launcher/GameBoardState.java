package bauernhof.app.launcher;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.PlayerCards;
import bauernhof.app.player.types.*;
import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;

import java.awt.*;
import java.util.*;
import java.util.List;

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
    private PlayerCards[] playercards;
    private AbstractGamePlayer[] players;
    private UiGame graphics;
    private String[] playernames;
    private PlayerType[] types;
    private GameConfiguration configuration;
    public GameBoardState(final String[] playernames, final PlayerType[] types, GameConfiguration configuration, ImmutableList<Card> cards, List<Color> playercolors) throws Exception {
        cards = mixCards(cards);
        this.playernames = playernames;
        this.run = true;
        this.round = 1;
        this.players = new AbstractGamePlayer[playernames.length];
        for (int i = 0; i < players.length; i++)
            switch (types[i]) {
                case ADVANCED_AI:
                    players[i] = new Advanced_AI(playernames[i], playercolors.get(i));
                    break;
                case HUMAN:
                    players[i] = new HumanPlayer(playernames[i], this, playercolors.get(i));
                    break;
                case RANDOM_AI:
                    players[i] = new Random_AI(playernames[i], playercolors.get(i));
                    break;
                case REMOTE:
                    players[i] = new LocalRemotePlayer(playernames[i], playercolors.get(i));
                    break;
                case SIMPLE_AI:
                    players[i] = new Simple_AI(playernames[i], playercolors.get(i));
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
                ((Advanced_AI)player).setGameBoardState(this);
    }
    public GameBoardState() {}
    public void initGame(final UiGame graphics) throws Exception {
        this.graphics = graphics;
        if (!this.getActualPlayer().getPlayerType().equals(PlayerType.HUMAN))
            this.doMove(actual_player.request());

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
    public AbstractGamePlayer getWinner() throws Exception {
        int[] scores = new int[getPlayers().length];
        for (int i = 0; i < this.getPlayers().length; i++) {
            scores[i] = this.getPlayers()[i].getScore();
        }
        Arrays.sort(scores);
        if (scores[getPlayers().length - 1] == scores[getPlayers().length - 2])
            return null;
        for (final Player player : this.getPlayers())
            if (player.getScore() == scores[getPlayers().length - 1])
                return (AbstractGamePlayer) player;
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
        if (!drawpile_cards.isEmpty())
            if (deposited_cards.contains(move.getTaken()))
                deposited_cards.remove(move.getTaken());
        if(!(drawpile_cards.isEmpty()) && drawpile_cards.lastElement().equals(move.getTaken()))
            drawpile_cards.pop();
        deposited_cards.add(move.getDeposited());
        getActualPlayer().add(move.getTaken());
        getActualPlayer().remove(move.getDeposited());
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
        if (round > 30 || drawpile_cards.isEmpty() || deposited_cards.size() >= configuration.getNumDepositionAreaSlots()) run = false;
        if (graphics == null) {
        } else {
            graphics.move(!run);
        }
        if (run) {
            if(!this.getActualPlayer().getPlayerType().equals(PlayerType.HUMAN)) {
                Thread.sleep(200);
                this.doMove(getActualPlayer().request());
            }
        }
        return true;
    }

    private ImmutableList<Card> mixCards(final ImmutableList<Card> cards) {
        ArrayList<Card> cards2 = new ArrayList<>(cards);
        Collections.shuffle(cards2);
        ImmutableList<Card> cards3 = new ImmutableList<>(cards2);
        return cards3;

    }

    @Override
    public AbstractGamePlayer getActualPlayer() {
        return players[activeplayerid];
    }
    public GameConfiguration getConfiguration() {
        return configuration;
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
    public boolean getRun() {
        return this.run;
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
