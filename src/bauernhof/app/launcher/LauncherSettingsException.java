package bauernhof.app.launcher;

/**
 * An exception that may be thrown while {@link LauncherSettingsParser parsing a setting configuration} indicating that the setting configuration is invalid.
 * 
 * @author Julius Hunold
 * Georg-August-Universität in Göttingen
 */
public class LauncherSettingsException extends Exception {
	private static final long serialVersionUID=1l;
	public LauncherSettingsException() {
		super();
	}
	public LauncherSettingsException(String message) {
		super(message);
	}
	public LauncherSettingsException(String message, Throwable cause) {
		super(message, cause);
	}
	public LauncherSettingsException(Throwable cause) {
		super(cause);
	}
}
