package bauernhof.app.card;

import bauernhof.preset.card.Card;
import bauernhof.preset.card.Effect;

import java.util.Set;

/**
 * A playing card.
 */
public interface BeCa extends Card {
    /**
     * Set effects associated with the card.
     *
     * @param effect    the effects associated with the card
     */
	public void setEffects(Set<Effect> effect);

    /**
     * Add one effect associated with the card.
     *
     * @param effect    the effects associated with the card
     */
	public void addEffect(Effect effect);
}