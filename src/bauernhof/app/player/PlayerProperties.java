package bauernhof.app.player;

import bauernhof.app.launcher.GameBoardState;
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
     * Gibt die ID zurück, die dem Spieler beim init zugeordnet wurde.
     * {@link Player#init(GameConfiguration, ImmutableList, int, int)}
     *
     * @return playerid
     */
    public int getPlayerID();

    /**
     * Gibt zurück, um welche Art von Spieler es sich handelt.
     * {@link AbstractGamePlayer}
     *
     * @return player_type
     */
    public PlayerType getPlayerType();
}

