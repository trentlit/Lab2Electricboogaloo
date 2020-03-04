package pkgCore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.HandException;


public class HandPokerTest {
//Four of a kind with non face cards
	@Test
	public void FourOfAKindTest1() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.TWO));
		hp.AddCard(new Card(eSuit.SPADES,eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FOUR)); 
		hp.ScoreHand();
	
		assertEquals(hp.isFourOfAKind(),true);
	}
	
	
//four of a kind with face cards	
	@Test
	public void FourOfAKindTest2() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.KING));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.KING));
		hp.AddCard(new Card(eSuit.SPADES,eRank.KING));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.KING)); 
		hp.ScoreHand();
		
		assertEquals(hp.isFourOfAKind(),true);

	}
//three of a kind with low cards	
	@Test
	public void ThreeOfAKindTest1() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.TWO));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hp.ScoreHand();
		
		assertEquals(hp.isThreeOfAKind(),true);

	}
//three of a kind with higher cards
	@Test
	public void ThreeOfAKindTest2() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.THREE));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FIVE));
		hp.ScoreHand();
		
		assertEquals(hp.isThreeOfAKind(),true);

	}
// three of a kind with a lower and higher card	aka "sandwiched"
	public void ThreeOfAKindTest3() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.KING));
		hp.ScoreHand();
		
		assertEquals(hp.isThreeOfAKind(),true);

	}
//two pair with non face cards	
	@Test
	public void TwoPairTest1() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hp.ScoreHand();
		
		
		assertEquals(hp.isTwoPair(),true);

	}
//two pair with one pair of face cards
	@Test
	public void TwoPairTest2() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.ACE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.ACE));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.THREE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hp.ScoreHand();
		
		
		assertEquals(hp.isTwoPair(),true);

		
	}
//full house with pair being lower than three of a kind	
	@Test
	public void FullHouseTest1() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FIVE));
		hp.ScoreHand();
		
		assertEquals(hp.isFullHouse(),true);
		
	}
//full house with pair being higher card than three of a kind	
	@Test
	public void FullHouseTest2() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.TWO));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FOUR));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
		hp.ScoreHand();
		
		assertEquals(hp.isFullHouse(),true);
		
	}

	@Test
	public void RoyalFlushTest1() throws HandException {
		
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.HEARTS,eRank.ACE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.KING));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.QUEEN));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.JACK));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TEN));
		hp.ScoreHand();
		
		assertEquals(hp.isRoyalFlush(),true);
		
	}
	
	@Test
	public void StraightFlushTest1() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.FOUR));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.SIX));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.THREE));
		hp.ScoreHand();
		
		assertEquals(hp.isStraightFlush(),true);
		
	}
	
	@Test
	public void FlushTest1() throws HandException {
	
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.SPADES,eRank.ACE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.NINE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.SIX));
		hp.AddCard(new Card(eSuit.SPADES,eRank.EIGHT));
		hp.AddCard(new Card(eSuit.SPADES,eRank.TWO));
		hp.ScoreHand();
		
		assertEquals(hp.isFlush(),true);
		
	}
//straight test with non face cards	
	@Test
	public void StraightTest1() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.SIX));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hp.ScoreHand();
		
		assertEquals(hp.isStraight(),true);
		
	}
// straight test with ace acting as a "one"	
	@Test
	public void StraightTest2() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.ACE));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.FOUR));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.ScoreHand();
		
		assertEquals(hp.isStraight(),true);
		
	}
	
	
//straight test ten through king	
	@Test
	public void StraightTest3() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.ACE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.KING));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.QUEEN));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.JACK));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.TEN));
		hp.ScoreHand();
		
		assertEquals(hp.isStraight(),true);
		
	}

	
	@Test
	public void HighCardTest1() throws HandException {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.EIGHT));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FOUR));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.NINE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hp.ScoreHand();
		
		assertEquals(hp.isHighCard(),true);
	
	}

}