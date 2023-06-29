package bauernhof.preset.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.GameConfigurationException;
import bauernhof.preset.GameConfigurationParser;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.Move;
import bauernhof.preset.OptionalFeature;
import bauernhof.preset.card.Card;

/**
 * A connection from a Client to a Server.
 *
 * @see S2CConnection
 */
public abstract class C2SConnection {

	private final Connection con;
	private GameConfiguration config = null;
	private final GameConfigurationParser gameConfigurationParser;
	private final String projectName;
	private boolean hasGameEnded = false;
	private boolean keepAlive = false;
	/**
	 * Construct a new {@link C2SConnection}.
	 *
	 * <p style="color:#31708f;background-color:#d9edf7;border-color:#bce8f1;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- INFO -->
	 * You need to call {@link #handlePackets()} to start handling the incomming packets from the server.
	 * </p>
	 *
	 * @param connection The connection that will be used for communicating with the server.
	 * @param gameConfigurationParser For parsing the received {@link GameConfiguration}.
	 * @param projectName The name of this project/group.
	 *
	 * @throws IOException A networking error occured.
	 */
	public C2SConnection(Socket connection, GameConfigurationParser gameConfigurationParser, String projectName) throws IOException {
		this.con = new Connection(connection);
		this.gameConfigurationParser = gameConfigurationParser;
		this.projectName = projectName;
	}
	/**
	 * Handles all incoming requests from the server.
	 *
	 * <p style="color:#31708f;background-color:#d9edf7;border-color:#bce8f1;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- INFO -->
	 * This function is blocking until either an error was thrown or the game has ended.<br>
	 * The connection is automatically closed if an exception was thrown.
	 * </p>
	 *
	 * @throws IOException  A networking error occured.
	 * @throws RemoteException  An error occured on the server or on a different client.
	 */
	public final void handlePackets() throws IOException, RemoteException {
		try {
			Set<String> ptset = new HashSet<>();
			for (PacketType pt : PacketType.values())
				ptset.add(pt.name());

			while (con.isOpen() && !hasGameEnded) {
				String ptstr = con.receive();
				if (!ptset.contains(ptstr))
					throw new IOException("Expected a PacketType but received: " + ptstr);
				PacketType pt = PacketType.valueOf(ptstr);
				handlePacket(pt);
			}
			hasGameEnded = false;
		} catch (IOException e) {
			disconnect();
			throw e;
		} catch (RemoteException e) {
			disconnect();
			throw e;
		}

	}
	private final void handlePacket(PacketType pt) throws IOException, RemoteException {
		switch (pt) {
			case KEEPALIVE:
				this.keepAlive = con.receiveBoolean();
				break;
			case INIT:
				String magic = con.receive();
				if (magic != S2CConnection.MAGIC)
					throw new IOException("Wrong MAGIC Packet received! Expected: '" + S2CConnection.MAGIC + "' Got: '" + magic + "'");
				con.send(S2CConnection.MAGIC);
				con.send(projectName);

				try {
					config = gameConfigurationParser.parse(con.receive());
				} catch (GameConfigurationException e) {
					con.send(e);
					break;
				}
				con.send((Exception)null);
				List<String> rawcards = con.receiveListString();
				List<Card> cards = new ArrayList<>();
				for (String card : rawcards) {
					cards.add(config.getCardByName(card));
				}
				List<String> playerNames = con.receiveListString();
				int playerid = con.receiveInteger();
				try {
					onInit(config,new ImmutableList<>(cards), new ImmutableList<>(playerNames), playerid);
				} catch (Exception e) {
					con.send(e);
					break;
				}
				con.send((Exception)null);
				break;
			case REQUEST:
				Move move = null;
				try {
					move = onRequest();
				} catch (Exception e) {
					con.send(e);
					break;
				}
				con.send((Exception)null);
				con.send(move.getTaken().getName());
				con.send(move.getDeposited().getName());
				break;
			case UPDATE:
				Card taken = config.getCardByName(con.receive());
				Card deposited = config.getCardByName(con.receive());
				try {
					onUpdate(new Move(taken, deposited));
				} catch (Exception e) {
					con.send(e);
					break;
				}
				con.send((Exception)null);
				break;
			case GETSCORE:
				int score = 0;
				try {
					score = onGetScore();
				} catch (Exception e) {
					con.send(e);
					break;
				}
				con.send((Exception)null);
				con.send(score);
				break;
			case GETNAME:
				String name = "";
				try {
					name = onGetName();
				} catch (Exception e) {
					con.send(e);
					break;
				}
				con.send((Exception)null);
				con.send(name);
				break;
			case VERIFYGAME:
				List<Integer> scores = con.receiveListInteger();
				try {
					onVerifyGame(new ImmutableList<>(scores));
				} catch (Exception e) {
					con.send(e);
					break;
				}
				con.send((Exception)null);
				hasGameEnded = true;
				break;
			case ERROR:
				try {
					con.receiveRemoteException();
				} catch (RemoteException e) {
					if (con.isOpen())
						con.close();
					throw e;
				}
				break;
		}
	}

