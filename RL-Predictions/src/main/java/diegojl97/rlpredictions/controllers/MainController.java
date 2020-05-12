package diegojl97.rlpredictions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import diegojl97.rlpredictions.model.League;
import diegojl97.rlpredictions.repositories.LeagueRepository;
import diegojl97.rlpredictions.security.UserSessionInfoComponent;

@Controller
public class MainController {
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private UserSessionInfoComponent userSession;
	
	@RequestMapping("/")
	public String loadHome (Model model) {
		model.addAttribute("logged", userSession.getLoggedUser());
		if(userSession.isLoggedUser()) {
			boolean madePrediction = userSession.getLoggedUser().isMadeNAPrediction() && userSession.getLoggedUser().isMadeEUPrediction();
			model.addAttribute("madePrediction",madePrediction);
			League euLeague = leagueRepository.findByLeagueName("EU");
			League naLeague = leagueRepository.findByLeagueName("NA");
			boolean finished = euLeague.isFinished() && naLeague.isFinished();
			model.addAttribute("finished", finished);
		}
		return "home";
	}
	
	@RequestMapping("/chooseLeague")
	public String chooseLeague(Model model) {
		model.addAttribute("logged", userSession.getLoggedUser());
		if(userSession.isLoggedUser()) {
			boolean madePrediction = userSession.getLoggedUser().isMadeNAPrediction() && userSession.getLoggedUser().isMadeEUPrediction();
			model.addAttribute("madePrediction",madePrediction);
		}
		return "chooseLeague";
	}

}
