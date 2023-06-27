package bauernhof.app;

import java.io.File;
import java.io.IOException;

import bauernhof.preset.*;

public class start {
    public static void main(String args[]) throws GameConfigurationException, IOException {
        File gameConfFile = new File("gameconfigs/cards.xml");
        GameConfigurationParser GameConfPars = new GaCoPa();
        GameConfiguration GaCo = GameConfPars.parse(gameConfFile);

        System.out.println(GaCo.getConfigDescription());

    }
}
