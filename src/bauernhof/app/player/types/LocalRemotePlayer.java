package bauernhof.app.player.types;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;
import bauernhof.preset.networking.RemotePlayer;
import bauernhof.preset.networking.S2CConnection;

/**
 * @author Ramon Cemil Kimyon
 * @date 10.07.2023 13:34
 */
public class LocalRemotePlayer extends AbstractGamePlayer {
    private RemotePlayer player;
    public LocalRemotePlayer(final String name) {
        super(name, PlayerType.REMOTE);
    }
    public void setS2CConnection(final S2CConnection connection) {
        this.player = connection.getRemotePlayer();
    }
    @Override
    public Move request() throws Exception {
        return player.request();
    }
    @Override
    public void update(final Move move) {

    }
}
