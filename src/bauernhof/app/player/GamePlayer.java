package bauernhof.app.player;

import bauernhof.app.launcher.GameProperties;
import bauernhof.preset.Player;

/**
 * Main Interface for the General Game-Player
 *
 * @author Ramon Cemil Kimyon
 * @date 11.06.2023 14:30
 */
public interface GamePlayer extends Player, PlayerProperties, GameProperties, CardSetHandler {

}
