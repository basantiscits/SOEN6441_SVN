package com.proj.views;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.proj.utilites.Constants;

// this code is regarding Load existing Map editor
public class LoadMapEditor extends JFrame {

	public LoadMapEditor() {
		setTitle("Import/Load Map Editor");
		setResizable(false);
		setSize(Constants.WIDTH, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		ImportFileName();
	}

	private void ImportFileName() {
		// TODO Auto-generated method stub
		String ImportFileName;
		JFileChooser chooser;
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("./save"));
		chooser.setDialogTitle("Choose saved game file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			ImportFileName = chooser.getSelectedFile().getAbsolutePath();
			if (ImportFileName.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "File name invalid");
			} else {
				// boolean isLoaded =
				// redirectGameToGameWindow(LoadMapEditor(ImportFileName.trim()));
			}
		}

	}

}
