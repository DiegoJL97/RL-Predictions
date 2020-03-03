package diegojl97.rlpredictions.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import diegojl97.rlpredictions.model.League;
import diegojl97.rlpredictions.model.Player;
import diegojl97.rlpredictions.model.Team;
import diegojl97.rlpredictions.model.User;
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
		model.addAttribute("league","NA");
		model.addAttribute("teams",naLeague.getTeams());
		List<Player> players = new ArrayList<>();
		for(Team team: naLeague.getTeams()) {
			for(Player player: team.getPlayers()) {
				players.add(player);
			}
		}
		model.addAttribute("players",players);
		return "makePrediction";
		
	}
	
	@RequestMapping("/eupredictions")
	public String loadEUPredictions(Model model) {

		model.addAttribute("logged", userSession.getLoggedUser());
		League euLeague = leagueRepository.findByLeagueName("EU");
		model.addAttribute("league","EU");
		model.addAttribute("teams",euLeague.getTeams());
		List<Player> players = new ArrayList<>();
		for(Team team: euLeague.getTeams()) {
			for(Player player: team.getPlayers()) {
				players.add(player);
			}
		}
		model.addAttribute("players",players);
		return "makePrediction";
		
	}
	
	@PostMapping(value="/makePrediction")
	  public String getLiValues(HttpServletRequest request, @RequestParam(name = "league") String league, @RequestParam(name = "savior") String savior, @RequestParam(name = "clutch") String clutch, @RequestParam(name = "striker") String striker){
		User user = userSession.getLoggedUser();
	    String[] liValues = request.getParameterValues("liContent");
	    System.out.println(league);
	    int i = 0;
	    while(i < liValues.length) {
	    	System.out.println(i+1 + " " + liValues[i]);
	    	i++;
	    }
	    System.out.println("SAVIOR : "+savior);
	    System.out.println("CLUTCH : "+clutch);
	    System.out.println("STRIKER : "+striker);
	    return "home";
	  }

}
