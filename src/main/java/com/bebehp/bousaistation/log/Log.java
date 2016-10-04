package com.bebehp.bousaistation.log;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

	public static Logger logger = Logger.getLogger("BousaiStation");

	public static void init(final JLogOutput console) {
		System.setOut(new PrintStream(console, true));
		final LogStreamHandler handler = new LogStreamHandler(new PrintStream(console, true));
		try {
			handler.setEncoding("UTF-8");
		} catch (SecurityException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		logger.addHandler(handler);
	}

	public static void fatal(final String msg) {
		logger.log(Level.SEVERE, msg);
	}

	public static void warn(final String msg) {
		logger.log(Level.WARNING, msg);
	}

	public static void info(final String msg) {
		logger.log(Level.INFO, msg);
	}

	public static void debug(final String msg) {
		logger.log(Level.FINER, msg);
	}

	public static void trace(final String msg) {
		logger.log(Level.FINE, msg);
	}

	public static void line() {
		System.out.println("");
	}
}
