package bauernhof.app;
import bauernhof.preset.*;
import bauernhof.preset.card.*;

import java.util.*;

/**
 * Represents the GameConfiguration class.
 * This class is responsible for managing the configuration of the game,
 * including the number of deposition area slots, the number of cards per player hand,
 * the cards and card colors, and the raw configuration.
 *
 * @author Julius Hunold
 * @version 1.0
 * @since 2023-06-27
 */

public class GaCo implements GameConfiguration{
    private String configDescription;
    private int numDepositionAreaSlots;
    private int numCardsPerPlayerHand;
    private Set<Card> cards;
    private Set<CardColor> cardColors;
    private String rawConfiguration;

    /**
     * Constructor for the GaCo class.
     *
     * @param configDescription The configuration description of the game.
     * @param numDepositionAreaSlots The number of deposition area slots.
     * @param numCardsPerPlayerHand The number of cards per player hand.
     * @param cards A set of Card objects representing the cards in the game.
     * @param cardColors A set of CardColor objects representing the card colors in the game.
     * @param rawConfiguration The raw configuration data.
     */
    public GaCo(String configDescription, int numDepositionAreaSlots, int numCardsPerPlayerHand, Set<Card> cards, Set<CardColor> cardColors, String rawConfiguration) {
        this.configDescription = configDescription;
        this.numDepositionAreaSlots = numDepositionAreaSlots;
        this.numCardsPerPlayerHand = numCardsPerPlayerHand;
        this.cards = cards != null ? cards : new HashSet<Card>();
        this.cardColors = cardColors != null ? cardColors : new HashSet<CardColor>();
        this.rawConfiguration = rawConfiguration;
    }

    /**
     * Getter method for the configDescription field.
     *
     * @return The configuration description of the game.
     */
    public String getConfigDescription() {
        return this.configDescription;
    }

    /**
     * Getter method for the numDepositionAreaSlots field.
     *
     * @return The number of deposition area slots.
     */
    public int getNumDepositionAreaSlots() {
        return this.numDepositionAreaSlots;
    }

    /**
     * Getter method for the numCardsPerPlayerHand field.
     *
     * @return The number of cards per player hand.
     */
    public int getNumCardsPerPlayerHand() {
        return this.numCardsPerPlayerHand;
    }

    /**
     * Getter method for the cards field.
     *
     * @return The set of Card objects representing the cards in the game.
     */
    public Set<Card> getCards() {
        return this.cards;
    }

    /**
     * Getter method for the cardColors field.
     *
     * @return The set of CardColor objects representing the card colors in the game.
     */
    public Set<CardColor> getCardColors() {
        return this.cardColors;
    }

    /**
     * Getter method for the rawConfiguration field.
     *
     * @return The raw configuration data.
     */
    public String getRawConfiguration() {
        return this.rawConfiguration;
    }
    
    /**
     * Sets the configuration description of the game.
     *
     * @param configDescription The new configuration description.
     */
    public void setConfigDescription(String configDescription) {
        this.configDescription = configDescription;
    }

    /**
     * Sets the number of deposition area slots.
     *
     * @param numDepositionAreaSlots The new number of deposition area slots.
     */
    public void setNumDepositionAreaSlots(int numDepositionAreaSlots) {
        this.numDepositionAreaSlots = numDepositionAreaSlots;
    }

    /**
     * Sets the number of cards per player hand.
     *
     * @param numCardsPerPlayerHand The new number of cards per player hand.
     */
    public void setNumCardsPerPlayerHand(int numCardsPerPlayerHand) {
        this.numCardsPerPlayerHand = numCardsPerPlayerHand;
    }

    /**
     * Sets the set of cards representing the cards in the game.
     *
     * @param cards The new set of cards.
     */
    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    /**
     * Adds a card to the set of cards in the game.
     *
     * @param card The card to add.
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }

    /**
     * Sets the set of card colors representing the card colors in the game.
     *
     * @param cardColors The new set of card colors.
     */
    public void setCardColors(Set<CardColor> cardColors) {
        this.cardColors = cardColors;
    }

    /**
     * Adds a card color to the set of card colors in the game.
     *
     * @param cardColor The card color to add.
     */
    public void addCardColor(CardColor cardColor) {
        this.cardColors.add(cardColor);
    }

    /**
     * Sets the raw configuration data.
     *
     * @param rawConfiguration The new raw configuration data.
     */
    public void setRawConfiguration(String rawConfiguration) {
        this.rawConfiguration = rawConfiguration;
    }

    /**
     * Retrieves a card from the set of cards by its name.
     *
     * @param cardname The name of the card to retrieve.
     * @return The Card object with the specified name, or null if not found.
     */
    public Card getCardByName(String cardname) {
        for (var item : this.cards) 
            if (item.getName().equals(cardname))
                return item;
        return null;
    }
}
