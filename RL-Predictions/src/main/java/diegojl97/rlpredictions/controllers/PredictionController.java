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
import diegojl97.rlpredictions.model.Prediction;
import diegojl97.rlpredictions.model.PredictionLeague;
import diegojl97.rlpredictions.model.Team;
import diegojl97.rlpredictions.model.User;
import diegojl97.rlpredictions.repositories.LeagueRepository;
import diegojl97.rlpredictions.repositories.PlayerRepository;
import diegojl97.rlpredictions.repositories.TeamRepository;
import diegojl97.rlpredictions.repositories.UserRepository;
import diegojl97.rlpredictions.security.UserSessionInfoComponent;

@Controller
public class PredictionController {
	
	@Autowired
	private UserSessionInfoComponent userSession;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	
	@RequestMapping("/napredictions")
	public String loadNAPredictions(Model model) {
		
		model.addAttribute("logged", userSession.getLoggedUser());
		boolean madePrediction = userSession.getLoggedUser().isMadeNAPrediction();
		model.addAttribute("madePrediction",madePrediction);
		if(madePrediction) {
			List<Team> teams = new ArrayList<>();
			for(Team team: userSession.getLoggedUser().getNaPrediction().getLeaguePrediction()) {
				teams.add(team);
			}
			Player savior = userSession.getLoggedUser().getNaPrediction().getSavior();
			Player clutch = userSession.getLoggedUser().getNaPrediction().getClutch();
			Player striker = userSession.getLoggedUser().getNaPrediction().getStriker();
			model.addAttribute("teams",teams);
			model.addAttribute("savior",savior.getPlayerName());
			model.addAttribute("clutch",clutch.getPlayerName());
			model.addAttribute("striker",striker.getPlayerName());
		} else {
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
		}
		return "makePrediction";
		
	}
	
	@RequestMapping("/eupredictions")
	public String loadEUPredictions(Model model) {

		model.addAttribute("logged", userSession.getLoggedUser());
		boolean madePrediction = userSession.getLoggedUser().isMadeEUPrediction();
		model.addAttribute("madePrediction",madePrediction);
		if(madePrediction) {
			List<Team> teams = new ArrayList<>();
			for(Team team: userSession.getLoggedUser().getEuPrediction().getLeaguePrediction()) {
				teams.add(team);
			}
			Player savior = userSession.getLoggedUser().getEuPrediction().getSavior();
			Player clutch = userSession.getLoggedUser().getEuPrediction().getClutch();
			Player striker = userSession.getLoggedUser().getEuPrediction().getStriker();
			model.addAttribute("teams",teams);
			model.addAttribute("savior",savior.getPlayerName());
			model.addAttribute("clutch",clutch.getPlayerName());
			model.addAttribute("striker",striker.getPlayerName());
		} else {
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
		}
		return "makePrediction";
		
	}
	
	@PostMapping(value="/makePrediction")
	public String getLiValues(Model model, HttpServletRequest request, @RequestParam(name = "league") String league, @RequestParam(name = "savior") String savior, @RequestParam(name = "clutch") String clutch, @RequestParam(name = "striker") String striker){
		
		User user = userSession.getLoggedUser();
	    String[] liValues = request.getParameterValues("liContent");
	    PredictionLeague leaguePrediction = new PredictionLeague();
	    leaguePrediction.setFirst(teamRepository.findByTeamName(liValues[0]));
	    leaguePrediction.setSecond(teamRepository.findByTeamName(liValues[1]));
	    leaguePrediction.setThird(teamRepository.findByTeamName(liValues[2]));
	    leaguePrediction.setFourth(teamRepository.findByTeamName(liValues[3]));
	    leaguePrediction.setFifth(teamRepository.findByTeamName(liValues[4]));
	    leaguePrediction.setSixth(teamRepository.findByTeamName(liValues[5]));
	    leaguePrediction.setSeventh(teamRepository.findByTeamName(liValues[6]));
	    leaguePrediction.setEighth(teamRepository.findByTeamName(liValues[7]));
	    leaguePrediction.setNinth(teamRepository.findByTeamName(liValues[8]));
	    leaguePrediction.setTenth(teamRepository.findByTeamName(liValues[9]));
	    Player saviorPlayer = playerRepository.findByPlayerName(savior);
	    Player clutchPlayer = playerRepository.findByPlayerName(clutch);
	    Player strikerPlayer = playerRepository.findByPlayerName(striker);
	    Prediction prediction = new Prediction(leaguePrediction,saviorPlayer,clutchPlayer,strikerPlayer);
	    switch(league) {
	    	case "NA":
	    		user.setMadeNAPrediction(true);
	    		user.setNaPrediction(prediction);
	    		break;
	    	case "EU":
	    		user.setMadeEUPrediction(true);
	    		user.setEuPrediction(prediction);
	    		break;
	    	default:
	    		break;
	    }
	    userSession.setLoggedUser(user);
	    userRepository.save(user);
	    model.addAttribute("logged", userSession.getLoggedUser());
	    return "predictionSaved";
	    
	  }
	
	@RequestMapping("/goHome")
	public String goHome(Model model) {
		model.addAttribute("logged", userSession.getLoggedUser());
		return "home";
	}
	
}
