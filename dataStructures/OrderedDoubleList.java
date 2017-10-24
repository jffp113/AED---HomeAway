package dataStructures;

/**
 * @author Jorge Pereira 49771 jff.pereira@campus.fct.unl.pt
 * @author Tiago Fornelos 49780 t.fornelos@campus.fct.unl.pt
 * @param <K>
 * @param <V>
 */
public class OrderedDoubleList<K extends Comparable<K>,V> implements OrderedDictionary<K, V> {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public V find(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V insert(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		// TODO Auto-generated method stub
		return null;
	}

}

