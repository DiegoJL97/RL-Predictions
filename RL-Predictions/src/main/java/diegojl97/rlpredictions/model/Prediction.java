package diegojl97.rlpredictions.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Prediction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private PredictionLeague league;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Player savior;
	@OneToOne(fetch=FetchType.EAGER)
	private Player clutch;
	@OneToOne(fetch=FetchType.EAGER)
	private Player striker;
	
	private boolean isSaviorCorrect;
	private boolean isClutchCorrect;
	private boolean isStrikerCorrect;
	
	public Prediction() {
		
	}
	
	public Prediction(PredictionLeague league, Player savior, Player clutch, Player striker) {
		this.league = league;
		this.savior = savior;
		this.clutch = clutch;
		this.striker = striker;
		this.isSaviorCorrect = false;
		this.isClutchCorrect = false;
		this.isStrikerCorrect = false;
	}

	public PredictionLeague getLeaguePrediction() {
		return league;
	}

	public void setLeaguePrediction(PredictionLeague league) {
		this.league = league;
	}

	public Player getSavior() {
		return savior;
	}

	public void setSavior(Player savior) {
		this.savior = savior;
	}

	public Player getClutch() {
		return clutch;
	}

	public void setClutch(Player clutch) {
		this.clutch = clutch;
	}

	public Player getStriker() {
		return striker;
	}

	public void setStriker(Player striker) {
		this.striker = striker;
	}

	public boolean isSaviorCorrect() {
		return isSaviorCorrect;
	}

	public void setSaviorCorrect(boolean isSaviorCorrect) {
		this.isSaviorCorrect = isSaviorCorrect;
	}

	public boolean isClutchCorrect() {
		return isClutchCorrect;
	}

	public void setClutchCorrect(boolean isClutchCorrect) {
		this.isClutchCorrect = isClutchCorrect;
	}

	public boolean isStrikerCorrect() {
		return isStrikerCorrect;
	}

	public void setStrikerCorrect(boolean isStrikerCorrect) {
		this.isStrikerCorrect = isStrikerCorrect;
	}
	
}