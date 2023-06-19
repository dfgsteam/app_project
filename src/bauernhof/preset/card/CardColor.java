package bauernhof.preset.card;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * A cardcolor.
 */
public class CardColor {

	/**
	 * @hidden
	 */
	protected final String name;
	/**
	 * @hidden
	 */
	protected final Color color;

	/**
	 * Create a {@link CardColor}.
	 *
	 * @param name The unique name of the cardcolor.
	 * @param color The color of the cardcolor. See {@link #decodeColor(String)}.
	 *
	 * @throws NumberFormatException See {@link #decodeColor(String)}.
	 */
	public CardColor(String name, String color) throws NumberFormatException {
		this(name, decodeColor(color));
	}
	/**
	 * Create a {@link CardColor}.
	 *
	 * @param name The unique name of the cardcolor.
	 * @param color The color of the cardcolor.
	 */
	public CardColor(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	/**
	 * Helper function to decode a color from a string.
	 *
	 * <p>
	 * The color string will first be interpreted as field name in {@link Color}. (e.g. MAGENTA, orange,...)<br>
	 * If that doesn't work decoding the color string using {@link Color#decode(String)} is tried (e.g. #0055DD).
	 * </p>
	 * @param color The color string.
	 * @return The decoded color.
	 * @throws NumberFormatException See {@link Color#decode(String)}.
	 *
	 */
	public static Color decodeColor(String color) throws NumberFormatException {
		try {
			// Try getting the color from the field name. e.g. MAGENTA, orange, etc...
			Field field = Color.class.getField(color);
			return (Color)field.get(null);
		} catch (Exception e) {
			// Color was not a field color
			return Color.decode(color); // try interpreting it as a 24bit number e.g. #0055DD
		}

	}

	/**
	 * Get the color of the cardcolor.
	 * @return The color.
	 */
	public Color getAWTColor() {
		return color;
	}

	/**
	 * Get the name of the cardcolor.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}


}
