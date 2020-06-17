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
import diegojl97.rlpredictions.model.Match;
import diegojl97.rlpredictions.model.MatchList;
import diegojl97.rlpredictions.model.Week;
import diegojl97.rlpredictions.repositories.LeagueRepository;
import diegojl97.rlpredictions.repositories.MatchRepository;
import diegojl97.rlpredictions.repositories.TeamRepository;
import diegojl97.rlpredictions.repositories.WeekRepository;
import diegojl97.rlpredictions.security.UserSessionInfoComponent;

@Controller
public class WeeklyController {
	
	@Autowired
	private UserSessionInfoComponent userSession;
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private WeekRepository weekRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@GetMapping("/weekly")
	public String loadWeeklyPredictions(Model model) {
		model.addAttribute("logged", userSession.getLoggedUser());
		League euLeague = leagueRepository.findByLeagueName("EU");
		League naLeague = leagueRepository.findByLeagueName("NA");
		boolean started = euLeague.isStarted() && naLeague.isStarted();
		/*
		 * 			AÑADIR A STARTED LA CONDICION DE QUE TODAVIA NO HAYA NINGUNA SEMANA CUANDO SE ACTUALIZE EL MODELO 
		*/
		model.addAttribute("started", started);
		return "weeklyPrediction";
	}
	
	@GetMapping("/addWeek")
	public String loadAddWeek(Model model, @RequestParam(name = "euMatches") int euMatches, @RequestParam(name = "naMatches") int naMatches) {
		model.addAttribute("logged", userSession.getLoggedUser());
		model.addAttribute("euTeams",leagueRepository.findByLeagueName("EU").getTeams());
		model.addAttribute("naTeams",leagueRepository.findByLeagueName("NA").getTeams());
		ArrayList<MatchList> euMatchesList = new ArrayList<>();
		ArrayList<MatchList> naMatchesList = new ArrayList<>();
		for(int i=1; i<=euMatches; i++) {
			MatchList match = new MatchList("euTeam"+i);
			euMatchesList.add(match);
		}
		for(int i=1; i<=naMatches; i++) {
			MatchList match = new MatchList("naTeam"+i);
			naMatchesList.add(match);
		}
		model.addAttribute("euMatches",euMatchesList);
		model.addAttribute("naMatches",naMatchesList);
		return "addWeek";
	}
	
	@PostMapping("/addWeek")
	public String postAddWeek(Model model, HttpServletRequest request, @RequestParam(name="liMatchesEU") ArrayList<MatchList> matchesEU, @RequestParam(name="liMatchesNA") ArrayList<MatchList> matchesNA) {
		model.addAttribute("logged", userSession.getLoggedUser());
		League euLeague = leagueRepository.findByLeagueName("EU");
		Week euWeek = new Week(euLeague.getWeeks().size()+1,euLeague);
		weekRepository.save(euWeek);
		ArrayList<Match> euMatches = new ArrayList<>();
		for(int i=1; i<=matchesEU.size(); i++) {
			String[] euTeam = request.getParameterValues("euTeam"+i);
			Match match = new Match(teamRepository.findByTeamName(euTeam[0]),teamRepository.findByTeamName(euTeam[1]),euWeek);
			matchRepository.save(match);
			euMatches.add(match);
		}
		euWeek.setMatches(euMatches);
		euLeague.getWeeks().add(euWeek);
		leagueRepository.save(euLeague);
		League naLeague = leagueRepository.findByLeagueName("NA");
		Week naWeek = new Week(naLeague.getWeeks().size()+1,naLeague);
		weekRepository.save(naWeek);
		ArrayList<Match> naMatches = new ArrayList<>();
		for(int i=1; i<=matchesNA.size(); i++) {
			String[] naTeam = request.getParameterValues("naTeam"+i);
			Match match = new Match(teamRepository.findByTeamName(naTeam[0]),teamRepository.findByTeamName(naTeam[1]),naWeek);
			matchRepository.save(match);
			naMatches.add(match);
		}
		naWeek.setMatches(naMatches);
		naLeague.getWeeks().add(naWeek);
		leagueRepository.save(naLeague);
		return "redirect:/";
	}
	
}
