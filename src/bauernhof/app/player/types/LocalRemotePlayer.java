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
    private RemotePlayer remote;
    private S2CConnection connection;
    public LocalRemotePlayer(final String name) {
        super(name, PlayerType.REMOTE);
    }
    public void setS2CConnection(final S2CConnection connection) {
        this.connection = connection;
        this.remote = connection.getRemotePlayer();
    }
    @Override
    public Move request() throws Exception {
        return remote.request();
    }
    @Override
    public void update(final Move move) throws Exception {
        super.update(move);
        this.remote.update(move);
    }
    @Override
    public int getScore() throws Exception {
        if (this.score != remote.getScore()) {
            connection.disconnect();
            System.out.println("RemotePlayer hat einen falschen Score!");
        }
        return remote.getScore();
    }
}
