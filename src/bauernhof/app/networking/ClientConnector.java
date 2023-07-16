package bauernhof.app.networking;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.LocalRemotePlayer;
import bauernhof.app.ui.game.UiGame;
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
    }

    @Override
    protected void onInit(GameConfiguration game_configuration, ImmutableList<Card> initialDrawPile, ImmutableList<String> playerNames, int playerid) throws Exception {
        System.out.println(initialDrawPile);
        System.out.println("INIT");
        final PlayerType[] types = new PlayerType[playerNames.size()];
        System.out.println("TYPES");
        for (int i = 0; i < playerNames.size(); i++) {
            if (i == playerid)
                types[i] = this.type;
            else types[i] = PlayerType.REMOTE;
        }
        System.out.println("NAMES");
        final String[] names = new String[types.length];
        for (int i = 0; i < names.length; i++)
            names[i] = playerNames.get(i);
        System.out.println("1");
        System.out.println(initialDrawPile);
        this.gameboardstate = new GameBoardState(names, types, game_configuration, initialDrawPile);
        System.out.println("2");
        UiGame UiGame = new UiGame(game_configuration, gameboardstate);
        System.out.println("3");
        gameboardstate.initGame(UiGame);
        System.out.println("4");
        this.player = gameboardstate.getPlayers()[playerid];
        System.out.println("END INIT");
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
