package bauernhof.app.launcher;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.card.Card;

/**
 * Interface für den aktuellen State des Games
 *
 * @author Ramon Cemil Kimyon
 * @date 10.06.2023 15:57
 */
public interface GameProperties {
    /**
     * Gibt die Standard-Konfiguration die übergeben wurde wieder
     *
     * @return configuration
     */
    public GameConfiguration getGameConfiguration();

    /**
     * Gibt den aktuellen Status des Spieles zurück.
     *
     * @return state
     */
    public GameBoardState getState();

    /**
     * Setzt den aktuellen Spielestand.
     * Fürs Laden von gespeicherten Spielen
     *
     * @param state
     */
    public void setState(final GameBoardState state);

    /**
     * Gibt den aktuellen Ziehstapel wieder
     *
     * returns the DrawPileStack
     */
    public ImmutableList<Card> getDrawPileStack();
}
