package bauernhof.preset;

import bauernhof.preset.card.Card;

/**
 * A move made by a player during a round.
 */
public final class Move {
	private final Card taken;
	private final Card deposited;
	
	/**
	 * Construct a move.
	 *
	 * @param taken See {@link #getTaken()}.
	 * @param deposited See {@link #getDeposited()}.
	 */
	public Move(Card taken, Card deposited) {
		this.taken = taken;
		this.deposited = deposited;
	}

	/**
	 * The card the player took from the deposition area or from the top of the draw pile during the first phase of their move.
	 *
	 * @return The card.
	 */
	public Card getTaken() {
		return taken;
	}


	/**
	 * The card the player deposited to the deposition area during the second phase of their move.
	 * @return The card.
	 */
	public Card getDeposited() {
		return deposited;
	}
}
