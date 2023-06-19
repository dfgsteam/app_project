package bauernhof.app;
import bauernhof.preset.*;
import bauernhof.preset.card.*;

import java.util.*;

class GaCo implements GameConfiguration{
    private final String configDescription;
    private final int numDepositionAreaSlots;
    private int numCardsPerPlayerHand;
    private Set<Card> cards;
    private final String rawConfiguration;

    public GaCo(String configDescription, int numDepositionAreaSlots, int numCardsPerPlayerHand, Set<Card> cards, String rawConfiguration) {
        this.configDescription = configDescription;
        this.numDepositionAreaSlots = numDepositionAreaSlots;
        this.numCardsPerPlayerHand = numCardsPerPlayerHand;
        this.cards = cards;
        this.rawConfiguration = rawConfiguration;
    }
    
    public String getConfigDescription() {return null;}

	public int getNumDepositionAreaSlots() {return 0;}

	public int getNumCardsPerPlayerHand() {return 0;}

	public Set<CardColor> getCardColors() {return null;}

	public Set<Card> getCards() {return null;}

	public Card getCardByName(String cardname) {return null;}

	public String getRawConfiguration() {return null;}
}
