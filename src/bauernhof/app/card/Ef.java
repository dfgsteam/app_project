package bauernhof.app.card;

import bauernhof.preset.*;
import bauernhof.preset.card.*;

import java.util.*;

public class Ef implements Effect {
	EffectType type;
	int effectValue;
	Set<Either<Card,CardColor>> selector;

	public Ef(EffectType type, int effectValue, Set<Either<Card,CardColor>> selector) {
		this.type = type;
		this.effectValue = effectValue;
		this.selector = selector;
	}

	public EffectType getType() {
		return this.type;
	}

	public int getEffectValue() {
		return this.effectValue;
	}

	public Set<Either<Card,CardColor>> getSelector() {
		return this.selector;
	}
}