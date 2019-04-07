package com.proj.models;

public interface BehaviorStrategies  {
	
	
	public void startUpPhase(GameModelCreation gameModel);
	
	public void reinforcementPhase(GameModelCreation gameModel);
	
	public void attackPhase(GameModelCreation gameModel);
	
	public void fortificationPhase(GameModelCreation gameModel);
	
	
}
