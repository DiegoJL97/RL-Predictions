package diegojl97.rlpredictions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import diegojl97.rlpredictions.model.League;
import diegojl97.rlpredictions.model.Week;

public interface WeekRepository extends JpaRepository<Week,Long>{

	Week findByWeekNumberAndLeague(int weekNumber,League league);
	
}
