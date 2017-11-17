package homeAway;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.NoSuchElementException;
import dataStructures.OrderedDictionary;

public class IteratorOfIterators<K,SK  extends Comparable<SK>,E> implements Iterator<E> {


	private static final long serialVersionUID = 1L;

	//Variables
	private Iterator<Entry<K,OrderedDictionary<SK,E>>> primeIt;
	private Iterator<Entry<SK,E>> secIt;
	
	public IteratorOfIterators(Iterator<Entry<K,OrderedDictionary<SK,E>>> it) {
		this.primeIt=it;
		this.rewind();
	}
	
	
	@Override
	public boolean hasNext() {
		return secIt != null;
	}

	@Override
	public E next() throws NoSuchElementException {
		E prop = secIt.next().getValue();
		
		if(!secIt.hasNext()) {
			if(primeIt.hasNext())
				secIt = primeIt.next().getValue().iterator();
			else
				secIt = null;
		}
		
		return prop;
	}


	@Override
	public void rewind() {
		secIt = null;
		primeIt.rewind();
		if(primeIt.hasNext())
			secIt = primeIt.next().getValue().iterator();
	
	}

}
