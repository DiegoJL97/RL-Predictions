package diegojl97.rlpredictions.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Match {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	private Week week;
	
	@OneToOne
	private Team team1;
	@OneToOne
	private Team team2;
	
	private int winsTeam1;
	private int winsTeam2;
	
	protected Match() {
		
	}
	
	public Match(Team team1, Team team2, Week week) {
		this.team1 = team1;
		this.team2 = team2;
		this.week = week;
	}
	
	public Match(Team team1, Team team2, int winsTeam1, int winsTeam2, Week week) {
		this.team1 = team1;
		this.team2 = team2;
		this.winsTeam1 = winsTeam1;
		this.winsTeam2 = winsTeam2;
		this.week = week;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public int getWinsTeam1() {
		return winsTeam1;
	}

	public void setWinsTeam1(int winsTeam1) {
		this.winsTeam1 = winsTeam1;
	}

	public int getWinsTeam2() {
		return winsTeam2;
	}

	public void setWinsTeam2(int winsTeam2) {
		this.winsTeam2 = winsTeam2;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

}
