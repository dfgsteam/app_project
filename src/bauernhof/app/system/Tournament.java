package bauernhof.app.system;

import bauernhof.preset.*;

import java.util.ArrayList;

/**
 * The Tournament class represents a tournament of games played between different player types. It manages the tournament
 * rounds, tracks the number of wins for each player type, and displays the results at the end of the tournament.
 * The Tournament class uses the GameSystem to simulate individual games and keeps track of the overall results.
 *
 * The Tournament class allows setting up and running a tournament with the specified settings and game configuration.
 * It uses the GameSystem to create and manage game instances for each tournament round. The results of each round are
 * accumulated to determine the overall winners and the number of wins for each player type.
 *
 * @see GameSystem
 * @see Settings
 * @see GameConfiguration
 * @see java.util.ArrayList
 * @see java.util.List
 * @author Florian Will
 */

public class Tournament {
    private ArrayList<Integer> wins = new ArrayList<>();
    private Settings settings;
    private GameConfiguration configuration;
    private int counter = 0;
    private
    final ArrayList<GameSystem> states = new ArrayList<>();

    /**
     * Constructs a new Tournament instance with the specified settings and game configuration.
     *
     * @param settings      The settings for the tournament, including player types, names, and number of rounds.
     * @param configuration The configuration for the games in the tournament, including card types and rules.
     * @throws Exception If an error occurs while initializing the tournament.
     */

    public Tournament(final Settings settings, final GameConfiguration configuration) throws Exception {
        this.settings = settings;
        this.configuration = configuration;
        for (int i = 0; i<= settings.playerTypes.size(); i++)
            wins.add(0);
    }

    /**
     * Initializes and runs the tournament. It simulates multiple rounds of games and tracks the results of each round.
     * At the end of the tournament, it displays the overall results and the number of wins for each player type.
     *
     * @throws Exception If an error occurs during the tournament or game simulations.
     */
    
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