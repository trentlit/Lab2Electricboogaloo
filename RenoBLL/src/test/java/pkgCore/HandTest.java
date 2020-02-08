package pkgCore;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import pkgEnum.eCardNo;
import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.HandException;

public class HandTest {

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 * SetHand - this method will set the cards for a given HandPoker.  Setting the 
	 * cards is a private method, must be executed with reflections
	 * 
	 * @param setCards
	 * @param handpoker
	 * @return
	 */
	private HandPoker SetHand(ArrayList<Card> setCards, HandPoker handpoker) {
		Object t = null;
		try {
			// Load the Class into 'c'
			Class<?> c = Class.forName("pkgCore.HandPoker");
			// Create a new instance 't' from the no-arg Deck constructor
			t = c.newInstance();
			// Load 'msetCardsInHand' with the 'Draw' method (no args);
			Method msetCardsInHand = c.getDeclaredMethod("setCards", new Class[] { ArrayList.class });
			// Change the visibility of 'setCardsInHand' to true *Good Grief!*
			msetCardsInHand.setAccessible(true);
			// invoke 'msetCardsInHand'
			Object oDraw = msetCardsInHand.invoke(t, setCards);

		} catch (ClassNotFoundException x) {
			fail ("Class Not Found Exception Thrown");
		} catch (SecurityException e) {
			fail ("Security Exception Thrown");
		} catch (IllegalArgumentException e) {
			fail ("Illegal Arugment Exception Thrown");
		} catch (InstantiationException e) {
			fail ("Instantiation Excpetion Thrown");
		} catch (IllegalAccessException e) {
			fail ("Illegal Access Exception Thrown");
		} catch (NoSuchMethodException e) {
			fail ("No Such Method Exception Thrown");
		} catch (InvocationTargetException e) {
			fail ("Invocation Target Exception Thrown");
		}
		handpoker = (HandPoker) t;
		return handpoker;
	}

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 * EvaluateHandScoreMethod - The scoring methods in HandPoker are private, they need to 
	 * be executed via reflections
	 * @param hp
	 * @param strHandStrength
	 * @return
	 */
	private boolean EvaluateHandScoreMethod(HandPoker hp, String strHandStrength) {
		Class c = hp.getClass();
		boolean bReturn = false;
		try {
			Method mFreq = c.getDeclaredMethod("Frequency", null);
			mFreq.setAccessible(true);
			mFreq.invoke(hp, null);
			
			Method msetCardsInHand = c.getDeclaredMethod(strHandStrength, null);
			msetCardsInHand.setAccessible(true);
			bReturn = (boolean)msetCardsInHand.invoke(hp, null);

		} catch (NoSuchMethodException | SecurityException e) {
			fail("NoSuchMethod or Security Exception Thrown");
		} catch (IllegalAccessException e) {
			fail ("Illegal Access Exception Thrown");
		} catch (IllegalArgumentException e) {
			fail ("Illegal Argument Exception Thrown");
		} catch (InvocationTargetException e) {
			fail ("Invoication Target Exception Thrown");
		}
		return bReturn;
	}


