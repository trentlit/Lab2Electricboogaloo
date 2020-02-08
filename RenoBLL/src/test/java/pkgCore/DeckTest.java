package pkgCore;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pkgException.DeckException;

public class DeckTest {

	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 *        Deck_Create_Test - Test creating a deck
	 */
	@Test
	public void Deck_Create_Test() {
		Deck d = new Deck();
		ArrayList<Card> cards = this.getCards(d);
		assertEquals(52, cards.size());
		assertEquals(52, d.getiDeckCount());

		try {
			Card c = d.Draw();
		} catch (DeckException e) {
			fail("draw failed");
		}
		cards = this.getCards(d);
		assertEquals(51, cards.size());
	}

	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 *        getCards - getCardsInDeck is private. It must be tested.
	 */
	private ArrayList<Card> getCards(Deck d) {
		ArrayList<Card> cards = new ArrayList<Card>();
		try {

			// Consume the Deck class
			Class<?> c = Class.forName("pkgCore.Deck");
			// Find the getCardsInDeck method
			Method mGetCardsInDeck = c.getDeclaredMethod("getCardsInDeck", null);
			// Set the method accessible
			mGetCardsInDeck.setAccessible(true);
			// Invoke the method, return the results
			cards = (ArrayList<Card>) mGetCardsInDeck.invoke(d, null);

		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// Return the results
		return cards;
	}

	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 *        Deck_Overdraw_Test - Create a deck and force an overdraw to test the exception is thrown.
	 */	
	@Test
	public void Deck_Overdraw_Test() {
		Assertions.assertThrows(DeckException.class, () -> {
			Deck d = new Deck();
			for (int i = 0; i < 60; i++) {
				Card c = d.Draw();
			}
		});

	}

}
