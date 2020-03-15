package diegojl97.rlpredictions.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import diegojl97.rlpredictions.model.League;
import diegojl97.rlpredictions.model.Player;
import diegojl97.rlpredictions.model.PredictionLeague;
import diegojl97.rlpredictions.model.Team;
import diegojl97.rlpredictions.model.User;
import diegojl97.rlpredictions.repositories.LeagueRepository;
import diegojl97.rlpredictions.repositories.UserRepository;
import diegojl97.rlpredictions.security.UserSessionInfoComponent;

@Controller
public class AdminController {
	
	@Autowired
	private UserSessionInfoComponent userSession;
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/finishLeague")
	public String loadFinishLeague(Model model) {
		
		model.addAttribute("logged", userSession.getLoggedUser());
		
		League naLeague = leagueRepository.findByLeagueName("NA");
		model.addAttribute("naTeams",naLeague.getTeams());
		List<Player> naPlayers = new ArrayList<>();
		for(Team team: naLeague.getTeams()) {
			for(Player player: team.getPlayers()) {
				naPlayers.add(player);
			}
		}
		model.addAttribute("naPlayers",naPlayers);
		
		League euLeague = leagueRepository.findByLeagueName("EU");
		model.addAttribute("euTeams",euLeague.getTeams());
		List<Player> euPlayers = new ArrayList<>();
		for(Team team: euLeague.getTeams()) {
			for(Player player: team.getPlayers()) {
				euPlayers.add(player);
			}
		}
		model.addAttribute("euPlayers",euPlayers);
		
		return "finishLeague";
	}
	
	@PostMapping("/finishLeague")
	public String sendFinishLeague (Model model, HttpServletRequest request, @RequestParam(name = "saviorNA") String saviorNA, 
									@RequestParam(name = "clutchNA") String clutchNA, @RequestParam(name = "strikerNA") String strikerNA, 
									@RequestParam(name = "saviorEU") String saviorEU, @RequestParam(name = "clutchEU") String clutchEU, 
									@RequestParam(name = "strikerEU") String strikerEU) 
	{
		model.addAttribute("logged", userSession.getLoggedUser());
		
		String[] liValuesNA = request.getParameterValues("liContentNA");
		String[] liValuesEU = request.getParameterValues("liContentEU");

		for(User user: userRepository.findAll()) {
			Integer points = 0;
			if(user.isMadeEUPrediction()) {
				if(user.getEuPrediction().getSavior().getPlayerName().equals(saviorEU)) {
					points = points + 1;
				}
				if(user.getEuPrediction().getClutch().getPlayerName().equals(clutchEU)) {
					points = points + 1;
				}
				if(user.getEuPrediction().getStriker().getPlayerName().equals(strikerEU)) {
					points = points + 1;
				}
				PredictionLeague pred = user.getEuPrediction().getLeaguePrediction();
				int i = 0;
				for(Team team: pred) {
					if(team.getTeamName().equals(liValuesEU[i])) {
						points = points + 1;
					}
					i++;
				}
			}
			if(user.isMadeNAPrediction()) {
				if(user.getNaPrediction().getSavior().getPlayerName().equals(saviorNA)) {
					points = points + 1;
				}
				if(user.getNaPrediction().getClutch().getPlayerName().equals(clutchNA)) {
					points = points + 1;
				}
				if(user.getNaPrediction().getStriker().getPlayerName().equals(strikerNA)) {
					points = points + 1;
				}
				PredictionLeague pred = user.getNaPrediction().getLeaguePrediction();
				int i = 0;
				for(Team team: pred) {
					if(team.getTeamName().equals(liValuesNA[i])) {
						points = points + 1;
					}
					i++;
				}
			}
			user.setPoints(points);
			userRepository.save(user);
		}
		
		return "home";
	}

}
