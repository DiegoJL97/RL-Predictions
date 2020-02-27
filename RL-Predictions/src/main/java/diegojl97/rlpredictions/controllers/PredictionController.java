package diegojl97.rlpredictions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import diegojl97.rlpredictions.security.UserSessionInfoComponent;

@Controller
public class PredictionController {
	
	@Autowired
	private UserSessionInfoComponent userSession;
	
	@RequestMapping("/napredictions")
	public String loadNAPredictions(Model model) {
		//Cargar todos los equipos y jugadores de NA de la BBDD
		model.addAttribute("logged", userSession.getLoggedUser());
		return "makePrediction";
	}
	
	@RequestMapping("/eupredictions")
	public String loadEUPredictions(Model model) {
		//Cargar todos los equipos y jugadores de EU de la BBDD
		model.addAttribute("logged", userSession.getLoggedUser());
		return "makePrediction";
	}

}
