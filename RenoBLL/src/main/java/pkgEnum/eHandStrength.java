package pkgEnum;

public enum eHandStrength {

	RoyalFlush(100, "isRoyalFlush") {
		public String toString() {
			return "Royal Flush";
		}
	},
	StraightFlush(90, "isStraightFlush") {
		public String toString() {
			return "Straight Flush";
		}
	},
	FourOfAKind(80, "isFourOfAKind") {
		public String toString() {
			return "Four of a Kind";
		}
	},
	FullHouse(70, "isFullHouse") {
		public String toString() {
			return "Full House";
		}
	},
	Flush(60, "isFlush") {
		public String toString() {
			return "Flush";
		}
	},
	Straight(50, "isStraight") {
		public String toString() {
			return "Straight";
		}
	},
 
	ThreeOfAKind(40, "isThreeOfAKind") {
		public String toString() {
			return "Three of a Kind";
		}
	},
	TwoPair(30, "isTwoPair") {
		public String toString() {
			return "Two Pairs";
		}
	},

	Pair(20, "isPair") {
		public String toString() {
			return "One Pair";
		}
	},
	HighCard(10, "isHighCard") {
		public String toString() {
			return "High Card";
		}
	};

	private eHandStrength(final int handstrength, final String EvalMethod) {
		this.iHandStrength = handstrength;
		this.strEvalMethod = EvalMethod;
	}

	private int iHandStrength;
	private String strEvalMethod;

	public int getHandStrength() {
		return iHandStrength;
	}

	public String getEvalMethod() {
		return this.strEvalMethod;
	}

}