	/**Es wird dem aktuellen menschlichen Spieler angezeigt welche Karten er in der jeweiligen Phase seines Zugs anklicken kann.
	 * Gets called when the server calls {@link RemotePlayer#init(bauernhof.preset.GameConfiguration, ImmutableList, int, int)}.
	 * @param gameConfiguration See {@link bauernhof.preset.Player#init(GameConfiguration, ImmutableList, int, int)}.
	 * @param initialDrawPile See {@link bauernhof.preset.Player#init(GameConfiguration, ImmutableList, int, int)}.
	 * @param playerNames The names of all players in order of their player id.
	 * @param playerid See {@link bauernhof.preset.Player#init(GameConfiguration, ImmutableList, int, int)}.
	 */
	protected abstract void onInit(GameConfiguration gameConfiguration, ImmutableList<Card> initialDrawPile, ImmutableList<String> playerNames, int playerid) throws Exception;
	/**
	 * Called when the server calls {@link RemotePlayer#request()}.
	 * @return See {@link bauernhof.preset.Player#request()}.
	 */
	protected abstract Move onRequest() throws Exception;
	/**
	 * Called when the server calls {@link RemotePlayer#update(Move)}.
	 * @param opponentMove See {@link bauernhof.preset.Player#update(Move)}.
	 */
	protected abstract void onUpdate(Move opponentMove) throws Exception;
	/**
	 * Called when the server calls {@link RemotePlayer#getScore()}.
	 * @return See {@link bauernhof.preset.Player#getScore()}.
	 */
	protected abstract int onGetScore() throws Exception;
	/**
	 * Called when the server calls {@link RemotePlayer#getName()}.
	 * @return See {@link bauernhof.preset.Player#getName()}.
	 */
	protected abstract String onGetName() throws Exception;
	/**
	 * Called when the server calls {@link RemotePlayer#verifyGame(ImmutableList)}.
	 * @param scores See {@link bauernhof.preset.Player#verifyGame(ImmutableList)}.
	 */
	protected abstract void onVerifyGame(ImmutableList<Integer> scores) throws Exception;

	protected abstract void onError(RemoteException re) throws Exception;


	/**
	 * Is the connection still open?
	 * @return true if the connection is still open.
	 */
	public boolean isOpen() {
		return con.isOpen();
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
	 * Whether the server asks of you to keep the connection open and reuse for another game after the game has ended.
	 *
	 * <p>
	 * This function is only relevant for {@link OptionalFeature#TOURNAMENTS}.
	 * </p>
	 *
	 * @see S2CConnection#setKeepAlive(boolean)
	 *
	 * @return true if the server still asks for the connection to be kept alive.
	 */
	public boolean shouldKeepAlive() {
		return keepAlive;
	}
}
