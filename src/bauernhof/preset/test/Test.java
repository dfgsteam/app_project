package bauernhof.preset.test;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import bauernhof.preset.ArgumentParser;
import bauernhof.preset.LogLevel;
import bauernhof.preset.Logger;
import bauernhof.preset.OptionalFeature;
import bauernhof.preset.Settings;
import bauernhof.preset.card.Card;
import bauernhof.preset.card.CardColor;
import bauernhof.preset.card.Effect;
import bauernhof.preset.card.GCard;
import sag.LayerPosition;
import sag.SAGFrame;
import sag.SAGPanel;
import sag.elements.GElement;
import sag.elements.GGroup;
import sag.elements.shapes.GCircle;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventAdapter;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

public class Test {
	
	public static void main(String[] args) {
		Settings settings = new ArgumentParser(args, "Test", "v0.0.1", Arrays.asList(new String[]{"Jake"}), Arrays.asList(new OptionalFeature[]{}));
		//Logger.setMaxLogLevel(settings.logLevel);
		Logger.setMaxLogLevel(LogLevel.DEBUG);
		System.out.println("========================================");

                SAGFrame frame = new SAGFrame("Test",30,1280,720);// Create the window with a 30 FPS limit and a preferred size of 1280x720 pixel.
                SAGPanel panel = new SAGPanel(1600,900);            // Create the panel used for drawing everything with a viewport size of 1600x900 units(not pixels!).
                frame.setSAGPanel(panel);                           // Set the panel to be automatically drawn on the window.
                frame.setVisible(true);                             // Make the window visible.

                GGroup main = panel.addLayer(LayerPosition.CENTER);
                //GGroup main = panel.addLayer(LayerPosition.CENTER_LEFT);

                GCircle mycircle = new GCircle(50);                 // Create a circle with radius of 50 units.
                mycircle.setFill(Color.GREEN);                      // Set the color of the circle.

                main.addChild(mycircle);                            // Add the circle to the main layer and thus make it visible.


		Card card = new Card() {
			public String getName() {
				return "General Flocke";
			}
			public int getBaseValue() {
				return 13;
			}

			public CardColor getColor() {
				//return new CardColor("Tiere", "#221188");
				//return new CardColor("Nahrungsmittel", "#221188");
				return new CardColor("Tiere", Color.PINK);
			}

			public String getImage() {
				return "logo";
			}
			public Set<Effect> getEffects() {
				Set<Effect> res = new HashSet<>();
				// TODO
				return res;
			}
		};



		MouseEventAdapter adapter = new MouseEventAdapter() {
			GElement prevraised = null;
			@Override
			public void mouseMoved(MouseMotionEvent event, GElement self) {
				Logger.debug("Moved: " + self.getPositionX());
				if (prevraised != null) {
					prevraised.move(0, 80);
				}
				self.move(0, -80);
				prevraised = self;
				event.setHandled();
			}
			@Override
			public void mouseExited(MouseMotionEvent event, GElement self) {
				if (prevraised == self) {
					prevraised.move(0, 80);
					prevraised = null;
				}
				event.setHandled();
			}
			@Override
			public void mouseClicked(MouseButtonEvent event, GElement self) {
				switch (event.getMouseButton()) {
					case LEFT: self.rotate(-10); break;
					case RIGHT: self.rotate(10); break;
					case MIDDLE: self.setRotation(0); break;
					case UNKNOWN: break;
				}
				event.setHandled();
			}
			@Override
			public void mouseWheelMoved(MouseWheelEvent event, GElement self) {
				self.skew(event.getWheelRotation()*2, 0);
			}
		};
		for (int i = -6; i <= 5; i++) {
			//float x = GCard.WIDTH * (1.1f* (float)i) + GCard.WIDTH/2;
			float x = GCard.WIDTH * (0.4f* (float)i) + GCard.WIDTH/2;
			System.out.println(x);
			main.addChild(new GCard(card).setMouseEventListener(adapter), x, GCard.HEIGHT);
		}
		main.addChild(new GCard(card).setMouseEventListener(adapter), 0, -GCard.HEIGHT/2);

		//main.addChild(new GCard(card).setMouseEventListener(adapter).scale(0.3f), 0, 0);


	}

}
