package com.proj.utilites;

import java.io.Serializable;

/**
 * The class of all constants
 * @author Arpit
 * @since 30 Jan 2019
 * @version 1.0
 */
public class Constants implements Serializable{

	private static final long serialVersionUID = 45443434343L;
	/** 
	 * The Constant WIDTH. 
	 */
	public static final int WIDTH = 640;

	/** 
	 * The Constant HEIGHT.
	 */
	public static final int HEIGHT = WIDTH / 12 * 9;

	/** 
	 * The Constant MAP_EDITOR_WIDTH. 
	 */
	public static final int MAP_EDITOR_WIDTH = 1200;

	/** 
	 * The Constant MAP_EDITOR_HEIGHT. 
	 */
	public static final int MAP_EDITOR_HEIGHT = MAP_EDITOR_WIDTH / 12 * 11;
}
