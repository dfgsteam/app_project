package bauernhof.app.settings;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import bauernhof.preset.GameConfigurationException;

public class SePa {
    /**
     * Parses the game configuration from the provided file contents.
     * @param filecontents The contents of the file to parse.
     * @return The parsed Settings object.
     * @throws GameConfigurationException If there is an error in the configuration.
     */
    public Se parse(String filecontents) throws SeEx {
        try {
            Se Settings = new Se();
            Document document = null;
            Element root = null;

            // XML-Datei einlesen
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                document = builder.parse(new InputSource(new StringReader(filecontents)));
                Settings.setRawConfiguration(filecontents);
            } catch (Exception e) {
                throw new SeEx("xml build not loading", e);
            }

            // Root-Element erhalten
            try {
                root = document.getDocumentElement();
            } catch (Exception e) {
                //throw new SeEx("rootElement not loading", e);
            }

            // Sound-Element erhalten
            try {
                Element soundElement = (Element) root.getElementsByTagName("Sound").item(0);
                Settings.setSound(Integer.parseInt(soundElement.getTextContent()));
            } catch (Exception e) {
                throw new SeEx("soundElement not loading", e);
            }

            // Cardset-Element erhalten
            try {
                Element cardSetElement = (Element) root.getElementsByTagName("Cardset").item(0);
                Settings.setCardSet(cardSetElement.getTextContent());
            } catch (Exception e) {
                throw new SeEx("cardSetElement not loading", e);
            }

            // AI-Element erhalten
            try {
                Element aiElement = (Element) root.getElementsByTagName("AI").item(0);
                Settings.setAi(aiElement.getTextContent());
            } catch (Exception e) {
                throw new SeEx("aiElement not loading", e);
            }

            // Name-Element erhalten
            try {
                Element nameElement = (Element) root.getElementsByTagName("Name").item(0);
                Settings.setName(nameElement.getTextContent());
            } catch (Exception e) {
                throw new SeEx("nameElement not loading", e);
            }

            return Settings;

        } catch (Exception e) {
            throw new SeEx(e);
        }
    }

        /**
         * Parses the given file to create a GameConfiguration object.
         *
         * @param file the File object representing the file to be parsed
         * @return the parsed Settings object
        //* @throws SeEx if there is an error in the configuration
         * @throws IOException if an I/O error occurs while reading the file
         */
        public Se parse(File file) throws SeEx, IOException {
            try (FileReader fileReader = new FileReader(file);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                StringBuilder stringBuilder = new StringBuilder();
                String line;

                // Liest und fügt jede Zeile dem SringBuilder hinzu
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");  // füge Zeilenumbbruch hinzu
                }

                String fileContents = stringBuilder.toString();
                return this.parse(fileContents);
            } catch (IOException e) {
                throw new IOException("FileNotFound", e);
            }
        }

        }
    
