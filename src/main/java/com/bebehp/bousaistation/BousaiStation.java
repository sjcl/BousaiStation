package com.bebehp.bousaistation;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.bebehp.bousaistation.log.JLogOutput;
import com.bebehp.bousaistation.log.Log;

public class BousaiStation {
	private static BousaiStation station;
	private final JFrame frame;

	public BousaiStation(final JFrame frame) {
		station = this;
		this.frame = frame;
	}

	public JFrame getFrame() {
		return this.frame;
	}

	public void preInit() {
		final JTextArea area = new JTextArea();
		area.setEditable(false);
		area.setFont(new Font(Font.SERIF, Font.PLAIN, 12));
		Log.init(new JLogOutput(area));

		final JScrollPane scrollpane = new JScrollPane(area,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.frame.getContentPane().add(scrollpane, BorderLayout.CENTER);
		this.frame.setVisible(true);
	}

	public void init() {

	}

	public void postInit() {

	}
}
