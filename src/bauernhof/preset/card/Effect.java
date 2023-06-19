package bauernhof.preset.card;

import java.util.Set;

import bauernhof.preset.Either;

/**
 * An effect on a {@link Card}.
 */
public interface Effect {
	/**
	 * The type of the effect.
	 * <p>
	 * This function will also be used when printing the effect on a {@link GCard}.
	 * </p>
	 * @return The type of the effect.
	 */
	public EffectType getType();

	/**
	 * Get the value of the effect.
	 * <p>
	 * This function will also be used when printing the effect on a {@link GCard}.
	 * </p>
	 *
	 * @return The value of the effect or 0 if the effect has something to do with blocking.
	 */
	public int getEffectValue();

	/**
	 * Get the cards that are related to this effect.
	 *
	 * <p>
	 * A {@link CardColor} is a placeholder for all the cards in that cardcolor, <b>except for the card that contains this effect</b>.<br>
	 * </p>
	 *
	 * <p>
	 * This function will also be used when printing the effect on a {@link GCard}.
	 * </p>
	 *
	 * @return The Set of cards and cardcolors related to this effect.
	 *
	 */
	public Set<Either<Card,CardColor>> getSelector();
}
