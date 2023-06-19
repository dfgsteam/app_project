package bauernhof.preset;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * A simple logger for creating fancy log outputs.
 *
 * <p style="color:#8a6d3b;background-color:#fcf8e3;border-color:#faebcc;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- WARNING -->
 * One of the first things your program should do is to initialize this logger using {@link #setMaxLogLevel(LogLevel)}.
 * </p>
 *
 * <p style="color:#31708f;background-color:#d9edf7;border-color:#bce8f1;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- INFO -->
 * You can log messages using the many debug, info, warn, and error functions.<br>
 * The output format and degree of information will depend on the maximum log level set via {@link #setMaxLogLevel(LogLevel)}.<br>
 * <br>
 * The verbosity of the log levels is as follows: DEBUG &gt; INFO &gt; WARNINGS &gt; ERRORS.
 * </p>
 *
 * <p style="color:#3c763d;background-color:#dff0d8;border-color:#d6e9c6;padding:1em;margin-bottom:1.2em;border:0.1em solid transparent;border-radius:0.25em;"> <!-- SUCCESS -->
 * Log messages may contain the name of the current thread and the calling class which makes debugging multithreaded applications easier.
 * </p>
 *
 */
public class Logger {
	/**
	 * @hidden
	 */
	protected static LogLevel maxLogLevel = LogLevel.DEBUG;
	/**
	 * @hidden
	 */
	protected static PrintStream outstream = System.out;
	/**
	 * @hidden
	 */
	protected static PrintStream errstream = System.err;
	/**
	 * @hidden
	 */
	protected static boolean colorsupport = true;

	private Logger() {}

	public static void debug(String text) { log(3, LogLevel.DEBUG,    text); }
	public static void info (String text) { log(3, LogLevel.INFO,     text); }
	public static void warn (String text) { log(3, LogLevel.WARNINGS, text); }
	public static void error(String text) { log(3, LogLevel.ERRORS,   text); }

	public static void debug(List<String> lines) { log(3, LogLevel.DEBUG,    lines); }
	public static void info (List<String> lines) { log(3, LogLevel.INFO,     lines); }
	public static void warn (List<String> lines) { log(3, LogLevel.WARNINGS, lines); }
	public static void error(List<String> lines) { log(3, LogLevel.ERRORS,   lines); }

	public static void debug(String text, List<String> lines) { log(3, LogLevel.DEBUG,    text ,lines); }
	public static void info (String text, List<String> lines) { log(3, LogLevel.INFO,     text ,lines); }
	public static void warn (String text, List<String> lines) { log(3, LogLevel.WARNINGS, text ,lines); }
	public static void error(String text, List<String> lines) { log(3, LogLevel.ERRORS,   text ,lines); }

	public static void debug(String[] lines) { log(3, LogLevel.DEBUG,    Arrays.asList(lines)); }
	public static void info (String[] lines) { log(3, LogLevel.INFO,     Arrays.asList(lines)); }
	public static void warn (String[] lines) { log(3, LogLevel.WARNINGS, Arrays.asList(lines)); }
	public static void error(String[] lines) { log(3, LogLevel.ERRORS,   Arrays.asList(lines)); }

	public static void debug(String text, String[] lines) { log(3, LogLevel.DEBUG,    text ,Arrays.asList(lines)); }
	public static void info (String text, String[] lines) { log(3, LogLevel.INFO,     text ,Arrays.asList(lines)); }
	public static void warn (String text, String[] lines) { log(3, LogLevel.WARNINGS, text ,Arrays.asList(lines)); }
	public static void error(String text, String[] lines) { log(3, LogLevel.ERRORS,   text ,Arrays.asList(lines)); }

	public static void debug(String text, Throwable e) { logThrowable(3, LogLevel.DEBUG,    text, e); }
	public static void info (String text, Throwable e) { logThrowable(3, LogLevel.INFO,     text, e); }
	public static void warn (String text, Throwable e) { logThrowable(3, LogLevel.WARNINGS, text, e); }
	public static void error(String text, Throwable e) { logThrowable(3, LogLevel.ERRORS,   text, e); }

	public static void debug(Throwable e) { logThrowable(3, LogLevel.DEBUG,    null, e); }
	public static void info (Throwable e) { logThrowable(3, LogLevel.INFO,     null, e); }
	public static void warn (Throwable e) { logThrowable(3, LogLevel.WARNINGS, null, e); }
	public static void error(Throwable e) { logThrowable(3, LogLevel.ERRORS,   null, e); }

	//protected static SimpleDateFormat timeformat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	/**
	 * @hidden
	 */
	protected static SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");

	/**
	 * @hidden
	 */
	protected static final String RESETCOLOR = (char)27 + "[0m";

	/**
	 * @hidden
	 * Log a throwable.
	 *
	 * @param callerlevel The number of functions between the initial log function and the actual internal log function.
	 * @param ll The log level to use.
	 * @param text The text.
	 * @param e The throwable.
	 *
	 */
	protected static void logThrowable(int callerlevel, LogLevel ll, String text, Throwable e) {
		logThrowable(callerlevel+1,ll,text,e,(maxLogLevel != LogLevel.DEBUG));
	}
	/**
	 * @hidden
	 * Log a throwable.
	 *
	 * @param callerlevel The number of functions between the initial log function and the actual internal log function.
	 * @param ll The log level to use.
	 * @param text The text.
	 * @param e The throwable.
	 * @param simplifyStackTrace Whether the stack trace should be simplified.
	 */
	protected static void logThrowable(int callerlevel, LogLevel ll, String text, Throwable e, boolean simplifyStackTrace) {
		if (!shouldLog(ll))
			return;
		if (e == null) {
			log(callerlevel+1,ll,text);
			return;
		}

		ArrayList<String> lines = new ArrayList<String>();

		// ser first line
		if (text != null) {
			lines.add(text);
		} else {
			lines.add(e.getMessage());
			//lines.add(e.toString());
		}

		// read stacktrace into lines
		if (simplifyStackTrace) {
			writeSimplifiedStackTrace(lines, e, false);
		} else {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			sw.flush();
			String trace = sw.toString();
			String tracelines[] = trace.split("\\r?\\n");
			for (String line : tracelines)
				lines.add(line);
		}

		log(callerlevel+1, ll, lines);
	}

	/**
	 * @hidden
	 * Add the throwable to the lines.
	 *
	 * @param lines The lines to add to.
	 * @param e The throwable or null.
	 * @param isCause Whether this is the initial throwable or a cause of that throwable.
	 */
	protected static void writeSimplifiedStackTrace(List<String> lines, Throwable e, boolean isCause) {
		if (e == null)
			return;
		lines.add((isCause ? "Caused by: " : "") + e.toString());
		writeSimplifiedStackTrace(lines, e.getCause(), true);
	}

	/**
	 * @hidden
	 * Log some text.
	 *
	 * @param callerlevel The number of functions between the initial log function and the actual internal log function.
	 * @param ll The log level to use.
	 * @param text The text.
	 */
	protected static void log(int callerlevel, LogLevel ll, String text) {
		if (text == null)
			return;
		log(callerlevel+1,ll, Arrays.asList(new String[] {text}));
	}

	/**
	 * @hidden
	 * Log some text.
	 *
	 * @param callerlevel The number of functions between the initial log function and the actual internal log function.
	 * @param ll The log level to use.
	 * @param text The text.
	 * @param lines Further text as lines.
	 */
	protected static void log(int callerlevel, LogLevel ll, String text, List<String> lines) {
		List<String> res = new ArrayList<String>();
		if (text != null)
			res.add(text);
		if (lines != null)
			res.addAll(lines);

		log(callerlevel+1,ll, res);
	}

	/**
	 * @hidden
	 * Log some text.
	 *
	 * @param callerlevel The number of functions between the initial log function and the actual internal log function.
	 * @param ll The log level to use.
	 * @param lines The text as lines.
	 */
	protected synchronized static void log(int callerlevel, LogLevel ll, List<String> lines) {
		if (!shouldLog(ll))
			return;
		if (lines == null || lines.size() < 1)
			return;
		
		// get thread
		Thread thread = Thread.currentThread();
		String threadname = thread.getName();
		boolean ismainthread = thread.getName().equalsIgnoreCase("main");

		// get caller
		StackTraceElement[] stacktrace = thread.getStackTrace();
		StackTraceElement caller = stacktrace[callerlevel];

		// get time
		String t = timeformat.format(new Date());

		// get color
		String c = colorsupport ? getColor(ll) : "";

		// get loglevel name
		String n = getLogLevelName(ll);

		// get output stream
		PrintStream out;
		if (ll == LogLevel.INFO) {
			out = outstream;
		} else {
			out = errstream;
		}


		// get iterator over lines
		Iterator<String> it = lines.iterator();


		// build prefix of first line
		String pre = (colorsupport ? RESETCOLOR : "");
		pre += "["+t+"]";
		pre += c + " [";
		if (!ismainthread)
			pre += threadname + "/";
		if (maxLogLevel == LogLevel.DEBUG) {
			pre += caller.getClassName() +"#"+ caller.getMethodName();
			int ln = caller.getLineNumber();
 			if (ln >= 0)
				pre += ":" + ln;
			pre += "/";
		}
		pre += n + "]";
		pre += (colorsupport ? RESETCOLOR : "") + ": ";

		// print first line
		String message = it.next();
		out.println(pre + message);
		out.flush();

		// print other lines
		while (it.hasNext()) {
			String line = it.next();
			out.println(RESETCOLOR + c  + "           | " + RESETCOLOR + line);
			out.flush();
		}
		// print empty line after multiple lines
		if (lines.size() > 2)
			out.println();
	
		// flush output
		out.flush();
	}


	/**
	 * @hidden
	 * Get the ansi escape sequence for the color of a specific log level.
	 *
	 * @param ll The log level.
	 *
	 * @return The ansi escape sequence of the color.
	 */
	protected static String getColor(LogLevel ll) {
		switch (ll) {
			case DEBUG:   return (char)27 + "[35m";
			case INFO:    return (char)27 + "[34m";
			case WARNINGS:return (char)27 + "[33m";
			case ERRORS:  return (char)27 + "[31m";
			default:      return "";
		}
	}

	/**
	 * @hidden
	 * Get the printable name of a log level.
	 *
	 * @param ll The log level.
	 *
	 * @return The name of the log level.
	 */
	protected static String getLogLevelName(LogLevel ll) {
		switch (ll) {
			case DEBUG:   return "DEBUG";
			case INFO:    return "INFO";
			case WARNINGS:return "WARN";
			case ERRORS:  return "ERROR";
			default:      return "";
		}
	}

	/**
	 * @hidden
	 * Determines whether a message with a specific loglevel gets logged.
	 *
	 * @param ll The loglevel to test.
	 * @see #setMaxLogLevel(LogLevel)
	 * @return Whether the message should be logged.
	 */
	@SuppressWarnings("fallthrough")
	protected static boolean shouldLog(LogLevel ll) {
		switch (maxLogLevel) {
			case DEBUG: if (ll == LogLevel.DEBUG) return true;
			case INFO: if (ll == LogLevel.INFO) return true;
			case WARNINGS: if (ll == LogLevel.WARNINGS) return true;
			case ERRORS: if (ll == LogLevel.ERRORS) return true;
			default: return false;	
		}
	}

	/**
	 * Set the maximum {@link LogLevel}.
	 *
	 * <p>
	 * Default: DEBUG
	 * </p>
	 *
	 * @param ll The new maximum loglevel.
	 */
	public static void setMaxLogLevel(LogLevel ll) {
		maxLogLevel = ll;
	}

	/**
	 * Set where log messages should be outputted to.
	 *
	 * <p>
	 * Use this function if you want to write to a log file instead of the output.
	 * </p>
	 *
	 * @param out Where INFO messages should be printed to. Default: <code>System.out</code>
	 * @param err Where other messages should be printed to. Default: <code>System.err</code>
	 * @param colorsupport Whether the messages should be colored. Often unwanted when writing to log files. Default: <code>true</code>
	 */ 
	public static void setPrintStreams(PrintStream out, PrintStream err, boolean colorsupport) {
		Logger.outstream = out;
		Logger.errstream = err;
		Logger.colorsupport = colorsupport;
	}

}
