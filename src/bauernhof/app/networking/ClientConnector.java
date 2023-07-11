package bauernhof.app.networking;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;
import bauernhof.preset.networking.C2SConnection;
import bauernhof.preset.networking.RemoteException;

import java.io.IOException;
import java.net.Socket;

public class ClientConnector extends C2SConnection {
    private GameBoardState gameboardstate;
    private PlayerType type;
    private AbstractGamePlayer player;
    public ClientConnector(final PlayerType type, final Socket socket, final GameConfigurationParser parser, final String porjectname) throws IOException, RemoteException {
        super(socket, parser, porjectname);
        this.type = type;
        handlePackets();
    }

    @Override
    protected void onInit(GameConfiguration game_configuration, ImmutableList<Card> initialDrawPile, ImmutableList<String> playerNames, int playerid) throws Exception {
        final PlayerType[] types = new PlayerType[playerNames.size()];
        for (int i = 0; i < playerNames.size(); i++) {
            if (i == playerid)
                types[i] = this.type;
            else types[i] = PlayerType.REMOTE;
        }
        final String[] names = new String[types.length];
        for (int i = 0; i < names.length; i++)
            names[i] = playerNames.get(i);
        this.gameboardstate = new GameBoardState(names, types, game_configuration, initialDrawPile);
        this.player = gameboardstate.getPlayers()[playerid];
    }
    public GameBoardState getGameBoardState() {
        return this.gameboardstate;
    }

    @Override
    protected Move onRequest() throws Exception {
        return player.request();
    }

    @Override
    protected void onUpdate(Move move) throws Exception {
        gameboardstate.doMove(move);
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
