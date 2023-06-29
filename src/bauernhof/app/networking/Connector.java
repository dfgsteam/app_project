package bauernhof.app.networking;

import java.io.IOException;
import java.net.Socket;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.GameConfigurationParser;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.Move;
import bauernhof.preset.card.Card;
import bauernhof.preset.networking.C2SConnection;
import bauernhof.preset.networking.RemoteException;

/**
 * This class was written by
 * @author Florian Will
 * @date 29.06.2023
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

    
    public Connector(Socket connection, GameConfigurationParser gameConfigurationParser, String projectName) throws IOException {
        super(connection, gameConfigurationParser, projectName);
        this.connection = connection;
        this.gameConfigurationParser = gameConfigurationParser;
        this.projectName = projectName;
        try {
            handlePackets();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onInit(GameConfiguration gameConfiguration, ImmutableList<Card> initialDrawPile, ImmutableList<String> playerNames, int playerid) throws Exception {
        

        throw new UnsupportedOperationException("Unimplemented method 'onInit'");
    }

    @Override
    protected Move onRequest() throws Exception {
        

        throw new UnsupportedOperationException("Unimplemented method 'onRequest'");
    }

    @Override
    protected void onUpdate(Move opponentMove) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onUpdate'");
    }

    @Override
    protected int onGetScore() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onGetScore'");
    }

    @Override
    protected String onGetName() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onGetName'");
    }

    @Override
    protected void onVerifyGame(ImmutableList<Integer> scores) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onVerifyGame'");
    }

    @Override
    protected void onError(RemoteException re) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onError'");
    }
    
}
