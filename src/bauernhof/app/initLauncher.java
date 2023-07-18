package bauernhof.app;

import java.io.File;
import java.io.IOException;

import LauncherSettings;
import bauernhof.app.ui.launcher.UiLauncher;

public class InitLauncher {
    public static void main(String args[]) throws IOException, InterruptedException, SeEx {
        new UiLauncher(new LauncherSettings().parse(new File("gameconfigs/game/general.xml")));
    }
}
