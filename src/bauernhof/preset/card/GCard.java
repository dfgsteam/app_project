package bauernhof.preset.card;

import java.awt.Color;
import java.awt.geom.Point2D.Float;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import bauernhof.preset.Logger;
import sag.LayerPosition;
import sag.elements.GElement;
import sag.elements.GGroup;
import sag.elements.GSVG;
import sag.elements.GText;
import sag.elements.GText.TextAnchor;
import sag.elements.Linecap;
import sag.elements.shapes.GCircle;
import sag.elements.shapes.GRect;

/**
 * A drawable card.
 *
 * <p style="color:#3c763d;background-color:#dff0d8;border-color:#d6e9c6;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- SUCCESS -->
 * Can be drawn by creating a layer using {@link sag.SAGPanel#addLayer(LayerPosition)} and adding to it (or a {@link GGroup} child of that layer) the {@link GCard} via {@link GGroup#addChild(sag.elements.Drawable)}.<br>
 * Further information can be found in the <a href="https://app.pages.gwdg.de/sag/doc/">SAG documentation</a> or in the <a href="https://gitlab.gwdg.de/app/sag/-/blob/main/README.md">README of the SAG Repo</a>.
 * </p>
 */
public class GCard extends GGroup {
	
	/**
	 * @hidden
	 */
	protected static final float scale = 4f;
	/**
	 * Width of a {@link GCard}.
	 */
	public static final float WIDTH = 63 * scale;
	/**
	 * Height of a {@link GCard}.
	 */
	public static final float HEIGHT = 88 * scale;
	/**
	 * Background color of a {@link GCard}.
	 */
	public static final Color bgcolor = CardColor.decodeColor("#dac5a8");


	/**
	 * @hidden
	 */
	protected final Card card;

	/**
	 * @hidden
	 */
	protected GGroup raw = new GGroup();
	/**
	 * @hidden
	 */
	protected GElement mainbg = null;

	/**
	 * Construct a {@link GCard}.
	 *
	 * @param card The card to display.
	 */
	public GCard(Card card) {
		super();
		this.card = card;
		addChild(raw);
		initCard();
		//setStroke(Color.MAGENTA);
	}

	/**
	 * Get the card that is being displayed.
	 *
	 * @return The card.
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * @hidden
	 */
	protected void initCard() {
		float ccwidth = WIDTH/6f;

		mainbg = new GRect(0, 0, WIDTH, HEIGHT, false, 10, 10).setFill(bgcolor); // main background
		raw.addChild(mainbg); // main background
		raw.addChild(new GRect(0, 1.0f, ccwidth*2, HEIGHT*0.999f, false, 10, 10).setFill(card.getColor().getAWTColor())); // cardcolor
		raw.addChild(new GRect(ccwidth, 0, WIDTH-ccwidth, HEIGHT, false, 10, 10).setFill(bgcolor)); // main background
		raw.addChild(new GRect(ccwidth, 0, ccwidth * 2, HEIGHT, false).setFill(bgcolor)); // main background

		raw.addChild(new GText(card.getColor().getName()).setAlignment(TextAnchor.MIDDLE).setFontSize(ccwidth / 2f).setFill(Color.WHITE).rotate(-90).move(ccwidth- ccwidth/3f,HEIGHT*0.4f)); // cardcolor
		
		// image
		//raw.addChild(new GRect(ccwidth + 5f, ccwidth - 5f, WIDTH-ccwidth-10f, HEIGHT*0.4f, false, 10, 10).setFill(CardColor.decodeColor("#ddddff")));
		raw.addChild(new GRect(ccwidth + 5f, ccwidth - 5f, WIDTH-ccwidth-10f, HEIGHT*0.4f, false, 10, 10).setFill(CardColor.decodeColor("#6ab8f7")));
		raw.addChild(loadPresetImage(card.getImage()), ccwidth + 5f + (WIDTH -ccwidth-10f)/2f, ccwidth -5f + (HEIGHT*0.4f)/2f);

		// card name
		raw.addChild(new GRect(ccwidth+5f, 5f, WIDTH-ccwidth-10f, ccwidth, false, 10, 10).setFill(CardColor.decodeColor("#9f3424")));
		raw.addChild(new GText(card.getName()).setAlignment(TextAnchor.MIDDLE).setFontSize(ccwidth / 2f).setFill(Color.WHITE).move(ccwidth + 5f + (WIDTH-ccwidth-10f)/2f,ccwidth/1.5f+5f));

		// base value
		raw.addChild(new GCircle(ccwidth*0.66f+5f, 5f+ccwidth*0.66f, ccwidth*0.66f).setFill(card.getColor().getAWTColor().darker()).setStroke(Color.ORANGE, 5f));
		String basevalue = card.getBaseValue() < 0 ? String.valueOf(card.getBaseValue()) : "+" + String.valueOf(card.getBaseValue());
		raw.addChild(new GText(basevalue).setFontSize(ccwidth / 2f).setBold(true).setAlignment(TextAnchor.MIDDLE).move(ccwidth*0.66f+5f, 5f+ccwidth*0.66f).move(0,ccwidth/6f).setFill(Color.WHITE));
		//raw.addChild(new GText("-30 mit Schere").setAlignment(TextAnchor.MIDDLE).setFontSize(14f).setFill(Color.BLACK).move(ccwidth + 5f + (WIDTH-ccwidth-10f)/2f, HEIGHT-textboxheight-5f + 25f));

		// effect text
		float textboxheight = HEIGHT - (ccwidth - 5f + HEIGHT*0.4f) - 10f;
		raw.addChild(new GRect(ccwidth + 5f, HEIGHT-textboxheight-5f, WIDTH-ccwidth-10f,  textboxheight, false, 10, 10).setFill(Color.WHITE));
		raw.addChild(new GText("Effect text").setAlignment(TextAnchor.MIDDLE).setFontSize(14f).setFill(Color.BLACK).move(ccwidth + 5f + (WIDTH-ccwidth-10f)/2f, HEIGHT-textboxheight-5f + 25f));
		raw.addChild(new GText("coming soon...").setAlignment(TextAnchor.MIDDLE).setFontSize(14f).setFill(Color.BLACK).move(ccwidth + 5f + (WIDTH-ccwidth-10f)/2f, HEIGHT-textboxheight-5f + 25f + 20f));
		// TODO

		//raw.addChild(new GCircle(WIDTH/2f, HEIGHT/2f,10).setFill(Color.BLUE));

		float endscale = 0.75f;
		raw.move(-(WIDTH*endscale)/2f, -(HEIGHT*endscale)/2f);
		raw.scale(endscale);
		setStroke(Color.BLACK);
		setStrokeWidth(5f);
	}

