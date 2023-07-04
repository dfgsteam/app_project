package bauernhof.app.settings;

import bauernhof.app.*;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.GameConfigurationParser;

import java.io.File;
import java.util.*;

public class Se {
    private GameConfiguration GameConf;
    private int ai;
    private String name;
    private int sound;
    private String rawConfiguration;
    protected HashMap<String, GameConfiguration> GameConfigurations;
    private SePa SettingsParser;


    public Se(SePa SettingsParser) {
        this.SettingsParser = SettingsParser;
        this.getGameConfs();
    }


    public Set<String> getGameConfs() {
        this.GameConfigurations = new HashMap<>();
        Set<String> fileNames = new HashSet<>();
        File folder = new File("gameconfigs");
        GameConfigurationParser GameConfigParser = new GaCoPa();


        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files)
                    if (file.isFile())
                        if (file.getName().endsWith(".xml"))
                            try {
                                System.out.println(file.getName());
                                GameConfiguration GameConf = GameConfigParser.parse(new File("gameconfigs/" + file.getName()));
                                fileNames.add(file.getName());
                                this.GameConfigurations.put(file.getName(), GameConf);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                return fileNames;
            }
        }
        return null;
    }


    public void setGameConf(GameConfiguration GameConf) {
        this.GameConf = GameConf;
    }

    public GameConfiguration getGameConf() {
        return this.GameConf;
    }

    public void setAi(int ai) {
        this.ai = ai;
    }
    public void setAi(int ai, boolean change) throws SeEx {
        this.ai = ai;
        this.SettingsParser.setAi(ai);
    }


    

    public int getAi() {
        return this.ai;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }
    
    public void setSound(int sound, boolean change) throws SeEx {
        this.sound = sound;
        this.SettingsParser.setSound(sound);
    }

    public int getSound() {
        return this.sound;
    }

    public void setRawConfiguration(String rawConfiguration) {
        this.rawConfiguration = rawConfiguration;
    }

    public String getRawConfiguration() {
        return this.rawConfiguration;
    }
}