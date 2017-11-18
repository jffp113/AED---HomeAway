package dataStructures;

/**
 * This Iterator Filter the Iteration by number of people
 *
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public class EntryFilterIterator<K,V> implements Iterator<V> {


	private static final long serialVersionUID = 1L;
	
	//Variables
	Iterator<Entry<K,V>> it;
	
	public EntryFilterIterator(Iterator<Entry<K,V>> it) {
		this.it = it;
	}
	
	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public V next() throws NoSuchElementException {
		return it.next().getValue();
	}

	@Override
	public void rewind() {
		this.it.rewind();
	}

}
