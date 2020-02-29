package diegojl97.rlpredictions.security;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import diegojl97.rlpredictions.model.League;
import diegojl97.rlpredictions.model.Player;
import diegojl97.rlpredictions.model.Team;
import diegojl97.rlpredictions.model.User;
import diegojl97.rlpredictions.repositories.LeagueRepository;
import diegojl97.rlpredictions.repositories.PlayerRepository;
import diegojl97.rlpredictions.repositories.TeamRepository;
import diegojl97.rlpredictions.repositories.UserRepository;

@Component
public class DatabaseLoader {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@PostConstruct
	private void initDatabase() {
		
		ArrayList<String> userRole = new ArrayList<>();
		userRole.add("ROLE_USER");
		ArrayList<String> adminRole = new ArrayList<>();
		adminRole.add("ROLE_USER");
		adminRole.add("ROLE_ADMIN");
		User user1 = new User("user", "pass", userRole);
		User user2 = new User("user2", "pass2", userRole);
		User admin = new User("admin", "adminpass", adminRole);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(admin);
		
		League naLeague = new League("NA");
		League euLeague = new League("EU");
		
		Team eUnited = new Team("eUnited",naLeague);
		Team ghostGaming = new Team("Ghost Gaming",naLeague);
		Team nrg = new Team("NRG Esports",naLeague);
		Team pk = new Team("Pittsburgh Knights",naLeague);
		Team rogue = new Team("Rogue",naLeague);
		Team ssg = new Team("Spacestation Gaming",naLeague);
		Team soniqs = new Team("Susquehanna Soniqs",naLeague);
		Team flight = new Team("Flight",naLeague);
		Team g2 = new Team("G2 Esports",naLeague);
		Team c9 = new Team("Cloud 9",naLeague);
		naLeague.addTeam(eUnited);
		naLeague.addTeam(ghostGaming);
		naLeague.addTeam(nrg);
		naLeague.addTeam(pk);
		naLeague.addTeam(rogue);
		naLeague.addTeam(ssg);
		naLeague.addTeam(soniqs);
		naLeague.addTeam(flight);
		naLeague.addTeam(g2);
		naLeague.addTeam(c9);
		leagueRepository.save(naLeague);
		teamRepository.save(eUnited);
		teamRepository.save(ghostGaming);
		teamRepository.save(nrg);
		teamRepository.save(pk);
		teamRepository.save(rogue);
		teamRepository.save(ssg);
		teamRepository.save(soniqs);
		teamRepository.save(flight);
		teamRepository.save(g2);
		teamRepository.save(c9);
		for(Team team: naLeague.getTeams()) {
			for(int i=1; i<=3; i++) {
				Player player = new Player("Player "+i,team);
				team.addPlayer(player);
				playerRepository.save(player);
			}
			teamRepository.save(team);
		}
		
		Team barcelona = new Team("FC Barcelona",euLeague);
		Team dignitas = new Team("Dignitas",euLeague);
		Team vitality = new Team("Renault Vitality",euLeague);
		Team mouse = new Team("mousesports",euLeague);
		Team reciprocity = new Team("Team Reciprocity",euLeague);
		Team veloce = new Team("Veloce Esports",euLeague);
		Team monaco = new Team("AS Monaco Esports",euLeague);
		Team endpoint = new Team("Endpoint",euLeague);
		Team tsm = new Team("Team SoloMid",euLeague);
		Team singularity = new Team("Team Singularity",euLeague);
		euLeague.addTeam(barcelona);
		euLeague.addTeam(dignitas);
		euLeague.addTeam(vitality);
		euLeague.addTeam(mouse);
		euLeague.addTeam(reciprocity);
		euLeague.addTeam(veloce);
		euLeague.addTeam(monaco);
		euLeague.addTeam(endpoint);
		euLeague.addTeam(tsm);
		euLeague.addTeam(singularity);
		leagueRepository.save(euLeague);
		teamRepository.save(barcelona);
		teamRepository.save(dignitas);
		teamRepository.save(vitality);
		teamRepository.save(mouse);
		teamRepository.save(reciprocity);
		teamRepository.save(veloce);
		teamRepository.save(monaco);
		teamRepository.save(endpoint);
		teamRepository.save(tsm);
		teamRepository.save(singularity);
		for(Team team: euLeague.getTeams()) {
			for(int i=1; i<=3; i++) {
				Player player = new Player("Player "+i,team);
				team.addPlayer(player);
				playerRepository.save(player);
			}
			teamRepository.save(team);
		}
		
	}

}
