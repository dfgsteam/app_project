package bauernhof.app.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kitfox.svg.A;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.types.Simple_AI;
import bauernhof.preset.ArgumentParser;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.OptionalFeature;

public class Main {

    private static final ArrayList<String> AUTHORS = new ArrayList<String>(Arrays.asList("Florian Will", "Julius Hunold", "Kirill Pochuenko", "Cemil", "Viktor Tevosyan"));
    private static final String PROJECT_NAME = "Hofbauern Kurwa Trottel";
    private static final String VERSION = "1.0";
    private static final ArrayList<OptionalFeature> FEATURES = new ArrayList<OptionalFeature>(Arrays.asList(OptionalFeature.ADVANCED_AI, OptionalFeature.SIMPLE_AI, OptionalFeature.LAUNCHER, OptionalFeature.SAVEGAMES, OptionalFeature.TOURNAMENTS, OptionalFeature.SOUNDEFFECTS));


    public static void main(String[] args) {
        ArgumentParser args_parser = new ArgumentParser(args, PROJECT_NAME, VERSION, AUTHORS, FEATURES);
        GameConfiguration game_config = 
    }
}
