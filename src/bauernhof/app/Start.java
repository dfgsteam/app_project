package bauernhof.app;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.GameBoard;
import bauernhof.preset.*;
import bauernhof.preset.networking.C2SConnection;

import javax.swing.*;

public class Start {

    static int Port = 1409;
    static int network = 1;

    public static void main(String args[]) throws Exception {
        if(network<1){
            File gameConfFile = new File("gameconfigs/bauernhof.xml");
            GameConfigurationParser GameConfPars = new GaCoPa();
            GameConfiguration GaCo = GameConfPars.parse(gameConfFile);

            GameBoardState gameBoardState = new GameBoardState(new String[]{"Florian", "Smila", "Alrun", "Lotta"}, new PlayerType[]{PlayerType.SIMPLE_AI, PlayerType.RANDOM_AI, PlayerType.RANDOM_AI, PlayerType.RANDOM_AI}, GaCo, new ImmutableList<>(GaCo.getCards()));

            GameBoard GB = new GameBoard(GaCo,gameBoardState);
            setDefaultDesigns();
            gameBoardState.initGame(GB);

            System.out.println(GaCo.getConfigDescription());
        }
        else if(network == 1){
            ServerSocket socket = new ServerSocket(Port);
            for(int i = 0; i < playernames.length; i++){
                Socket client = socket.accept();
            }
        }
        else if(network == 2){//HHH
            Socket socket = new Socket("localhost", Port);
            C2SConnection(socket, GameConfigurationParser, "hi");
            socket.handlePacket();
        }

        //BaFr sf = new BaFr();

    }
    // Habe die Funktion erstellt weil ich das Fenster gerne im FULLSCREEN HABEN WILL!!!
    private static void setDefaultDesigns() {
        JFrame.getFrames()[0].setExtendedState(JFrame.MAXIMIZED_BOTH);
        JFrame.getFrames()[0].setIconImage(new ImageIcon("graphics/bauernhof_logo.png").getImage());
    }


}
