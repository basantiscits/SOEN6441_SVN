package com.proj.views;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.proj.utilites.Constants;

// this code is regarding Load existing Map editor

public class LoadMapEditor extends JFrame {
	String sFileName = "";
	String sLocationWhereFileisKept = "";
	String sAppendParam = "";
	String sFinalString = "";

	public LoadMapEditor() {
		setTitle("Import/Load Map Editor");
		setResizable(false);
		setSize(Constants.WIDTH, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		sFinalString = ImportFileName(sAppendParam);

		if (sFinalString.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "File name invalid");
		}
	}

	private String ImportFileName(String sReturnFileAndLoc) {

		try {
			
			String ImportFileName;
			JFileChooser chooser;
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("./save"));
			chooser.setDialogTitle("Choose saved game file");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.map", "map"));

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				// get the path of the file.
				ImportFileName = chooser.getSelectedFile().getAbsolutePath();
				// if the path of the selected file is empty.
				if (ImportFileName.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "File name invalid");
				} else {
					if(ImportFileName.trim().substring(ImportFileName.length() - 4).equals(".map"))
					{
						// hand over to Aman
						File f = new File(ImportFileName);
						sFileName = f.getName();
						sLocationWhereFileisKept = ImportFileName;
						sAppendParam = sFileName + "^" + sLocationWhereFileisKept;
						JOptionPane.showMessageDialog(null, "File in Correct format");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "File name invalid");
						String sPrint=ImportFileName.trim().substring(ImportFileName.length() - 4);
						System.out.println(sPrint);
					}
					
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return sAppendParam;

	}

}
