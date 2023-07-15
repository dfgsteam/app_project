package bauernhof.app;

import java.io.File;
import java.io.IOException;

import bauernhof.app.settings.*;
import bauernhof.app.ui.launcher.UiLauncher;

public class initLauncher {
    public static void main(String args[]) throws IOException, InterruptedException, SeEx {
        new UiLauncher(new SePa().parse(new File("gameconfigs/game/general.xml")));
    }
}
