package bauernhof.preset.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.Move;
import bauernhof.preset.OptionalFeature;
import bauernhof.preset.card.Card;

/**
 * A connection from a Server to a Client.
 * @see C2SConnection
 */
public final class S2CConnection {
	public final static String MAGIC = "BauernhofNetworkingProtocol_v1.0.0";
	private Connection con;
	private RemotePlayer player;
	private GameConfiguration config = null;
	private List<String> playerNames = new ArrayList<>();
	private String projectName = null;
	/**
	 * Construct a new {@link S2CConnection}.
	 * @param connection The connection that will be used for communicating with the client. See {@link ServerSocket#accept()}.
	 *
	 * @throws IOException A networking error occured.
	 */
	public S2CConnection(Socket connection) throws IOException {
		this.con = new Connection(connection);
	}
	/**
	 * Inform the client about the names of all players.
	 * <p style="color:#a94442;background-color:#f2dede;border-color:#ebccd1;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- DANGER -->
	 * You must call this function before calling {@link RemotePlayer#init(GameConfiguration, ImmutableList, int, int)}!
	 * </p>
	 * @param playerNames The names of the players in order of their playerid.
	 */
	public void setPlayerNames(ImmutableList<String> playerNames) {
		this.playerNames = playerNames;
	}
	void init(GameConfiguration gameConfiguration, ImmutableList<Card> initialDrawPile, int numplayers, int playerid) throws Exception {
		if (numplayers != playerNames.size()) {
			throw new RuntimeException("setPlayerNames wasn't called before calling init.");
		}
		this.config = gameConfiguration;
		if (config == null) {
			throw new RuntimeException("GameConfiguration is null.");
		}
		send(PacketType.INIT);
		con.send(S2CConnection.MAGIC);
		String magic = con.receive();
		if (magic != S2CConnection.MAGIC)
			throw new IOException("Wrong MAGIC Packet received! Expected: '" + S2CConnection.MAGIC + "' Got: '" + magic + "'");
		this.projectName = con.receive();
		con.send(gameConfiguration.getRawConfiguration());
		con.receiveRemoteException();
		List<String> cards = new ArrayList<>();
		for (Card card : initialDrawPile) {
			cards.add(card.getName());
		}
		con.send(cards);
		con.send(playerNames);
		con.send(playerid);
		con.receiveRemoteException();
	}

	Move request() throws Exception {
		if (config == null) {
			throw new RuntimeException("GameConfiguration is null.");
		}
		send(PacketType.REQUEST);
		con.receiveRemoteException();
		Card taken = config.getCardByName(con.receive());
		Card deposited = config.getCardByName(con.receive());
		return new Move(taken, deposited);

	}
	void update(Move opponentMove) throws Exception {
		if (config == null) {
			throw new RuntimeException("GameConfiguration is null.");
		}
		send(PacketType.UPDATE);
		con.send(opponentMove.getTaken().getName());
		con.send(opponentMove.getDeposited().getName());
		con.receiveRemoteException();
	}

	int getScore() throws Exception {
		send(PacketType.GETSCORE);
		con.receiveRemoteException();
		return con.receiveInteger();
	}
	String getName() throws Exception {
		send(PacketType.GETNAME);
		con.receiveRemoteException();
		return con.receive();
	}
	void verifyGame(ImmutableList<Integer> scores) throws Exception {
		send(PacketType.VERIFYGAME);
		con.send(scores);
		con.receiveRemoteException();
	}

	private void send(PacketType pt) throws IOException {
		con.send(pt.name());
	}
	/**
	 * Inform the client about an error and close the connection.
	 * <p>
	 * This function does nothing if the connection is already closed.
	 * </p>
	 * @param e The error.
	 * @param source The source of the error. E.g. "Server", "Player 2", ...
	 * @throws IOException A networking error occured.
	 * @see C2SConnection#onError(RemoteException)
	 * @see RemoteException
	 */
	public void sendError(Exception e, String source) throws IOException {
		if (con.isOpen()) {
			send(PacketType.ERROR);
			con.send(e, source);
			disconnect();
		}
	}

	/**
	 * Is the connection still open?
	 * @return true if the connection is still open.
	 */
	public boolean isOpen() {
		return con.isOpen();
	}
	/**
	 * Get the remote player.
	 * @return The remote player.
	 */
	public RemotePlayer getRemotePlayer() {
		return player;
	}
	/**
	 * Closes the connection.
	 * <p>
	 * This function does nothing if the connection is already closed.
	 * </p>
	 */
	public void disconnect() {
		try {
			if (con.isOpen())
				con.close();
		} catch (IOException e) {
			// We don't care about exceptions on disconnect.
		}
	}

	/**
	 * Get the name of the clients project.
	 *
	 * @return The project name or null if init wasn't called yet.
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Ask the client to keep the connection open and reuse for another game after the game has ended.
	 * @param keepAlive Whether the connection should be kept alive.
	 * @throws IOException A networking error has occured.
	 * <p>
	 * This function is only relevant for {@link OptionalFeature#TOURNAMENTS}.
	 * </p>
	 *
	 * @see C2SConnection#shouldKeepAlive()
	 */
	public void setKeepAlive(boolean keepAlive) throws IOException {
		send(PacketType.KEEPALIVE);
		con.send(keepAlive);
	}

}

