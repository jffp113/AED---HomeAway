package homeAway;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.NoSuchElementException;
import dataStructures.OrderedDictionary;

/**
 * This Iterator can receive multiples Iterators in order to iterate all without the need
 * of 2 cycles
 *
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public class IteratorOfIterators<K,SK  extends Comparable<SK>,E> implements Iterator<E> {


	private static final long serialVersionUID = 1L;

	//Variables
	private Iterator<Entry<K,OrderedDictionary<SK,E>>> primeIt;
	private Iterator<Entry<SK,E>> secIt;
	
	public IteratorOfIterators(Iterator<Entry<K,OrderedDictionary<SK,E>>> it) {
		this.primeIt=it;
		this.rewind();
	}
	/**
	 * Verify if secIt is not empty
	 */
	
	@Override
	public boolean hasNext() {
		return secIt != null;
	}
	/**
	 * Returnt the next element from secIt. If secIt !hasNext(), verify if primeIt
	 * has a next entry. If so , gets the iterator for that entry and saves it at secIt.
	 */
	@Override
	public E next() throws NoSuchElementException {
		
		if(secIt == null)
			throw new NoSuchElementException();
		
			E prop = secIt.next().getValue();
		
		if(!secIt.hasNext()) {
			if(primeIt.hasNext())
				secIt = primeIt.next().getValue().iterator();
			else
				secIt = null;
		}
		
		return prop;
	}

	/**
	 * gets the iterator for the first primeIt entry(if primeIt is not empty) and saves at secIt
	 */
	@Override
	public void rewind() {
		secIt = null;
		primeIt.rewind();
		
		if(primeIt.hasNext())
			secIt = primeIt.next().getValue().iterator();
	
	}

}
