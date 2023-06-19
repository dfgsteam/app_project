package bauernhof.preset.networking;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.Move;
import bauernhof.preset.Player;
import bauernhof.preset.card.Card;

/**
 * A remote player.
 *
 * <p style="color:#8a6d3b;background-color:#fcf8e3;border-color:#faebcc;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- WARNING -->
 * You must call {@link S2CConnection#setPlayerNames(ImmutableList)} before calling {@link #init(GameConfiguration, ImmutableList, int, int)}!
 * </p>
 *
 * @see S2CConnection#getRemotePlayer()
 */
public final class RemotePlayer implements Player {
	private final S2CConnection con;
	RemotePlayer(S2CConnection con) {
		this.con = con;
	}
	public String getName() throws Exception {
		return con.getName();
	}
	public void init(GameConfiguration gameConfiguration, ImmutableList<Card> initialDrawPile, int numplayers, int playerid) throws Exception {
		con.init(gameConfiguration, initialDrawPile, numplayers, playerid);
	}

	public Move request() throws Exception {
		return con.request();
	}
	public void update(Move opponentMove) throws Exception {
		con.update(opponentMove);
	}

	public int getScore() throws Exception {
		return con.getScore();
	}
	public void verifyGame(ImmutableList<Integer> scores) throws Exception {
		con.verifyGame(scores);
	}
}

