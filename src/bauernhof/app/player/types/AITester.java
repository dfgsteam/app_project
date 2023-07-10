package bauernhof.app.player.types;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import bauernhof.app.GaCoPa;
import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.GameConfigurationParser;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.PlayerType;

public class AITester {
    public static void main(String[] args) throws Exception {
        File gameConfFile = new File("gameconfigs/bauernhof.xml");
        GameConfigurationParser GameConfPars = new GaCoPa();
        GameConfiguration GaCo = GameConfPars.parse(gameConfFile);

        ArrayList<AbstractGamePlayer> players = new ArrayList<AbstractGamePlayer>();
        GameBoardState gameBoardState = new GameBoardState(new String[]{"Florian", "Smila", "Alrun", "Lotta"}, new PlayerType[]{PlayerType.ADVANCED_AI, PlayerType.RANDOM_AI, PlayerType.RANDOM_AI, PlayerType.RANDOM_AI}, GaCo, new ImmutableList<>(GaCo.getCards()));

        Advanced_AI a = new Advanced_AI("Florian");
        a.setGameBoardState(gameBoardState);

        System.out.println(a.request());

    }
}
