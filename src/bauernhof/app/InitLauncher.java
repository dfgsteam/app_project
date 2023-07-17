package bauernhof.app;

import java.io.File;
import java.io.IOException;

import bauernhof.app.launcherSettings.*;
import bauernhof.app.ui.launcher.UiLauncher;

public class InitLauncher {
    public InitLauncher() throws IOException, InterruptedException, LauncherSettingsException {
        new UiLauncher(new LauncherSettingsParser().parse(new File("gameconfigs/game/general.xml")));
    }
}
