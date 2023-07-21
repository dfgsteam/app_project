package bauernhof.app;

import java.awt.*;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import bauernhof.app.networking.Client;
import bauernhof.app.system.GameSystem;
import bauernhof.app.system.Tournament;
import bauernhof.preset.*;
import bauernhof.preset.networking.S2CConnection;

public class Init {
    public static void main(String[] args) throws Exception {
        List<String> names = new ArrayList<>(List.of("Kirill Pokhilenko", "Ramon Cemil Kimyon", "Viktor Tevosyan","Florian Will", "Julius Hunold"));
        List<OptionalFeature> optionalFeatures = new ArrayList<>(List.of(OptionalFeature.ADVANCED_AI, OptionalFeature.LAUNCHER, OptionalFeature.SAVEGAMES, OptionalFeature.SCREENSHOTS, OptionalFeature.SIMPLE_AI, OptionalFeature.SOUNDEFFECTS, OptionalFeature.TOURNAMENTS));
        if (args.length == 0) {
            args = new String[1];
            args[0] = "-l";
        }
        Settings settings = new ArgumentParser(args, "Hofbauern", "1.2.1", names, optionalFeatures);

        GaCoPa gacopa = new GaCoPa();
        if (settings.shouldLauncherLaunch)
            //new InitLauncher();
            settings.delay = 1000;
            settings.showGUI = true;
            settings.volume = 0;
            settings.logLevel = LogLevel.INFO;
            settings.playerNames = List.of(new String[]{"Florian", "S", "B"});
        settings.playerColors = List.of(new Color[]{Color.RED, Color.BLACK, Color.YELLOW});
        settings.playerTypes = List.of(new PlayerType[]{PlayerType.HUMAN, PlayerType.RANDOM_AI, PlayerType.RANDOM_AI});
        settings.gameConfigurationFile = new File("gameconfigs/bauernhof.xml");
        settings.delay = 1000L;
        settings.showGUI = true;
        settings.connectToHostname = null;
        settings.port = 6600;
        settings.loadSaveGameFile = null;
        settings.shouldLauncherLaunch = false;
        settings.numTournamentRounds = 0;
        settings.waitAfterTournamentRound = false;
        settings.volume = 0;
        Init.initGame(settings);


    }
    public static void initGame(final Settings settings) throws Exception {
        final GaCoPa gacopa = new GaCoPa();
        System.out.println("GAME STARTET");
        ArrayList<S2CConnection> connections = new ArrayList<>();
        if(settings.numTournamentRounds == 0) {
            if (settings.connectToHostname != null) new Client(settings, new Socket(settings.connectToHostname, settings.port), gacopa, "Hofbauern");
            if (settings.playerTypes.contains(PlayerType.REMOTE)){
                final ServerSocket socket = new ServerSocket(settings.port);
                for (final PlayerType type : settings.playerTypes)
                    if (type.equals(PlayerType.REMOTE))
                        connections.add(new S2CConnection(socket.accept()));
            }
            final GameSystem system = new GameSystem(settings, gacopa.parse(settings.gameConfigurationFile));
            system.createPlayers(connections);
            system.initPlayers();
        } else {
            Tournament tournament = new Tournament(settings, gacopa.parse(settings.gameConfigurationFile));
            tournament.initTournament();
        }
    }
}