package pkgCore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import pkgEnum.eCardNo;
import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eRow;
import pkgEnum.eRowCount;
import pkgEnum.eSuit;
import pkgException.HandException;

public class HandPoker extends Hand implements Comparable {

	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 *        CRC - Each hand score has a private attribute of an Array of
	 *        CardRankCount. Each instance of CardRankCount shows the Card, it's
	 *        ranking and the number of instances. Example:
	 * 
	 *        If Hand was 5H-2K-5D-AH-KH There would be three rows in the CRC matrix
	 *        Card 2, position 1, count 1 Card 5, position 2, count 2 Card K,
	 *        position 4, count 1 Card A, position 5, count 1
	 * 
	 *        From this we can determine that the hand should be a pair, and card
	 *        that makes the pair is a 5, and that the first 5 is found in position
	 *        2 in the hand.
	 * 
	 *        CRC works great with high card, pair, two pair, three of a kind, four
	 *        of a kind, full house CRC doesn't work great for straight, straight
	 *        flush, flush, royal flush
	 * 
	 *        CRC works when the frequency of the cards matter.
	 * 
	 */
	private ArrayList<CardRankCount> CRC = null;

	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 *        HandPoker - Create an instance of HandPoker
	 */
	public HandPoker() {
		super.setHS(new HandScorePoker());
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * 
	 * getCRC - get the CRC arraylist
	 * @return
	 */
	protected ArrayList<CardRankCount> getCRC() {
		return CRC;
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * getHandScorePoker - Return HandScorePoker
	 * @return
	 */
	public HandScorePoker getHandScorePoker() {
		return (HandScorePoker) this.getHS();
	}

	@Override
	protected void setCards(ArrayList<Card> cards) {
		super.setCards(cards);
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * 
	 * EvaluateHand - evaulate the hand calling methods that use reflections
	 * @param hp
	 * @return
	 * @throws HandException
	 */
	public HandPoker EvaluateHand(HandPoker hp) throws HandException {
		ScoreHand();
		return this;
	}



	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * 
	 * ScoreHand - this method will score the hand.  It will dynamically call the frequence method to 
	 * count the distinct number of counts by rank, then call ScoreReflections
	 */
	@Override
	public HandScore ScoreHand() throws HandException {

		// If the hand isn't 5 cards... throw an exception
		if (this.getCards().size() != 5) {
			throw new HandException(this.getCards());
		}

		// Sort the hand by rank
		Collections.sort(super.getCards());

		// Count the Frequency of cards, store in CRC ArrayList
		Frequency();

		// Score the hand using Java Reflections
		return ScoreHandReflections();
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * 
	 *        ScoreHandReflections - Using reflections, score the hand.
	 * @return
	 */
	private HandScorePoker ScoreHandReflections() {

		HandScorePoker HSP = null;
		try {

			// c = structure of class 'Hand'
			Class<?> c = Class.forName("pkgCore.HandPoker");

			Object o = null;

			for (eHandStrength eHandStrength : eHandStrength.values()) {
				String strEvalMethod = eHandStrength.getEvalMethod();
				Method mEval = c.getDeclaredMethod(strEvalMethod, null);
				mEval.setAccessible(true);
				o = mEval.invoke(this, null);

				if ((boolean) o) {
					break;
				}

			}
			HSP = (HandScorePoker) this.getHandScorePoker();

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

		return HSP;
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2 Frequency - Find the frequence of elements in the cards, store
	 *        it in the CRC
	 */
	private void Frequency() {

		CRC = new ArrayList<CardRankCount>();

		int iCnt = 0;
		int iPos = 0;

		for (eRank eRank : eRank.values()) {
			iCnt = (CountRank(eRank));
			if (iCnt > 0) {
				iPos = FindCardRank(eRank);
				CRC.add(new CardRankCount(eRank, iCnt, iPos));
			}
		}
		Collections.sort(CRC);
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2 CountRank - Count the ranks in a given set of cards, return the
	 *        count
	 * @param eRank
	 * @return
	 */
	private int CountRank(eRank eRank) {
		int iCnt = 0;
		for (Card c : super.getCards()) {
			if (c.geteRank() == eRank) {
				iCnt++;
			}
		}
		return iCnt;
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2 FindCardRank - Find the position where a rank is found in a set
	 *        of cards.
	 * @param eRank
	 * @return
	 */
	private int FindCardRank(eRank eRank) {
		int iPos = 0;

		for (iPos = 0; iPos < super.getCards().size(); iPos++) {
			if (super.getCards().get(iPos).geteRank() == eRank) {
				break;
			}
		}
		return iPos;
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * @return 'true' if Hand is a Royal Flush
	 * 
	 */
	private boolean isRoyalFlush() {

		boolean bIsRoyalFlush = false;
		if (isStraightFlush()) {
			
		}
		//TODO 
		return bIsRoyalFlush;
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * @return 'true' if Hand is a Straight Flush
	 * 
	 */
	private boolean isStraightFlush() {
		boolean bisStraightFlush = false;
		
		if ((isStraight()&&(isFlush())){
			bisStraightFlush =true;
			
		// TODO - Double check 
		return bisStraightFlush;
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * @return 'true' if Hand is a Four of a kind
	 * 
	 */
	private boolean isFourOfAKind() {
		boolean bisFourOfAKind = false;
		//TODO- Complete implementation for this method.
		
		}
		return bisFourOfAKind;
	}
	
	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * @return 'true' if Hand is a Full House
	 * 
	 *
	private boolean isFullHouse() {
		boolean bisFullHouse = false;
		// TODO - Complete implementation for this method.
		return bisFullHouse;
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * @return 'true' if Hand is a Flush
	 * 
	 */
	private boolean isFlush() {
		boolean bisFlush = false;
		int iCnt=0;
		for (eSuit eSuit : EnumSet.range(eSuit.HEARTS, eSuit.SPADES)) {
			for(Card c: this.getCards()) {
				if (c.geteSuit(==eSuit)) {
					iCnt++;
				}
			}
		if (iCnt==0)
			continue;
		else if (iCnt==5) {
			bisFlush=true;
			break;
		}
		else {
			return false;
		}
		}
		
		// TODO - Double check 
		return bisFlush;
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * @return 'true' if Hand is a Straight
	 * 
	 */
	private boolean isStraight() {
		boolean bisStraight = true;
		
		int i=0;
		
		if((this.getCards().get(0).geteRank().equals(eRank.ACE)) &&
			if(this.getCards().get(1).geteRank().equals(eRank.FIVE))) {
				i=1;
			}
		
		for (; i< this.getCards().size()-1;i++) {
			if ((this.getCards().get(i).geteRank().getiRankNbr()-1)!=
					(this.getCards().get(i+1).geteRank().getiRankNubr())) {
					bisStraight = false;
					return bisStraight;
			}
			
			}
		
		return bisStraight;
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * @return 'true' if Hand is a Three of a Kind
	 * 
	 */
	private boolean isThreeOfAKind() {
		boolean bisThreeOfAKind = false;

		if ((GetCRCSize() == eRowCount.THREE.getiRowCountItems()) && (GetCRCCount(eRow.ONE.ordinal()) == 3)) {
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.ThreeOfAKind);
			HSP.setHiCard(this.getCards().get(this.getCRC().get(eRow.ONE.ordinal()).getiCardPosition()));
			HSP.setLoCard(null);
			HSP.setKickers(FindTheKickers(this.getCRC()));
			this.setHS(HSP);
			bisThreeOfAKind = true;
		}
		return bisThreeOfAKind;
	} 

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * @return 'true' if Hand is two pair
	 * 
	 */
	private boolean isTwoPair() {
		boolean bisTwoPair = false;
		// TODO - Complete implementation for this method.
		return bisTwoPair;
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * @return 'true' if Hand is a pair
	 * 
	 */
	private boolean isPair() {

		boolean bisPair = false;
		// TODO - Complete implementation for this method.
		return bisPair;
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * @return 'true' if Hand is a high card
	 * 
	 */
	private boolean isHighCard() {
		boolean bisHighCard = true;
		// TODO - Complete implementation for this method.
		return bisHighCard;
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * 
	 *        GetCRCSize - get the size of the CRC arraylist
	 * @return size of the CRC
	 */
	private int GetCRCSize() {
		return CRC.size();
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2 
	 * GetCRCCount - get the count in a given row
	 * @param iRow
	 * @return
	 */
	private int GetCRCCount(int iRow) {
		return CRC.get(iRow).getiCnt();
	}

	/** 
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2 
	 * 
	 * FindTheKickers - set the kickers for a given handscore.  Return the Array of cards
	 * 
	 * @param CRC
	 * @return
	 */
	private ArrayList<Card> FindTheKickers(ArrayList<CardRankCount> CRC) {
		ArrayList<Card> kickers = new ArrayList<Card>();

		// Start at '1' to skip the first CRC
		for (int i = 1; i < CRC.size(); i++) {
			if (CRC.get(i).getiCnt() == 1) {
				kickers.add(this.getCards().get(CRC.get(i).getiCardPosition()));
			}
		}
		return kickers;
	}

	/**
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2 
	 * equals - return 'true' if the cards are the same.
	 */
	@Override
	public boolean equals(Object obj) {

		HandPoker hp = (HandPoker) obj;
		ArrayList<Card> PassedCards = hp.getCards();
		ArrayList<Card> ThisCards = this.getCards();

		boolean isEqual = PassedCards.equals(ThisCards);

		return isEqual;

	}

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 *        compareTo - This is the default sort for HandPoker. Sorted by...
	 * 
	 *        HandStrength HiHand LoHand Kickers
	 */
	@Override
	public int compareTo(Object o) {

		HandPoker PassedHP = (HandPoker) o;

		HandScorePoker PassedHSP = PassedHP.getHandScorePoker();
		HandScorePoker ThisHSP = this.getHandScorePoker();

		// Sort on Hand Strength
		if (PassedHSP.geteHandStrength().getHandStrength() - ThisHSP.geteHandStrength().getHandStrength() != 0)
			return PassedHSP.geteHandStrength().getHandStrength() - ThisHSP.geteHandStrength().getHandStrength();

		// Then Sort on High Card
		if (PassedHSP.getHiCard().geteRank().getiRankNbr() - ThisHSP.getHiCard().geteRank().getiRankNbr() != 0)
			return PassedHSP.getHiCard().geteRank().getiRankNbr() - ThisHSP.getHiCard().geteRank().getiRankNbr();

		// Then Sort on Low Card
		if ((PassedHSP.getLoCard() != null) && (ThisHSP.getLoCard() != null)) {
			if (PassedHSP.getLoCard().geteRank().getiRankNbr() - ThisHSP.getLoCard().geteRank().getiRankNbr() != 0) {
				return PassedHSP.getLoCard().geteRank().getiRankNbr() - ThisHSP.getLoCard().geteRank().getiRankNbr();
			}
		}

		// Then Sort by kickers.
		for (int k = 0; k < 4; k++) {
			if ((PassedHSP.getKickers() != null) && (ThisHSP.getKickers() != null)) {
				if ((PassedHSP.getKickers().size() > k) && (ThisHSP.getKickers().size() > k)) {
					if (PassedHSP.getKickers().get(k).geteRank().getiRankNbr()
							- ThisHSP.getKickers().get(k).geteRank().getiRankNbr() != 0) {
						return PassedHSP.getKickers().get(k).geteRank().getiRankNbr()
								- ThisHSP.getKickers().get(k).geteRank().getiRankNbr();
					}
				}
			}
		}
		return 0;
	}

}