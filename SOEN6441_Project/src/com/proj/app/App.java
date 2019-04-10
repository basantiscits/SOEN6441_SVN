package com.proj.app;
import com.proj.views.*;
/**
 * Main Menu Screen Class
 * @author Basant
 * @since 28 Jan 2019
 * @version 1.0
 */
public class App {
	/** 
	 * Creates main menu screen object. 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		System.setProperty("sun.io.serialization.extendedDebugInfo", "true");
		MainMenuScreen screen = new MainMenuScreen();
		screen.setVisible(true);	
	}
}
