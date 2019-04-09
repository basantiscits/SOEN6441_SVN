package com.proj.models;

/**
 * BehaviorStrategies interface
 * @author Basant 
 * @since 23 Mar 2019
 * @version 1.2
 */
public interface BehaviorStrategies {
	
	/**
	 * start up phase
	 * @param gameModel Object of GameModelCreation class
	 */
	public void startUpPhase(GameModelCreation gameModel);
	
	/**
	 * Reinforcement phase
	 * @param gameModel Object of GameModelCreation class
	 */
	public void reinforcementPhase(GameModelCreation gameModel);
	
	/**
	 * Attack phase
	 * @param gameModel Object of GameModelCreation class
	 */
	public void attackPhase(GameModelCreation gameModel);
	
	/**
	 * Fortification phase
	 * @param gameModel Object of GameModelCreation class
	 */
	public void fortificationPhase(GameModelCreation gameModel);
	
	
}
