package bauernhof.app.settings;

/**
 * An exception that may be thrown while {@link GameConfigurationParser parsing a game configuration} indicating that the game configuration is invalid.
 */
public class SeEx extends Exception {
	private static final long serialVersionUID=1l;
	public SeEx() {
		super();
	}
	public SeEx(String message) {
		super(message);
	}
	public SeEx(String message, Throwable cause) {
		super(message, cause);
	}
	public SeEx(Throwable cause) {
		super(cause);
	}

}