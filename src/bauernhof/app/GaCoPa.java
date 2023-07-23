//import bauernhof.preset;
package bauernhof.app;
import bauernhof.preset.*;
import bauernhof.preset.card.*;
import bauernhof.app.card.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import java.io.*;
import java.util.*;

/**
 * GaCoPa class that implements the GameConfigurationParser interface.
 *  GameConfigurationParser create from xml files a GameConfiguration object.
 *
 * @author julius.hunold
 * @version 1.0
 * @since 2023-06-27
 */
public class
GaCoPa implements GameConfigurationParser{

    /**
     * Parses the game configuration from the provided file contents.
     *
     * @param filecontents The contents of the file to parse.
     * @return The parsed GameConfiguration object.
     * @throws GameConfigurationException If there is an error parsing the game configuration.
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    public GameConfiguration parse(String filecontents) throws GameConfigurationException {
        try {
            Document document = null;
            Element root = null;
            GaCo gameConfiguration = new GaCo(null, 0, 0, null, null, null);
            
            // XML-Datei einlesen
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                document = builder.parse(new InputSource(new StringReader(filecontents)));
                gameConfiguration.setRawConfiguration(filecontents);
            } catch(Exception e) {
                throw new GameConfigurationException("xml build not loading", e);
            }

            // Root-Element erhalten
            try {
                root = document.getDocumentElement();
            } catch(Exception e) {
                throw new GameConfigurationException("rootElement not loading", e);
            }

            // Description-Element erhalten
            try {
                Element descriptionElement = (Element) root.getElementsByTagName("Description").item(0);
                gameConfiguration.setConfigDescription(descriptionElement.getTextContent());
            } catch(Exception e) {
                throw new GameConfigurationException("descriptionElement not loading", e);
            }

            // NumCardsPerPlayerHand-Element erhalten
            try {
                Element numCardsPerPlayerHandElement = (Element) root.getElementsByTagName("NumCardsPerPlayerHand").item(0);
                gameConfiguration.setNumCardsPerPlayerHand(Integer.parseInt(numCardsPerPlayerHandElement.getTextContent()));
            } catch(Exception e) {
                throw new GameConfigurationException("numCardsPerPlayerHandElement not loading", e);
            }

            // numDepositionAreaSlots-Element erhalten
            try {
                Element numDepositionAreaSlotsElement = (Element) root.getElementsByTagName("NumDepositionAreaSlots").item(0);
                gameConfiguration.setNumDepositionAreaSlots(Integer.parseInt(numDepositionAreaSlotsElement.getTextContent()));
            } catch(Exception e) {
                throw new GameConfigurationException("setNumDepositionAreaSlots not loading", e);
            }

            // CardColors erhalten
            Map<String, CardColor> cardColors = new HashMap<String, CardColor>();
            CardColor cardColorTemp = null;
            try {
                NodeList cardColorElements = root.getElementsByTagName("CardColor");

                for (int i=0; i<cardColorElements.getLength(); i++) {
                    Element cardColorElement = (Element) cardColorElements.item(i);

                    //-> Erst Karten, dann Effekte und mit einem NEUEN Setter in Karte hinzufügen
                    if (!cardColors.containsKey(cardColorElement.getTextContent())) {
                        cardColorTemp = new CardColor(cardColorElement.getTextContent(), cardColorElement.getAttribute("color"));
                        cardColors.put(cardColorElement.getTextContent(), cardColorTemp);
                        gameConfiguration.addCardColor(cardColorTemp);
                    } else {
                        throw new GameConfigurationException("cardColors duplicate");
                    }
                }
            } catch(Exception e) {
                throw new GameConfigurationException("cardColors not loading", e);
            }

            // Card erhalten
            Set<Map<String, Object>> effects = new HashSet<Map<String, Object>>();
            Map<String, Card> cards = new HashMap<String, Card>();

            try {
                NodeList cardsElements = root.getElementsByTagName("Card");
                for (int i=0; i<cardsElements.getLength(); i++) {
                    Element cardElement = (Element) cardsElements.item(i);
                    
                    // check, ob karte ist doppelt
                    if (cards.containsKey(cardElement.getAttribute("name"))) {
                        throw new GameConfigurationException("cardNameDuplicate");
                    } 

                    // Erstelle Karten Map
                    Map<String, Object> card = new HashMap<String, Object>();
                    
                    // Erstelle Karten Objekt und füge es zur Karten-Map hinzu 
                    Card cardObject = new Ca(cardElement.getAttribute("name"), Integer.parseInt(cardElement.getAttribute("basevalue")), cardColors.get(cardElement.getAttribute("color")), cardElement.getAttribute("image"), null);
                    cards.put(cardElement.getAttribute("name"), cardObject);


                    // Weise Map Kartenobjekt zu
                    card.put("object", cardObject);
                    
                    // Erstelle Set für betroffenen Karten/Farben eines Effekts
                    Set<Map<String, Object>> cardEffects = new HashSet<Map<String, Object>>();

                    // Erstelle Effect HashMaps
                    NodeList cardEffectElements = cardElement.getElementsByTagName("Effect");
                    for (int x=0; x<cardEffectElements.getLength(); x++) {
                        // XML Bereich von Hashmaps
                        Element cardEffectElement = (Element) cardEffectElements.item(x);
                        
                        // Füge zu Hashmap hinzu
                        Map<String, Object> effect = new HashMap<String, Object>();
                        effect.put("type", (Object) EffectType.valueOf(cardEffectElement.getAttribute("type")));
                        effect.put("effectValue", (Object) Integer.parseInt(cardEffectElement.getAttribute("effectvalue")));
                        
                        //Set<Either<Card,CardColor>> effectedCards = new HashSet<Either<Card,CardColor>>();
                        Set<String> effectedCards = new HashSet<>();

                    
                        // For-Loop durch XML File. Erst ColorRef, dann CardRef
                        NodeList effectElements;
                        try {
                            effectElements = cardEffectElement.getElementsByTagName("CardColorRef");
                            if (effectElements != null)
                                for (int y=0; y<effectElements.getLength(); y++) {
                                    Element effectElement = (Element) effectElements.item(y);
                                    effectedCards.add(effectElement.getTextContent());
                                }
                        } catch (Exception e) {
                            System.out.println(e); // Wenn keine CardCorlorRef vorhanden ist
                        }
                        try {  
                            effectElements = cardEffectElement.getElementsByTagName("CardRef");
                            if (effectElements != null)
                                for (int y=0; y<effectElements.getLength(); y++) {
                                    Element effectElement = (Element) effectElements.item(y);
                                    effectedCards.add(effectElement.getTextContent());
                                }
                        } catch (Exception e) {
                            System.out.println(e); // Wenn keine CardRef vorhanden ist
                        }

                        if (effectedCards.isEmpty()) {
                            throw new GameConfigurationException("effectNotAssignet");
                        }

                        effect.put("selector", effectedCards);
                        cardEffects.add(effect);
                    }

                    card.put("effects", cardEffects);

                    // für Karte zu Effekt Map hinzu
                    effects.add(card);
                    

                }
            } catch (Exception e) {
                throw new GameConfigurationException("cardColors not loading", e);
            }

            // Weise Karten Effekte zu
            
            Either<Card,CardColor> inp = null;

            try {
                Ca cardObject = null;
                Set<Effect> effectsList = null;
                Set<Card> cardList = new HashSet<Card>();
                for (var card : effects) { 
                    cardObject = (Ca) card.get("object");
                    effectsList = new HashSet<Effect>();
                    for (Map<String, Object> effect : (Set<Map<String, Object>>) card.get("effects")) {
                        Set<Either<Card,CardColor>> selector = new HashSet<Either<Card,CardColor>>();
                        Set<String> effectSelector = (Set<String>) effect.get("selector");
    
                        for (String effectCard : effectSelector) {
                            Card inpCard = cards.get(effectCard);
                            if (inpCard != null) {
                                inp = new Either<>(inpCard, null);
                            } else {
                                try {
                                    inp = new Either<>(null, cardColors.get(effectCard));
                                } catch (Exception e) {
                                    throw new GameConfigurationException("EffectedType not found", e);
                                }
                            }
                            selector.add(inp);
                        }
                        
                        Effect effectObject = (Effect) new Ef((EffectType) effect.get("type"), (int) effect.get("effectValue"), selector);
                        effectsList.add(effectObject);
                    }
                    cardObject.setEffects(effectsList);
                    cardList.add(cardObject);
                }
                gameConfiguration.setCards(cardList);
                return (GameConfiguration) gameConfiguration;
            } catch (Exception e) {
                throw new GameConfigurationException("cardAssignedToCard Error", e);
            } 
        } catch (Exception e) {
            throw new GameConfigurationException(e);
        }
    }

    /**
     * Parses the given file to create a GameConfiguration object.
     *
     * @param file the File object representing the file to be parsed
     * @return the parsed GameConfiguration object
     * @throws GameConfigurationException if there is an error in the game configuration
     * @throws IOException if an I/O error occurs while reading the file
     */
    public GameConfiguration parse(File file) throws GameConfigurationException, IOException {
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
