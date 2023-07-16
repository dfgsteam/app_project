package bauernhof.app.player;

/**
 * Die Abtrakte Klasse {@link bauernhof.app.player.AbstractGamePlayer}
 * ist die Klasse von der jede Instanz eines {@link bauernhof.preset.PlayerType}
 * erben wird.
 *
 * @author Ramon Cemil Kimyon
 * @date 09.06.2023 00:55
 */

import bauernhof.app.system.GameSystem;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;

public abstract class AbstractGamePlayer implements Player {
    private int playerid;
    protected GameConfiguration configuration;
    private PlayerCards playercards;
    protected GameSystem gamesystem;
    private Settings settings;

    public AbstractGamePlayer(final Settings settings, final PlayerCards playercards, final GameSystem gamesystem) {
        this.settings = settings;
        this.playercards = playercards;
        this.gamesystem = gamesystem;
    }


    @Override
    public String getName() {
        return this.settings.playerNames.get(playerid);
    }

    @Override
    public void init(final GameConfiguration configuration, final ImmutableList<Card> cards, final int numplayers, final int playerid) throws Exception {
        this.playerid = playerid - 1;
        this.configuration = configuration;
        for (int i = 0; i < numplayers; i++)
            gamesystem.initBeginnerCards(playerid);
    }

    @Override
    public void update(Move opponentMove) throws Exception {
        if(!gamesystem.executeMove(opponentMove)) GameSystem.getGraphics().createCheaterPanel(settings.playerNames.get(gamesystem.getActivePlayerID()));
    }

    public boolean executeMove(Move move) throws Exception {
        return gamesystem.executeMove(move);
    }

    @Override
    public void verifyGame(ImmutableList<Integer> scores) throws Exception {
        for (int playerid = 0; playerid < scores.size(); playerid++)
            if (scores.get(playerid) != gamesystem.getAllScores().get(playerid))
                GameSystem.getGraphics().createCheaterPanel(settings.playerNames.get(playerid));
    }

    public int getPlayerID() {
        return playerid + 1;
    }

    @Override
    public int getScore() throws Exception {
        return playercards.getScore();
    }
}