package bauernhof.app;

import java.io.File;
import java.io.IOException;

import bauernhof.app.settings.*;
import bauernhof.app.ui.launcher2.Launcher;

public class Launcher2 {
    public static void main(String args[]) throws IOException, InterruptedException, SeEx {
        new Launcher(new SePa().parse(new File("gameconfigs/game/general.xml")));
    }
}
