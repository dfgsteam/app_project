package bauernhof.preset.card;

import java.util.Set;

/**
 * A playing card.
 */
public interface Card {
	
	/**
	 * Get the name of the card.
	 *
	 * <p>
	 * Card names must be unique in the game and thus can be used as an identifier.
	 * </p>
	 *
	 * @return The name of the card.
	 */
	public String getName();

	/**
	 * Get the base value of the card.
	 *
	 * @return The base value of the card.
	 */
	public int getBaseValue();

	/**
	 * Get the cardcolor.
	 *
	 * @return The cardcolor.
	 */
	public CardColor getColor();

	/**
	 * Get the name of the image that should be drawn on the card.
	 *
	 * @return The name of the image.
	 */
	public String getImage();

	/**
	 * Get the effects of this card.
	 *
	 * @return The effects.
	 */
	public Set<Effect> getEffects();

	/**
	 * Compares two cards by their names.
	 *
	 * <p>
	 * The following implementation should be used:
	 * </p>
	 * <pre>
	 * {@literal @}Override
	 * public boolean equals(Object obj) {
	 * 	if (obj instanceof Card) {
	 * 		return getName().equals(((Card)obj).getName());
	 * 	} else {
	 * 		return false;
	 * 	}
	 * }
	 * </pre>
	 *
	 */
	@Override
	public boolean equals(Object obj);
}
