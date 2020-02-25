package diegojl97.rlpredictions.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import diegojl97.rlpredictions.model.User;
import diegojl97.rlpredictions.repositories.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/registerPage")
	public String loadRegister (Model model) {
		return "register";
	}
	
	@PostMapping("/registerPage")
	public String createUser (Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
		if(username == "" || password == "") {
			String errorNull = "You must complete all the fields";
			model.addAttribute("error",errorNull);
			return "register";
		}
		User user = userRepository.findByUsername(username);
		if(user != null) {
			String errorUser = "Username already exists!";
			model.addAttribute("error",errorUser);
			return "register";
		}
		User newUser = new User(username,password);
		userRepository.save(newUser);
		return "login";
	}

}
