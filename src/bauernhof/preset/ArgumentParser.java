package bauernhof.preset;


import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import sag.SAGInfo;

/**
 * Parse arguments from the command line and store them as settings.
 *
 *
 * @see bauernhof.preset.Settings
 */
public class ArgumentParser extends Settings {

	/**
	 * How many arguments an option has.
	 * <p style="color:#8a6d3b;background-color:#fcf8e3;border-color:#faebcc;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- WARNING -->
	 * This is only relevant if you intend to add your own custom options.<br>
	 * Otherwise ignore this enum.
	 * </p>
	 * @see ArgumentParser#addOption(String, String, String, ArgAmount, String, Class, OptionalFeature, Handler)
	 */
	protected enum ArgAmount {
		/**
		 * Option has no further arguments.
		 */
		NONE,
		/**
		 * Option has one further arguments.
		 */
		ONE,
		/**
		 * Option has up to unlimited further arguments.
		 */
		UNLIMITED
	}
	
	/**
	 * Abstract class for a handler of an option.
	 * <p style="color:#8a6d3b;background-color:#fcf8e3;border-color:#faebcc;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- WARNING -->
	 * This is only relevant if you intend to add your own custom options.<br>
	 * Otherwise ignore this class.
	 * </p>
	 * @see ArgumentParser#addOption(String, String, String, ArgAmount, String, Class, OptionalFeature, Handler)
	 */
	protected abstract class Handler {
		public abstract void handle(CommandLine line, Option opt, List<String> values) throws ParseException;
		public void notHandled(CommandLine line, Option opt) throws ParseException {};
	}

	/**
	 * @hidden
	 */
	protected CommandLine line;
	/**
	 * @hidden
	 */
	protected final Options options = new Options();

	/**
	 * @hidden
	 */
	protected String javaCommandLine = null;

	/**
	 * The header text shown when printing the full help message.
	 *
	 * @see #showHelp()
	 */
	public final ArrayList<String> helpHeader = new ArrayList<String>();
	/**
	 * The footer text shown when printing the full help message.
	 *
	 * @see #showHelp()
	 */
	public final ArrayList<String> helpFooter = new ArrayList<String>();

	/**
	 * @hidden
	 */
	protected String[] args;

	/**
	 * @hidden
	 */
	protected LinkedHashMap<Option, Handler> handlers = new LinkedHashMap<Option, Handler>();

	/**
	 * Create a new ArgumentParser instance and parse the given arguments immediately.
	 *
	 * @param args the command line arguments given by <code>main(String[] args)</code>
	 * @param projectName the name of your project
	 * @param projectVersion the version of your project
	 * @param projectAuthors the authors of your project
	 * @param implementedOptionalFeatures the optional features you have implemented (value can also be null, if no optional feature is implemented)
	 */
	public ArgumentParser(String[] args, String projectName, String projectVersion, List<String> projectAuthors, List<OptionalFeature> implementedOptionalFeatures) {
		this(args, projectName, projectVersion, projectAuthors, implementedOptionalFeatures, true);
	}

	/**
	 * Create a new ArgumentParser instance.
	 *
	 * @param args the command line arguments given by <code>main(String[] args)</code>
	 * @param projectName the name of your project
	 * @param projectVersion the version of your project
	 * @param projectAuthors the authors of your project
	 * @param implementedOptionalFeatures the optional features you have implemented (value can also be null, if no optional feature is implemented)
	 * @param parseImmediately whether {@link #parse()} should be called immediately
	 *
	 *
	 */
	public ArgumentParser(String[] args, String projectName, String projectVersion, List<String> projectAuthors, List<OptionalFeature> implementedOptionalFeatures, boolean parseImmediately) {
		super();
		this.args = args;
		if (implementedOptionalFeatures == null)
			implementedOptionalFeatures = new ArrayList<OptionalFeature>();
		this.implementedOptionalFeatures = implementedOptionalFeatures;

		// help header
		helpHeader.add("");
		helpHeader.add("=========================================================================");
		String headertext = projectName + " <"+ projectVersion +">";
		int offset = (73/2) - (headertext.length()/2);
		if (offset < 0)
			offset = 0;
		helpHeader.add(" ".repeat(offset) + headertext);
		helpHeader.add("  Authors: " + String.join(", ", projectAuthors));
		helpHeader.add("=========================================================================");
		helpHeader.add("");
		helpHeader.add("Options:");

		// help footer
		helpFooter.add("");
		try {
			PresetInfo pi = new PresetInfo();
			helpFooter.add("Preset: " + pi.getVersion() + " ("+pi.getBuildDate().toString()+")");
			if (!pi.isRelease()) {
				helpFooter.add("  ACHTUNG: Dies ist kein Release vom Preset!");
				helpFooter.add("           Gemäß ORGANISATION.md muss der aktuellste Release");
				helpFooter.add("           vom Preset verwendet werden.");
			}
		} catch (IOException e) {
			helpFooter.add("ACHTUNG: Fehler beim Lesen der Preset Jar Datei: " + e.getMessage());
			helpFooter.add("");
		}
		try {
			SAGInfo si = new SAGInfo();
			helpFooter.add("SAG: " + si.getVersion() + " ("+si.getBuildDate().toString()+")");
			if (!si.isRelease()) {
				helpFooter.add("  ACHTUNG: Dies ist kein Release von SAG!");
			}
		} catch (IOException e) {
			helpFooter.add("ACHTUNG: Fehler beim Lesen der SAG Jar Datei: " + e.getMessage());
			helpFooter.add("");
		}
		if (!implementedOptionalFeatures.isEmpty()) {
			//helpFooter.add("");
			helpFooter.add("Implemented optional features: ");
			for (OptionalFeature of : implementedOptionalFeatures) {
				helpFooter.add("  - " + of.toString());
			}
		}


		// options
		addOptions();

		// parse
		if (parseImmediately) {
			parse();
		}

	}

