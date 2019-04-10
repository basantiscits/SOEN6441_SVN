package com.proj.models;

import java.io.Serializable;

/**
 * Human class
 * @author Ofreish
 * @since 25 Mar 2019
 * @version 1.2
 */
public class Human implements BehaviorStrategies, Serializable {
	private static final long serialVersionUID = 45443434343L;


	/**
	 * start up phase
	 * @param gameModel Object of GameModelCreation class
	 */
	@Override
	public void startUpPhase(GameModelCreation gameModel) {
		
	}

	/**
	 * reinforcement phase
	 * @param gameModel Object of GameModelCreation class
	 */
	@Override
	public void reinforcementPhase(GameModelCreation gameModel) {
		
	}

	/**
	 * attack phase
	 * @param gameModel Object of GameModelCreation class
	 */
	@Override
	public void attackPhase(GameModelCreation gameModel) {
		
	}

	/**
	 * fortification phase
	 * @param gameModel Object of GameModelCreation class
	 */
	@Override
	public void fortificationPhase(GameModelCreation gameModel) {
		
	}

}
