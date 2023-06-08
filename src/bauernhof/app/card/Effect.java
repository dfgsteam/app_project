package bauernhof.app.card;

import bauernhof.preset.Effect;
import bauernhof.preset.EffectType;

public class Effect {
	EffectType type;
	int effectValue;
	Set<Either<Card,CardColor>> selector;

	public Effect(EffectType type, int effectValue, Set<Either<Card,CardColor>> selector) {
		this.type = type;
		this.effectValue = effectValue;
		this.selector = selector;
	}

	public EffectType getType() {
		return this.type;
	}

	public int getEffectValue() {
		return this.value;
	}

	public Set<Either<Card,CardColor>> getSelector() {
		return this.selector;
	}
}