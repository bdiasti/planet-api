package br.com.b2w.mapper;

import java.util.List;

/**
 * @author bdiasti
 *
 *Classe responsável por representar o json que vem da api star wars
 *
 */
public class PlanetMapper {
	
	private int count;
	
	private int next;
	
	private int previous;
	
	private List<PlanetJSON> results;
	

	public List<PlanetJSON> getResults() {
		return results;
	}

	public void setResults(List<PlanetJSON> results) {
		this.results = results;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrevious() {
		return previous;
	}

	public void setPrevious(int previous) {
		this.previous = previous;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}
	

}
