package bauernhof.app.player.types;

import bauernhof.app.player.PlayerCards;
import bauernhof.app.system.GameBoard;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.system.GameSystem;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerGUIAccess;
import bauernhof.preset.PlayerType;
import bauernhof.preset.Settings;
import bauernhof.preset.card.Card;

import java.awt.*;

/**
 * Human Player - PlayerType Human Instanz
 *
 * @author Ramon Cemil Kimyon
 * @date 10.06.2023 15:15
 */
public class HumanPlayer extends AbstractGamePlayer {
    private PlayerGUIAccess access;
    public HumanPlayer(final Settings settings, final PlayerCards playercards, final GameSystem gamesystem) {
        super(settings, playercards, gamesystem);
        this.access = GameSystem.getGraphics();
    }

    @Override
    public Move request() throws Exception {
        return access.requestMoveFromCurrentHumanPlayer();
    }

}
