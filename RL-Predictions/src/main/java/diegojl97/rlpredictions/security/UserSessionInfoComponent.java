package diegojl97.rlpredictions.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import diegojl97.rlpredictions.model.User;

@Component
@SessionScope
public class UserSessionInfoComponent {
	
	private User user;
	
	public User getLoggedUser() {
		return user;
	}

	public void setLoggedUser(User user) {
		this.user = user;
	}

	public boolean isLoggedUser() {
		return this.user != null;
	}

}