	/**
	 * Get the raw parsed command line arguments.
	 *
	 * @return the raw parsed command line arguments
	 */
	public CommandLine getRawLine() {
		return line;
	}


	/**
	 * Get the raw options.
	 *
	 * @return the raw options
	 */
	public Options getRawOptions() {
		return options;
	}

	/**
	 * Adds a new option.
	 *
	 * <p>
	 * This is only relevant if you intend to add your own custom options.<br>
	 * Otherwise ignore this function.
	 * </p>
	 *
	 * <p>
	 * This function should be called inside of {@link #addOptions()}.
	 * </p>
	 *
	 * @param longOpt the long name (e.g.: playerNames)
	 * @param shortOpt the short name (e.g.: pn)
	 * @param desc the description
	 * @param argAmount the amount of arguments this option handles
	 * @param argName the name of the arguments to this option
	 * @param argType the type of the arguments to this option
	 * @param req the optional feature implementing this option, or null if it is always there
	 * @param handler is called when the argument is found on the command line
	 *
	 */
	protected void addOption(String longOpt, String shortOpt, String desc, ArgAmount argAmount, String argName, Class<?> argType, OptionalFeature req, Handler handler) {
		if (req != null && !implementedOptionalFeatures.contains(req))
			return; // the optional feature is not implemented

		Option.Builder opt = Option.builder(shortOpt);
		opt.longOpt(longOpt);

		opt.required(false); // otherwise --help isn't working

		// add description
		if (req != null)
			desc = desc + " ("+req.toString()+")";
		opt.desc(desc);


		// add arguments to option, if necessary
		switch (argAmount) {
			case NONE:
				opt.hasArg(false);
				break;
			case ONE:
				opt.hasArg(true);
				break;
			case UNLIMITED:
				opt.hasArgs();
				break;
		}
		if (argAmount != ArgAmount.NONE) {
			opt.optionalArg(false);
			opt.argName(argName);
			opt.type(argType);
		}

		// build and strore option
		Option option = opt.build();
		handlers.put(option, handler);
		options.addOption(option);
	}

