package bauernhof.preset.card;

import bauernhof.preset.Player;

/**
 * The type of an {@link Effect} determines how it should be evaluated when calculating the score of a {@link Player}.
 */
public enum EffectType {
	/**
	 * The effect evaluates to {@link Effect#getEffectValue()} times the number of cards in {@link Effect#getSelector()} that the player has on their hand.
	 */
	POINTS_FOREACH,
	/**
	 * The effect evaluates to the sum of {@link Card#getBaseValue()} of each card in {@link Effect#getSelector()} that the player has on their hand.
	 */
	POINTS_SUM_BASEVALUES,
	/**
	 * The effect evaluates to {@link Card#getBaseValue()} if the player has ANY card in {@link Effect#getSelector()} on their hand, otherwise 0.
	 */
	POINTS_FLAT_DISJUNCTION,
	/**
	 * The effect evaluates to {@link Card#getBaseValue()} if the player has ALL cards in {@link Effect#getSelector()} on their hand, otherwise 0.
	 */
	POINTS_FLAT_CONJUNCTION,
	/**
	 * The card this effect is on is blocked if the player has ANY card in {@link Effect#getSelector()} on their hand.
	 */
	BLOCKED_IF_WITH,
	/**
	 * The card this effect is on is blocked if the player has NONE of the cards in {@link Effect#getSelector()} on their hand.
	 */
	BLOCKED_IF_WITHOUT,
	/**
	 * Every card the player has on their hand that is in {@link Effect#getSelector()} gets blocked.
	 */
	BLOCKS_EVERY;
}
