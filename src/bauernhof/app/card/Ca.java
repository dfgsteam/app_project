package bauernhof.app.card;

import bauernhof.preset.card.*;

import java.util.*;

/**
 * Represents a card in a card game.
 * 
 * @author Julius Hunold
 * Georg-August-Universität in Göttingen
 */

public class Ca implements Card {
    private final String name;
    private final int baseValue;
    private final CardColor color;
    private final String image;
    private Set<Effect> effect;
    private boolean blocked;
    private GCard gCard;

    /**
     * Constructs a Card object with the specified properties.
     *
     * @param name      the name of the card
     * @param baseValue the base value of the card
     * @param color     the color of the card
     * @param image     the image associated with the card
     * @param effect    the effects associated with the card
     */
    public Ca(String name, int baseValue, CardColor color, String image, Set<Effect> effect) {
        this.name = name;
        this.baseValue = baseValue;
        this.color = color;
        this.image = image;
        this.effect = effect != null ? effect : new HashSet<Effect>();
        this.gCard = null;
    }

    /**
     * Returns the name of the card.
     *
     * @return the name of the card
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the base value of the card.
     *
     * @return the base value of the card
     */
    public int getBaseValue() {
        return this.baseValue;
    }

    /**
     * Returns the color of the card.
     *
     * @return the color of the card
     */
    public CardColor getColor() {
        return this.color;
    }

    /**
     * Returns the image associated with the card.
     *
     * @return the image associated with the card
     */
    public String getImage() {
        return this.image;
    }

    /**
     * Returns the effects associated with the card.
     *
     * @return effect    the effects associated with the card
     */
    public Set<Effect> getEffects() {
        return this.effect;
    }

    /**
     * Set the effects associated with the card.
     *
     * @param effect    the effects associated with the card
     */
    public void setEffects(Set<Effect> effect) {
        this.effect = effect;
    }

    /**
     * Add one effect associated with the card.
     *
     * @param effect    the effects associated with the card
     */
    public void addEffect(Effect effect) {
        this.effect.add(effect);
    }

    /**
     * Checks if this card is equal to the specified object.
     *
     * @param obj the object to compare with
     * @return true if the cards are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Card otherCard = (Card) obj;
        return this.name.equals(otherCard.getName()) &&
                this.baseValue == otherCard.getBaseValue() &&
                this.color == otherCard.getColor() &&
                this.image.equals(otherCard.getImage()) &&
                this.effect.equals(otherCard.getEffects());
    }
    /**
    * Checks if the object is blocked.
    * 
    * @return true if the object is blocked, false otherwise.
    */
    public boolean isBlocked() {
        return this.blocked;
    }

    /**
    Sets the object as blocked.
    */
    public void setBlocked() {
        this.blocked = true;
    }

    /**
    * Retrieves the GCard object associated with this object.
    * If the GCard object has not been initialized, a new one is created.
    * 
    * @return the GCard object.
    */
    public GCard getGCard() {        
        // Erzeuge neues Kartenobjekte, bei der ersten Abfrage
        if (this.gCard == null)
            this.gCard = new GCard(this);
        return (GCard) this.gCard.setPosition(0f, 0f);
    }

}
