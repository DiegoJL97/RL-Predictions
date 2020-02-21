package diegojl97.rlpredictions.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import diegojl97.rlpredictions.security.UserSessionInfoComponent;

@Controller
public class LoginController {
	
	@Autowired
	private UserSessionInfoComponent userSession;

	@RequestMapping("/registerPage")
	public String loadRegister (Model model) {
		model.addAttribute("logged", userSession.getLoggedUser());
		return "register";
	}

}
