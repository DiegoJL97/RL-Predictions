package diegojl97.rlpredictions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/registerPage")
	public String loadRegister (Model model) {
		return "register";
	}

}
