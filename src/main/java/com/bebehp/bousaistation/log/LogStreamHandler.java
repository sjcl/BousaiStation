package com.bebehp.bousaistation.log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class LogStreamHandler extends StreamHandler {

	private void configure() {
		setFormatter(new SimpleFormatter());
		try {
			setEncoding("UTF-8");
		} catch (final IOException e) {
			try {
				setEncoding(null);
			} catch (final IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public LogStreamHandler(final OutputStream os) {
		super();
		configure();
		setOutputStream(os);
	}

	@Override
	public void publish(final LogRecord record) {
		super.publish(record);
		flush();
	}

	@Override
	public void close() {
		flush();
	}
}
