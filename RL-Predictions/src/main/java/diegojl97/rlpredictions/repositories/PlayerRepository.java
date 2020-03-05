package diegojl97.rlpredictions.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import diegojl97.rlpredictions.model.Player;

public interface PlayerRepository extends JpaRepository<Player,Long> {
	
	Player findByPlayerName(String playerName);

}
