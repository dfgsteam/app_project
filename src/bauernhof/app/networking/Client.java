package bauernhof.app.networking;

import bauernhof.app.system.GameSystem;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;
import bauernhof.preset.networking.C2SConnection;
import bauernhof.preset.networking.RemoteException;

import java.io.IOException;
import java.net.Socket;

public class Client extends C2SConnection {
    private Settings settings;
    private Player player;

    public Client(final Settings settings, final Socket socket, final GameConfigurationParser parser, final String porjectname) throws IOException, RemoteException {
        super(socket, parser, porjectname);
        this.settings = settings;
    }

    @Override
    protected void onInit(GameConfiguration game_configuration, ImmutableList<Card> initialDrawPile, ImmutableList<String> playerNames, int playerid) throws Exception {

    }
    @Override
    protected Move onRequest() throws Exception {
        return player.request();
    }

    @Override
    protected void onUpdate(Move move) throws Exception {
        //((LocalRemotePlayer) gameboardstate.getActualPlayer()).setNextMove(move);
    }

    @Override
    protected int onGetScore() throws Exception {
        return player.getScore();
    }

    @Override
    protected String onGetName() throws Exception {
        return player.getName();
    }

    @Override
    protected void onVerifyGame(ImmutableList<Integer> immutableList) throws Exception {
        player.verifyGame(immutableList);
    }
}
