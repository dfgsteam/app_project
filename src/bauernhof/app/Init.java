package bauernhof.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bauernhof.app.launcher.LauncherSettingsException;
import bauernhof.preset.ArgumentParser;
import bauernhof.preset.OptionalFeature;
import bauernhof.preset.Settings;

public class Init {
    public static void main(String[] args) throws IOException, InterruptedException, LauncherSettingsException {
        List<String> names = new ArrayList<>(List.of("Kirill Pokhilenko", "Ramon Cemil Kimyon", "Viktor Tevosyan","Florian Will", "Julius Hunold"));
        List<OptionalFeature> optionalFeatures = new ArrayList<>(List.of(OptionalFeature.ADVANCED_AI, OptionalFeature.LAUNCHER, OptionalFeature.SAVEGAMES, OptionalFeature.SCREENSHOTS, OptionalFeature.SIMPLE_AI, OptionalFeature.SOUNDEFFECTS, OptionalFeature.TOURNAMENTS));
        
        // Wenn keine Argumente, soll launcher Starten
        if (args.length == 0) {
            args = new String[1];
            args[0] = "-l";
        }
        Settings parser = new ArgumentParser(args, "Hofbauern", "1.2.1", names, optionalFeatures);
        if (parser.shouldLauncherLaunch) {
            new InitLauncher();
        }
    }
}