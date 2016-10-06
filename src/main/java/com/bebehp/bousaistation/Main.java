package com.bebehp.bousaistation;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main extends JFrame {

	public static void main(final String[] args) {
		final JFrame frame = new Main(Reference.NAME + " " + Reference.VERSION);
		final BousaiStation bousaiStation = new BousaiStation(frame);

		BousaiStation.DATA_DIR = OperatingSystem.getCurrentPlatform().getWorkingDirectory("BousaiStation");
		BousaiStation.OS_NAME = System.getProperty("os.name");
		BousaiStation.OS_VERSION = System.getProperty("os.version");
		BousaiStation.OS_ARCH = System.getProperty("os.arch");
		BousaiStation.JAVA_VERSION = System.getProperty("java.version");
		BousaiStation.JAVA_VENDOR = System.getProperty("java.vendor");
		BousaiStation.SUN_ARCH_DATA_MODEL = System.getProperty("sun.arch.data.model");
		BousaiStation.JAVA_SPECIFICATION_VESION = System.getProperty("java.specification.version");
		bousaiStation.preInit();
		bousaiStation.init();
	}

	public Main(final String title) {
		setTitle(title);
		setSize(855, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

}
