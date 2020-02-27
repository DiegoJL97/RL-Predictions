package diegojl97.rlpredictions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import diegojl97.rlpredictions.model.Team;

public interface TeamRepository extends JpaRepository<Team,Long>{
	
	Team findByTeamName(String teamName);

}
