package homeAway;

import dataStructures.Iterator;
import dataStructures.NoSuchElementException;

/**
 * This Iterator Filter by number of persons
 * 
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public class FilterPeopleIterator implements Iterator<Property> {


	private static final long serialVersionUID = 1L;

	//Variables
	private Iterator<Property> it;
	private int min;
	private Property next;
	
	public FilterPeopleIterator(Iterator<Property> it , int min) {
		this.it = it;
		this.min = min;
		this.rewind();
	}
	
	@Override
	public boolean hasNext() {
		return next != null && next.getMaxPersons() >= min;
	}

	@Override
	public Property next() throws NoSuchElementException {
		
		if(next == null)
			throw new NoSuchElementException();
		
		Property toGo = this.next;
		
		if(it.hasNext())
			this.next = it.next();
		else
			next = null;
		
		return toGo;
	}

	@Override
	public void rewind() {
		it.rewind();
		
		while(it.hasNext() && (next = it.next()).getMaxPersons() < min);
		
	}

}
