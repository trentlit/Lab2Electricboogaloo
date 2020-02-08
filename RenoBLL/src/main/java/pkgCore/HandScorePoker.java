package pkgCore;

import java.util.ArrayList;
import pkgEnum.eHandStrength;

public class HandScorePoker extends HandScore {
	private eHandStrength eHandStrength;
	private Card HiCard;
	private Card LoCard;
	private ArrayList<Card> kickers = new ArrayList<Card>();
	private boolean Natural = true;

	protected eHandStrength geteHandStrength() {
		return eHandStrength;
	}

	protected void seteHandStrength(eHandStrength eHandStrength) {
		this.eHandStrength = eHandStrength;
	}

	protected Card getHiCard() {
		return HiCard;
	}

	protected void setHiCard(Card hiCard) {
		HiCard = hiCard;
	}

	protected Card getLoCard() {
		return LoCard;
	}

	protected void setLoCard(Card loCard) {
		LoCard = loCard;
	}

	protected ArrayList<Card> getKickers() {
		return kickers;
	}

	protected void setKickers(ArrayList<Card> kickers) {
		this.kickers = kickers;
	}

	public boolean isNatural() {
		return Natural;
	}

	public void setNatural(boolean natural) {
		Natural = natural;
	}

	@Override
	public boolean equals(Object obj) {
		HandScorePoker PassedHSP = (HandScorePoker) obj;
		HandScorePoker ThisHSP = this;

		if ((PassedHSP.geteHandStrength() != null) && (ThisHSP.geteHandStrength() != null)
				&& (PassedHSP.geteHandStrength() != ThisHSP.geteHandStrength())) {
			return false;
		}
		if ((PassedHSP.geteHandStrength() == null) && (ThisHSP.geteHandStrength() != null)) {
			return false;
		}
		if ((PassedHSP.geteHandStrength() != null) && (ThisHSP.geteHandStrength() == null)) {
			return false;
		}		
		
		if ((PassedHSP.getHiCard() != null) && (ThisHSP.getHiCard() != null)
				&& (PassedHSP.getHiCard() != ThisHSP.getHiCard())) {
			return false;
		}
		if ((PassedHSP.getHiCard() == null) && (ThisHSP.getHiCard() != null)) {
			return false;
		}
		if ((PassedHSP.getHiCard() != null) && (ThisHSP.getHiCard() == null)) {
			return false;
		}		
		
		if ((PassedHSP.getLoCard() != null) && (ThisHSP.getLoCard() != null)
				&& (PassedHSP.getLoCard() != ThisHSP.getLoCard())) {
			return false;
		}
		if ((PassedHSP.getLoCard() == null) && (ThisHSP.getLoCard() != null)) {
			return false;
		}
		if ((PassedHSP.getLoCard() != null) && (ThisHSP.getLoCard() == null)) {
			return false;
		}		
		
		if ((PassedHSP.getKickers() != null) && (ThisHSP.getKickers() != null)
				&& (PassedHSP.getKickers() != ThisHSP.getKickers())) {
			return false;
		}
		if ((PassedHSP.getKickers() == null) && (ThisHSP.getKickers() != null)) {
			return false;
		}
		if ((PassedHSP.getKickers() != null) && (ThisHSP.getKickers() == null)) {
			return false;
		}	

		return true;
	}

}