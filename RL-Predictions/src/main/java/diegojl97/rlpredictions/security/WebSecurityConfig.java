package diegojl97.rlpredictions.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/register").permitAll();
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/h2-console/**").permitAll();

		// Private pages
		http.authorizeRequests().antMatchers("/chooseLeague").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/napredictions").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/eupredictions").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/makePredictions").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/goHome").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/leaderboard").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/modifyPrediction").hasAnyRole("USER","ADMIN");
		
		http.authorizeRequests().antMatchers("/startLeague").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/finishLeague").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/resetLeagues").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/modifyTeam/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/redirectModify").hasAnyRole("ADMIN");
		

		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().failureUrl("/login");

		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider);

	}

}