package pkgCore;

import java.util.ArrayList;
import java.util.UUID;

import pkgException.DeckException;
import pkgException.HandException;

public abstract class Hand {

	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 * Each Hand has a HandScore
	 */
	private HandScore HS;
	
	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 * Each hand has an array of cards
	 */
	private ArrayList<Card> cards;
	
	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 *        Hand no-arg constructor.
	 * 
	 *        This constructor should initialize 'cards' ArrayList.
	 */
	public Hand() {
		cards = new ArrayList<Card>();
	}

	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 * @return - this method should return the 'cards' ArrayList.
	 */
	protected ArrayList<Card> getCards() {
		return cards;
	}


	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 * setCards - set the hand of cards.  We may end up removing this.
	 * 
	 * @param cards
	 */
	protected void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * @param d - given deck to draw from
	 * @throws DeckException - if deck is empty, throw DeckException
	 * 
	 *                       This method doesn't actually return anything, just adds
	 *                       a card to the 'cards' ArrayList, drawing from given
	 *                       deck.
	 */
	public Hand Draw(Deck d) throws DeckException {
		cards.add(d.Draw());
		return this;
	}

	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * @return This method should return the HS (HandScore) attribute
	 */
	protected HandScore getHS() {
		return HS;
	}

	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * @param hS - given HandScore
	 * 
	 *           This method will set the HandScore for the Hand.
	 */
	protected void setHS(HandScore hS) {
		HS = hS;
	}


	/**
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * 
	 * AddCard - Add a card to the hand
	 * @param card
	 */
	protected void AddCard(Card card) {
		cards.add(card);
	}
	
	/**
	 * This abstract method must be implemented in each class that extends Hand.
	 * HandPoker and HandBlackJack will be evaluated differently, but must be
	 * evaluated.
	 * 
	 * @author BRG
	 * @version Lab #1
	 * @since Lab #1
	 * @return HandScore calculated for given Hand
	 * @throws HandException
	 */
	public abstract HandScore ScoreHand() throws HandException;

}