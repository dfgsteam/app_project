package bauernhof.app.networking;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;
import bauernhof.preset.networking.C2SConnection;
import bauernhof.preset.networking.RemoteException;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Florian Will
 * @date 10.07.2023 14:03
 */
public class ClientConnection extends C2SConnection {
    private GameBoardState gameboardstate;
    private PlayerType type;
    public ClientConnection(final Socket socket, final GameConfigurationParser parser, final String porjectname) throws IOException, RemoteException {
        super(socket, parser, porjectname);
        handlePackets();
    }

    @Override
    protected void onInit(GameConfiguration game_configuration, ImmutableList<Card> initialDrawPile, ImmutableList<String> playerNames, int playerid) throws Exception {
        PlayerType[] types = new PlayerType[playerNames.size()];
        for (int i = 0; i < playerNames.size(); i++) {
            if (i == playerid)
                types[i] = this.type;
            else types[i] = PlayerType.REMOTE;
        }
       // final String[] playernames = new String[this.playerNames]
        //this.gameboardstate = new GameBoardState(playerNames.toArray(new String[1]), types, gameConfiguration, initialDrawPile);
    }
    public GameBoardState getGameBoardState() {
        return null;
    }

    @Override
    protected Move onRequest() throws Exception {
        return null;
    }

    @Override
    protected void onUpdate(Move move) throws Exception {

    }

    @Override
    protected int onGetScore() throws Exception {
        return 0;
    }

    @Override
    protected String onGetName() throws Exception {
        return null;
    }

    @Override
    protected void onVerifyGame(ImmutableList<Integer> immutableList) throws Exception {

    }
}
