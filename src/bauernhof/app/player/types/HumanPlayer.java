package bauernhof.app.player.types;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;

/**
 * Human Player - PlayerType Human Instanz
 *
 * @author Ramon Cemil Kimyon
 * @date 10.06.2023 15:15
 */
public class HumanPlayer extends AbstractGamePlayer {
    public HumanPlayer(String name) {
        super(name, PlayerType.HUMAN);
    }

    @Override
    public Move request() throws Exception {
        return null;
    }
}
