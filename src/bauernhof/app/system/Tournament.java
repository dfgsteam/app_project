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
    private int counter = 0;
    private
    final ArrayList<GameSystem> states = new ArrayList<>();
    public Tournament(final Settings settings, final GameConfiguration configuration) throws Exception {
        this.settings = settings;
        this.configuration = configuration;
        for (int i = 0; i<= settings.playerTypes.size(); i++)
            wins.add(0);
    }

    public final void initTournament() throws Exception {
        GameSystem system;
        settings.showGUI = false;
        settings.delay = 0L;
        for (int i = 1; i <= settings.numTournamentRounds; i++) {
            system = new GameSystem(settings, configuration);
            states.add(system);
            system.createPlayers(new ArrayList<>());
            system.initPlayers();
            wins.set(system.getWinnerID(), wins.get(system.getWinnerID()) + 1);
            System.out.println("Tournament Runde " + i + "\t || \t" + wins + "\t || \t" + system.getAllScores());
        }
        System.out.println("\n\nErgebnis: ");
        for (int i = 0; i <= settings.playerTypes.size(); i++)
            if (i != settings.playerTypes.size()) {
                System.out.println(settings.playerTypes.get(i).name() + " --> " + settings.playerNames.get(i) + "\t|| \t" + wins.get(i));
            } else {
                System.out.println("Unentschieden: \t\t" + wins.get(i));
            }
    }
}