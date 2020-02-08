package pkgCore;

import pkgEnum.eRank;
import pkgEnum.eSuit;

/**
 * @author BRG
 * @version Lab #1
 * @since Lab #1
 *
 *
 *        The <b>Card</b> class describes an instance of a playing Card. It
 *        interfaces with Comparable to set the natural order of a collection of
 *        Cards.
 *
 */
public class Card implements Comparable {

	/**
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 *        eSuit - enum for Suit of the Card
	 */

	private eSuit eSuit;

	/**
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 *        eRank - enum for Rank of the Card
	 */
	private eRank eRank;

	/**
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 * Wild - signifies if the card is 'Wild' or not.  If Wild, it can be substituted by any other card
	 */
	private boolean Wild;
	
	/**
	 * @version Lab #1
	 * @since Lab #1
	 * @param eSuit - Given suit of the Card
	 * @param eRank - Given rank of the Card
	 * 
	 *              Create an instance of the Card class
	 */
	public Card(pkgEnum.eSuit eSuit, pkgEnum.eRank eRank) {
		super();
		this.eSuit = eSuit;
		this.eRank = eRank;
		this.Wild = false;
	}

	/**
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 *        compareTo - set the generic sort order to sort by rank ascending
	 */
	@Override
	public int compareTo(Object o) {
		Card c = (Card) o;
		return c.geteRank().compareTo(this.geteRank());
	}

	/**
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 * equals - return 'true' if eSuit and eRank are the same. 
	 */
	@Override
	public boolean equals(Object o) {
		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of Complex or not "null instanceof [type]" also
		 * returns false
		 */
		if (!(o instanceof Card)) {
			return false;
		}

		// typecast o to Complex so that we can compare data members
		Card c = (Card) o;

		if ((c.geteRank().equals(this.geteRank())) && (c.geteSuit().equals(this.geteSuit())))

			return true;

		return false;
	}

	/**
	 * @version Lab #1
	 * @since Lab #1
	 * @return eRank of Card instance
	 * 
	 *         geteRank - get the eRank of the Card
	 */
	public eRank geteRank() {
		return eRank;
	}

	/**
	 * @version Lab #1
	 * @since Lab #1
	 * @return eSuit of Card instance
	 * 
	 *         geteSuit - get the eSuit of the Card
	 */
	public eSuit geteSuit() {
		return eSuit;
	}

	
	/**
	 * 
	 * @return
	 */
	public boolean isWild() {
		return Wild;
	}

	/**
	 * @version Lab #1
	 * @since Lab #1
	 * @param eRank - set to a given Rank
	 * 
	 *              seteRank - set the eRank of the Card
	 */
	private void seteRank(eRank eRank) {
		this.eRank = eRank;
	}

	/**
	 * @version Lab #1
	 * @since Lab #1
	 * @param eSuit - set to a given Suit
	 * 
	 *              seteSuit - Set the eSuit of the Card
	 */
	private void seteSuit(eSuit eSuit) {
		this.eSuit = eSuit;
	}
	
	
	
}