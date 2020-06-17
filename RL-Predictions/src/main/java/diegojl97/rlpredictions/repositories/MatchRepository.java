package diegojl97.rlpredictions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import diegojl97.rlpredictions.model.Match;

public interface MatchRepository extends JpaRepository<Match,Long> {

}
