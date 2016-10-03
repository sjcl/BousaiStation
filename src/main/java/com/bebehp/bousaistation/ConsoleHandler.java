package com.bebehp.bousaistation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class ConsoleHandler extends StreamHandler {

	private void configure() {
		setFormatter(new SimpleFormatter());
		try {
			setEncoding("UTF-8");
		} catch (final IOException ex) {
			try {
				setEncoding(null);
			} catch (final IOException ex2) {
				// doing a setEncoding with null should always work.
				// assert false;
				ex2.printStackTrace();
			}
		}
	}

	public ConsoleHandler(final OutputStream os) {
		super();
		configure();
		setOutputStream(os);
	}

	@Override public void publish(final LogRecord record) {
		super.publish(record);
		flush();
	}
	@Override public void close() {
		flush();
	}
}
