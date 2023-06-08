package bauernhof.preset;

import java.io.File;
import java.io.IOException;

/**
 * A parser for a {@link GameConfiguration}.
 */
public interface GameConfigurationParser {
	/**
	 * Parse the contents of game configuration file to a {@link GameConfiguration}.
	 * @param filecontents The contents of the game configuration file (xml).
	 * @throws GameConfigurationException The file contents is not a valid game configuration.
	 * @return The parsed {@link GameConfiguration}.
	 */
	public GameConfiguration parse(String filecontents) throws GameConfigurationException;
	
	/**
	 * Parse a game configuration file to a {@link GameConfiguration}.
	 * @param file The game configuration file (xml).
	 * @throws GameConfigurationException The file contents is not a valid game configuration.
	 * @throws IOException Failed reading the file.
	 * @return The parsed {@link GameConfiguration}.
	 */
	public GameConfiguration parse(File file) throws GameConfigurationException, IOException;
}
