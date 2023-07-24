package bauernhof.app;

import java.io.File;
import java.io.IOException;

import bauernhof.app.launcher.LauncherSettingsException;
import bauernhof.app.launcher.LauncherSettingsParser;
import bauernhof.app.ui.launcher.UiLauncher;

/**
 * The InitLauncher class is responsible for initializing the game launcher.
 * It reads the configuration settings from an XML file, creates the launcher settings parser, and launches the user interface (UI) for the game launcher.
 * The launcher allows the user to configure various settings before starting the game.
 *
 * The main functionality of this class is to set up the game launcher UI using the parsed settings from the XML file.
 * The launcher settings are specified in the "general.xml" file located in the "gameconfigs/game/" directory.
 *
 * Note: This class may throw IOException and InterruptedException if there are errors in file handling or thread interruption,
 * and it may throw LauncherSettingsException if there are issues parsing the launcher settings from the XML file.
 *
 * @see LauncherSettingsParser
 * @see UiLauncher
 * @author Julius Hunold
 * @version 1.0
 */

public class InitLauncher {

    /**
     * Constructs a new InitLauncher instance.
     * It initializes the game launcher by reading the configuration settings from the "general.xml" file
     * and launching the user interface for the game launcher (UiLauncher).
     *
     * @throws IOException If there is an I/O error while reading the "general.xml" file.
     * @throws InterruptedException If the current thread is interrupted during the execution.
     * @throws LauncherSettingsException If there is an error parsing the launcher settings from the XML file.
     */
    
    public InitLauncher() throws IOException, InterruptedException, LauncherSettingsException {
        new UiLauncher(new LauncherSettingsParser().parse(new File("gameconfigs/game/general.xml")));
    }
}
