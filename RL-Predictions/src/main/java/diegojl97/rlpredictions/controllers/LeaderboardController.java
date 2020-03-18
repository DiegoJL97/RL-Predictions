package diegojl97.rlpredictions.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import diegojl97.rlpredictions.model.League;
import diegojl97.rlpredictions.model.User;
import diegojl97.rlpredictions.repositories.LeagueRepository;
import diegojl97.rlpredictions.repositories.UserRepository;
import diegojl97.rlpredictions.security.UserSessionInfoComponent;

@Controller
public class LeaderboardController {
	
	@Autowired
	private UserSessionInfoComponent userSession;
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/leaderboard")
	public String loadLeaderboard(Model model) {
		
		model.addAttribute("logged", userSession.getLoggedUser());
		League euLeague = leagueRepository.findByLeagueName("EU");
		League naLeague = leagueRepository.findByLeagueName("NA");
		boolean finished = euLeague.isFinished() && naLeague.isFinished();
		model.addAttribute("finished",finished);
		
		if(finished) {
			List<User> users = userRepository.findAll();
			Collections.sort(users,Collections.reverseOrder());
			List<User> usersLeaderboard;
			if(users.size() > 20) {
				usersLeaderboard = users.subList(0,20); 
			} else {
				usersLeaderboard = users.subList(0,users.size());
			}
			for(User u: usersLeaderboard) {
				u.setPosition(findPosition(u,usersLeaderboard));
			}
			model.addAttribute("allUsers", usersLeaderboard);
			model.addAttribute("userPosition", findPosition(userSession.getLoggedUser(),users));
			model.addAttribute("userPoints", userSession.getLoggedUser().getPoints());
		}
		
		return "leaderboard";
		
	}
	
	public int findPosition(User user, List<User> allUsers) {
		int i = 0;
		for(User u: allUsers) {
			if(u.getUsername().equals(user.getUsername())) {
				return i+1;
			}
			i++;
		}
		return -1;
	}

}
