package diegojl97.rlpredictions.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class League {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String leagueName;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "league")
	private List<Team> teams = new ArrayList<>();
	
	protected League() {
		
	}

	public League(String leagueName) {
		super();
		this.leagueName = leagueName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	public void addTeam(Team team) {
		this.teams.add(team);
	}
	
	public void removeTeam(Team team) {
		this.teams.remove(team);
	}

}
