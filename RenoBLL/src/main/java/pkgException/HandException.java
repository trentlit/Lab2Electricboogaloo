package pkgException;

import java.util.ArrayList;

import pkgCore.Card;
import pkgCore.Hand;

public class HandException extends Exception {

	private ArrayList<Card> CardsInHand = new ArrayList<Card>();
	private Hand h; 

	public HandException(Hand h)
	{
		super();
		this.h = h;
	}
	public HandException(ArrayList<Card> cardsInHand) {
		super();
		CardsInHand = cardsInHand;
	}

	protected ArrayList<Card> getCardsInHand() {
		return CardsInHand;
	}

}