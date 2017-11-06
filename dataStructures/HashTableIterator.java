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
		return currentIt.hasNext();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException();
		Entry<K, V> next = currentIt.next();
		if (!hasNext())
			nextList();
		return next;
	}

	@Override
	public void rewind() {
		current = 0;
		currentIt = table[0].iterator();
		nextList();
	}

	protected void nextList() {

		while (current < this.table.length && table[current].isEmpty())
			current++;
		if(current <this.table.length)
		currentIt = table[current++].iterator();
		
	}

}
