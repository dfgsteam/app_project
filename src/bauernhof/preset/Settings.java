package bauernhof.preset;

import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.awt.Color;


/**
 * The settings for the program.
 *
 * @see bauernhof.preset.ArgumentParser
 */
public class Settings {
	/**
	 * The maximum log level the program should use when printing to stdout/stderr.
	 */
	public LogLevel logLevel = LogLevel.INFO;

	/**
	 * A list of optional features which are implemented in this project.
	 * <p>
	 * This is useful, so that only the necessary options are used in the {@link ArgumentParser} and that the help text includes information about what is implemented.
	 * </p>
	 */
	public List<OptionalFeature> implementedOptionalFeatures = Arrays.asList(new OptionalFeature[] {});

	/**
	 * The names of the different players.
	 *
	 * <p style="color:#3c763d;background-color:#dff0d8;border-color:#d6e9c6;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- SUCCESS -->
	 * Only the values that are needed, are used.
	 * </p>
	 *
	 * <p style="color:#8a6d3b;background-color:#fcf8e3;border-color:#faebcc;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- WARNING -->
	 * If the program is told to connect to a server only the first name is used as the name.
	 * </p>
	 */
	public List<String> playerNames = Arrays.asList(new String[] {"Player 1", "Player 2", "Player 3", "Player 4"});

	/**
	 * The colors of the different players.
	 *
	 * <p>
	 * These colors can be used to differentiate the different players in the GUI.
	 * </p>
	 *
	 * <p style="color:#3c763d;background-color:#dff0d8;border-color:#d6e9c6;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- SUCCESS -->
	 * Only the values that are needed, are used.
	 * </p>
	 *
	 * <p style="color:#8a6d3b;background-color:#fcf8e3;border-color:#faebcc;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- WARNING -->
	 * If the program is told to connect to a server these values are still used for the players.
	 * </p>
	 */
	public List<Color> playerColors = Arrays.asList(new Color[] {Color.blue, Color.orange, Color.pink, Color.green});

	/**
	 * The types of the different players.
	 *
	 * <p>
	 * These types determine if the player is a human, a computer, or a remotly connected player.
	 * </p>
	 *
	 * <p style="color:#3c763d;background-color:#dff0d8;border-color:#d6e9c6;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- SUCCESS -->
	 * Only the values that are needed, are used.
	 * </p>
	 *
	 * <p style="color:#8a6d3b;background-color:#fcf8e3;border-color:#faebcc;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- WARNING -->
	 * If the program is told to connect to a server only the first type is used and cannot be {@link PlayerType#REMOTE}.<br>
	 * <br>
	 * If at least one of the values is {@link PlayerType#REMOTE} then the game should start the server and wait for the remote players to connect.
	 * </p>
	 */
	public List<PlayerType> playerTypes = Arrays.asList(new PlayerType[] {PlayerType.HUMAN, PlayerType.HUMAN, PlayerType.HUMAN, PlayerType.HUMAN});

	/**
	 * The file from which the game configuration should be read.
	 *
	 * @see bauernhof.preset.GameConfiguration
	 *
	 * <p>
	 * If the program is told to connect to a server this value is ignored.
	 * </p>
	 */
	public File gameConfigurationFile = null;

	/**
	 * Delay after a computerplayer has made its move.
	 *
	 * <p>
	 * The value is  in milliseconds.
	 * </p>
	 *
	 * <p>
	 * This is needed, because otherwise a game that is completely filled with computerplayers would end before a human could blink.
	 * </p>
	 */
	public long delay = 50;

	/**
	 * Whether a GUI should be shown, eventhough no HUMAN player exists.
	 */
	public boolean showGUI = false;

	/**
	 * This value tells the program that it should be a client and to which server it should connect.
	 * 
	 */
	public String connectToHostname = null;


       	/**
	 * The port to be used when either hosting the game as a server or connecting to a server as a client.
	 */
	public int port = 6600;

	/**
	 * The save game to be loaded.
	 * <p>
	 * This variable is only relevant for {@link OptionalFeature#SAVEGAMES}.
	 * </p>
	 */
	public File loadSaveGameFile = null;


	/**
	 * Whether the launcher should launch.
	 *
	 * <p>
	 * This variable is only relevant for {@link OptionalFeature#LAUNCHER}.
	 * </p>
	 */
	public boolean shouldLauncherLaunch = false;

	/**
	 * The number of games to be played in the tournament.
	 *
	 * <p style="color:#31708f;background-color:#d9edf7;border-color:#bce8f1;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- INFO -->
	 * 0 means that this is just a normal game, without tournament enabled.
	 * </p>
	 *
	 * This variable is only relevant for {@link OptionalFeature#TOURNAMENTS}.
	 */
	public int numTournamentRounds = 0;

	/**
	 * Whether the game should wait for a user interaction before starting the next game in a tournament.
	 *
	 * <p>
	 * The idea behind this setting is that you may want to test your computerplayers by letting them play multiple thousend rounds against each other, but you don't want to press a button after every single game run.
	 * </p>
	 *
	 * <p>
	 * This variable is only relevant for {@link OptionalFeature#TOURNAMENTS}.
	 * </p>
	 */
	public boolean waitAfterTournamentRound = true;

	/**
	 * This is the volume control for the sound effects.
	 * 
	 * <p style="color:#31708f;background-color:#d9edf7;border-color:#bce8f1;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- INFO -->
	 * Values are in range of 0 to 100, including 0 and 100.<br>
	 * 0 means that soundeffects are disabled.
	 * </p>
	 *
	 * <p style="color:#a94442;background-color:#f2dede;border-color:#ebccd1;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- DANGER -->
	 * THIS VALUE MUST BE 0 BY DEFAULT.<br>
	 * Your examiner might get angry if this value isn't 0 by default.
	 * </p>
	 *
	 * <p>
	 * This variable is only relevant for {@link OptionalFeature#SOUNDEFFECTS}.
	 * </p>
	 */
	public int volume = 0;


	/**
	 * Construct settings with default values.
	 */
	public Settings() {

	}

	/**
	 * Construct settings from other settings.
	 * <p style="color:#3c763d;background-color:#dff0d8;border-color:#d6e9c6;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- SUCCESS -->
	 * This constructor is mostly relevant for {@link OptionalFeature#LAUNCHER}.
	 * </p>
	 * @param src The other settings.
	 */
	public Settings(Settings src) {
		this.logLevel = src.logLevel;
		this.implementedOptionalFeatures = src.implementedOptionalFeatures;
		this.playerNames = src.playerNames;
		this.playerColors = src.playerColors;
		this.playerTypes = src.playerTypes;
		this.gameConfigurationFile = src.gameConfigurationFile;
		this.delay = src.delay;
		this.showGUI = src.showGUI;
		this.connectToHostname = src.connectToHostname;
		this.port = src.port;
		this.loadSaveGameFile = src.loadSaveGameFile;
		this.shouldLauncherLaunch = src.shouldLauncherLaunch;
		this.numTournamentRounds = src.numTournamentRounds;
		this.waitAfterTournamentRound = src.waitAfterTournamentRound;
		this.volume = src.volume;
	}
}