	@Test
	public void Test_FourOfAKind1() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.KING));
		Collections.sort(FourOfAKind);
		HandPoker h = new HandPoker();
		h = SetHand(FourOfAKind, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isFourOfAKind"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.FourOfAKind, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.ACE, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker card
		assertEquals(HSP.getKickers().get(eCardNo.FIRST.getiCardNo()).geteRank(),
				  eRank.KING);
		
		//	Check the kicker count
		assertEquals(1,HSP.getKickers().size());

	}
	
	@Test
	public void Test_FourOfAKind2() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.KING));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.KING));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.KING));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.KING));
		Collections.sort(FourOfAKind);
		HandPoker h = new HandPoker();
		h = SetHand(FourOfAKind, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isFourOfAKind"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.FourOfAKind, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.KING, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker card
		assertEquals(HSP.getKickers().get(eCardNo.FIRST.getiCardNo()).geteRank(),
				  eRank.ACE);
		
		//	Check the kicker count
		assertEquals(1,HSP.getKickers().size());

	}

	@Test
	public void Test_RoyalFlush() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> RoyalFlush = new ArrayList<Card>();
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.ACE));
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.KING));
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.QUEEN));
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.JACK));
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.TEN));
		Collections.sort(RoyalFlush);
		HandPoker h = new HandPoker();
		h = SetHand(RoyalFlush, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isRoyalFlush"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.RoyalFlush, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.ACE, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertNull(HSP.getKickers());

	}	
	
	@Test
	public void Test_StraightFlush1() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> StraightFlush = new ArrayList<Card>();
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.NINE));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.KING));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.QUEEN));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.JACK));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.TEN));
		Collections.sort(StraightFlush);
		HandPoker h = new HandPoker();
		h = SetHand(StraightFlush, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isStraightFlush"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.StraightFlush, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.KING, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertNull(HSP.getKickers());

	}		
	
	@Test
	public void Test_StraightFlush2() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> StraightFlush = new ArrayList<Card>();
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.ACE));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.TWO));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.THREE));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.FOUR));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.FIVE));
		Collections.sort(StraightFlush);
		HandPoker h = new HandPoker();
		h = SetHand(StraightFlush, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isStraightFlush"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.StraightFlush, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.FIVE, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertNull(HSP.getKickers());

	}		
	
	@Test
	public void Test_FullHouse1() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.KING));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.KING));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		h = SetHand(FullHouse, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isFullHouse"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.FullHouse, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.ACE, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertEquals(eRank.KING, HSP.getLoCard().geteRank());
		
		//	Check the kicker count
		assertNull(HSP.getKickers());

	}
	@Test
	public void Test_FullHouse2() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.KING));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.KING));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.KING));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		h = SetHand(FullHouse, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isFullHouse"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.FullHouse, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.KING, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertEquals(eRank.ACE, HSP.getLoCard().geteRank());
		
		//	Check the kicker count
		assertNull(HSP.getKickers());

	}	
	
	@Test
	public void Test_Flush() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> Flush = new ArrayList<Card>();
		Flush.add(new Card(eSuit.CLUBS, eRank.ACE));
		Flush.add(new Card(eSuit.CLUBS, eRank.TWO));
		Flush.add(new Card(eSuit.CLUBS, eRank.THREE));
		Flush.add(new Card(eSuit.CLUBS, eRank.KING));
		Flush.add(new Card(eSuit.CLUBS, eRank.KING));
		Collections.sort(Flush);
		HandPoker h = new HandPoker();
		h = SetHand(Flush, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isFlush"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.Flush, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.ACE, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertEquals(4, HSP.getKickers().size());
		
		//	Get Kicker 1 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(0).geteSuit());
		//	Get Kicker 1 Rank
		assertEquals(eRank.KING, HSP.getKickers().get(0).geteRank());

		//	Get Kicker 2 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(1).geteSuit());
		//	Get Kicker 2 Rank
		assertEquals(eRank.KING, HSP.getKickers().get(1).geteRank());
		
		//	Get Kicker 3 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(2).geteSuit());
		//	Get Kicker 3 Rank
		assertEquals(eRank.THREE, HSP.getKickers().get(2).geteRank());		
		
		//	Get Kicker 4 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(3).geteSuit());
		//	Get Kicker 4 Rank
		assertEquals(eRank.TWO, HSP.getKickers().get(3).geteRank());			
	}		
 
	@Test
	public void Test_Straight1() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> Straight = new ArrayList<Card>();
		Straight.add(new Card(eSuit.CLUBS, eRank.NINE));
		Straight.add(new Card(eSuit.CLUBS, eRank.KING));
		Straight.add(new Card(eSuit.CLUBS, eRank.QUEEN));
		Straight.add(new Card(eSuit.DIAMONDS, eRank.JACK));
		Straight.add(new Card(eSuit.CLUBS, eRank.TEN));
		Collections.sort(Straight);
		HandPoker h = new HandPoker();
		h = SetHand(Straight, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isStraight"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.Straight, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.KING, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertNull(HSP.getKickers());
	}	
	
	@Test
	public void Test_Straight2() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> Straight = new ArrayList<Card>();
		Straight.add(new Card(eSuit.CLUBS, eRank.ACE));
		Straight.add(new Card(eSuit.CLUBS, eRank.KING));
		Straight.add(new Card(eSuit.CLUBS, eRank.QUEEN));
		Straight.add(new Card(eSuit.DIAMONDS, eRank.JACK));
		Straight.add(new Card(eSuit.CLUBS, eRank.TEN));
		Collections.sort(Straight);
		HandPoker h = new HandPoker();
		h = SetHand(Straight, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isStraight"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.Straight, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.ACE, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertNull(HSP.getKickers());
	}		
	@Test
	public void Test_Straight3() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> Straight = new ArrayList<Card>();
		Straight.add(new Card(eSuit.CLUBS, eRank.ACE));
		Straight.add(new Card(eSuit.CLUBS, eRank.TWO));
		Straight.add(new Card(eSuit.CLUBS, eRank.FOUR));
		Straight.add(new Card(eSuit.DIAMONDS, eRank.THREE));
		Straight.add(new Card(eSuit.CLUBS, eRank.FIVE));
		Collections.sort(Straight);
		HandPoker h = new HandPoker();
		h = SetHand(Straight, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isStraight"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.Straight, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.FIVE, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertNull(HSP.getKickers());
	}		
	
	@Test
	public void Test_ThreeOfAKind1() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.THREE));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		h = SetHand(FullHouse, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isThreeOfAKind"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.ThreeOfAKind, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.ACE, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertEquals(2, HSP.getKickers().size());
		
		//	Get Kicker 1 Suit
		assertEquals(eSuit.DIAMONDS, HSP.getKickers().get(0).geteSuit());
		//	Get Kicker 1 Rank
		assertEquals(eRank.THREE, HSP.getKickers().get(0).geteRank());

		//	Get Kicker 2 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(1).geteSuit());
		//	Get Kicker 2 Rank
		assertEquals(eRank.TWO, HSP.getKickers().get(1).geteRank());

	}
	
	@Test
	public void Test_ThreeOfAKind2() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.KING));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.TWO));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		h = SetHand(FullHouse, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isThreeOfAKind"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.ThreeOfAKind, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.TWO, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertEquals(2, HSP.getKickers().size());
		
		//	Get Kicker 1 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(0).geteSuit());
		//	Get Kicker 1 Rank
		assertEquals(eRank.ACE, HSP.getKickers().get(0).geteRank());

		//	Get Kicker 2 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(1).geteSuit());
		//	Get Kicker 2 Rank
		assertEquals(eRank.KING, HSP.getKickers().get(1).geteRank());

	}
	
	@Test
	public void Test_ThreeOfAKind3() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.FOUR));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		h = SetHand(FullHouse, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isThreeOfAKind"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.ThreeOfAKind, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.THREE, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertEquals(2, HSP.getKickers().size());
		
		//	Get Kicker 1 Suit
		assertEquals(eSuit.DIAMONDS, HSP.getKickers().get(0).geteSuit());
		//	Get Kicker 1 Rank
		assertEquals(eRank.FOUR, HSP.getKickers().get(0).geteRank());

		//	Get Kicker 2 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(1).geteSuit());
		//	Get Kicker 2 Rank
		assertEquals(eRank.TWO, HSP.getKickers().get(1).geteRank());

	}
	
	@Test
	public void Test_TwoPair1() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.FOUR));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		h = SetHand(FullHouse, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isTwoPair"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.TwoPair, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.THREE, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertEquals(eRank.TWO, HSP.getLoCard().geteRank());
		
		//	Check the kicker count
		assertEquals(1, HSP.getKickers().size());
		
		//	Get Kicker 1 Suit
		assertEquals(eSuit.DIAMONDS, HSP.getKickers().get(0).geteSuit());
		//	Get Kicker 1 Rank
		assertEquals(eRank.FOUR, HSP.getKickers().get(0).geteRank());
	}	
	
	@Test
	public void Test_TwoPair2() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.FOUR));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.FOUR));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		h = SetHand(FullHouse, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isTwoPair"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.TwoPair, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.FOUR, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertEquals(eRank.TWO, HSP.getLoCard().geteRank());
		
		//	Check the kicker count
		assertEquals(1, HSP.getKickers().size());
		
		//	Get Kicker 1 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(0).geteSuit());
		//	Get Kicker 1 Rank
		assertEquals(eRank.THREE, HSP.getKickers().get(0).geteRank());
	}		
	
	@Test
	public void Test_TwoPair3() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.FOUR));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.FOUR));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		h = SetHand(FullHouse, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isTwoPair"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.TwoPair, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.FOUR, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertEquals(eRank.THREE, HSP.getLoCard().geteRank());
		
		//	Check the kicker count
		assertEquals(1, HSP.getKickers().size());
		
		//	Get Kicker 1 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(0).geteSuit());
		//	Get Kicker 1 Rank
		assertEquals(eRank.TWO, HSP.getKickers().get(0).geteRank());
	}		
	
	@Test
	public void Test_Pair1() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.FOUR));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.FIVE));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		h = SetHand(FullHouse, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isPair"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.Pair, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.TWO, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertEquals(3, HSP.getKickers().size());
		
		//	Get Kicker 1 Suit
		assertEquals(eSuit.DIAMONDS, HSP.getKickers().get(0).geteSuit());
		//	Get Kicker 1 Rank
		assertEquals(eRank.FIVE, HSP.getKickers().get(0).geteRank());
		
		//	Get Kicker 2 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(1).geteSuit());
		//	Get Kicker 2 Rank
		assertEquals(eRank.FOUR, HSP.getKickers().get(1).geteRank());
		
		//	Get Kicker 3 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(2).geteSuit());
		//	Get Kicker 3 Rank
		assertEquals(eRank.THREE, HSP.getKickers().get(2).geteRank());			
	}	
	
	@Test
	public void Test_Pair2() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.FOUR));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.FIVE));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		h = SetHand(FullHouse, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isPair"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.Pair, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.THREE, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertEquals(3, HSP.getKickers().size());
		
		//	Get Kicker 1 Suit
		assertEquals(eSuit.DIAMONDS, HSP.getKickers().get(0).geteSuit());
		//	Get Kicker 1 Rank
		assertEquals(eRank.FIVE, HSP.getKickers().get(0).geteRank());
		
		//	Get Kicker 2 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(1).geteSuit());
		//	Get Kicker 2 Rank
		assertEquals(eRank.FOUR, HSP.getKickers().get(1).geteRank());
		
		//	Get Kicker 3 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(2).geteSuit());
		//	Get Kicker 3 Rank
		assertEquals(eRank.TWO, HSP.getKickers().get(2).geteRank());			
	}		
	
	@Test
	public void Test_Pair3() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.FOUR));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.FOUR));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.FIVE));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		h = SetHand(FullHouse, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isPair"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.Pair, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.FOUR, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertEquals(3, HSP.getKickers().size());
		
		//	Get Kicker 1 Suit
		assertEquals(eSuit.DIAMONDS, HSP.getKickers().get(0).geteSuit());
		//	Get Kicker 1 Rank
		assertEquals(eRank.FIVE, HSP.getKickers().get(0).geteRank());
		
		//	Get Kicker 2 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(1).geteSuit());
		//	Get Kicker 2 Rank
		assertEquals(eRank.THREE, HSP.getKickers().get(1).geteRank());
		
		//	Get Kicker 3 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(2).geteSuit());
		//	Get Kicker 3 Rank
		assertEquals(eRank.TWO, HSP.getKickers().get(2).geteRank());			
	}	
	@Test
	public void Test_Pair4() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.FOUR));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.FIVE));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.FIVE));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		h = SetHand(FullHouse, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isPair"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.Pair, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.FIVE, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertEquals(3, HSP.getKickers().size());
		
		//	Get Kicker 1 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(0).geteSuit());
		//	Get Kicker 1 Rank
		assertEquals(eRank.FOUR, HSP.getKickers().get(0).geteRank());
		
		//	Get Kicker 2 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(1).geteSuit());
		//	Get Kicker 2 Rank
		assertEquals(eRank.THREE, HSP.getKickers().get(1).geteRank());
		
		//	Get Kicker 3 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(2).geteSuit());
		//	Get Kicker 3 Rank
		assertEquals(eRank.TWO, HSP.getKickers().get(2).geteRank());			
	}		
	
	@Test
	public void Test_HighCard1() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.TWO));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.THREE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.FOUR));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.FIVE));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.ACE));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		h = SetHand(FullHouse, h);
		
		//	Make sure the hand has five cards
		assertEquals(5, h.getCards().size());
		
		//	Ensure the hand evaluates to Four of a Kind
		assertTrue(EvaluateHandScoreMethod(h,"isHighCard"));
		
		//	Extract the HSP
		HandScorePoker HSP = h.getHandScorePoker();
		
		//	Check Hand Strength
		assertEquals(eHandStrength.HighCard, HSP.geteHandStrength());
		
		//	Check Hi Card
		assertEquals(eRank.ACE, HSP.getHiCard().geteRank());
		
		//	Check Lo Card
		assertNull(HSP.getLoCard());
		
		//	Check the kicker count
		assertEquals(4, HSP.getKickers().size());
		
		//	Get Kicker 1 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(0).geteSuit());
		//	Get Kicker 1 Rank
		assertEquals(eRank.FIVE, HSP.getKickers().get(0).geteRank());
		
		//	Get Kicker 2 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(1).geteSuit());
		//	Get Kicker 2 Rank
		assertEquals(eRank.FOUR, HSP.getKickers().get(1).geteRank());
		
		//	Get Kicker 3 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(2).geteSuit());
		//	Get Kicker 3 Rank
		assertEquals(eRank.THREE, HSP.getKickers().get(2).geteRank());
		
		//	Get Kicker 4 Suit
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(3).geteSuit());
		//	Get Kicker 4 Rank
		assertEquals(eRank.TWO, HSP.getKickers().get(3).geteRank());			
	}
}
