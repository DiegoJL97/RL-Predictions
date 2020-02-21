package diegojl97.rlpredictions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import diegojl97.rlpredictions.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	User findByUsername(String username);

}
