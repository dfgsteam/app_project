package bauernhof.app;

import java.io.File;
import java.io.IOException;

import bauernhof.app.settings.*;
import bauernhof.app.ui.launcher.BaFr;

public class Launcher {
    public static void main(String args[]) throws IOException, InterruptedException, SeEx {
        File settConfFile = new File("gameconfigs/game/general.xml");
        SePa SettPars = new SePa();
        Se Settings = SettPars.parse(settConfFile);

        System.out.println(Settings.getRawConfiguration());

        // Start Launcher
        BaFr sf = new BaFr(Settings);

    }
}
