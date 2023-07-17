package bauernhof.app.system;

import bauernhof.preset.*;

import java.util.ArrayList;
/**
 * @author Ramon Cemil Kimyon
 * @date 13.07.2023 14:05
 */
public class Tournament {
    private ArrayList<Integer> wins = new ArrayList<>();
    private Settings settings;
    private GameConfiguration configuration;
    public Tournament(final Settings settings, final GameConfiguration configuration) throws Exception {
        this.settings = settings;
        this.configuration = configuration;
    }
    public final void initTournament() throws Exception {
        final ArrayList<GameSystem> states = new ArrayList<>();
        int x = 0;
        if(settings.numTournamentRounds == 0) {
            final GameSystem system = new GameSystem(settings, configuration);
            system.createPlayers(new ArrayList<>());
            system.initPlayers();
        } else {
            for (int i = 1; i <= settings.numTournamentRounds; i++) {
                if (settings.playerTypes.contains(PlayerType.ADVANCED_AI)) {
                    System.out.println("\n\n\n\n");
                    System.out.println("RUNDE: " + i);
                    System.out.println("\n\n\n\n");
                }
                if (x != (int) (((double) i / settings.numTournamentRounds) * 100))
                    System.out.println("Status: " + (int) (((double) i / settings.numTournamentRounds) * 100) + "%");
                x = (int) (((double) i / settings.numTournamentRounds) * 100);
                states.add(new GameSystem(settings, configuration));
                states.get(states.size() - 1).createPlayers(new ArrayList<>());
                states.get(states.size() - 1).initPlayers();
            }
            for (int i = 0; i <= states.get(0).numplayers; i++)
                wins.add(0);
            for (final GameSystem system : states)
                wins.set(system.getWinnerID(), wins.get(system.getWinnerID()) + 1);
            System.out.println(wins);
        }
    }
    public ArrayList<Integer> getWins() {
        return wins;
    }
}