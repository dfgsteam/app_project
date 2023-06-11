package bauernhof.app.player;

import bauernhof.preset.ImmutableList;
import bauernhof.preset.card.Card;

import java.util.Set;

/**
 * Interface für die Kartenhand des Spielers.
 * Man kann Karten entfernen und hinzufügen von der PlayerHand.
 * Blockierte Karten etc. können auch wiedergegeben werden.
 *
 * @author Ramon Cemil Kimyon
 * @date 10.06.2023 15:29
 */
public interface CardSetHandler {
    /**
     * Fügt Karten hinzu auf die Hand des Spielers.
     * card --> {@link #getCards()}
     *
     * @param card - Karte die hinzugefügt werden soll.
     */
    public void add(final Card card);

    /**
     * Entfernt Karten von der Hand des Spielers.
     * card --> {@link #getCards()}
     *
     * @param card - Karte die entfernt werden soll.
     */
    public void remove(final Card card);

    /**
     * Karten in der Hand des Spielers.
     *
     * @return list of cards
     */
    public Set<Card> getCards();

    /**
     * Gibt alle Karten zurück die in der Hand des Spielers blockiert sind.
     * (Also Karten die nicht aktiviert sind)
     * Teilmenge von {@link #getCards()}
     *
     * @return list of cards
     */
    public Set<Card> getBlockedCards();

    /**
     * Gibt alle aktivierten Karten zurück.
     * (Also Karten die nicht blockiert sind)
     * Teilmenge von {@link #getCards()}
     *
     * @return list of cards
     */
    public Set<Card> getActiveCards();
}
