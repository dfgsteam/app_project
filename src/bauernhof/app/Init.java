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

public class Init {

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

    public static void checkSettings(Settings settings) throws WrongInputException, NoTournamentGUIException {
        //
        if (settings.playerNames.size() != settings.playerColors.size() || settings.playerTypes.size() != settings.playerNames.size() || settings.playerTypes.size() != settings.playerColors.size()) { throw new WrongInputException(); }
        if (settings.numTournamentRounds > 0 && settings.showGUI) { throw new NoTournamentGUIException(); }
    }

}