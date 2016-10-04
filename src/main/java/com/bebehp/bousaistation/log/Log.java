package com.bebehp.bousaistation.log;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

public class Log {

	public static Logger logger = Logger.getLogger("BousaiStation");

	public static void init(final LogOutput console) {
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

	public static void fatal(final Object... obj) {
		fatal(StringUtils.join(obj));
	}

	public static void warn(final String msg) {
		logger.log(Level.WARNING, msg);
	}

	public static void warn(final Object... obj) {
		warn(StringUtils.join(obj));
	}

	public static void info(final String msg) {
		logger.log(Level.INFO, msg);
	}

	public static void info(final Object... obj) {
		info(StringUtils.join(obj));
	}

	public static void debug(final String msg) {
		logger.log(Level.FINER, msg);
	}

	public static void debug(final Object... obj) {
		debug(StringUtils.join(obj));
	}

	public static void trace(final String msg) {
		logger.log(Level.FINE, msg);
	}

	public static void trace(final Object... obj) {
		trace(StringUtils.join(obj));
	}

	public static void line() {
		System.out.println("");
	}
}
