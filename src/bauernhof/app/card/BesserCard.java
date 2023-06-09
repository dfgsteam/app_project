package bauernhof.preset.card;

import java.util.Set;

public interface BetterCard extends Card {

	// public String getName();

	// public int getBaseValue();

	// public CardColor getColor();

	// public String getImage();

	// public Set<Effect> getEffects();

	// @Override
	// public boolean equals(Object obj);

    public boolean isBlocked();

    public void setBlocked();

    public EffectedCards getEffectedCards();



}
