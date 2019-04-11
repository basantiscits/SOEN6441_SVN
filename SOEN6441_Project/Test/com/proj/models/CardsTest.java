package com.proj.models;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * CardsTest class
 * @author Aman
 * @since 28/03/2019
 * @version 1.2
 */
public class CardsTest {
	
	/**
	 * get new card test
	 */
	@Test
	public void getNewCardTest() {
		
		Card card1 = Card.getNewCard();
		Card card2 = Card.getNewCard();
		Card card3 = Card.getNewCard();
		
		assertTrue(card1.getTypeOfCard().equals(Card.Type.ARTILLERY) || card1.getTypeOfCard().equals(Card.Type.CAVALRY) || card1.getTypeOfCard().equals(Card.Type.INFANTRY));	
		
		assertTrue(card3.getTypeOfCard().equals(Card.Type.ARTILLERY) || card3.getTypeOfCard().equals(Card.Type.CAVALRY) || card3.getTypeOfCard().equals(Card.Type.INFANTRY));
		
		assertTrue(card2.getTypeOfCard().equals(Card.Type.ARTILLERY) || card2.getTypeOfCard().equals(Card.Type.CAVALRY) || card2.getTypeOfCard().equals(Card.Type.INFANTRY));
			
	}
}
