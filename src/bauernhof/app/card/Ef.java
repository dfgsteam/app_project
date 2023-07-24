package bauernhof.app.card;

import bauernhof.preset.*;
import bauernhof.preset.card.*;

import java.util.*;

/**
 * Represents an effect (Ef) in a card game and implements the Inferface "Effect".
 * An effect can have a type, effect value, and a selector that determines which cards or card colors are affected.
 *
 * @author Julius Hunold
 * 
 */
public class Ef implements Effect {
	EffectType type;
	int effectValue;
	Set<Either<Card,CardColor>> selector;

	/**
	 * Constructs a new Ef object with the specified type, effect value, and selector.
	 *
	 * @param type     the type of the effect.
	 * @param effectValue   the value of the effect.
	 * @param selector   the set of cards or card colors that are affected by the effect.
	 */
	public Ef(EffectType type, int effectValue, Set<Either<Card,CardColor>> selector) {
		this.type = type;
		this.effectValue = effectValue;
		this.selector = selector;
	}

	/**
	 * Retrieves the type of the effect.
	 *
	 * @return the type of the effect.
	 */
	public EffectType getType() {
		return this.type;
	}

	/**
	 * Retrieves the value of the effect.
	 *
	 * @return the value of the effect.
	 */
	public int getEffectValue() {
		return this.effectValue;
	}

	/**
	 * Retrieves the selector that determines which cards or card colors are affected by the effect.
	 *
	 * @return the set of cards or card colors that are affected by the effect.
	 */
	public Set<Either<Card,CardColor>> getSelector() {
		return this.selector;
	}
}