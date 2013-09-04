package org.rcs;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import org.rcs.ui.RcsFrame;

public class RcsDecoderUI {

	public RcsDecoderUI() {
	}

	public static void main(String args[]) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception exception) {
		}
		
		RcsFrame rcsframe = new RcsFrame();
		rcsframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension appDimension = rcsframe.getSize();
		int left = (dimension.width - appDimension.width) / 2;
		int top = (dimension.height - appDimension.height) / 2;
		rcsframe.setLocation(left, top);
		rcsframe.setVisible(true);
	}
}
