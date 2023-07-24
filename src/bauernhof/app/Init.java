package bauernhof.app;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import bauernhof.app.exceptions.NoTournamentGUIException;
import bauernhof.app.exceptions.WrongInputException;
import bauernhof.app.networking.Client;
import bauernhof.app.system.GameSystem;
import bauernhof.app.system.Tournament;
import bauernhof.preset.*;
import bauernhof.preset.networking.S2CConnection;

/**
 * The Init class is the entry point for the "Hofbauern" game application.
 * It handles the initialization of the game and tournament based on the provided settings.
 * @author Florian Will
 */

public class Init {

    /**
     * The main method that is called when starting the "Hofbauern" game application.
     * It parses the command-line arguments to create the game settings and then initializes the game or tournament accordingly.
     *
     * @param args The command-line arguments provided to the application.
     * @throws Exception If an error occurs during game or tournament initialization.
     */

    public static void main(String[] args) throws Exception {
        List<String> names = new ArrayList<>(List.of("Kirill Rokhilenko", "Ramon Cemil Kimyon", "Viktor Tevosyan", "Florian Will", "Julius Hunold"));
        List<OptionalFeature> optionalFeatures = new ArrayList<>(List.of(
                OptionalFeature.ADVANCED_AI,
                OptionalFeature.LAUNCHER,
                OptionalFeature.SCREENSHOTS,
                OptionalFeature.SIMPLE_AI,
                OptionalFeature.TOURNAMENTS));
        final Settings settings = new ArgumentParser(args, "Hofbauern", "1.2.1", names, optionalFeatures);
        checkSettings(settings);
        if (settings.shouldLauncherLaunch)
            new InitLauncher();
        else initGame(settings);
    }

    /**
     * Initializes the game based on the provided settings.
     *
     * @param settings The game settings.
     * @throws Exception If an error occurs during game initialization.
     */

    public static void initGame(final Settings settings) throws Exception {
        final GaCoPa gacopa = new GaCoPa();
        ArrayList<S2CConnection> connections = new ArrayList<>();
        ServerSocket socket;
        if(settings.numTournamentRounds == 0) {
            if (settings.connectToHostname != null)
                new Client(settings, new Socket(settings.connectToHostname, settings.port), gacopa, "Hofbauern");
            else {
                if (settings.playerTypes.contains(PlayerType.REMOTE)) {
                    socket = new ServerSocket(settings.port);
                    for (final PlayerType type : settings.playerTypes)
                        if (type.equals(PlayerType.REMOTE))
                            connections.add(new S2CConnection(socket.accept()));
                }
                final GameSystem system = new GameSystem(settings, gacopa.parse(settings.gameConfigurationFile));
                system.createPlayers(connections);
                system.initPlayers();
            }
        } else {
            Tournament tournament = new Tournament(settings, gacopa.parse(settings.gameConfigurationFile));
            tournament.initTournament();
        }
    }

    /**
     * Checks the provided game settings for validity.
     * Throws exceptions if there are any wrong inputs or invalid configurations.
     *
     * @param settings The game settings.
     * @throws WrongInputException If there are wrong inputs in the settings.
     * @throws NoTournamentGUIException If a tournament is configured to use GUI, which is not allowed.
     */
    
    public static void checkSettings(Settings settings) throws WrongInputException, NoTournamentGUIException {
        if (settings.playerNames.size() != settings.playerColors.size() || settings.playerTypes.size() != settings.playerNames.size() || settings.playerTypes.size() != settings.playerColors.size()) { throw new WrongInputException(); }
        if (settings.numTournamentRounds > 0 && settings.showGUI) { throw new NoTournamentGUIException(); }
    }

}