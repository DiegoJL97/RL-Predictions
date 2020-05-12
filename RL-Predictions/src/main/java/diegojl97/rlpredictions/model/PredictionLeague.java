package diegojl97.rlpredictions.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PredictionLeague implements Iterable<Team> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Team first;
	@OneToOne(fetch=FetchType.EAGER)
	private Team second;
	@OneToOne(fetch=FetchType.EAGER)
	private Team third;
	@OneToOne(fetch=FetchType.EAGER)
	private Team fourth;
	@OneToOne(fetch=FetchType.EAGER)
	private Team fifth;
	@OneToOne(fetch=FetchType.EAGER)
	private Team sixth;
	@OneToOne(fetch=FetchType.EAGER)
	private Team seventh;
	@OneToOne(fetch=FetchType.EAGER)
	private Team eighth;
	@OneToOne(fetch=FetchType.EAGER)
	private Team ninth;
	@OneToOne(fetch=FetchType.EAGER)
	private Team tenth;
	
	public PredictionLeague() {
		
	}

	public PredictionLeague(long id, Team first, Team second, Team third, Team fourth, Team fifth, Team sixth,Team seventh, Team eighth, Team ninth, Team tenth) {
		super();
		this.id = id;
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
		this.sixth = sixth;
		this.seventh = seventh;
		this.eighth = eighth;
		this.ninth = ninth;
		this.tenth = tenth;
	}

	public Team getFirst() {
		return first;
	}

	public void setFirst(Team first) {
		this.first = first;
	}

	public Team getSecond() {
		return second;
	}

	public void setSecond(Team second) {
		this.second = second;
	}

	public Team getThird() {
		return third;
	}

	public void setThird(Team third) {
		this.third = third;
	}

	public Team getFourth() {
		return fourth;
	}

	public void setFourth(Team fourth) {
		this.fourth = fourth;
	}

	public Team getFifth() {
		return fifth;
	}

	public void setFifth(Team fifth) {
		this.fifth = fifth;
	}

	public Team getSixth() {
		return sixth;
	}

	public void setSixth(Team sixth) {
		this.sixth = sixth;
	}

	public Team getSeventh() {
		return seventh;
	}

	public void setSeventh(Team seventh) {
		this.seventh = seventh;
	}

	public Team getEighth() {
		return eighth;
	}

	public void setEighth(Team eighth) {
		this.eighth = eighth;
	}

	public Team getNinth() {
		return ninth;
	}

	public void setNinth(Team ninth) {
		this.ninth = ninth;
	}

	public Team getTenth() {
		return tenth;
	}

	public void setTenth(Team tenth) {
		this.tenth = tenth;
	}

	@Override
	public Iterator<Team> iterator() {
		return new LeagueIterator();
	}
	
	private class LeagueIterator implements Iterator<Team> {
		
        private int position = 1;
 
        public boolean hasNext() {
            return position <= 10;
        }
 
        public Team next() {
        	if(!hasNext()){
        	      throw new NoSuchElementException();
    	    }
            switch(position) {
            	case 1: position++;
            			return first;
            	case 2: position++; 
            			return second;
            	case 3: position++;
            			return third;
            	case 4: position++;
            			return fourth;
            	case 5: position++;
            			return fifth;
            	case 6: position++;
            			return sixth;
            	case 7: position++;
            			return seventh;
            	case 8: position++;
            			return eighth;
            	case 9: position++;
            			return ninth;
            	case 10: position++;
            			return tenth;
            	default: throw new NoSuchElementException();
            }
        }
 
        @Override
        public void remove() {
        	throw new UnsupportedOperationException();
        }
    }

}
