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
import diegojl97.rlpredictions.model.Team;
import diegojl97.rlpredictions.repositories.LeagueRepository;
import diegojl97.rlpredictions.repositories.PlayerRepository;
import diegojl97.rlpredictions.repositories.PredictionRepository;
import diegojl97.rlpredictions.repositories.TeamRepository;
import diegojl97.rlpredictions.repositories.UserRepository;
import diegojl97.rlpredictions.security.UserSessionInfoComponent;

@Controller
public class TeamManagementController {
	
	@Autowired
	private UserSessionInfoComponent userSession;
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	private static final String logged = "logged";
	
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
	
	@GetMapping("/addTeam")
	public String addTeamGet(Model model) {
		model.addAttribute(logged, userSession.getLoggedUser());
		List<League> leagues = leagueRepository.findAll();
		model.addAttribute("leagues",leagues);
		return "addTeam";
	}
	
	@PostMapping("/addTeam")
	public String addTeamPost(Model model, HttpServletRequest request, @RequestParam(name = "teamName") String teamName, @RequestParam(name = "leagueString") String leagueString) {
		model.addAttribute(logged, userSession.getLoggedUser());
		String[] liValues = request.getParameterValues("liContent");
		League league = leagueRepository.findByLeagueName(leagueString);
		Team newTeam = new Team(teamName,league);
		teamRepository.save(newTeam);
		int i = 0;
		ArrayList<Player> players = new ArrayList<>();
		while(i < liValues.length) {
			Player newPlayer = new Player(liValues[i],newTeam);
			playerRepository.save(newPlayer);
			players.add(newPlayer);
			i++;
		}
		newTeam.setPlayers(players);
		teamRepository.save(newTeam);
		return "redirect:/";
	}
	
	@GetMapping("/deleteTeam")
	public String deleteTeamGet (Model model) {
		model.addAttribute(logged, userSession.getLoggedUser());
		List<Team> teams = teamRepository.findAll();
		model.addAttribute("teams",teams);
		return "deleteTeam";
	}
	
	@PostMapping("/deleteTeam")
	public String deleteTeamPost(Model model, @RequestParam(name = "teamDeleted") String teamDeleted) {
		model.addAttribute(logged, userSession.getLoggedUser());
		Team team = teamRepository.findByTeamName(teamDeleted);
		teamRepository.delete(team);
		return "redirect:/";
	}

}
