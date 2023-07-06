package bauernhof.app.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.HumanPlayer;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.GameConfigurationParser;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;
import bauernhof.preset.networking.C2SConnection;
import bauernhof.preset.networking.RemoteException;
import bauernhof.preset.networking.RemotePlayer;

/**
 * This class was written by
 * @author Florian Will
 * @date 29.06.2023
 * This class extends from C2SConnection. It gets and initalises from RemotePlayer so it can be send to to the Server.
 */
public class Connector extends C2SConnection {
    private Socket connection;
    private GameConfigurationParser gameConfigurationParser;
    private String projectName;
    private GameConfiguration gameConfiguration;
    private ImmutableList<String> playerNames;
    private ImmutableList<Card> initialDrawPile;
    private int playerid;
    private ImmutableList<Integer> scores;
    private PlayerType type;
    private AbstractGamePlayer player = null;

    
    
    public Connector(Socket connection, GameConfigurationParser gameConfigurationParser, String projectName, PlayerType type) throws IOException {
        super(connection, gameConfigurationParser, projectName);
        this.connection = connection;
        this.gameConfigurationParser = gameConfigurationParser;
        this.projectName = projectName;
        this.type = type;
        try {
            handlePackets();
        } catch (RemoteException e) {
            e.printStackTrace();
        } 
    }

    @Override
    protected void onInit(GameConfiguration gameConfiguration, ImmutableList<Card> initialDrawPile, ImmutableList<String> playerNames, int playerid) throws Exception {
        PlayerType[] types = new PlayerType[playerNames.size()];
        for (int i = 0; i < playerNames.size(); i++) {
            if (i == playerid) { types[i] = this.type; }
            else { types[i] = PlayerType.REMOTE; }
        }
        GameBoardState game_board = new GameBoardState(playerNames.toArray(new String[1]), types, gameConfiguration, initialDrawPile);
    }

    @Override
    protected Move onRequest() throws Exception {
        return this.player.request();
    }

    @Override
    protected void onUpdate(Move opponentMove) throws Exception {
        this.player.update(opponentMove);
    }

    @Override
    protected int onGetScore() throws Exception {
        return this.player.getScore();
    }

    @Override
    protected String onGetName() throws Exception{
        return this.player.getName();
    }
       

    @Override
    protected void onVerifyGame(ImmutableList<Integer> scores) throws Exception {
        this.player.verifyGame(scores);
    }

    @Override
    protected void onError(RemoteException re) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'onError'");
    }
    
}


