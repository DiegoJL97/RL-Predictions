package diegojl97.rlpredictions.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import diegojl97.rlpredictions.model.Player;
import diegojl97.rlpredictions.model.Prediction;

public interface PredictionRepository extends JpaRepository<Prediction,Long>{
	
	List<Prediction> findBySavior(Player savior);

}
