package bauernhof.app.player.types;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

/**
 * Human Player - PlayerType Human Instanz
 *
 * @author Ramon Cemil Kimyon
 * @date 10.06.2023 15:15
 */
public class HumanPlayer extends AbstractGamePlayer {
    public HumanPlayer(String name, GameBoardState status) {
        super(name, status, PlayerType.HUMAN);
    }
}
