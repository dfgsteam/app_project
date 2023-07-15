package bauernhof.app;

import java.io.File;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.ui.game.GameBoard;
import bauernhof.preset.*;

import javax.swing.*;

public class Start {
    public static void main(String args[]) throws Exception {
        File gameConfFile = new File("gameconfigs/bauernhof.xml");
        GameConfigurationParser GameConfPars = new GaCoPa();
        GameConfiguration GaCo = GameConfPars.parse(gameConfFile);

        GameBoardState gameBoardState = new GameBoardState(new String[]{"Florian", "Smila", "Manfred"}, new PlayerType[]{PlayerType.ADVANCED_AI, PlayerType.SIMPLE_AI, PlayerType.SIMPLE_AI}, GaCo, new ImmutableList<>(GaCo.getCards()));

        GameBoard GB = new GameBoard(GaCo,gameBoardState);
        setDefaultDesigns();
        gameBoardState.initGame(GB);

        System.out.println(GaCo.getConfigDescription());


        //BaFr sf = new BaFr();

    }
    // Habe die Funktion erstellt weil ich das Fenster gerne im FULLSCREEN HABEN WILL!!!
    private static void setDefaultDesigns() {
        JFrame.getFrames()[0].setExtendedState(JFrame.MAXIMIZED_BOTH);
        JFrame.getFrames()[0].setIconImage(new ImageIcon("graphics/bauernhof_logo.png").getImage());
    }
}
