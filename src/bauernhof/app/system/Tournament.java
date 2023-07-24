package bauernhof.app.system;

import bauernhof.preset.*;

import java.util.ArrayList;
/**
 * @author Florian Will
 * @date 13.07.2023 14:05
 */
public class Tournament {
    private ArrayList<Integer> wins = new ArrayList<>();
    private Settings settings;
    private GameConfiguration configuration;
    private int counter = 0;
    final ArrayList<GameSystem> states = new ArrayList<>();
    public Tournament(final Settings settings, final GameConfiguration configuration) throws Exception {
        this.settings = settings;
        this.configuration = configuration;
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
        }
    }
    public final void nextTournamentRound() throws Exception {
        final GameSystem system = new GameSystem(settings, configuration);
        states.add(system);
        system.createPlayers(new ArrayList<>());
        if (GameBoard.graphics != null) {
            GameBoard.graphics.initUI(system, true);
        }
        system.initPlayers();
    }
}