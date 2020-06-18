package diegojl97.rlpredictions.controllers;

import java.util.ArrayList;

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
		/*
		 * 			HABRA QUE HACER QUE SI YA HA HECHO LA PREDICCION NO PUEDA VOLVER A HACERLA Y SE MUESTRE LA QUE TIENE
		 */
		model.addAttribute("logged", userSession.getLoggedUser());
		League euLeague = leagueRepository.findByLeagueName("EU");
		League naLeague = leagueRepository.findByLeagueName("NA");
		boolean started = (euLeague.isStarted() && naLeague.isStarted()) || (euLeague.getWeeks().size() != 0 && naLeague.getWeeks().size() != 0);
		model.addAttribute("started", started);
		if(started) {
			model.addAttribute("euMatches",euLeague.getWeeks().get(euLeague.getWeeks().size()-1).getMatches());
			model.addAttribute("naMatches",naLeague.getWeeks().get(naLeague.getWeeks().size()-1).getMatches());
		}
		return "weeklyPrediction";
	}
	
	@PostMapping("/weekly")
	public String postWeeklyPredictions(Model model) {
		return "redirect:/weekly";
	}
	
	@GetMapping("/addWeek")
	public String loadAddWeek(Model model, @RequestParam(name = "euMatches") int euMatches, @RequestParam(name = "naMatches") int naMatches) {
		model.addAttribute("logged", userSession.getLoggedUser());
		model.addAttribute("euTeams",leagueRepository.findByLeagueName("EU").getTeams());
		model.addAttribute("naTeams",leagueRepository.findByLeagueName("NA").getTeams());
		model.addAttribute("euMatches",loadAddWeekAux(euMatches,"EU"));
		model.addAttribute("naMatches",loadAddWeekAux(naMatches,"NA"));
		return "addWeek";
	}
	
	public ArrayList<MatchList> loadAddWeekAux(int matches, String leagueName){
		ArrayList<MatchList> matchesList = new ArrayList<>();
		for(int i=1; i<=matches; i++) {
			String teamMatch = "";
			switch(leagueName) {
				case "EU":
					teamMatch = "euTeam";
					break;
				case "NA":
					teamMatch = "naTeam";
					break;
				default: 
					break;
			}
			MatchList match = new MatchList(teamMatch+i);
			matchesList.add(match);
		}
		return matchesList;
	}
	
	@PostMapping("/addWeek")
	public String postAddWeek(Model model, HttpServletRequest request, @RequestParam(name="liMatchesEU") ArrayList<MatchList> matchesEU, @RequestParam(name="liMatchesNA") ArrayList<MatchList> matchesNA) {
		model.addAttribute("logged", userSession.getLoggedUser());
		addWeekAux("EU",request,matchesEU);
		addWeekAux("NA",request,matchesNA);
		return "redirect:/";
	}
	
	public void addWeekAux(String leagueName, HttpServletRequest request, ArrayList<MatchList> matchesNumber) {
		League league = leagueRepository.findByLeagueName(leagueName);
		Week week = new Week(league.getWeeks().size()+1,league);
		weekRepository.save(week);
		ArrayList<Match> matches = new ArrayList<>();
		for(int i=1; i<=matchesNumber.size(); i++) {
			String teamMatch = "";
			switch(league.getLeagueName()) {
				case "EU":
					teamMatch = "euTeam";
					break;
				case "NA":
					teamMatch = "naTeam";
					break;
				default: 
					break;
			}
			String[] team = request.getParameterValues(teamMatch+i);
			Match match = new Match(teamRepository.findByTeamName(team[0]),teamRepository.findByTeamName(team[1]),week);
			matchRepository.save(match);
			matches.add(match);
		}
		week.setMatches(matches);
		league.getWeeks().add(week);
		leagueRepository.save(league);
	}
	
}
