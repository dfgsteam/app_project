package bauernhof.app;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.launcher.Tournament;
import bauernhof.app.networking.ClientConnector;
import bauernhof.app.player.types.LocalRemotePlayer;
import bauernhof.app.ui.game.GameBoard;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;
import bauernhof.preset.networking.RemoteException;
import bauernhof.preset.networking.S2CConnection;
import com.sun.jdi.IntegerValue;

import javax.swing.*;

public class  Start {
    private static int client_connections = 1;
    private static int port = 1000;
    public static void main(String args[]) throws Exception {
        // int network = Integer.valueOf(args[0]);
        // if (network >= 0)
        // network = Integer.valueOf(args[0]);
            File gameConfFile = new File("gameconfigs/bauernhof.xml");
            GameConfigurationParser GameConfPars = new GaCoPa();
            GameConfiguration GaCo = GameConfPars.parse(gameConfFile);
        if (args.length == 0) {
            final GameBoardState gameBoardState = new GameBoardState(new String[]{"Florian", "Julius", "Cemil", "Horst"}, new PlayerType[]{PlayerType.RANDOM_AI, PlayerType.RANDOM_AI, PlayerType.RANDOM_AI, PlayerType.RANDOM_AI}, GaCo, new ImmutableList<>(GaCo.getCards()));
            GameBoard GB = new GameBoard(GaCo, gameBoardState);
            setDefaultDesigns();
            gameBoardState.initGame(GB);
            System.out.println(GaCo.getConfigDescription());
        }
        if (network == 1) {
            initServer(GaCo);
        }
        if (network == 3) {
            new Tournament(new String[]{"Flo", "PLAYER"}, new PlayerType[]{PlayerType.ADVANCED_AI, PlayerType.SIMPLE_AI}, GaCo, new ImmutableList<>(GaCo.getCards()), 100);
        }
        if (network == 2){
            initClient(GameConfPars, "HOFBAUERN");
        }
    }
    private static final void initServer(final GameConfiguration configuration) throws Exception {
        try (// SERVER
        ServerSocket server_socket = new ServerSocket(port)) {
            final ArrayList<S2CConnection> s2cconnections = new ArrayList<>();
            for (int i = 0; i < client_connections; i++) {
                System.out.println("Wait for Client...");
                s2cconnections.add(new S2CConnection(server_socket.accept()));
                System.out.println("Client connected");
            }

            final String[] playernames = new String[4];
            playernames[0] = "Player 1";
            playernames[1] = "Player 2";
            playernames[2] = "Player 3";
            playernames[3] = "HOFBAUERN";
            PlayerType[] types = new PlayerType[playernames.length];
            types[0] = PlayerType.RANDOM_AI;
            types[1] = PlayerType.RANDOM_AI;
            types[2] = PlayerType.RANDOM_AI;
            types[3] = PlayerType.REMOTE;

            /*for (int i = playernames.length - 1; i > playernames.length - 1 - client_connections; i--) {
                playernames[i] = s2cconnections.get(i - (playernames.length - client_connections)).getProjectName();
                System.out.println(s2cconnections.get(i - (playernames.length - client_connections)).getProjectName());
                System.out.println(i);
                System.out.println(i - (playernames.length - client_connections));
            } */
            for (final S2CConnection connection : s2cconnections) {
                connection.setPlayerNames(new ImmutableList<>(Arrays.asList(playernames)));
                for (final String name : new ImmutableList<>(Arrays.asList(playernames)))
                    System.out.println(name);
                ImmutableList<Card> cards = new ImmutableList<>(configuration.getCards());
                System.out.println(connection.getProjectName());
                System.out.println(cards);
                connection.getRemotePlayer().init(configuration, cards, playernames.length, 3);
                System.out.println(connection.isOpen());
            }
            for (int i = playernames.length - 1; i > playernames.length - 1 - client_connections; i--) {
                System.out.println(i - (playernames.length - client_connections));
                //s2cconnections.get(i - (playernames.length - client_connections))

                types[i] = PlayerType.REMOTE;
            }
            final GameBoardState gameBoardState = new GameBoardState(playernames, types, configuration, new ImmutableList<>(configuration.getCards()));
            GameBoard GB = new GameBoard(configuration, gameBoardState);
            setDefaultDesigns();
            for (int i = 0; i < playernames.length; i++)
                if(gameBoardState.getPlayers()[i].getPlayerType() == PlayerType.REMOTE)
                    ((LocalRemotePlayer) gameBoardState.getPlayers()[i]).setS2CConnection(s2cconnections.get(i - (playernames.length - client_connections)));
            gameBoardState.initGame(GB);
        }
        System.out.println(configuration.getConfigDescription());
    }
    private static final void initClient(final GameConfigurationParser parser, final String projectname) throws IOException, RemoteException {
        Socket socket = new Socket("127.0.0.1", port);
        ClientConnector myPLAYER = new ClientConnector(PlayerType.SIMPLE_AI, socket, parser, projectname);
        myPLAYER.handlePackets();
        return;
    }
    // Habe die Funktion erstellt weil ich das Fenster gerne im FULLSCREEN HABEN WILL!!!
    private static void setDefaultDesigns() {
        JFrame.getFrames()[0].setExtendedState(JFrame.MAXIMIZED_BOTH);
        JFrame.getFrames()[0].setIconImage(new ImageIcon("graphics/bauernhof_logo.png").getImage());
    }
}
