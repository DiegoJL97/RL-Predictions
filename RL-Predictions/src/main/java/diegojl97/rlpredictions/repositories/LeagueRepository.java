package diegojl97.rlpredictions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import diegojl97.rlpredictions.model.League;

public interface LeagueRepository extends JpaRepository<League,Long> {
	
	League findByLeagueName(String leagueName);

}
