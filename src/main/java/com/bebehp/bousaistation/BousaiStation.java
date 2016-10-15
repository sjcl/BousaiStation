package com.bebehp.bousaistation;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import com.bebehp.bousaistation.deploader.DepLoader;
import com.bebehp.bousaistation.log.Log;
import com.bebehp.bousaistation.log.LogOutput;

public class BousaiStation {
	private static BousaiStation station;
	private final JFrame frame;
	private final JScrollPane logArea;

	public static File DATA_DIR;
	public static String OS_NAME;
	public static String OS_VERSION;
	public static String OS_ARCH;
	public static String JAVA_VERSION;
	public static String JAVA_VENDOR;
	public static String SUN_ARCH_DATA_MODEL;
	public static String JAVA_SPECIFICATION_VESION;

	public BousaiStation(final JFrame frame) {
		station = this;
		this.frame = frame;
		this.logArea = createLogArea();
	}

	public JFrame getFrame() {
		return this.frame;
	}

	public void preInit() {
		this.frame.getContentPane().add(this.logArea, BorderLayout.CENTER);
		this.frame.setVisible(true);

		System.out.println("Welcome to " + Reference.NAME);
		final SimpleDateFormat format = new SimpleDateFormat("MMM d, YYYY K:mm:ss aaaa", Locale.US);
		System.out.println("Current time is " + format.format(new Date()));
		System.out.println("System.getProperty('os.name') == '" + OS_NAME + "'");
		System.out.println("System.getProperty('os.version') == '" + OS_VERSION + "'");
		System.out.println("System.getProperty('os.arch') == '" + OS_ARCH + "'");
		System.out.println("System.getProperty('java.version') == '" + JAVA_VERSION + "'");
		System.out.println("System.getProperty('java.vendor') == '" + JAVA_VENDOR + "'");
		System.out.println("System.getProperty('sun.arch.data.model') == '" + SUN_ARCH_DATA_MODEL + "'");
		Log.line();

		if (!DATA_DIR.exists())
			if (DATA_DIR.mkdirs())
				System.out.println("mkdir: " + DATA_DIR);

		System.out.println("Check Dependencies");
		if (!DepLoader.INSTANCE.readCSV().checkDep().depDLTaskListIsEmpty()) {
			Log.line();
			DepLoader.INSTANCE.download();
		} else {
			System.out.println("Dependencies will all present");
		}

		if (!DepLoader.INSTANCE.depListIsEmpty()) {
			Log.line();
			DepLoader.INSTANCE.addClassPath();
		}

		Log.line();
		System.out.println("Starting " + Reference.NAME);

		try {
			Thread.sleep(2000L);
		} catch (final InterruptedException e) {
			Log.warn(e);
		}

		this.frame.remove(this.logArea);
	}

	public void init() {
		this.frame.setSize(900, 580);
		this.frame.setLocationRelativeTo(null);
		this.frame.setTitle(Reference.NAME);

	}

	public void postInit() {

	}

	public JScrollPane createLogArea() {
		final JTextArea area = new JTextArea();
		area.setEditable(false);
		area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		final DefaultCaret caret = (DefaultCaret) area.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		final JScrollPane scrollpane = new JScrollPane(area,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setBorder(new EmptyBorder(0, 0, 0, 0));
		Log.init(new LogOutput(area));
		return scrollpane;
	}
}
