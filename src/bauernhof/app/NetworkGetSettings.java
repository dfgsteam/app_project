package bauernhof.app;

import bauernhof.preset.LogLevel;
import bauernhof.preset.PlayerType;
import bauernhof.preset.Settings;

import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * @author Ramon Cemil Kimyon
 * @date 22.07.2023 19:46
 */
public class NetworkGetSettings {
    public static Settings getSettings(int i) {
        final Settings settings = new Settings();
        if (i == 0) {
            settings.showGUI = true;
            settings.volume = 0;
            settings.logLevel = LogLevel.INFO;
            settings.playerNames = List.of(new String[]{"Player 1", "Player 2", "Player 3"});
            settings.playerColors = List.of(new Color[]{Color.RED, Color.GREEN, Color.YELLOW});
            settings.playerTypes = List.of(new PlayerType[]{PlayerType.HUMAN, PlayerType.HUMAN, PlayerType.HUMAN});
            settings.gameConfigurationFile = new File("gameconfigs/bauernhof.xml");
            settings.delay = 10L;
            settings.showGUI = true;
            settings.connectToHostname = null;
            settings.port = 6600;
            settings.loadSaveGameFile = null;
            settings.shouldLauncherLaunch = false;
            settings.numTournamentRounds = 0;
            settings.waitAfterTournamentRound = true;
            settings.volume = 0;
        } else if(i == 1){
            settings.delay = 1000;
            settings.showGUI = true;
            settings.volume = 0;
            settings.logLevel = LogLevel.INFO;
            settings.playerNames = List.of(new String[]{"Player 1", "Player 2", "Player 3"});//
            settings.playerColors = List.of(new Color[]{Color.RED, Color.GREEN, Color.YELLOW});
            settings.playerTypes = List.of(new PlayerType[]{PlayerType.RANDOM_AI, PlayerType.REMOTE, PlayerType.RANDOM_AI});
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
        } else {
            settings.delay = 0;
            settings.showGUI = true;
            settings.volume = 0;
            settings.logLevel = LogLevel.INFO;
            settings.playerNames = List.of(new String[]{"Player 1"});
            settings.playerColors = List.of(new Color[]{Color.RED});
            settings.playerTypes = List.of(new PlayerType[]{PlayerType.RANDOM_AI});
            settings.gameConfigurationFile = new File("gameconfigs/bauernhof.xml");
            settings.delay = 1000L;
            settings.showGUI = true;
            settings.connectToHostname = "localhost";
            settings.port = 6600;
            settings.loadSaveGameFile = null;
            settings.shouldLauncherLaunch = false;
            settings.numTournamentRounds = 0;
            settings.waitAfterTournamentRound = false;
            settings.volume = 0;
        }
        return settings;
    }
}
