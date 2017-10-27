package dataStructures;

/**
 * Chained Hash table implementation
 * 
 * @author AED Team
 * @version 1.0
 * @param <K>
 *            Generic Key, must extend comparable
 * @param <V>
 *            Generic Value
 */

public class ChainedHashTable<K extends Comparable<K>, V> extends HashTable<K, V> {
	/**
	 * Serial Version UID of the Class.
	 */
	static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
	protected Dictionary<K, V>[] table;

	/**
	 * Constructor of an empty chained hash table, with the specified initial
	 * capacity. Each position of the array is initialized to a new ordered list
	 * maxSize is initialized to the capacity.
	 * 
	 * @param capacity
	 *            defines the table capacity.
	 */
	public ChainedHashTable(int capacity) {
		int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
		initNew(arraySize);
	}

	public ChainedHashTable() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Returns the hash value of the specified key.
	 * 
	 * @param key
	 *            to be encoded
	 * @return hash value of the specified key
	 */
	protected int hash(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	@SuppressWarnings("unchecked")
	protected void initNew(int size) {
		table = (Dictionary<K, V>[]) new Dictionary[size];

		for (int i = 0; i < size; i++)
			table[i] = new OrderedDoubleList<K, V>();

		maxSize = size;
		currentSize = 0;
	}

	@Override
	public V find(K key) {
		return table[this.hash(key)].find(key);
	}

	@Override
	public V insert(K key, V value) {
		V oldV = null;
		if (this.isFull())
			this.rehash();

		oldV = table[this.hash(key)].insert(key, value);
		if (oldV == null)
			super.currentSize++;

		return oldV;
	}

	protected void rehash() {
		Iterator<Entry<K, V>> it = this.iterator();
		int nextPrime = super.nextPrime(table.length * 2);
		this.initNew(nextPrime);
		Entry<K, V> next;
		while (it.hasNext()) {
			next = it.next();
			this.insert(next.getKey(), next.getValue());
		}
	}

	@Override
	public V remove(K key) {
		V oldV = null;
		oldV = table[this.hash(key)].remove(key);

		if (oldV != null)
			super.currentSize--;

		return oldV;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new HashTableIterator<K, V>(table);
	}
}
