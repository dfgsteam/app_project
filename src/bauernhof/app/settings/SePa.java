package bauernhof.app.settings;

import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class SePa {
    File file;

    /**
     * Parses the given file to create a GameConfiguration object.
     *
     * @param file the File object representing the file to be parsed
     * @return the parsed Settings object
     * @throws SeEx if there is an error in the configuration
     * @throws IOException if an I/O error occurs while reading the file
     */
    public Se parse(File file) throws SeEx, IOException {
        this.file = file;
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
            
            try {
                Se Settings = new Se(this);
                Document document = null;
                Element root = null;
                
                // XML-Datei einlesen
                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    document = builder.parse(new InputSource(new StringReader(fileContents)));
                    Settings.setRawConfiguration(fileContents);
                } catch(Exception e) {
                    throw new SeEx("xml build not loading", e);
                }

                // Root-Element erhalten
                try {
                    root = document.getDocumentElement();
                } catch(Exception e) {
                    throw new SeEx("rootElement not loading", e);
                }

                // Sound-Element erhalten
                try {
                    Element soundElement = (Element) root.getElementsByTagName("Sound").item(0);
                    Settings.setSound(Integer.parseInt(soundElement.getTextContent()));
                } catch(Exception e) {
                    throw new SeEx("soundElement not loading", e);
                }

                // Cardset-Element erhalten
                try {
                    Element cardSetElement = (Element) root.getElementsByTagName("CardSet").item(0);
                    if (Settings.GameConfigurations.containsKey(cardSetElement.getTextContent()))
                        Settings.setGameConf(Settings.GameConfigurations.get(cardSetElement.getTextContent()));
                    else
                        System.out.println("Card doesnt excist / not working");
                } catch(Exception e) {
                    throw new SeEx("cardSetElement not loading", e);
                }

                // AI-Element erhalten
                try {
                    Element aiElement = (Element) root.getElementsByTagName("AI").item(0);
                    Settings.setAi(Integer.parseInt(aiElement.getTextContent()));
                } catch(Exception e) {
                    throw new SeEx("aiElement not loading", e);
                }

                // Name-Element erhalten
                try {
                    Element nameElement = (Element) root.getElementsByTagName("Name").item(0);
                    Settings.setName(nameElement.getTextContent());
                } catch(Exception e) {
                    throw new SeEx("nameElement not loading", e);
                }

                return Settings;

            } catch (Exception e) {
                throw new SeEx(e);
            }

        } catch (IOException e) {
            throw new IOException("FileNotFound", e);
        }  
    }

    public void setSound(int sound) throws SeEx {
        Document document = null;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(this.file);
        } catch(Exception e) {
            throw new SeEx("xml build not loading", e);
        }

        try {
            NodeList nodeList = document.getElementsByTagName("Sound");
            Element soundElement = (Element) nodeList.item(0);
            soundElement.setTextContent(Integer.toString(sound));
        } catch(Exception e) {
            throw new SeEx("change failed", e);
        }

        this.save(document);
    }

    public void setCardSet(String cardSet) {return;}

    public void setAi(int ai) throws SeEx {
       Document document = null;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(this.file);
        } catch(Exception e) {
            throw new SeEx("xml build not loading", e);
        }

        try {
            NodeList nodeList = document.getElementsByTagName("AI");
            Element soundElement = (Element) nodeList.item(0);
            soundElement.setTextContent(Integer.toString(ai));
        } catch(Exception e) {
            throw new SeEx("change failed", e);
        }

        this.save(document); 
    }

    public void setName(String name) {return;}

    private void save(Document document) throws SeEx {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(this.file);
            transformer.transform(source, result);
        } catch (Exception e) {
            throw new SeEx("file not save", e);
        }
    }

}