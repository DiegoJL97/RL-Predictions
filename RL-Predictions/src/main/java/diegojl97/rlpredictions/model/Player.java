package diegojl97.rlpredictions.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String playerName;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Team team;
	
	public Player() {
		
	}

	public Player(String playerName, Team team) {
		super();
		this.playerName = playerName;
		this.team = team;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	@Override public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode()); 
		int idNew = (int) id;
		result = prime * result + idNew; 
		return result; 
	}

	@Override
	public boolean equals(Object obj) { 
		if (obj == this) { 
			return true; 
		} 
		if (obj == null || obj.getClass() != this.getClass()) { 
			return false; 
		} 
		Player guest = (Player) obj; 
		return guest.playerName == playerName  || (playerName != null && playerName.equals(guest.getPlayerName()));
	} 
	
}


