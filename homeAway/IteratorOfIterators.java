package homeAway;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.NoSuchElementException;
import dataStructures.OrderedDictionary;

public class IteratorOfIterators implements Iterator<Property> {


	private static final long serialVersionUID = 1L;

	//Variables
	Iterator<Entry<Integer,OrderedDictionary<String,Property>>> primeIt;
	Iterator<Entry<String,Property>> secIt;
	
	public IteratorOfIterators(Iterator<Entry<Integer,OrderedDictionary<String,Property>>> it) {
		this.primeIt=it;
		this.rewind();
	}
	
	
	@Override
	public boolean hasNext() {
		return secIt != null;
	}

	@Override
	public Property next() throws NoSuchElementException {
		Property prop = secIt.next().getValue();
		
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
		if(primeIt.hasNext())
			secIt = primeIt.next().getValue().iterator();
	
	}

}
