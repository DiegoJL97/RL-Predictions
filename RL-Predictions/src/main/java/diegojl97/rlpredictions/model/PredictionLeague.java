package diegojl97.rlpredictions.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PredictionLeague implements Iterable<TeamPrediction> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	private TeamPrediction first;
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	private TeamPrediction second;
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	private TeamPrediction third;
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	private TeamPrediction fourth;
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	private TeamPrediction fifth;
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	private TeamPrediction sixth;
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	private TeamPrediction seventh;
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	private TeamPrediction eighth;
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	private TeamPrediction ninth;
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	private TeamPrediction tenth;
	
	public PredictionLeague() {
		
	}

	public PredictionLeague(long id, TeamPrediction first, TeamPrediction second, TeamPrediction third, TeamPrediction fourth, TeamPrediction fifth, TeamPrediction sixth,TeamPrediction seventh, TeamPrediction eighth, TeamPrediction ninth, TeamPrediction tenth) {
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

	public TeamPrediction getFirst() {
		return first;
	}

	public void setFirst(TeamPrediction first) {
		this.first = first;
	}

	public TeamPrediction getSecond() {
		return second;
	}

	public void setSecond(TeamPrediction second) {
		this.second = second;
	}

	public TeamPrediction getThird() {
		return third;
	}

	public void setThird(TeamPrediction third) {
		this.third = third;
	}

	public TeamPrediction getFourth() {
		return fourth;
	}

	public void setFourth(TeamPrediction fourth) {
		this.fourth = fourth;
	}

	public TeamPrediction getFifth() {
		return fifth;
	}

	public void setFifth(TeamPrediction fifth) {
		this.fifth = fifth;
	}

	public TeamPrediction getSixth() {
		return sixth;
	}

	public void setSixth(TeamPrediction sixth) {
		this.sixth = sixth;
	}

	public TeamPrediction getSeventh() {
		return seventh;
	}

	public void setSeventh(TeamPrediction seventh) {
		this.seventh = seventh;
	}

	public TeamPrediction getEighth() {
		return eighth;
	}

	public void setEighth(TeamPrediction eighth) {
		this.eighth = eighth;
	}

	public TeamPrediction getNinth() {
		return ninth;
	}

	public void setNinth(TeamPrediction ninth) {
		this.ninth = ninth;
	}

	public TeamPrediction getTenth() {
		return tenth;
	}

	public void setTenth(TeamPrediction tenth) {
		this.tenth = tenth;
	}

	@Override
	public Iterator<TeamPrediction> iterator() {
		return new LeagueIterator();
	}
	
	private class LeagueIterator implements Iterator<TeamPrediction> {
		
        private int position = 1;
 
        public boolean hasNext() {
            return position <= 10;
        }
 
        public TeamPrediction next() {
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
