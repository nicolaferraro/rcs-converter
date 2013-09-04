// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.rcs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.rcs.convert.ConversionType;
import org.rcs.convert.RcsFileConverter;

public class RcsFrame extends JFrame {

	public RcsFrame() {
		setup();
		setControls();
	}

	protected void setup() {
		setTitle(UIConstants.FRAME_TITLE);
		setSize(UIConstants.FRAME_WIDTH, UIConstants.FRAME_HEIGHT);
		setResizable(false);
		decodeBtn = new JButton(UIConstants.FRAME_DECODE_CAPTION);
		encodeBtn = new JButton(UIConstants.FRAME_ENCODE_CAPTION);
		JPanel jpanel = new JPanel();
		jpanel.add(decodeBtn);
		jpanel.add(encodeBtn);
		setContentPane(jpanel);
	}

	protected void setControls() {
		decodeBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				doConversion(ConversionType.DECODE);
			}

		});
		encodeBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				doConversion(ConversionType.ENCODE);
			}

		});
	}

	protected void doConversion(ConversionType conversiontype) {
		try {

			File inFile = chooseSourceFile(conversiontype);
			if (inFile == null)
				return;
			File outFile = chooseDestinationFile(inFile, conversiontype);
			if (outFile == null)
				return;

			RcsFileConverter rcsfileconverter = new RcsFileConverter();
			rcsfileconverter.convert(inFile, outFile, conversiontype);
			JOptionPane.showMessageDialog(this, UIConstants.FRAME_COMPLETION_MESSAGE, UIConstants.FRAME_COMPLETION_TITLE, JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(this, UIConstants.FRAME_ERROR_MESSAGE, UIConstants.FRAME_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
		}
		return;
	}

	protected File chooseSourceFile(ConversionType conversiontype) {
		if (fileChooser == null)
			fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			return file;
		} else {
			return null;
		}
	}

	protected File chooseDestinationFile(File file, ConversionType conversiontype) throws IOException {

		File folder = file.getParentFile();
		String baseFileName = file.getName();
		if (baseFileName.toLowerCase().endsWith(conversiontype.getSourceFileExtension()))
			baseFileName = baseFileName.substring(0, baseFileName.length() - conversiontype.getSourceFileExtension().length());
		String destFileName = baseFileName + conversiontype.getDestFileExtension().toString();
		File destFile = new File(folder, destFileName);

		int i = 1;
		while (destFile.exists()) {

			String newName = baseFileName + " (" + (i++) + ")" + conversiontype.getDestFileExtension();
			destFile = new File(folder, newName);
		}
		destFile.createNewFile();
		return destFile;
	}

	private static final long serialVersionUID = 1L;
	protected JButton decodeBtn;
	protected JButton encodeBtn;
	protected JFileChooser fileChooser;
}
