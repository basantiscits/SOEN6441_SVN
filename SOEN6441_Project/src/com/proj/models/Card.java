package com.proj.models;

import java.util.Observable;
import java.util.Random;

/**
 * Attack Controller Class
 * @author Basant
 * @since 11 Mar 2019
 * @version 1.1
 */
public class Card extends Observable {

	private int cardId;
	private Type typeOfCard;
	static int num = 0;
	private static Random random;
	
	/**
	 * Constructor of Card class
	 * @param cardId card id
	 * @param typeOfCard type of card
	 */
	public Card(int cardId, Type typeOfCard) {
		super();
		this.cardId = cardId;
		this.typeOfCard = typeOfCard;
		updateChanges();
	}

	public enum Type {
		INFANTRY, CAVALRY, ARTILLERY;

	}

	/**
	 * getter for card id
	 * @return the cardId
	 */
	public int getCardId() {
		return cardId;
	}

	/**
	 * setter for card id
	 * @param cardId the cardId to set
	 */
	public void setCardId(int cardId) {
		this.cardId = cardId;
		updateChanges();
	}

	/**
	 * getter for type of card
	 * @return the typeOfCard
	 */
	public Type getTypeOfCard() {
		return typeOfCard;
	}

	/**
	 * setter for type of card
	 * @param typeOfCard the typeOfCard to set
	 */
	public void setTypeOfCard(Type typeOfCard) {
		this.typeOfCard = typeOfCard;
		updateChanges();
	}
	
	/**
	 * getter for new card
	 * @return Object of type Card
	 */
	public static Card getNewCard() {	
		random = new Random();
		int randomNum = random.nextInt((3 - 1) + 1) + 1;
		return new Card(++num, randomNum == 1 ? Type.INFANTRY : randomNum == 2 ? Type.CAVALRY : Type.ARTILLERY);
	}
		
	/**
	 * Update changes
	 */
	public void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
}
