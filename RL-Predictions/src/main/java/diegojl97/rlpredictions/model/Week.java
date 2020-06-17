package diegojl97.rlpredictions.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Week {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private int weekNumber;
	
	@ManyToOne
	private League league;
	
	@OneToMany(mappedBy = "week", fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	private List<Match> matches = new ArrayList<>();
	
	protected Week() {
		
	}
	
	public Week(int weekNumber, League league) {
		this.weekNumber = weekNumber;
		this.league = league;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public List<Match> getMatches() {
		return matches;
	}
	
}
