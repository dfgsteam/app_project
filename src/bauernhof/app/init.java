package bauernhof.app;

import java.util.ArrayList;
import java.util.List;

import bauernhof.preset.ArgumentParser;
import bauernhof.preset.OptionalFeature;

public class init {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("Kirill Pokhilenko", "Ramon Cemil Kimyon", "Viktor Tevosyan","Florian Will", "Julius Hunold"));
        List<OptionalFeature> optionalFeatures = new ArrayList<>(List.of(OptionalFeature.ADVANCED_AI, OptionalFeature.LAUNCHER, OptionalFeature.SAVEGAMES, OptionalFeature.SCREENSHOTS, OptionalFeature.SIMPLE_AI, OptionalFeature.SOUNDEFFECTS, OptionalFeature.TOURNAMENTS));
        new ArgumentParser(args, "Hofbauern", "1.2.1", names, optionalFeatures);
    }
}