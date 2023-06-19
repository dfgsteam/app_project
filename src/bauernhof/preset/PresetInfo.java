package bauernhof.preset;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;

import java.util.HashMap;
import java.util.Date;
import java.util.Locale;

import java.text.SimpleDateFormat;
import java.text.ParsePosition;

/**
 * Information about the current build of the preset.
 */
public class PresetInfo {

	/*
	 *
	 * preset.txt:
	 *
	 * #Ant properties
	 * #Sun Jul 10 02:35:00 CEST 2022
	 * preset.DSTAMP=20220710
	 * preset.TSTAMP=0234
	 * preset.version=argumentparser
	 * preset.TODAY=July 10 2022
	 * preset.builddate=2022-07-10T02_34_59.266+0200
	 *
	 *
	 */

	final HashMap<String, String> map = new HashMap<String, String>();

	private String presetversion = null;
	private Date builddate = null;

	/**
	 * Constructor.
	 *
	 * @throws IOException failed to read local files in the presets jar file
	 */
	public PresetInfo() throws IOException {
		parseLocalVarFile("/preset.txt", this.map);
	}

	/**
	 * Get the link to the homepage of the preset.
	 * @return <a href="https://gitlab.gwdg.de/app/2023ss/bauernhof-preset">https://gitlab.gwdg.de/app/2023ss/bauernhof-preset</a>
	 */
	public String getHomepage() {
		return "https://gitlab.gwdg.de/app/2023ss/bauernhof-preset";
	}

	/**
	 * Get the build date of the preset.
	 *
	 * @return the build date of the preset
	 */
	public Date getBuildDate() {
		if (builddate == null) {
			// <format property="builddate" pattern="yyyy-MM-dd'T'HH_mm_ss.SSSZ" locale="de,DE"/>
			String bds = map.get("preset.builddate");
			String pattern = "yyyy-MM-dd'T'HH_mm_ss.SSSZ";
			Locale locale = new Locale("de","DE");
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
			builddate = sdf.parse(bds, new ParsePosition(0));
		}
		return builddate;
	}

	/**
	 * Get the build version of the preset.
	 *
	 * Some examples:
	 * <ul>
	 * <li>v1.2.0</li>
	 * <li>main</li>
	 * <li>dev</li>
	 * <li>HEAD</li>
	 * </ul>
	 *
	 *
	 * @return the build version of the preset
	 */
	public String getVersion() {
		if (presetversion == null) {
			presetversion = map.get("preset.version");
		}
		return presetversion;
	}

	/**
	 * Whether this is a release build of the preset.
	 *
	 * @return true if this is a release build
	 */
	public boolean isRelease() {
		// v1.2.0
		return getVersion().startsWith("v") && getVersion().split("\\.").length == 3;
	}


	void parseLocalVarFile(String path, HashMap<String, String> map) throws IOException {
		BufferedReader br = readLocalFile(path);
		String line = null;
		String[] parts = null;
		while ((line = br.readLine()) != null) { // loop over lines
			String key = "";
			String value = "";

			// is line relevant?
			if (line.startsWith("#") || !line.contains("="))
				continue;

			// get parts
			parts = line.split("=");
			if (parts.length < 2)
				continue;

			// get key
			key = parts[0];

			// build value
			for (int i=1; i< parts.length; i++) {
				if (i != 1 && parts.length > 2)
					value = value + "=";
				value = value + parts[i];
			}

			// store key-value-pair
			map.put(key, value);
		}
	}

	BufferedReader readLocalFile(String path) throws IOException { 
		URL url = PresetInfo.class.getResource(path);
		return new BufferedReader(new InputStreamReader(url.openStream()));
	}

}

