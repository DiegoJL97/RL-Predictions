package diegojl97.rlpredictions.security;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import diegojl97.rlpredictions.model.User;
import diegojl97.rlpredictions.repositories.UserRepository;

@Component
public class DatabaseLoader {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	private void initDatabase() {
		ArrayList<String> userRole = new ArrayList<>();
		userRole.add("ROLE_USER");
		ArrayList<String> adminRole = new ArrayList<>();
		adminRole.add("ROLE_USER");
		adminRole.add("ROLE_ADMIN");
		User user1 = new User("user", "pass", userRole);
		User user2 = new User("user2", "pass2", userRole);
		User admin = new User("admin", "adminpass", adminRole);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(admin);
	}

}
