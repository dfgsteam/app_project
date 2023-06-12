package bauernhof.app.player;

import bauernhof.preset.ImmutableList;
import bauernhof.preset.card.Card;
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
     *
     * @return True - Wenn Karte im KartenSet enthalten ist {@link #getCards()}
     * @return False - Wenn Karte nicht enthalten ist im Set {@link #getCards()}
     */
    public boolean remove(final Card card);

    /**
     * Gibt den theoretischen Wert wieder sollte eine Karte geaddet werden.
     * Diese Funktion existiert explizit für die {@link bauernhof.preset.PlayerType#ADVANCED_AI}
     * um den theoretischen Score zu getten.
     * Diese Methode wird auch von der {@link #add(Card)} Methode aufgerufen
     *
     * @param card - Karte die theoretisch geadded wird. (Wird nicht wirklich geadded
     * @return theoretical score by adding the card
     */
    public int getAddScore(final Card card);

    /**
     * Gibt den theoretischen Wert wieder sollte eine Karte removed werden.
     * Diese Funktion existiert explizit für die {@link bauernhof.preset.PlayerType#ADVANCED_AI}
     * um den theoretischen Score zu getten.
     * Diese Methode wird auch von der {@link #remove(Card)} Methode aufgerufen
     *
     * @param card - Karte die theoretisch removed wird. (Wird nicht wirklich removed
     * @return theoretical score by adding the card
     */
    public int getRemoveScore(final Card card);


    /**
     * Karten in der Hand des Spielers.
     *
     * @return list of cards
     */
    public ImmutableList<Card> getCards();

    /**
     * Gibt alle Karten zurück die in der Hand des Spielers blockiert sind.
     * (Also Karten die nicht aktiviert sind)
     * Teilmenge von {@link #getCards()}
     *
     * @return list of cards
     */
    public ImmutableList<Card> getBlockedCards();

    /**
     * Gibt alle aktivierten Karten zurück.
     * (Also Karten die nicht blockiert sind)
     * Teilmenge von {@link #getCards()}
     *
     * @return list of cards
     */
    public ImmutableList<Card> getActiveCards();
}
