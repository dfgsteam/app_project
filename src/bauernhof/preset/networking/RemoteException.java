package bauernhof.preset.networking;

import java.net.SocketAddress;

/**
 * An exception on the remote end of a connection.
 *
 */
public class RemoteException extends Exception {
	private static final long serialVersionUID = 1L;

	private final String remoteMessage;
	private final String remoteCanonicalExceptionName;
	private final SocketAddress source;
	private String origSource;

	/**
	 * Construct a remote exception.
	 *
	 * @param remoteMessage See {@link #getRemoteMessage()}.
	 * @param remoteCanonicalExceptionName See {@link #getRemoteCanonicalExceptionName()}.
	 * @param source See {@link #getSource()}.
	 * @param origSource See {@link #getOriginalSource()}.
	 */
	public RemoteException(String remoteMessage, String remoteCanonicalExceptionName, SocketAddress source, String origSource) {
		super();
		this.source = source;
		this.remoteMessage = remoteMessage;
		this.remoteCanonicalExceptionName = remoteCanonicalExceptionName;
		this.origSource = origSource;
	}


	/**
	 * Construct a remote exception.
	 *
	 * @param remoteMessage See {@link #getRemoteMessage()}.
	 * @param remoteCanonicalExceptionName See {@link #getRemoteCanonicalExceptionName()}.
	 * @param source See {@link #getSource()}.
	 */
	public RemoteException(String remoteMessage, String remoteCanonicalExceptionName, SocketAddress source) {
		this(remoteMessage, remoteCanonicalExceptionName, source, null);
	}


	/**
	 * Construct a remote exception as a copy of another but with an original source set.
	 * <p>
	 * This is useful for forwarding remote exceptions.
	 * </p>
	 * @param e The remote exception to copy.
	 * @param origSource See {@link #getOriginalSource()}.
	 * @see #getOriginalSource()
	 */
	public RemoteException(RemoteException e, String origSource) {
		this(e.getRemoteMessage(), e.getRemoteCanonicalExceptionName(), e.getSource(), origSource);
	}


	/**
	 * Creates a message based on {@link #hasOriginalSource()}, {@link #getOriginalSource()}, {@link #getSource()}, {@link #getRemoteCanonicalExceptionName()}, and {@link #getRemoteMessage()}.
	 * @return The message.
	 */
	@Override
	public String getMessage() {
		String res = "";
		res += "Error at remote endpoint.";
		if (hasOriginalSource())
			res += " Original Source: \"" + getOriginalSource().replace('\"', '\'') + "\"";
		else
			res += " Source: \"" + getSource() + "\"";
		res += " CanonicalExceptionName: \"" + getRemoteCanonicalExceptionName().replace('\"', '\'') + "\"";
		res += " Message: \"" + getRemoteMessage().replace('\"', '\'') + "\"";
		return res;
	}

	/**
	 * Get the remote exception message.
	 * @return What <code>e.getMessage()</code> returned on the other side of the connection.
	 */
	public String getRemoteMessage() {
		return remoteMessage;
	}

	/**
	 * Get the class of the remote exception.
	 * @return What <code>e.getClass().etCanonicalName</code> returned on the other side of the connection.
	 */
	public String getRemoteCanonicalExceptionName() {
		return remoteCanonicalExceptionName;
	}

	/**
	 * Get the address that send the remote exception here.
	 * @return The address that send the remote exception.
	 */
	public SocketAddress getSource() {
		return source;
	}

	/**
	 * Get the original source that send the exception.
	 * <p>
	 * This is useful for implementing {@link RemoteException} forwarding or just setting a custom source.
	 * </p>
	 * <p style="color:#31708f;background-color:#d9edf7;border-color:#bce8f1;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- INFO -->
	 * Note that the original source can be anything, e.g. a playerid. It must not necessarily be a stringified {@link SocketAddress}.
	 * </p>
	 * @return The source that send the original remote exception or null if none known.
	 * @see #hasOriginalSource()
	 */
	public String getOriginalSource() {
		return origSource;
	}

	/**
	 * Whether the original source is known.
	 *
	 * @return <code>getOriginalSource() != null</code>
	 * @see #getOriginalSource()
	 */
	public boolean hasOriginalSource() {
		return getOriginalSource() != null;
	}
}

