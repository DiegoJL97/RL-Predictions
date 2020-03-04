package diegojl97.rlpredictions.model;

import java.util.List;

import javax.persistence.ElementCollection;
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
	
	@ElementCollection
	private List<Team> leaguePrediction;
	@OneToOne(fetch=FetchType.EAGER)
	private Player savior;
	@OneToOne(fetch=FetchType.EAGER)
	private Player clutch;
	@OneToOne(fetch=FetchType.EAGER)
	private Player striker;
	
	public Prediction() {
		
	}
	
	public Prediction(List<Team> leaguePrediction, Player savior, Player clutch, Player striker) {
		this.leaguePrediction = leaguePrediction;
		this.savior = savior;
		this.clutch = clutch;
		this.striker = striker;
	}

	public List<Team> getLeaguePrediction() {
		return leaguePrediction;
	}

	public void setLeaguePrediction(List<Team> leaguePrediction) {
		this.leaguePrediction = leaguePrediction;
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
	
}
