package diegojl97.rlpredictions.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User implements Comparable<User>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String username;
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	private boolean madeNAPrediction;
	@OneToOne(fetch=FetchType.EAGER)
	private Prediction naPrediction;
	
	private boolean madeEUPrediction;
	@OneToOne(fetch=FetchType.EAGER)
	private Prediction euPrediction;
	
	private Integer points;
	private Integer position;
	
	public User() {
		super();
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = new BCryptPasswordEncoder().encode(password);
		ArrayList<String> userRoles = new ArrayList<>();
		userRoles.add("ROLE_USER");
		this.roles = userRoles;
		this.madeNAPrediction = false;
		this.madeEUPrediction = false;
		this.points = 0;
		this.position = -1;
	}

	public User(String username, String password, List<String> roles) {
		super();
		this.username = username;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.roles = roles;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public boolean isMadeNAPrediction() {
		return madeNAPrediction;
	}

	public void setMadeNAPrediction(boolean madeNAPrediction) {
		this.madeNAPrediction = madeNAPrediction;
	}

	public Prediction getNaPrediction() {
		return naPrediction;
	}

	public void setNaPrediction(Prediction naPrediction) {
		this.naPrediction = naPrediction;
	}

	public boolean isMadeEUPrediction() {
		return madeEUPrediction;
	}

	public void setMadeEUPrediction(boolean madeEUPrediction) {
		this.madeEUPrediction = madeEUPrediction;
	}

	public Prediction getEuPrediction() {
		return euPrediction;
	}

	public void setEuPrediction(Prediction euPrediction) {
		this.euPrediction = euPrediction;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
	public int compareTo(User arg0) {
		return this.points - arg0.getPoints();
	}
	
}
