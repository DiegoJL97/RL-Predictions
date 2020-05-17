package diegojl97.rlpredictions.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import diegojl97.rlpredictions.model.League;
import diegojl97.rlpredictions.model.Player;
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
public class AdminController {
	
	@Autowired
	private UserSessionInfoComponent userSession;
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private PredictionRepository predictionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private static final String logged = "logged";
	
	/*
	 * 
	 * 		/startLeague --> /finishLeague ---> /resetLeagues ---> /modifyTeam/** ---> /resetLeagues
	 * 
	 */
	
	@GetMapping("/admin")
	public String loadAdminPage(Model model) {
		model.addAttribute(logged, userSession.getLoggedUser());
		return "admin";
	}
	
	@GetMapping("/redirectModify")
	public String loadModifyPageRedirect(Model model,@RequestParam(name = "team") String team) {
		model.addAttribute(logged, userSession.getLoggedUser());
		return "redirect:/modifyTeam/"+team;
	}
	
	@GetMapping("/startLeague")
	public String loadStartLeague(Model model) {
		League naLeague = leagueRepository.findByLeagueName("NA");
		League euLeague = leagueRepository.findByLeagueName("EU");
		naLeague.setStarted(true);
		euLeague.setStarted(true);
		leagueRepository.save(naLeague);
		leagueRepository.save(euLeague);
		return "redirect:/";
	}
	
	@GetMapping("/finishLeague")
	public String loadFinishLeague(Model model) {
		
		model.addAttribute(logged, userSession.getLoggedUser());
		
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
		model.addAttribute(logged, userSession.getLoggedUser());
		
		String[] liValuesNA = request.getParameterValues("liContentNA");
		String[] liValuesEU = request.getParameterValues("liContentEU");

		for(User user: userRepository.findAll()) {
			Integer points = 0;
			if(user.isMadeEUPrediction()) {
				if(user.getEuPrediction().getSavior().getPlayerName().equals(saviorEU)) {
					points = points + 50;
					user.getEuPrediction().setSaviorCorrect(true);
				}
				if(user.getEuPrediction().getClutch().getPlayerName().equals(clutchEU)) {
					points = points+ 50;
					user.getEuPrediction().setClutchCorrect(true);
				}
				if(user.getEuPrediction().getStriker().getPlayerName().equals(strikerEU)) {
					points = points + 50;
					user.getEuPrediction().setStrikerCorrect(true);
				}
				points = points + pointsAux(user.getEuPrediction().getLeaguePrediction(),liValuesEU);
			}
			if(user.isMadeNAPrediction()) {
				if(user.getNaPrediction().getSavior().getPlayerName().equals(saviorNA)) {
					points = points + 50;
					user.getNaPrediction().setSaviorCorrect(true);
				}
				if(user.getNaPrediction().getClutch().getPlayerName().equals(clutchNA)) {
					points = points + 50;
					user.getNaPrediction().setClutchCorrect(true);
				}
				if(user.getNaPrediction().getStriker().getPlayerName().equals(strikerNA)) {
					points = points + 50;
					user.getNaPrediction().setStrikerCorrect(true);
				}
				points = points + pointsAux(user.getNaPrediction().getLeaguePrediction(),liValuesNA);
			}
			user.setPoints(points);
			userRepository.save(user);
		}
		
		League naLeague = leagueRepository.findByLeagueName("NA");
		League euLeague = leagueRepository.findByLeagueName("EU");
		naLeague.setFinished(true);
		euLeague.setFinished(true);
		leagueRepository.save(naLeague);
		leagueRepository.save(euLeague);
		
		return "home";
	}
	
	public Integer pointsAux(PredictionLeague pred, String[] liValues ) {
		int i = 0;
		int points = 0;
		for(TeamPrediction team: pred) {
			if(team.getTeam().getTeamName().equals(liValues[i])) {
				points = points + 20;
				team.setCorrect(true);
			}
			if(i != 0) {
				if(team.getTeam().getTeamName().equals(liValues[i-1])) {
					points = points + 10;
				}
			}
			if(i != 9) {
				if(team.getTeam().getTeamName().equals(liValues[i+1])) {
					points = points + 10;
				}
			}
			i++;
		}
		return points;
	}
	
	@GetMapping("/resetLeagues")
	public String loadResetLeagues (Model model) {			
		model.addAttribute(logged, userSession.getLoggedUser());
		for(User user: userRepository.findAll()) {
			if(user.isMadeEUPrediction()) {
				user.setEuPrediction(null);
				user.setMadeEUPrediction(false);
			}
			if(user.isMadeNAPrediction()) {
				user.setNaPrediction(null);
				user.setMadeNAPrediction(false);
			}
			user.setPoints(0);
			userRepository.save(user);
		}
		predictionRepository.deleteAll();
		League naLeague = leagueRepository.findByLeagueName("NA");
		League euLeague = leagueRepository.findByLeagueName("EU");
		naLeague.setFinished(false);
		euLeague.setFinished(false);
		naLeague.setStarted(false);
		euLeague.setStarted(false);
		leagueRepository.save(naLeague);
		leagueRepository.save(euLeague);
		return "home";
	}

	@GetMapping("/modifyTeam/{teamName}")
	public String modifySpecificTeam(Model model, @PathVariable String teamName) {
		model.addAttribute(logged, userSession.getLoggedUser());
		Team team = teamRepository.findByTeamName(teamName);
		model.addAttribute("team",team);
		return "modifyTeam";
	}
	
	@PostMapping("/modifyTeam/{teamName}")
	public String modifySpecificTeamPost(Model model, HttpServletRequest request, @PathVariable String teamName, @RequestParam(name = "teamName") String newTeamName) {
		model.addAttribute(logged, userSession.getLoggedUser());
		String[] liValues = request.getParameterValues("liContent");
		Team team = teamRepository.findByTeamName(teamName);
		if(!newTeamName.equalsIgnoreCase(teamName)) {
			team.setTeamName(newTeamName);
		}
		int i = 0;
		List<Player> players = new ArrayList<>();
		for(Player p: team.getPlayers()) {
			if(!p.getPlayerName().equals(liValues[i])) {
				Player newPlayer = new Player(liValues[i],team);
				players.add(newPlayer);
				playerRepository.delete(p);
				playerRepository.save(newPlayer);
			} else {
				players.add(p);
			}
			i++;
		}
		team.setPlayers(players);
		teamRepository.save(team);
		return "redirect:/";
	}
	

}
