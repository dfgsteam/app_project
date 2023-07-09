package bauernhof.app;

import java.io.File;
import java.io.IOException;

import bauernhof.app.settings.*;
import bauernhof.app.ui.launcher.BaFr;

public class Launcher {
    public static boolean debug;

    public static void main(String args[]) throws IOException, InterruptedException, SeEx {
        Se Settings = new SePa().parse(new File("gameconfigs/game/general.xml"));

        System.out.println(Settings.getRawConfiguration());

        // Start Launcher
        BaFr sf = new BaFr(Settings);

    }
}
