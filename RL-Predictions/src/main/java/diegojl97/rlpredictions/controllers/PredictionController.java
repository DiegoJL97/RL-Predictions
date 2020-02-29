package diegojl97.rlpredictions.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import diegojl97.rlpredictions.model.League;
import diegojl97.rlpredictions.repositories.LeagueRepository;
import diegojl97.rlpredictions.security.UserSessionInfoComponent;

@Controller
public class PredictionController {
	
	@Autowired
	private UserSessionInfoComponent userSession;
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@RequestMapping("/napredictions")
	public String loadNAPredictions(Model model) {
		
		model.addAttribute("logged", userSession.getLoggedUser());
		League naLeague = leagueRepository.findByLeagueName("NA");
		model.addAttribute("teams",naLeague.getTeams());
		return "makePrediction";
		
	}
	
	@RequestMapping("/eupredictions")
	public String loadEUPredictions(Model model) {

		model.addAttribute("logged", userSession.getLoggedUser());
		League euLeague = leagueRepository.findByLeagueName("EU");
		model.addAttribute("teams",euLeague.getTeams());
		return "makePrediction";
		
	}
	
	@PostMapping(value="/makePrediction")
	  public String getLiValues(HttpServletRequest request){
	    String[] liValues = request.getParameterValues("liContent");
	    int i = 0;
	    while(i < liValues.length) {
	    	System.out.println(i+1 + " " + liValues[i]);
	    	i++;
	    }
	    return "home";
	  }

}
