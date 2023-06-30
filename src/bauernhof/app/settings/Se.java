package bauernhof.app.settings;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Se {
    private String cardSet;
    private String ai;
    private String name;
    private int sound;
    private String rawConfiguration;

    public static void main(String[] argvs) {
        Se obj = new Se();
            
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
        System.out.println("Displaying Files from the directory: ");
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
        
        System.out.println(obj.getCardSets());
    }


    public Se() {
        return;
    }



    public Set<String> getCardSets() {
        Set<String> fileNames = new HashSet<>();
        File folder = new File("gameconfigs");

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files)
                    if (file.isFile())
                        if (file.getName().endsWith(".xml"))
                            fileNames.add(file.getName());
                return fileNames;
            }
        }
        return null;
    }


    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
    }

    public String getCardSet() {
        return this.cardSet;
    }

    public void setAi(String ai) {
        this.ai = ai;
    }

    public String getAi() {
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