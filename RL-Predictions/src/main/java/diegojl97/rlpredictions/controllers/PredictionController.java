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
import diegojl97.rlpredictions.model.Prediction;
import diegojl97.rlpredictions.model.PredictionLeague;
import diegojl97.rlpredictions.model.Team;
import diegojl97.rlpredictions.model.TeamPrediction;
import diegojl97.rlpredictions.model.User;
import diegojl97.rlpredictions.repositories.LeagueRepository;
import diegojl97.rlpredictions.repositories.PlayerRepository;
import diegojl97.rlpredictions.repositories.PredictionRepository;
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
	
	@Autowired
	private PredictionRepository predictionRepository;
	
	private static final String logged = "logged";
	private static final String madePredictionString = "madePrediction";
	private static final String teamsString = "teams";
	
	
	@GetMapping("/napredictions")
	public String loadNAPredictions(Model model) {
		
		model.addAttribute(logged, userSession.getLoggedUser());
		boolean madePrediction = userSession.getLoggedUser().isMadeNAPrediction();
		model.addAttribute(madePredictionString,madePrediction);
		League naLeague = leagueRepository.findByLeagueName("NA");
		model.addAttribute("started", naLeague.isStarted());
		model.addAttribute("league","NA");
		if(madePrediction) {
			List<TeamPrediction> teams = new ArrayList<>();
			for(TeamPrediction team: userSession.getLoggedUser().getNaPrediction().getLeaguePrediction()) {
				teams.add(team);
			}
			Player savior = userSession.getLoggedUser().getNaPrediction().getSavior();
			Player clutch = userSession.getLoggedUser().getNaPrediction().getClutch();
			Player striker = userSession.getLoggedUser().getNaPrediction().getStriker();
			model.addAttribute("finished",naLeague.isFinished());
			model.addAttribute(teamsString,teams);
			model.addAttribute("savior",savior.getPlayerName());
			model.addAttribute("clutch",clutch.getPlayerName());
			model.addAttribute("striker",striker.getPlayerName());
			model.addAttribute("saviorCorrect",userSession.getLoggedUser().getNaPrediction().isSaviorCorrect());
			model.addAttribute("clutchCorrect",userSession.getLoggedUser().getNaPrediction().isClutchCorrect());
			model.addAttribute("strikerCorrect",userSession.getLoggedUser().getNaPrediction().isStrikerCorrect());
		} else {
			model.addAttribute(teamsString,naLeague.getTeams());
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
	
	@GetMapping("/eupredictions")
	public String loadEUPredictions(Model model) {

		model.addAttribute(logged, userSession.getLoggedUser());
		boolean madePrediction = userSession.getLoggedUser().isMadeEUPrediction();
		model.addAttribute(madePredictionString,madePrediction);
		League euLeague = leagueRepository.findByLeagueName("EU");
		model.addAttribute("started", euLeague.isStarted());
		model.addAttribute("league","EU");
		if(madePrediction) {
			List<TeamPrediction> teams = new ArrayList<>();
			for(TeamPrediction team: userSession.getLoggedUser().getEuPrediction().getLeaguePrediction()) {
				teams.add(team);
			}
			Player savior = userSession.getLoggedUser().getEuPrediction().getSavior();
			Player clutch = userSession.getLoggedUser().getEuPrediction().getClutch();
			Player striker = userSession.getLoggedUser().getEuPrediction().getStriker();
			model.addAttribute("finished",euLeague.isFinished());
			model.addAttribute(teamsString,teams);
			model.addAttribute("savior",savior.getPlayerName());
			model.addAttribute("clutch",clutch.getPlayerName());
			model.addAttribute("striker",striker.getPlayerName());
			model.addAttribute("saviorCorrect",userSession.getLoggedUser().getEuPrediction().isSaviorCorrect());
			model.addAttribute("clutchCorrect",userSession.getLoggedUser().getEuPrediction().isClutchCorrect());
			model.addAttribute("strikerCorrect",userSession.getLoggedUser().getEuPrediction().isStrikerCorrect());
		} else {
			model.addAttribute(teamsString,euLeague.getTeams());
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
	    leaguePrediction.setFirst(new TeamPrediction(teamRepository.findByTeamName(liValues[0]),false));
	    leaguePrediction.setSecond(new TeamPrediction(teamRepository.findByTeamName(liValues[1]),false));
	    leaguePrediction.setThird(new TeamPrediction(teamRepository.findByTeamName(liValues[2]),false));
	    leaguePrediction.setFourth(new TeamPrediction(teamRepository.findByTeamName(liValues[3]),false));
	    leaguePrediction.setFifth(new TeamPrediction(teamRepository.findByTeamName(liValues[4]),false));
	    leaguePrediction.setSixth(new TeamPrediction(teamRepository.findByTeamName(liValues[5]),false));
	    leaguePrediction.setSeventh(new TeamPrediction(teamRepository.findByTeamName(liValues[6]),false));
	    leaguePrediction.setEighth(new TeamPrediction(teamRepository.findByTeamName(liValues[7]),false));
	    leaguePrediction.setNinth(new TeamPrediction(teamRepository.findByTeamName(liValues[8]),false));
	    leaguePrediction.setTenth(new TeamPrediction(teamRepository.findByTeamName(liValues[9]),false));
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
	    predictionRepository.save(prediction);
	    userSession.setLoggedUser(user);
	    userRepository.save(user);
	    model.addAttribute(logged, userSession.getLoggedUser());
	    return "predictionSaved";
	    
	}
	
	@GetMapping("/goHome")
	public String goHome(Model model) {
		model.addAttribute(logged, userSession.getLoggedUser());
		boolean madePrediction = userSession.getLoggedUser().isMadeNAPrediction() && userSession.getLoggedUser().isMadeEUPrediction();
		model.addAttribute(madePredictionString,madePrediction);
		return "home";
	}
	
	@GetMapping("/modifyPrediction")
	public String modifyPredictionPage(Model model, @RequestParam(name = "league") String league) {
		model.addAttribute(logged, userSession.getLoggedUser());
		League leagueObject = leagueRepository.findByLeagueName(league);
		model.addAttribute("started",leagueObject.isStarted());
		if(!leagueObject.isStarted()) {
			model.addAttribute("league",leagueObject.getLeagueName());
			ArrayList<Team> teams = new ArrayList<>();
			Player actualSavior = new Player();
			Player actualClutch = new Player();
			Player actualStriker = new Player();
			switch(leagueObject.getLeagueName()) {
				case "NA":
					for(TeamPrediction teamPre: userSession.getLoggedUser().getNaPrediction().getLeaguePrediction()) {
						teams.add(teamPre.getTeam());
					}
					actualSavior = userSession.getLoggedUser().getNaPrediction().getSavior();
					actualClutch = userSession.getLoggedUser().getNaPrediction().getClutch();
					actualStriker = userSession.getLoggedUser().getNaPrediction().getStriker();
					break;
				case "EU": 
					for(TeamPrediction teamPre: userSession.getLoggedUser().getEuPrediction().getLeaguePrediction()) {
						teams.add(teamPre.getTeam());
					}
					actualSavior = userSession.getLoggedUser().getEuPrediction().getSavior();
					actualClutch = userSession.getLoggedUser().getEuPrediction().getClutch();
					actualStriker = userSession.getLoggedUser().getEuPrediction().getStriker();
					break;
				default: break;
			}
			model.addAttribute("actualSavior", actualSavior.getPlayerName());
			model.addAttribute("actualClutch", actualClutch.getPlayerName());
			model.addAttribute("actualStriker", actualStriker.getPlayerName());
			model.addAttribute(teamsString,teams);
			List<Player> players = new ArrayList<>();
			for(Team team: leagueObject.getTeams()) {
				for(Player player: team.getPlayers()) {
					players.add(player);
				}
			}
			ArrayList<Player> playersSavior = new ArrayList<>(players);
			playersSavior.remove(actualSavior);
			model.addAttribute("playersSavior",playersSavior);
			ArrayList<Player> playersClutch = new ArrayList<>(players);
			playersClutch.remove(actualClutch);
			model.addAttribute("playersClutch",playersClutch);
			ArrayList<Player> playersStriker = new ArrayList<>(players);
			playersStriker.remove(actualStriker);
			model.addAttribute("playersStriker",playersStriker);
		}
		return "modify";
	}
	
	@PostMapping("/modifyPrediction")
	public String modifyPredictionPost(Model model, HttpServletRequest request, @RequestParam(name = "league") String league, @RequestParam(name = "savior") String savior, @RequestParam(name = "clutch") String clutch, @RequestParam(name = "striker") String striker) {
		model.addAttribute(logged, userSession.getLoggedUser());
		User user = userSession.getLoggedUser();
	    String[] liValues = request.getParameterValues("liContent");
	    PredictionLeague leaguePrediction = new PredictionLeague();
	    leaguePrediction.setFirst(new TeamPrediction(teamRepository.findByTeamName(liValues[0]),false));
	    leaguePrediction.setSecond(new TeamPrediction(teamRepository.findByTeamName(liValues[1]),false));
	    leaguePrediction.setThird(new TeamPrediction(teamRepository.findByTeamName(liValues[2]),false));
	    leaguePrediction.setFourth(new TeamPrediction(teamRepository.findByTeamName(liValues[3]),false));
	    leaguePrediction.setFifth(new TeamPrediction(teamRepository.findByTeamName(liValues[4]),false));
	    leaguePrediction.setSixth(new TeamPrediction(teamRepository.findByTeamName(liValues[5]),false));
	    leaguePrediction.setSeventh(new TeamPrediction(teamRepository.findByTeamName(liValues[6]),false));
	    leaguePrediction.setEighth(new TeamPrediction(teamRepository.findByTeamName(liValues[7]),false));
	    leaguePrediction.setNinth(new TeamPrediction(teamRepository.findByTeamName(liValues[8]),false));
	    leaguePrediction.setTenth(new TeamPrediction(teamRepository.findByTeamName(liValues[9]),false));
	    Player saviorPlayer = playerRepository.findByPlayerName(savior);
	    Player clutchPlayer = playerRepository.findByPlayerName(clutch);
	    Player strikerPlayer = playerRepository.findByPlayerName(striker);
	    Prediction prediction = new Prediction(leaguePrediction,saviorPlayer,clutchPlayer,strikerPlayer);
	    Prediction pred = new Prediction();
	    switch(league) {
	    	case "NA":
	    		pred = user.getNaPrediction();
	    		user.setNaPrediction(prediction);
	    		break;
	    	case "EU":
	    		pred = user.getEuPrediction();
	    		user.setEuPrediction(prediction);
	    		break;
	    	default:
	    		break;
	    }
	    predictionRepository.save(prediction);
	    userSession.setLoggedUser(user);
	    userRepository.save(user);
	    predictionRepository.delete(pred);
		return "predictionSaved";
	}
	
}
