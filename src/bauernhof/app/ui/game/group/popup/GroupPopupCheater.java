package bauernhof.app.ui.game.group.popup;

import java.awt.Color;

import bauernhof.app.ui.game.UiGame;
import bauernhof.app.ui.game.listener.ListenerHomeButton;
import sag.LayerPosition;
import sag.elements.GGroup;
import sag.elements.GText;
import sag.elements.shapes.GRect;

/**
 * This class represents a cheater popup group in the game UI.
 * It extends the GGroup class from the sag.elements package.
 * The popup group is displayed at the center of the main panel and provides information about a detected cheater.
 * The class is designed to be used within the UiGame class.
 * 
 * The GroupPopupCheater class provides the following functionality:
 * - Creates a popup group for displaying information about a detected cheater.
 * - The popup group is added to the main panel of the UiGame instance.
 * - The popup group contains a rectangular area with a red translucent background and a thick black border.
 * - The popup group displays a bold headline text indicating the detection of a cheater.
 * - The popup group displays the name of the cheater.
 * - The popup group contains a button for returning to the main menu.
 * - The button has a yellow translucent background and a thick black border.
 * - The button displays a bold text for indicating the action of returning to the main menu.
 * - The button triggers an event listener when clicked, redirecting to the main menu.
 *
 * @author Julius Hunold
 * @version 1.0
 * @since 2023-07-14
 */

public class GroupPopupCheater extends GGroup {
    
    GGroup panel;

    /**
     * Constructs a new GroupPopupCheater object.
     * The object represents a cheater popup group in the game UI.
     * 
     * @param uiGame The UiGame object that represents the game UI.
     * @param name of the Cheater
     * @throws Exception If an error occurs during initialization.
     */
    public GroupPopupCheater(UiGame uiGame, String name) throws Exception {
        // Panel
        this.panel = uiGame.getMainPanel().addLayer(LayerPosition.CENTER);

        // Feld
        GRect area = new GRect(0f, 0f, 700f, 500f, true, 0f, 0f);
        area.setFill(new Color(255, 0, 0, 70));
        area.setStroke(new Color(0, 0, 0), 5f);
        this.panel.addChild(area, 0f, 0f);

        // Headline
        GText headline = new GText("Cheater erkannt!");
        headline.setBold(true);
        headline.setAlignment(GText.TextAnchor.MIDDLE);
        headline.setFontSize(55f);
        this.panel.addChild(headline, 0f, -150f);

        // Cheaternames
        GText playerName = new GText("\u2620\uFE0F " + name + " \u2620\uFE0F");
        playerName.setAlignment(GText.TextAnchor.MIDDLE);
        playerName.setFontSize(40f);
        this.panel.addChild(playerName, 0f, 0f);

        // Main Menu Button
        GRect homeButton = new GRect(0f, 0f, 450f, 75f, true, 0f, 0f);
        homeButton.setFill(new Color(255, 255, 0, 70));
        homeButton.setStroke(new Color(0, 0, 0), 5f);
        this.panel.addChild(homeButton, 0f, 175f);

        // Main Menu Headline
        GText homeHeadline = new GText("Zurück zum Hauptmenü");
        homeHeadline.setBold(true);
        homeHeadline.setAlignment(GText.TextAnchor.MIDDLE);
        homeHeadline.setFontSize(25f);
        this.panel.addChild(homeHeadline, 0f, 183f);

        homeButton.setMouseEventListener(new ListenerHomeButton(uiGame, homeHeadline));
    }

    public GGroup getPanel() {
        return this.panel;
    }
}