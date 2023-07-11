package bauernhof.app;

import java.io.File;
import java.util.ArrayList;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.ui.game.GameBoard;
import bauernhof.preset.*;
import sag.SAGPanel;

public class Start {
    public static void main(String args[]) throws Exception {
        File gameConfFile = new File("gameconfigs/bauernhof.xml");
        GameConfigurationParser GameConfPars = new GaCoPa();
        GameConfiguration GaCo = GameConfPars.parse(gameConfFile);

        ArrayList<AbstractGamePlayer> players = new ArrayList<AbstractGamePlayer>();

        GameBoardState gameBoardState = new GameBoardState(new String[]{"Florian", "Smila", "Alrun", "Lotta"}, new PlayerType[]{PlayerType.HUMAN, PlayerType.SIMPLE_AI, PlayerType.SIMPLE_AI, PlayerType.SIMPLE_AI}, GaCo, new ImmutableList<>(GaCo.getCards()));

        GameBoard GB = new GameBoard(GaCo,gameBoardState);
        gameBoardState.initGame(GB);

        System.out.println(GaCo.getConfigDescription());

        //BaFr sf = new BaFr();

    }
}
