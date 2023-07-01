package bauernhof.app;

import java.io.File;
import java.io.IOException;

import bauernhof.app.UI.launcher.BaFr;
import bauernhof.preset.*;

public class Start {
    public static void main(String args[]) throws GameConfigurationException, IOException, InterruptedException {
        File gameConfFile = new File("gameconfigs/bauernhof.xml");
        GameConfigurationParser GameConfPars = new GaCoPa();
        GameConfiguration GaCo = GameConfPars.parse(gameConfFile);

        System.out.println(GaCo.getConfigDescription());

        BaFr sf = new BaFr();

    }
}
