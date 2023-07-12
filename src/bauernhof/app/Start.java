package bauernhof.app;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.networking.ClientConnector;
import bauernhof.app.player.types.LocalRemotePlayer;
import bauernhof.app.ui.game.GameBoard;
import bauernhof.preset.*;
import bauernhof.preset.networking.RemoteException;
import bauernhof.preset.networking.S2CConnection;
import sag.SAGPanel;


public class Start {
    private static int client_connections = 1;
    private static int port = 1000;
    private static int network = 0;
    public static void main(String args[]) throws Exception {

        File gameConfFile = new File("gameconfigs/bauernhof.xml");
        GameConfigurationParser GameConfPars = new GaCoPa();
        GameConfiguration GaCo = GameConfPars.parse(gameConfFile);
        if (network < 1) {
            final GameBoardState gameBoardState = new GameBoardState(new String[]{"Florian", "Smila", "Test", "Test2"}, new PlayerType[]{PlayerType.SIMPLE_AI, PlayerType.SIMPLE_AI, PlayerType.RANDOM_AI, PlayerType.HUMAN}, GaCo, new ImmutableList<>(GaCo.getCards()));
            GameBoard GB = new GameBoard(GaCo, gameBoardState);
            //setDefaultDesigns();
            gameBoardState.initGame(GB);
            System.out.println(GaCo.getConfigDescription());
        }
        Socket client_socket;
        if (network == 1) {
            initServer(GaCo);
        } else {
            initClient(GameConfPars, "HOFBAUERN");
        }
    }
    private static final void initServer(final GameConfiguration configuration) throws Exception {
        // SERVER
        final ServerSocket server_socket = new ServerSocket(port);
        final ArrayList<S2CConnection> s2cconnections = new ArrayList<>();
        for (int i = 0; i < client_connections; i++)
            s2cconnections.add(new S2CConnection(server_socket.accept()));
        final String[] playernames = new String[4];
        playernames[0] = "Player 1";
        playernames[1] = "Player 2";
        playernames[2] = "Player 3";
        playernames[3] = "Player 4";
        PlayerType[] types = new PlayerType[playernames.length];
        types[0] = PlayerType.RANDOM_AI;
        types[1] = PlayerType.RANDOM_AI;
        types[2] = PlayerType.RANDOM_AI;
        types[3] = PlayerType.RANDOM_AI;
        for (int i = playernames.length - 1; i > playernames.length - 1 - client_connections; i--) {
            playernames[i] = s2cconnections.get(i - (playernames.length - client_connections)).getRemotePlayer().getName();
            types[i] = PlayerType.REMOTE;
        }
        final GameBoardState gameBoardState = new GameBoardState(playernames, types, configuration, new ImmutableList<>(configuration.getCards()));
        GameBoard GB = new GameBoard(configuration, gameBoardState);
        setDefaultDesigns();
        for (int i = 0; i < playernames.length; i++)
            if(gameBoardState.getPlayers()[i].getPlayerType() == PlayerType.REMOTE)
                ((LocalRemotePlayer) gameBoardState.getPlayers()[i]).setS2CConnection(s2cconnections.get(i - (playernames.length - client_connections)));
        gameBoardState.initGame(GB);
        System.out.println(configuration.getConfigDescription());
    }
    private static final void initClient(final GameConfigurationParser parser, final String projectname) throws IOException, RemoteException {
        Socket socket = new Socket("localhost", port);
        new ClientConnector(PlayerType.SIMPLE_AI, socket, parser, projectname);
    }
    // Habe die Funktion erstellt weil ich das Fenster gerne im FULLSCREEN HABEN WILL!!!
    private static void setDefaultDesigns() {
        JFrame.getFrames()[0].setExtendedState(JFrame.MAXIMIZED_BOTH);
        JFrame.getFrames()[0].setIconImage(new ImageIcon("graphics/bauernhof_logo.png").getImage());
    }
}
