package bauernhof.preset;

import bauernhof.preset.card.Card;

/**
 * The API for interacting with the player.
 */
public interface Player {
	public String getName() throws Exception;

	public void init(GameConfiguration gameConfiguration, ImmutableList<Card> initialDrawPile, int numplayers, int playerid) throws Exception;

	public Move request() throws Exception;
	public void update(Move opponentMove) throws Exception;

	public int getScore() throws Exception;
	public void verifyGame(ImmutableList<Integer> scores) throws Exception;
}

