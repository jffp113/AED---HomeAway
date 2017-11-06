package dataStructures;

/**
 * @author Jorge Pereira 49771 jff.pereira@campus.fct.unl.pt
 * @author Tiago Fornelos 49780 t.fornelos@campus.fct.unl.pt
 * @param <K>
 * @param <V>
 */
class HashTableIterator<K, V> implements Iterator<Entry<K, V>> {

	private static final long serialVersionUID = 0L;

	// Variables
	protected int current;
	protected Iterator<Entry<K, V>> currentIt;
	Dictionary<K, V>[] table;

	// Constructor
	public HashTableIterator(Dictionary<K, V>[] table) {
		this.table = table;
		rewind();
	}

	@Override
	public boolean hasNext() {
		return currentIt.hasNext(); //check if the current iterator has a next entry
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		if (!hasNext())							// if there is no next entry the method
			throw new NoSuchElementException(); // throws an exception
		Entry<K, V> next = currentIt.next();    //get the next entry from currentIt
		if (!hasNext())							//check if there are more entries in currentIt
			nextList();							//if not get the next list
		return next;							//return the entry
	}

	@Override
	public void rewind() {				
		current = 0;
		currentIt = table[0].iterator(); 
		nextList();
	}

	protected void nextList() {

		while (current < this.table.length && table[current].isEmpty()) //get the next list not empty
			current++;													//from the hash table
		if(current <this.table.length)
		currentIt = table[current++].iterator();	
	}
}
