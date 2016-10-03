package com.bebehp.bousaistation;

import java.awt.BorderLayout;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

public class BousaiStation {
	public static Logger logger = Logger.getLogger(BousaiStation.class.getName());

	public static void main(final String[] args) {
		final JTextArea area = new JTextArea();
		area.setEditable(false);
		final JScrollPane scrollpane = new JScrollPane(area,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		final JConsole stream = new JConsole(area);
		System.setOut(new PrintStream(stream, true));
		final ConsoleHandler handler = new ConsoleHandler(new PrintStream(stream, true));
		try {
			handler.setEncoding("UTF-8");
		} catch (SecurityException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		logger.addHandler(handler);

		final JFrame frame = new JFrame();
		frame.getContentPane().add(scrollpane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(16*32, 18*20+2);
		frame.setVisible(true);

		for (int i = 0; i < 100; i++)
			logger.log(Level.INFO, "BUBUBUBUBUBUBUBUBUBUBUBUBUBUBU!");
	}

}