	/**
	 * Loads an image from the preset.
	 *
	 * @param imagename The name of the image.
	 *
	 * @return Either the loaded image, the logo of the preset or a red circle.
	 */
	public static GElement loadPresetImage(String imagename) {
		URL url = GCard.class.getResource("/graphics/" + imagename + ".svg");
		if (url == null) {
			Logger.warn("Couldn't load preset image: " + imagename);
			url = GCard.class.getResource("/graphics/logo.svg");
		}
		if (url != null) {
			try {
				GElement res = null;
				res = new GSVG(url);
				float scale = 0.25f;
				float w = 512f * scale;
				float h = 512f * scale;
				res.scale(scale);
				res.move(-w/2f, -h/2f);
				return res;
			} catch (IOException e) {
				Logger.error("Couldn't load preset image '"+ imagename+"' but it was found. Please contact Jake! ", e);
				return new GCircle(64).setFill(Color.RED);
			}
		}
		Logger.error("Couldn't load preset logo. Please contact Jake!");
		return new GCircle(64).setFill(Color.RED);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement setStroke(Color c) {
		return mainbg.setStroke(c);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement setStroke(Color c, float width) {
		return mainbg.setStroke(c, width);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement setStroke(String s) {
		return mainbg.setStroke(s);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement setStrokeWidth(float width) {
		return mainbg.setStrokeWidth(width);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement setStrokeLinecap(Linecap linecap) {
		return mainbg.setStrokeLinecap(linecap);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement setStrokeOpacity(float opacity) {
		return mainbg.setStrokeOpacity(opacity);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement setStrokePattern(List<Float> arg0) {
		return mainbg.setStrokePattern(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement setStrokePatternOffset(float offset) {
		return mainbg.setStrokePatternOffset(offset);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement unsetStroke() {
		return mainbg.unsetStroke();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement unsetStrokeWidth() {
		return mainbg.unsetStrokeWidth();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement unsetStrokeLinecap() {
		return mainbg.unsetStrokeLinecap();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement unsetStrokeOpacity() {
		return mainbg.unsetStrokeOpacity();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement unsetStrokePattern() {
		return mainbg.unsetStrokePattern();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GElement unsetStrokePatternOffset() {
		return mainbg.unsetStrokePatternOffset();
	}

}

