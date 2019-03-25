package com.proj.models;

import java.util.Observable;
import java.util.Random;

public class Card extends Observable{

	private int cardId;

	private Type typeOfCard;

	public Card(int cardId, Type typeOfCard) {
		super();
		this.cardId = cardId;
		this.typeOfCard = typeOfCard;
	}

	public enum Type {
		INFANTRY, CAVALRY, ARTILLERY;

	}

	/**
	 * @return the cardId
	 */
	public int getCardId() {
		return cardId;
	}

	/**
	 * @param cardId the cardId to set
	 */
	public void setCardId(int cardId) {
		this.cardId = cardId;
		updateChanges();
	}

	/**
	 * @return the typeOfCard
	 */
	public Type getTypeOfCard() {
		return typeOfCard;
	}

	/**
	 * @param typeOfCard the typeOfCard to set
	 */
	public void setTypeOfCard(Type typeOfCard) {
		this.typeOfCard = typeOfCard;
		updateChanges();
	}

	static int num = 0;
	private static Random random;

	public static Card getNewCard() {

		random = new Random();
		int randomNum = random.nextInt((3 - 1) + 1) + 1;

		return new Card(++num, randomNum == 1 ? Type.INFANTRY : randomNum == 2 ? Type.CAVALRY : Type.ARTILLERY);

	}
	
	public void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
	

}
