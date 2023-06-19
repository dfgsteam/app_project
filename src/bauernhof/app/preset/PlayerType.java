package bauernhof.preset;

/**
 * The different personalities of players.
 */
public enum PlayerType {
	/**
	 * A generic monkybra..äh..human who places tiles using his mouse and GUI like a caveman.
	 * Truly pathetic.
	 */
	HUMAN,
	/**
	 * A happy little AI just placing tiles randomly, because he doesn't know better.
	 */
	RANDOM_AI,
	/**
	 * He may not be the smartest, but he tries his best damn it!
	 * Placing his tiles while following some simple deterministic rules is all he needs to be happy in life.
	 */
	SIMPLE_AI,
	/**
	 * He was always the smartest one in class.
	 * No social skills at all, but very talented and focused in what he does... playing the game!
	 */
	ADVANCED_AI,
	/**
	 * This one was too cool for playing the game in person.
	 * He just joins in remotly using some new thing called "the internet". Whatever that is supposed to mean.
	 */
	REMOTE
}


