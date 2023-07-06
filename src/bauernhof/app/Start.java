package bauernhof.app;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import bauernhof.app.player.types.HumanPlayer;
import bauernhof.app.ui.game.GameBoard;
import bauernhof.app.ui.launcher.BaFr;
import bauernhof.preset.*;

public class Start {
    public static void main(String args[]) throws GameConfigurationException, IOException, InterruptedException {
        File gameConfFile = new File("gameconfigs/bauernhof.xml");
        GameConfigurationParser GameConfPars = new GaCoPa();
        GameConfiguration GaCo = GameConfPars.parse(gameConfFile);

        Set<Player> set = new HashSet<>();
        set.add(new HumanPlayer(null, null));
        set.add(new HumanPlayer(null, null));

        GameBoard GB = new GameBoard(GaCo,set);

        System.out.println(GaCo.getConfigDescription());

        //BaFr sf = new BaFr();

    }
}
