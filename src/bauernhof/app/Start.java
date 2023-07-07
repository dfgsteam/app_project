package bauernhof.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.HumanPlayer;
import bauernhof.app.ui.game.GameBoard;
import bauernhof.app.ui.launcher.BaFr;
import bauernhof.preset.*;

public class Start {
    public static void main(String args[]) throws Exception {
        File gameConfFile = new File("gameconfigs/bauernhof.xml");
        GameConfigurationParser GameConfPars = new GaCoPa();
        GameConfiguration GaCo = GameConfPars.parse(gameConfFile);

        ArrayList<AbstractGamePlayer> players = new ArrayList<AbstractGamePlayer>();

       /* players.add(new HumanPlayer("Flo", null));
        players.add(new HumanPlayer("Smila", null));
        players.add(new HumanPlayer("Alrun", null));
        players.add(new HumanPlayer("Celina", null));*/

        int i;
        for (var item : players) {
            i = 0;
            while (i++ < 10)
                item.add(GaCo.getCards().iterator().next());
            System.out.println(item.getCards());
        }

        GameBoard GB = new GameBoard(GaCo,players);

        System.out.println(GaCo.getConfigDescription());

        //BaFr sf = new BaFr();

    }
}
