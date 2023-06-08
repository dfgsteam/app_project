package bauernhof.app.card;

import bauernhof.preset.Card;
import bauernhof.preset.CardColor;

/**
 * Represents a card in a card game.
 * @author Julius Hunold
 * Georg-August-Universität in Göttingen
 */

public class Card {
    private final String name;
    private final int baseValue;
    private final CardColor color;
    private final String image;
    private final Set<Effect> effect;

    /**
     * Constructs a Card object with the specified properties.
     *
     * @param name      the name of the card
     * @param baseValue the base value of the card
     * @param color     the color of the card
     * @param image     the image associated with the card
     * @param effect    the effects associated with the card
     */
    public Card(String name, int baseValue, CardColor color, String image, Set<Effect> effect) {
        this.name = name;
        this.baseValue = baseValue;
        this.color = color;
        this.image = image;
        this.effect = effect;
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
     * @return the effects associated with the card
     */
    public Set<Effect> getEffects() {
        return this.effect;
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
}
