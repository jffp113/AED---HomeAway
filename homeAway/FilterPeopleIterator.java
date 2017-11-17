package homeAway;

import dataStructures.Iterator;
import dataStructures.NoSuchElementException;

public class FilterPeopleIterator<E> implements Iterator<E> {


	private static final long serialVersionUID = 1L;

	//Variabels
	private Iterator<E> it;
	private int min;
	
	public FilterPeopleIterator(Iterator<E> it , int min) {
		this.it = it;;
		this.min = min;
	}
	
	@Override
	public boolean hasNext() {
		return it != null;
	}

	@Override
	public E next() throws NoSuchElementException {
		E next = it.next();
		
		
		return null;
	}

	@Override
	public void rewind() {
		it.rewind();
	}

}
