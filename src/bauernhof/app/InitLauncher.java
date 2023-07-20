package bauernhof.app;

import java.io.File;
import java.io.IOException;

import bauernhof.app.launcher.LauncherSettingsException;
import bauernhof.app.launcher.LauncherSettingsParser;
import bauernhof.app.ui.launcher.UiLauncher;

public class InitLauncher {
    public InitLauncher() throws IOException, InterruptedException, LauncherSettingsException {
        new UiLauncher(new LauncherSettingsParser().parse(new File("gameconfigs/game/general.xml")));
    }
}
