package bauernhof.app.player.types;

import bauernhof.app.player.PlayerCards;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.system.GameBoard;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerGUIAccess;
import bauernhof.preset.Settings;

/**
 * Human Player - PlayerType Human Instanz
 *
 * @author Ramon Cemil Kimyon
 * @date 10.06.2023 15:15
 */
public class HumanPlayer extends AbstractGamePlayer {
    public HumanPlayer(final Settings settings, final PlayerCards playercards, final GameBoard gamesystem) {
        super(settings, playercards, gamesystem);
    }

    @Override
    public Move request() throws Exception {
        return GameBoard.getGraphics().requestMoveFromCurrentHumanPlayer();
    }
}