package bauernhof.preset;

import java.util.Set;

import bauernhof.preset.card.Card;
import bauernhof.preset.card.CardColor;

/**
 * A game configuration.
 */
public interface GameConfiguration {
	/**
	 * The description of the game configuration.
	 * @return The description-
	 */
	public String getConfigDescription();
	/**
	 * The maximum number of cards in the deposition area.
	 *
	 * <p>
	 * The game ends as soon as the deposition area is full.
	 * </p>
	 * <p>
	 * Must be at least 2 and cannot be more than 12.
	 * </p>
	 *
	 * @return The number of slots in the deposition area.
	 */
	public int getNumDepositionAreaSlots();

	/**
	 * The number of cards dealt to each player on the start of the game.
	 *
	 * <p>
	 * Must be at least 2 and cannot be more than 10.
	 * </p>
	 *
	 * @return The number of cards dealt to each player.
	 */
	public int getNumCardsPerPlayerHand();
	/**
	 * All {@link CardColor CardColors}.
	 *
	 * <p>
	 * Every cardcolor must have a unique name.<br>
	 * Every cardcolor must be used by at least one card.
	 * </p>
	 *
	 * @return The cardcolors.
	 */
	public Set<CardColor> getCardColors();
	/**
	 * All {@link Card Cards}.
	 *
	 * <p>
	 * Every card must have a unique name.<br>
	 * There must be at least ({@link #getNumDepositionAreaSlots()} + {@link #getNumCardsPerPlayerHand()} * 4) cards.
	 * </p>
	 *
	 * @return The card.
	 */
	public Set<Card> getCards();

	/**
	 * Get a card by its name.
	 * <p>
	 * This function is used by {@link bauernhof.preset.networking.S2CConnection}.
	 * </p>
	 * @param cardname The unique name of the card.
	 * @return The card or null if no card by that name was found.
	 */
	public Card getCardByName(String cardname);

	/**
	 * The contents of the xml file that was used for creating this {@link GameConfiguration}.
	 * <p>
	 * This function is used by {@link bauernhof.preset.networking.S2CConnection}.
	 * </p>
	 *
	 * @return The raw xml of this configuration.
	 */
	public String getRawConfiguration();
}
