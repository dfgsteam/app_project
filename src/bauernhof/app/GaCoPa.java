//import bauernhof.preset;
package bauernhof.app;
import bauernhof.preset.*;
import bauernhof.preset.card.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import java.util.*;


class CaGoPa implements GameConfigurationParser{
    public static void main(String args[]) {
        try {
            // XML-Datei einlesen
            File inputFile = new File("../../../config/cards.xml");
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

            for (int i=0; i<cardsElements.getLength(); i++) {
                Element cardElement = (Element) cardsElements.item(i);
                System.out.println("card name: " + cardElement.getAttribute("name") + " " + cardColors.get(cardElement.getAttribute("color")).getAWTColor());
            }











        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameConfiguration parse(String filecontents) throws GameConfigurationException {return null;}

    public GameConfiguration parse(File file) throws GameConfigurationException, IOException {return null;}
}
