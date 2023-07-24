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
 * @author Julius Hunold
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
            
            // Read XML file
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                document = builder.parse(new InputSource(new StringReader(filecontents)));
                gameConfiguration.setRawConfiguration(filecontents);
            } catch(Exception e) {
                throw new GameConfigurationException("xml build not loading", e);
            }

            // Get Root Element
            try {
                root = document.getDocumentElement();
            } catch(Exception e) {
                throw new GameConfigurationException("rootElement not loading", e);
            }

            // Receive Description Element
            try {
                Element descriptionElement = (Element) root.getElementsByTagName("Description").item(0);
                gameConfiguration.setConfigDescription(descriptionElement.getTextContent());
            } catch(Exception e) {
                throw new GameConfigurationException("descriptionElement not loading", e);
            }

            // Receive NumCardsPerPlayerHand element
            try {
                Element numCardsPerPlayerHandElement = (Element) root.getElementsByTagName("NumCardsPerPlayerHand").item(0);
                gameConfiguration.setNumCardsPerPlayerHand(Integer.parseInt(numCardsPerPlayerHandElement.getTextContent()));
            } catch(Exception e) {
                throw new GameConfigurationException("numCardsPerPlayerHandElement not loading", e);
            }

            // Receive numDepositionAreaSlots element
            try {
                Element numDepositionAreaSlotsElement = (Element) root.getElementsByTagName("NumDepositionAreaSlots").item(0);
                gameConfiguration.setNumDepositionAreaSlots(Integer.parseInt(numDepositionAreaSlotsElement.getTextContent()));
            } catch(Exception e) {
                throw new GameConfigurationException("setNumDepositionAreaSlots not loading", e);
            }

            // Receive Card Colors
            Map<String, CardColor> cardColors = new HashMap<String, CardColor>();
            CardColor cardColorTemp = null;
            try {
                NodeList cardColorElements = root.getElementsByTagName("CardColor");

                for (int i=0; i<cardColorElements.getLength(); i++) {
                    Element cardColorElement = (Element) cardColorElements.item(i);

                    // -> First add cards, then effects, and use a NEW setter in the card to add them.
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

            // Receive card
            Set<Map<String, Object>> effects = new HashSet<Map<String, Object>>();
            Map<String, Card> cards = new HashMap<String, Card>();

            try {
                NodeList cardsElements = root.getElementsByTagName("Card");
                for (int i=0; i<cardsElements.getLength(); i++) {
                    Element cardElement = (Element) cardsElements.item(i);
                    
                    // Check if the card is a duplicate
                    if (cards.containsKey(cardElement.getAttribute("name"))) {
                        throw new GameConfigurationException("cardNameDuplicate");
                    } 

                    // Create cards map
                    Map<String, Object> card = new HashMap<String, Object>();
                    
                    // Create a card object and add it to the card map
                    Card cardObject = new Ca(cardElement.getAttribute("name"), Integer.parseInt(cardElement.getAttribute("basevalue")), cardColors.get(cardElement.getAttribute("color")), cardElement.getAttribute("image"), null);
                    cards.put(cardElement.getAttribute("name"), cardObject);


                    // Assign Map Card object
                    card.put("object", cardObject);
                    
                    // Create a Set for the affected cards/colors of an effect.
                    Set<Map<String, Object>> cardEffects = new HashSet<Map<String, Object>>();

                    // Create Effect HashMaps
                    NodeList cardEffectElements = cardElement.getElementsByTagName("Effect");
                    for (int x=0; x<cardEffectElements.getLength(); x++) {
                        // XML section of Hashmaps
                        Element cardEffectElement = (Element) cardEffectElements.item(x);
                        
                        // Add to Hashmap
                        Map<String, Object> effect = new HashMap<String, Object>();
                        effect.put("type", (Object) EffectType.valueOf(cardEffectElement.getAttribute("type")));
                        effect.put("effectValue", (Object) Integer.parseInt(cardEffectElement.getAttribute("effectvalue")));
                        
                        Set<String> effectedCards = new HashSet<>();

                    
                        // For-loop through XML file. First ColorRef, then CardRef
                        NodeList effectElements;
                        try {
                            effectElements = cardEffectElement.getElementsByTagName("CardColorRef");
                            if (effectElements != null)
                                for (int y=0; y<effectElements.getLength(); y++) {
                                    Element effectElement = (Element) effectElements.item(y);
                                    effectedCards.add(effectElement.getTextContent());
                                }
                        } catch (Exception e) {
                            System.out.println(e); // If there is no CardColorRef available
                        }
                        try {  
                            effectElements = cardEffectElement.getElementsByTagName("CardRef");
                            if (effectElements != null)
                                for (int y=0; y<effectElements.getLength(); y++) {
                                    Element effectElement = (Element) effectElements.item(y);
                                    effectedCards.add(effectElement.getTextContent());
                                }
                        } catch (Exception e) {
                            System.out.println(e); // If there is no CardRef available
                        }

                        if (effectedCards.isEmpty()) {
                            throw new GameConfigurationException("effectNotAssignet");
                        }

                        effect.put("selector", effectedCards);
                        cardEffects.add(effect);
                    }

                    card.put("effects", cardEffects);

                    // Add to the card-to-effect map
                    effects.add(card);
                    

                }
            } catch (Exception e) {
                throw new GameConfigurationException("cardColors not loading", e);
            }

            // Assign card effects
            
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
            
            // Reads and appends each line to the StringBuilder.
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");  // add line break
            }

            String fileContents = stringBuilder.toString();
            return this.parse(fileContents);
        } catch (IOException e) {
            throw new IOException("FileNotFound", e);
        }  
    }
}