	/**
	 * Adds the options.
	 *
	 * <p>
	 * This is only relevant if you intend to add your own custom options.<br>
	 * Otherwise ignore this function.
	 * </p>
	 *
	 * <p>
	 * Override this, if you want to add new options.<br>
	 * Don't forget to call <code>super.addOptions()</code> to add the old options.
	 * </p>
	 * <p>
	 * Options are handled in the order in which they are added.
	 * </p>
	 *
	 */
	protected void addOptions() {
		addOption("help", "h", "Print this help message and some extra information about this program.", ArgAmount.NONE, null, null, null, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				showHelp(true);
				System.exit(0);
			}
		});

		addOption("config", "c", "The file from which the game configuration should be read.", ArgAmount.ONE, "FILE", File.class, null, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				gameConfigurationFile = new File(values.get(0));
			}
		});

		ArrayList<PlayerType> supportedPlayerTypes = new ArrayList<PlayerType>();
		supportedPlayerTypes.add(PlayerType.HUMAN);
		supportedPlayerTypes.add(PlayerType.RANDOM_AI);
		supportedPlayerTypes.add(PlayerType.REMOTE);
		if (implementedOptionalFeatures.contains(OptionalFeature.SIMPLE_AI))
			supportedPlayerTypes.add(PlayerType.SIMPLE_AI);
		if (implementedOptionalFeatures.contains(OptionalFeature.ADVANCED_AI))
			supportedPlayerTypes.add(PlayerType.ADVANCED_AI);
		String supPlayerTypes = "";
		boolean first = true;
		for (PlayerType pt : supportedPlayerTypes) {
			if (first)
				first = false;
			else
				supPlayerTypes += ", ";
			supPlayerTypes += pt.toString();
		}
		addOption("playerTypes", "pt", "The type(s) of the player(s). ["+supPlayerTypes+"]", ArgAmount.UNLIMITED, "TYPE ...", PlayerType.class, null, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				playerTypes = new ArrayList<>(values.size());
				for (String s : values) {
					s = s.toUpperCase();
					PlayerType pt = PlayerType.valueOf(s);
					if (!supportedPlayerTypes.contains(pt))
						throw new IllegalArgumentException("The player type '"+ s + "' is not supported.");
					playerTypes.add(pt);
				}
			}
		});

		addOption("playerNames", "pn", "The name(s) of the player(s).", ArgAmount.UNLIMITED, "NAME ...", String.class, null, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				playerNames = new ArrayList<>(values.size());
				for (String s : values) {
					playerNames.add(s);
				}
			}
		});

		addOption("playerColors", "pc", "The color(s) of the player(s). [RED, BLUE, ...]", ArgAmount.UNLIMITED, "COLOR ...", Color.class, null, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				playerColors = new ArrayList<>(values.size());
				for (String s : values) {
					try {
						Field field = Class.forName("java.awt.Color").getField(s);
						Color c = (Color)field.get(null);
						playerColors.add(c);
					} catch (NoSuchFieldException e) {
						throw new ParseException("Color not found: " + s);
					} catch (IllegalAccessException | ClassNotFoundException e) {
						e.printStackTrace();
						throw new ParseException("Weird color caused exceptions: " + s);
					}
				}
			}
		});

		addOption("delay", "d", "Delay in milliseconds after a computerplayer has made his move.", ArgAmount.ONE, "DELAY", Long.class, null, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				try {
					delay = Long.parseLong(values.get(0));
				} catch (NumberFormatException e) {
					throw new ParseException("DELAY must be a valid number.");
				}
				if (delay < 0l)
					throw new ParseException("Delay cannot be negative.");
			}
		});

		addOption("gui", "g", "Show the GUI, even if no HUMAN player exists.", ArgAmount.NONE, null, null, null, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				showGUI = true;
			}
			public void notHandled(CommandLine line, Option opt) throws ParseException {
				showGUI = false;
			}
		});

		String loglevels = "";
		first = true;
		for (LogLevel ll : LogLevel.values()) {
			if (first)
				first = false;
			else
				loglevels += ", ";
			loglevels += ll.toString();
		}
		addOption("loglevel", "ll", "The maximum log level. ["+loglevels+"]", ArgAmount.ONE, "LEVEL", LogLevel.class, null, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				logLevel = LogLevel.valueOf(values.get(0).toUpperCase());
			}
		});


		// NETWORKING
		addOption("connect", "con", "Connect as a client to the host.", ArgAmount.ONE, "HOST", String.class, null, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				connectToHostname = values.get(0);
			}
		});
		addOption("port", "p", "The port to be used when either hosting the game as a server or conntecting to a server as a client.", ArgAmount.ONE, "PORT", Integer.class, null, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				try {
					port = Integer.valueOf(values.get(0));
				} catch (NumberFormatException e) {
					throw new ParseException("PORT must be a valid number.");
				}
				if (port <= 0)
					throw new ParseException("Negative ports do not exist.");
			}
		});


		// SAVEGAMES
		addOption("loadsave", "load", "Load a save game.", ArgAmount.ONE, "FILE", File.class, OptionalFeature.SAVEGAMES, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				loadSaveGameFile = new File(values.get(0));
			}
		});

		// TOURNAMENTS
		addOption("tournament", "t", "Play a tournament.", ArgAmount.ONE, "ROUNDS", Integer.class, OptionalFeature.TOURNAMENTS, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				try {
					numTournamentRounds = Integer.valueOf(values.get(0));
				} catch (NumberFormatException e) {
					throw new ParseException("ROUNDS must be a valid number.");
				}
				if (numTournamentRounds <= 0)
					throw new ParseException("A tournament must have at least one round.");
			}
		});
		addOption("tournamentwait", "tw", "Wait for a user interaction before starting the next game in a tournament.", ArgAmount.NONE, null, null, OptionalFeature.TOURNAMENTS, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				waitAfterTournamentRound = true;
			}
			public void notHandled(CommandLine line, Option opt) throws ParseException {
				waitAfterTournamentRound = false;
			}
		});

		// LAUNCHER
		addOption("launcher", "l", "Launch the launcher.", ArgAmount.NONE, null, null, OptionalFeature.LAUNCHER, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				shouldLauncherLaunch = true;
			}
			public void notHandled(CommandLine line, Option opt) throws ParseException {
				shouldLauncherLaunch = false;
			}
		});

		// SOUNDEFFECT
		addOption("volume", "v", "Volume of the soundeffects. (0-100)", ArgAmount.ONE, "VOLUME", Integer.class, OptionalFeature.SOUNDEFFECTS, new Handler() {
			public void handle(CommandLine line, Option opt, List<String> values) throws ParseException {
				try {
					volume = Integer.valueOf(values.get(0));
				} catch (NumberFormatException e) {
					throw new ParseException("VOLUME must be a valid number.");
				}
				if (volume < 0 || volume > 100)
					throw new ParseException("Volume must be in range from 0 to 100, including 0 and 100. 0 means soundeffects are disabled.");
			}
			public void notHandled(CommandLine line, Option opt) throws ParseException {
				volume = 0; // Default value must be 0 !!!
			}
		});


	}

	/**
	 * Parse the command line arguments.
	 */
	public void parse() {
		CommandLineParser parser = new DefaultParser();
		try {
			line = parser.parse(options, args);

			for (Option option : handlers.keySet()) {
				Handler handler = handlers.get(option);
				//System.out.println(option.toString());
				String opt = option.getOpt();

				if (line.hasOption(opt)) {
					List<String> values = new ArrayList<String>();
					if (line.getOptionValues(opt) != null)
						values = Arrays.asList(line.getOptionValues(opt));
					handler.handle(line, option, values);
				} else {
					handler.notHandled(line, option);
				}

			}
		} catch (ParseException e) {
			System.err.println("Error: " + e.getMessage());
			System.err.println();
			showHelp(false);
			//throw e;
			System.exit(1);
		}

		// set showGUI to true, if at least one HUMAN player exists
		if (playerTypes.contains(PlayerType.HUMAN))
			showGUI = true;
	}


	/**
	 * Print the help text.
	 *
	 * @param full whether to print the full print text with some extra information, or just the minimal usage text with options
	 */
	public void showHelp(boolean full) {
		HelpFormatter formatter = new HelpFormatter();
		String nl = formatter.getNewLine();

		if (full) {
			// convert helpHeader and helpFooter to strings
			String hs = String.join(nl, helpHeader);
			String fs = String.join(nl, helpFooter);

			// print help
			formatter.printHelp(getJavaCommandLine(), hs, getRawOptions(), fs, true);
		} else {
			// print help
			formatter.printHelp(getJavaCommandLine(), getRawOptions(), true);
		}
	}

	/**
	 * Print the help text with extra information.
	 *
	 */
	public void showHelp() {
		showHelp(true);
	}


	/**
	 * Overwrite what {@link #getJavaCommandLine()} returns.
	 *
	 * @param javaCommandLine the new value
	 */
	public void overwriteJavaCommandLine(String javaCommandLine) {
		this.javaCommandLine = javaCommandLine;
	}


	/**
	 * Get the "java command line" used for starting the program without arguments.
	 *
	 * <p>
	 * This might return something like "java -jar &lt;YOUR-JAR-FILE&gt;".
	 * </p>
	 *
	 * @return the java command line without arguments
	 *
	 * @see #overwriteJavaCommandLine(java.lang.String)
	 */
	public String getJavaCommandLine() {
		if (this.javaCommandLine != null)
			return this.javaCommandLine;
		ProcessHandle.Info info = ProcessHandle.current().info();
		try {
			//String command = info.command().get();
			String command = "java";
			String[] javaarguments = info.arguments().get();
			javaarguments = Arrays.copyOf(javaarguments, javaarguments.length - args.length); // remove args
			overwriteJavaCommandLine(command + " " + String.join(" ", javaarguments)); // store java commandline
			return this.javaCommandLine;
		} catch (NoSuchElementException e) {
			return "java -jar <YOUR-JAR-FILE>";
		}
	}

}


