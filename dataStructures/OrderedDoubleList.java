package dataStructures;

/**
 * @author Jorge Pereira 49771 jff.pereira@campus.fct.unl.pt
 * @author Tiago Fornelos 49780 t.fornelos@campus.fct.unl.pt
 * @param <K>
 * @param <V>
 */
public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

	private static final long serialVersionUID = 1L;
	protected int currentSize;
	protected DListNode<Entry<K, V>> head;
	protected DListNode<Entry<K, V>> tail;

	public OrderedDoubleList() {
		head = null;
		tail = null;
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {

		return currentSize == 0;
	}

	@Override
	public int size() {

		return currentSize;
	}

	protected DListNode<Entry<K, V>> searchSpot(K key) {
		DListNode<Entry<K, V>> s = head;

		if (this.isEmpty())
			return null;

		while (s.getNext() != null && s.getElement().getKey().compareTo(key) <= -1)
			s = s.getNext();

		return s;
	}

	@Override

	public V find(K key) {
		DListNode<Entry<K, V>> node = searchSpot(key);
		if (node == null)
			return null;
		else if (key.equals(node.getElement().getKey()))
			return node.getElement().getValue();
		else
			return null;
	}

	protected void insertFirst(K key, V value) {
		DListNode<Entry<K, V>> node = new DListNode<Entry<K, V>>(new EntryClass<K, V>(key, value), null, head);

		if (head == null)
			tail = node;
		else
			head.setPrevious(node);

		head = node;
		currentSize++;

	}

	protected void insertLast(K key, V value) {
		DListNode<Entry<K, V>> node = new DListNode<Entry<K, V>>(new EntryClass<K, V>(key, value), tail, null);

		if (tail == null)
			head = node;
		else
			tail.setNext(node);

		tail = node;
		currentSize++;
	}

	protected V insertMiddle(K key, V value) {
		DListNode<Entry<K, V>> s = searchSpot(key);
		DListNode<Entry<K, V>> newNode;
		V oldV = null;
		if (key.equals(s.getElement().getKey())) {
			oldV = s.getElement().getValue();
			s.setElement(new EntryClass<K, V>(key, value));
		} else {
			newNode = new DListNode<Entry<K, V>>(new EntryClass<K, V>(key, value), s.getPrevious(), s);
			if (s.getPrevious() != null)
				s.getPrevious().setNext(newNode);
			s.setPrevious(newNode);
			currentSize++;
		}
		return oldV;
	}

	@Override

	public V insert(K key, V value) {
		if (currentSize == 0 || head.getElement().getKey().compareTo(key) >= 1)
			insertFirst(key, value);
		else if (tail.getElement().getKey().compareTo(key) <= -1)
			insertLast(key, value);
		else
			return insertMiddle(key, value);
		return null;

	}

	/**
	 * Removes first element
	 * 
	 * @requires list must not be empty
	 */
	protected void removeFirst() {
		head = head.getNext();

		if (head == null)
			tail = null;
		else
			head.setPrevious(null);

		this.currentSize--;
	}

	/**
	 * Removes last element
	 * 
	 * @requires list must not be empty
	 */
	protected void removeLast() {
		tail = tail.getPrevious();

		if (tail == null)
			head = null;
		else
			tail.setNext(null);

		this.currentSize--;

	}

	protected void removeMiddle(DListNode<Entry<K, V>> node) {
		DListNode<Entry<K, V>> next = node.getNext();
		DListNode<Entry<K, V>> previous = node.getPrevious();
		next.setPrevious(previous);
		previous.setNext(next);

		this.currentSize--;

	}

	@Override
	public V remove(K key) {
		V value = null;
		DListNode<Entry<K, V>> node = searchSpot(key);
		if (node == null)
			return null;
		else if (node.getElement().getKey().equals(key)) {
			value = node.getElement().getValue();
			if (node == head)
				removeFirst();
			else if (node == tail)
				removeLast();
			else
				removeMiddle(node);
		}
		return value;

	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoublyLLIterator<>(head, tail);
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {

		return head.getElement();
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		// TODO Auto-generated method stub
		return tail.getElement();
	}

}
