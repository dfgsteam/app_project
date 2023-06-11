package bauernhof.app.player;

import bauernhof.app.launcher.GameState;
import bauernhof.preset.*;

/**
 * Interface für die Wiedergabe und für das Setzen von einzelnen Properties des Players.
 *
 * @author Ramon Cemil Kimyon
 * @date 10.06.2023 15:48
 */
public interface PlayerProperties {
    /**
     * Setzt den Namen des Spielers neu.
     * {@link Player#getName()}
     *
     * @param name - Player Name
     */
    public void setName(final String name);

    /**
     * Ändert den Wert für {@link Player#request()} ab.
     *
     * @param next_move - der Move der ausgeführt wird, wenn der Spieler dran ist.
     */
    public void initNextMove(final Move next_move);

    /**
     * Führt den {@link Move} aus der durch die Methode {@link PlayerProperties#initNextMove(Move)}
     * initialisiert wurde.
     * Dabei wird der Nachziehen-Stapel und der Stapel der hingelegten Karten
     * updated und an den aktuellen {@link GameState} gesendet.
     */
    public void executeMove();
    /**
     * Gibt die ID zurück, die dem Spieler beim init zugeordnet wurde.
     * {@link Player#init(GameConfiguration, ImmutableList, int, int)}
     *
     * @return playerid
     */
    public int getPlayerID();

    /**
     * Gibt zurück, um welche Art von Spieler es sich handelt.
     * {@link AbstractGamePlayer#AbstractGamePlayer(String, GameState, PlayerType)}
     *
     * @return player_type
     */
    public PlayerType getPlayerType();
}

