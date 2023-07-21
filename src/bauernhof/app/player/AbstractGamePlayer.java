package bauernhof.app.player;

/**
 * Die Abtrakte Klasse {@link bauernhof.app.player.AbstractGamePlayer}
 * ist die Klasse von der jede Instanz eines {@link bauernhof.preset.PlayerType}
 * erben wird.
 *
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 00:55
 */

import bauernhof.app.system.GameBoard;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;

public abstract class AbstractGamePlayer implements Player {
    private int playerid;
    protected GameConfiguration configuration;
    protected PlayerCards playercards;
    protected GameBoard gameBoard;
    private Settings settings;

    public AbstractGamePlayer(final Settings settings, final PlayerCards playercards, final GameBoard gameBoard) {
        this.settings = settings;
        this.playercards = playercards;
        this.gameBoard = gameBoard;
    }


    @Override
    public String getName() {
        return this.settings.playerNames.get(playerid);
    }

    @Override
    public void init(final GameConfiguration configuration, final ImmutableList<Card> cards, final int numplayers, final int playerid) throws Exception {
        this.playerid = playerid - 1;
        this.configuration = configuration;
        this.gameBoard.setDrawPileCards(cards);
        System.out.println("EXECUTED");
        this.gameBoard.setGameConfiguration(configuration);
        for (int i = 0; i < numplayers; i++)
            gameBoard.initBeginnerCards(i);
    }

    @Override
    public void update(Move opponentMove) throws Exception {
        if(!gameBoard.executeMove(opponentMove)) {
            System.out.println(getName());
            GameBoard.getGraphics().createCheaterPanel(settings.playerNames.get(gameBoard.getCurrentPlayerID()));

        }
    }

    public boolean executeMove(Move move) throws Exception {
        return gameBoard.executeMove(move);
    }

    @Override
    public void verifyGame(final ImmutableList<Integer> scores) throws Exception {
        for (int playerid = 0; playerid < scores.size(); playerid++)
            if (!scores.get(playerid).equals(gameBoard.getAllScores().get(playerid)))
                GameBoard.getGraphics().createCheaterPanel(settings.playerNames.get(playerid));
    }
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    @Override
    public int getScore() throws Exception {
        return playercards.getScore();
    }
}