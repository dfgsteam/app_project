package bauernhof.app.launcher;

import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import bauernhof.preset.GameConfiguration;

/**
 * SettingsParser create from xml files a Setting object.
 *
 * @author Julius Hunold
 * @version 1.0
 */

public class LauncherSettingsParser {
    File file;

    /**
     * Parses the given file to create a GameConfiguration object.
     *
     * @param file the File object representing the file to be parsed
     * @return the parsed Settings object
     * @throws LauncherSettingsException if there is an error in the configuration
     * @throws IOException if an I/O error occurs while reading the file
     */
    public LauncherSettings parse(File file) throws LauncherSettingsException, IOException {
        this.file = file;
        try (FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            
            // Reads and appends each line to the StringBuilder
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");  // Add line break
            }

            String fileContents = stringBuilder.toString();
            
            try {
                LauncherSettings Settings = new LauncherSettings(this);
                Document document = null;
                Element root = null;
                
                // Read XML file
                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    document = builder.parse(new InputSource(new StringReader(fileContents)));
                    Settings.setRawConfiguration(fileContents);
                } catch(Exception e) {
                    throw new LauncherSettingsException("xml build not loading", e);
                }

                // Get Root Element
                try {
                    root = document.getDocumentElement();
                } catch(Exception e) {
                    throw new LauncherSettingsException("rootElement not loading", e);
                }

                // Receive Sound Element
                try {
                    Element soundElement = (Element) root.getElementsByTagName("Sound").item(0);
                    Settings.setSound(Integer.parseInt(soundElement.getTextContent()));
                } catch(Exception e) {
                    throw new LauncherSettingsException("soundElement not loading", e);
                }

                // Receive Cardset Element
                try {
                    Element cardSetElement = (Element) root.getElementsByTagName("CardSet").item(0);
                    if (Settings.GameConfigurations.containsKey(cardSetElement.getTextContent())) {
                        Settings.setGameConf(Settings.GameConfigurations.get(cardSetElement.getTextContent()));
                    } else {
                        System.out.println("Card doesnt excist / not working");
                        Settings.setGameConf((GameConfiguration)null);
                    }
                } catch(Exception e) {
                    throw new LauncherSettingsException("cardSetElement not loading", e);
                }

                // Receive AI element
                try {
                    Element aiElement = (Element) root.getElementsByTagName("AI").item(0);
                    Settings.setAi(Integer.parseInt(aiElement.getTextContent()));
                } catch(Exception e) {
                    throw new LauncherSettingsException("aiElement not loading", e);
                }

                // Receive Name Element
                try {
                    Element nameElement = (Element) root.getElementsByTagName("Name").item(0);
                    Settings.setName(nameElement.getTextContent());
                } catch(Exception e) {
                    throw new LauncherSettingsException("nameElement not loading", e);
                }

                return Settings;

            } catch (Exception e) {
                throw new LauncherSettingsException(e);
            }

        } catch (IOException e) {
            throw new IOException("FileNotFound", e);
        }  
    }

    /**
     * Sets the sound value in the XML file and saves the changes.
     * 
     * @param sound the sound value to set.
     * @throws LauncherSettingsException if there is an error during the XML modification or saving.
     */
    public void setSound(int sound) throws LauncherSettingsException {
        Document document = null;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(this.file);
        } catch(Exception e) {
            throw new LauncherSettingsException("xml build not loading", e);
        }

        try {
            NodeList nodeList = document.getElementsByTagName("Sound");
            Element soundElement = (Element) nodeList.item(0);
            soundElement.setTextContent(Integer.toString(sound));
        } catch(Exception e) {
            throw new LauncherSettingsException("change failed", e);
        }

        this.save(document);
    }

    /**
     * Sets the card set in the XML file.
     * 
     * @param cardSet the card set value to set.
     */
    public void setCardSet(String cardSet) {return;}

    /**
     * Sets the AI value in the XML file and saves the changes.
     * 
     * @param ai the AI value to set.
     * @throws LauncherSettingsException if there is an error during the XML modification or saving.
     */
    public void setAi(int ai) throws LauncherSettingsException {
       Document document = null;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(this.file);
        } catch(Exception e) {
            throw new LauncherSettingsException("xml build not loading", e);
        }

        try {
            NodeList nodeList = document.getElementsByTagName("AI");
            Element soundElement = (Element) nodeList.item(0);
            soundElement.setTextContent(Integer.toString(ai));
        } catch(Exception e) {
            throw new LauncherSettingsException("change failed", e);
        }

        this.save(document); 
    }

    /**
     * Sets the name in the XML file.
     * 
     * @param name the name value to set.
     */
    public void setName(String name) {return;}

    /**
     * Saves the XML document to the file.
     * 
     * @param document the XML document to save.
     * @throws LauncherSettingsException if there is an error during saving.
     */
    private void save(Document document) throws LauncherSettingsException {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(this.file);
            transformer.transform(source, result);
        } catch (Exception e) {
            throw new LauncherSettingsException("file not save", e);
        }
    }

}