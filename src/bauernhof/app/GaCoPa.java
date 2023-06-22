//import bauernhof.preset;
package bauernhof.app;
import bauernhof.preset.*;
import bauernhof.preset.card.*;
import bauernhof.app.card.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import java.util.*;


class GaCoPa implements GameConfigurationParser{
    public static void main(String args[]) {
        try {
            // XML-Datei einlesen
            File inputFile = new File("cards.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            // Root-Element erhalten
            Element root = document.getDocumentElement();
            System.out.println("Root-Element: " + root.getNodeName());

            // Description-Element erhalten
            Element descriptionElement = (Element) root.getElementsByTagName("Description").item(0);
            String description = descriptionElement.getTextContent();
            System.out.println("Beschreibung: " + description);

            // NumCardsPerPlayerHand-Element erhalten
            Element numCardsPerPlayerHandElement = (Element) root.getElementsByTagName("NumCardsPerPlayerHand").item(0);
            String numCardsPerPlayerHand = numCardsPerPlayerHandElement.getTextContent();
            System.out.println("NumCardsPerPlayerHand: " + numCardsPerPlayerHand);

            // NumCardsPerPlayerHand-Element erhalten
            Element numDepositionAreaSlotsElement = (Element) root.getElementsByTagName("NumDepositionAreaSlots").item(0);
            String numDepositionAreaSlots = numDepositionAreaSlotsElement.getTextContent();
            System.out.println("numDepositionAreaSlots: " + numDepositionAreaSlots);

            // CardColors erhalten
            NodeList cardColorElements = root.getElementsByTagName("CardColor");
            System.out.println("cardColors: " + cardColorElements.getLength());
            Map<String, CardColor> cardColors = new HashMap<String, CardColor>();

            for (int i=0; i<cardColorElements.getLength(); i++) {
                Element cardColorElement = (Element) cardColorElements.item(i);
                //System.out.println("cardColor: " + cardColorElement.getTextContent() + " " + cardColorElement.getAttribute("color"));

                //-> Erst Karten, dann Effekte und mit einem NEUEN Setter in Karte hinzufügen
        
                if (!cardColors.containsKey(cardColorElement.getTextContent())) {
                    cardColors.put(cardColorElement.getTextContent(), new CardColor(cardColorElement.getTextContent(), cardColorElement.getAttribute("color")));
                } else {
                    System.out.println("CardColor duplicate");
                    System.exit(0);
                }
            }

            // Card erhalten
            NodeList cardsElements = root.getElementsByTagName("Card");
            System.out.println("cards: " + cardsElements.getLength());


            Set<Map<String, Object>> effects = new HashSet<Map<String, Object>>();
            Map<String, Ca> cards = new HashMap<String, Ca>();


            for (int i=0; i<cardsElements.getLength(); i++) {
                Element cardElement = (Element) cardsElements.item(i);
                System.out.println("card name: " + cardElement.getAttribute("name") + " " + cardColors.get(cardElement.getAttribute("color")).getAWTColor());
                
                // check, ob karte ist doppelt
                if (cards.containsKey(cardElement.getAttribute("name"))) {
                    System.out.println("Kartenname ist doppelt!");
                    System.exit(0);
                } 

                // Erstelle Karten Map
                Map<String, Object> card = new HashMap<String, Object>();
                
                // Erstelle Karten Objekt und füge es zur Karten-Map hinzu 
                Ca cardObject = new Ca(cardElement.getAttribute("name"), Integer.parseInt(cardElement.getAttribute("basevalue")), cardColors.get(cardElement.getAttribute("color")), cardElement.getAttribute("image"), null);
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
                        System.out.println(e);
                    }
                    try {  
                        effectElements = cardEffectElement.getElementsByTagName("CardRef");
                        if (effectElements != null)
                            for (int y=0; y<effectElements.getLength(); y++) {
                                Element effectElement = (Element) effectElements.item(y);
                                effectedCards.add(effectElement.getTextContent());
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    //System.out.println(effectedCards);

                    if (effectedCards.isEmpty()) {
                        System.out.println("ERROR: Effekt sind keine Karten zugeorndet!");
                        System.exit(1);
                    }

                    effect.put("selector", effectedCards);
                    cardEffects.add(effect);
                }

                card.put("effects", cardEffects);

                // für Karte zu Effekt Map hinzu
                effects.add(card);
                

            }
            System.out.println(effects);

            // Weise Karten Effekte zu
            for (var card : effects) {
                System.out.println(card);
                for (Map<String, Object> effect : (Set<Map<String, Object>>) card.get("effects")) {
                    Set<Either<Ca,CardColor>> selector = new HashSet<>();
                    for (String effectCard : (Set<String>)effect.get("selector")) {
                        Either<Ca,CardColor> inp = null;
                        Ca inpCard = cards.get(effectCard);
                        if (inpCard != null) {
                            inp = new Either<>(inpCard, null);
                        } else {
                            try {
                                inp = new Either<>(null, cardColors.get(effectCard));
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                        selector.add(inp);
                    }
                    BeEf EffectObject = new Ef((EffectType) effect.get("type"), (int) effect.get("effectValue"), selector);
                    Ca cardObject = (Ca) card.get("object");
                    cardObject.addEffect(EffectObject);
                }

            }









        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameConfiguration parse(String filecontents) throws GameConfigurationException {return null;}

    public GameConfiguration parse(File file) throws GameConfigurationException, IOException {return null;}
}
