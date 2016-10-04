package com.bebehp.bousaistation;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.bebehp.bousaistation.log.Log;

public class Main extends JFrame {

	public static void main(final String[] args) {
		final JFrame frame = new Main("Kamesuta");
		final BousaiStation bousaiStation = new BousaiStation(frame);

		bousaiStation.preInit();
		for (int i = 0; i < 100; i++) {
			Log.info("BUBUBUBUBUBUBUBUBUBUBUBUBUBUBU!");
			Log.info("BSC24!!!!!!!");
		}
	}

	public Main(final String title) {
		setTitle(title);
		setSize(855, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//		frame.setSize(900, 580);
	}

}
