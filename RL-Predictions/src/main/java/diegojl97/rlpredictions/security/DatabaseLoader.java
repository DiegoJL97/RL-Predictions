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
		Team g2 = new Team("G2",naLeague);
		Team c9 = new Team("Cloud9",naLeague);
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
		/*
		for(Team team: euLeague.getTeams()) {
			for(int i=1; i<=3; i++) {
				Player player = new Player("Player "+i,team);
				team.addPlayer(player);
				playerRepository.save(player);
			}
			teamRepository.save(team);
		}*/
		Player flakes = new Player("Flakes",barcelona);
		Player ronaky = new Player("Ronaky",barcelona);
		Player deevo = new Player("Deevo",barcelona);
		barcelona.addPlayer(flakes);
		barcelona.addPlayer(ronaky);
		barcelona.addPlayer(deevo);
		playerRepository.save(flakes);
		playerRepository.save(ronaky);
		playerRepository.save(deevo);
		teamRepository.save(barcelona);
		Player violentPanda = new Player("ViolentPanda",dignitas);
		Player yukeo = new Player("Yukeo",dignitas);
		Player aztral = new Player("AztraL",dignitas);
		dignitas.addPlayer(violentPanda);
		dignitas.addPlayer(yukeo);
		dignitas.addPlayer(aztral);
		playerRepository.save(violentPanda);
		playerRepository.save(yukeo);
		playerRepository.save(aztral);
		teamRepository.save(dignitas);
		Player kaydop = new Player("Kaydop",vitality);
		Player fairyPeak = new Player("Fairy Peak",vitality);
		Player alpha = new Player("Alpha64",vitality);
		vitality.addPlayer(kaydop);
		vitality.addPlayer(fairyPeak);
		vitality.addPlayer(alpha);
		playerRepository.save(kaydop);
		playerRepository.save(fairyPeak);
		playerRepository.save(alpha);
		teamRepository.save(vitality);
		Player scrub = new Player("Scrub Killa",mouse);
		Player speed = new Player("Speed",mouse);
		Player kuxir = new Player("kuxir97",mouse);
		mouse.addPlayer(scrub);
		mouse.addPlayer(speed);
		mouse.addPlayer(kuxir);
		playerRepository.save(scrub);
		playerRepository.save(speed);
		playerRepository.save(kuxir);
		teamRepository.save(mouse);
		Player chausette = new Player("Chausette45",reciprocity);
		Player ferra = new Player("Ferra",reciprocity);
		Player fruity = new Player("fruity",reciprocity);
		reciprocity.addPlayer(chausette);
		reciprocity.addPlayer(ferra);
		reciprocity.addPlayer(fruity);
		playerRepository.save(chausette);
		playerRepository.save(ferra);
		playerRepository.save(fruity);
		teamRepository.save(reciprocity);
		Player freakii = new Player("FreaKii",veloce);
		Player flame = new Player("FlamE",veloce);
		Player kassio = new Player("Kassio",veloce);
		veloce.addPlayer(freakii);
		veloce.addPlayer(flame);
		veloce.addPlayer(kassio);
		playerRepository.save(freakii);
		playerRepository.save(flame);
		playerRepository.save(kassio);
		teamRepository.save(veloce);
		Player eyeIgnite = new Player("EyeIgnite",monaco);
		Player extra = new Player("Extra",monaco);
		Player trigree = new Player("Tigree",monaco);
		monaco.addPlayer(eyeIgnite);
		monaco.addPlayer(extra);
		monaco.addPlayer(trigree);
		playerRepository.save(eyeIgnite);
		playerRepository.save(extra);
		playerRepository.save(trigree);
		teamRepository.save(monaco);
		Player nachitow = new Player("Nachitow",endpoint);
		Player virtuoso = new Player("Virtuoso",endpoint);
		Player relatingWave = new Player("RelatingWave",endpoint);
		endpoint.addPlayer(nachitow);
		endpoint.addPlayer(virtuoso);
		endpoint.addPlayer(relatingWave);
		playerRepository.save(nachitow);
		playerRepository.save(virtuoso);
		playerRepository.save(relatingWave);
		teamRepository.save(endpoint);
		Player mognus = new Player("Mognus",tsm);
		Player metsanaurius = new Player("Metsanaurius",tsm);
		Player remkoe = new Player("remkoe",tsm);
		tsm.addPlayer(mognus);
		tsm.addPlayer(metsanaurius);
		tsm.addPlayer(remkoe);
		playerRepository.save(mognus);
		playerRepository.save(metsanaurius);
		playerRepository.save(remkoe);
		teamRepository.save(tsm);
		Player godsmilla = new Player("Godsmilla",singularity);
		Player noly = new Player("noly",singularity);
		Player tho = new Player("Tho",singularity);
		singularity.addPlayer(godsmilla);
		singularity.addPlayer(noly);
		singularity.addPlayer(tho);
		playerRepository.save(godsmilla);
		playerRepository.save(noly);
		playerRepository.save(tho);
		teamRepository.save(singularity);
		
	}

}
