package bauernhof.app.settings;

import bauernhof.app.*;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.GameConfigurationParser;

import java.io.File;
import java.util.*;

/**
 * Represents the settings (Se) of the game.
 * 
 * @author Julius Hunold
 * Georg-August-Universität in Göttingen
 */
public class Se {
    private GameConfiguration GameConf;
    private int ai;
    private String name;
    private int sound;
    private String rawConfiguration;
    protected HashMap<String, GameConfiguration> GameConfigurations;
    private SePa SettingsParser;

    /**
     * Constructs a new Se object with the specified settings parser.
     *
     * @param SettingsParser the settings parser to use.
     */
    public Se(SePa SettingsParser) {
        this.SettingsParser = SettingsParser;
        this.getGameConfs();
    }

    /**
     * Retrieves the available game configurations.
     *
     * @return a set of file names corresponding to the available game configurations.
     */
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

    /**
     * Sets the game configuration.
     *
     * @param GameConf the game configuration to set.
     */
    public void setGameConf(GameConfiguration GameConf) {
        this.GameConf = GameConf;
    }

    /**
     * Sets the game configuration by name.
     *
     * @param String the game configuration by name to set.
     */
    public void setGameConf(String name) {
        this.GameConf = this.GameConfigurations.get(name);
    }

    /**
     * Retrieves the current game configuration.
     *
     * @return the current game configuration.
     */
    public GameConfiguration getGameConf() {
        return this.GameConf;
    }

    /**
     * Sets the AI value.
     *
     * @param ai the AI value to set.
     */
    public void setAi(int ai) {
        this.ai = ai;
    }

    /**
     * Sets the AI value and updates the settings parser.
     *
     * @param ai     the AI value to set.
     * @param change true if the settings should be updated in xml, false otherwise.
     * @throws SeEx if there is an error during the update process.
     */
    public void setAi(int ai, boolean change) throws SeEx {
        this.ai = ai;
        this.SettingsParser.setAi(ai);
    }


    /**
     * Retrieves the AI value.
     *
     * @return the AI value.
     */
    public int getAi() {
        return this.ai;
    }

    /**
     * Sets the name.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name.
     *
     * @return the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the sound value.
     *
     * @param sound the sound value to set.
     */
    public void setSound(int sound) {
        this.sound = sound;
    }
    
    /**
     * Sets the sound value and updates the settings parser.
     *
     * @param sound  the sound value to set.
     * @param change true if the settings should be updated in xml, false otherwise.
     * @throws SeEx if there is an error during the update process.
     */
    public void setSound(int sound, boolean change) throws SeEx {
        this.sound = sound;
        this.SettingsParser.setSound(sound);
    }

    /**
     * Retrieves the sound value.
     *
     * @return the sound value.
     */
    public int getSound() {
        return this.sound;
    }

    /**
     * Sets the raw configuration.
     *
     * @param rawConfiguration the raw configuration to set.
     */
    public void setRawConfiguration(String rawConfiguration) {
        this.rawConfiguration = rawConfiguration;
    }

    /**
     * Retrieves the raw configuration.
     *
     * @return the raw configuration.
     */
    public String getRawConfiguration() {
        return this.rawConfiguration;
    }
}