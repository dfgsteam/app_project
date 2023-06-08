package bauernhof.preset;

/**
 * An exception that may be thrown while {@link GameConfigurationParser parsing a game configuration} indicating that the game configuration is invalid.
 */
public class GameConfigurationException extends Exception {
	private static final long serialVersionUID=1l;
	public GameConfigurationException() {
		super();
	}
	public GameConfigurationException(String message) {
		super(message);
	}
	public GameConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}
	public GameConfigurationException(Throwable cause) {
		super(cause);
	}

}
