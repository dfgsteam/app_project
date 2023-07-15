package bauernhof.app.player.types;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;

import java.awt.*;

/**
 * @author Ramon Cemil Kimyon
 * @date 10.07.2023 18:38
 */
public class Cheater extends AbstractGamePlayer {
    public Cheater(final String name, final PlayerType type, final Color color) {
        super(name, type, color);
    }
    @Override
    public Move request() throws Exception {
        return null;
    }
}
