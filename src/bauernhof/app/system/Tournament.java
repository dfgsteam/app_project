package bauernhof.app.system;

import bauernhof.app.GaCoPa;
import bauernhof.app.networking.Client;
import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.group.popup.GroupPopupTournament;
import bauernhof.preset.*;

import java.net.Socket;
import java.util.ArrayList;
/**
 * @author Ramon Cemil Kimyon
 * @date 13.07.2023 14:05
 */
public class Tournament {
    private ArrayList<Integer> wins = new ArrayList<>();
    private Settings settings;
    private GameConfiguration configuration;
    private int counter = 0;
    final ArrayList<GameSystem> states = new ArrayList<>();
    private GroupPopupTournament tournament;
    public Tournament(final Settings settings, final GameConfiguration configuration) throws Exception {
        this.settings = settings;
        this.configuration = configuration;
        tournament = new GroupPopupTournament(this, settings);
    }

    public final void initTournament() throws Exception {
        if (settings.waitAfterTournamentRound)
            nextTournamentRound();
        else {
            for (int i = 1; i <= settings.numTournamentRounds; i++) {
                states.add(new GameSystem(settings, configuration));
                states.get(states.size() - 1).createPlayers(new ArrayList<>());
                states.get(states.size() - 1).initPlayers();
            }
            tournament.popupTournamentScore(true);
        }
    }
    public final void nextTournamentRound() throws Exception {
        try {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        final GameSystem system = new GameSystem(settings, configuration);

        if (GameBoard.graphics != null) {
            GameBoard.graphics.FRAME.dispose();

        }
        states.add(system);
        system.createPlayers(new ArrayList<>());
        if (GameBoard.graphics != null) {
            GameBoard.graphics = new UiGame(system);
        }
        system.initPlayers();

        if (counter == settings.numTournamentRounds)
            tournament.popupTournamentScore(true);
        else {
            tournament.popupTournamentScore(false);
            counter++;
        }
    }
    public ArrayList<Integer> getWins() {
        return wins;
    }
}