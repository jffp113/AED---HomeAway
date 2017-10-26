package dataStructures;

/**
 * @author Jorge Pereira 49771 jff.pereira@campus.fct.unl.pt
 * @author Tiago Fornelos 49780 t.fornelos@campus.fct.unl.pt
 * @param <K>
 * @param <V>
 */
public class OrderedDoubleList<K extends Comparable<K>,V> implements OrderedDictionary<K, V> {
	//Constants
	

	private static final long serialVersionUID = 1L;
	
	//Variables
	protected DListNode<Entry<K,V>> head;
	protected DListNode<Entry<K,V>> tail;
	protected int currentSize;
	
	//Constructor
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

	
	@Override
	public V find(K key) {
		DListNode<Entry<K,V>> next = searchSpot(key);
		
		if(next == null)
			return null;
		else if(next.getElement().getKey().compareTo(key) == 0)
			return next.getElement().getValue();
		else		
			return null;
	}

	protected void insertFirst(K key, V value) {
		DListNode<Entry<K,V>> node = new DListNode<Entry<K,V>>(new EntryClass<K,V>(key,value),null,head); 
				
		if(head == null)
			tail = node;
		else 
			head.setPrevious(node);
		
		head = node;
		currentSize++;
		
	}
	
	protected void insertLast(K key, V value) {
		DListNode<Entry<K,V>> node = new DListNode<Entry<K,V>>(new EntryClass<K,V>(key,value),tail,null);
		
		if(tail == null)
			head = node;
		else
			tail.setNext(node);
		
		tail = node;
		currentSize++;
	}
	
	protected V insertMiddle(K key, V value) {
		V oldV = null;
		DListNode<Entry<K,V>> s = searchSpot(key);
		DListNode<Entry<K,V>> node;
		
		if(s.getElement().getKey().compareTo(key) == 0) {//If the key is present substitute with new vaule
			oldV = s.getElement().getValue();
			s.setElement(new EntryClass<K,V>(key,value));
		}
		else {
			node = new DListNode<Entry<K,V>>(new EntryClass<K,V>(key,value),s.getPrevious(),s);
			if(s.getPrevious() != null)
				s.getPrevious().setNext(node);
			s.setPrevious(node);
			this.currentSize++;
		}
		
		
		return oldV;
	}
	
	/**
	 * Search the element or the nears element to that
	 * key
	 * @param key
	 * @return
	 */
	protected DListNode<Entry<K,V>> searchSpot(K key) {
		DListNode<Entry<K,V>> s = head;
		
		if(this.isEmpty())
			return null;
		
		while(s.getNext() != null && s.getElement().getKey().compareTo(key) <= -1)
			s = s.getNext();
		
		return s;
	}
	
	@Override
	public V insert(K key, V value) {
		V oldV = null;
		
		if(currentSize == 0 || head.getElement().getKey().compareTo(key) >= 1)//if there are no elements
			insertFirst(key,value);
		else if(tail.getElement().getKey().compareTo(key) <= -1)//if key is higher that
			insertLast(key, value);
		else 													//if the element must be in the middle
			oldV = insertMiddle(key,value);
		
		return oldV;
	}

	/**
	 * Removes first element
	 * @requires list must not be empty
	 */
	protected void removeFirst() {
		head = head.getNext();
		
		if(head == null)
			tail = null;
		else
			head.setPrevious(null);
		
		this.currentSize--;
	}
	
	/**
	 * Removes last element
	 * @requires list must not be empty
	 */
	protected void removeLast() {
		tail = tail.getPrevious();
		
		if(tail == null)
			head = null;
		else
			tail.setNext(null);
		
		this.currentSize--;
		
	}
	
	/**
	 * Removes middle node
	 * @param elem node where must be placed near or substitute
	 */
	protected void removeMiddle(DListNode<Entry<K, V>> elem) {
		DListNode<Entry<K,V>> next = elem.getNext();
		DListNode<Entry<K,V>> previous = elem.getPrevious();
		next.setPrevious(previous);
		previous.setNext(next);
		
		this.currentSize--;
		
	}
	
	@Override
	public V remove(K key) {
		DListNode<Entry<K,V>> elem = this.searchSpot(key);
		V old = null;
		
		if(elem == null)
			return null;
		
		else if(elem.getElement().getKey().compareTo(key) == 0) {
			old = elem.getElement().getValue();
			
			if(elem == head)
				removeFirst();
			else if (elem == tail)
				removeLast();
			else
				removeMiddle(elem);
		}
		return old;
	}


	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoublyLLIterator<Entry<K,V>>(head, tail);
	}


	@Override
	public Entry<K, V> minEntry() {
		return head.getElement();
	}


	@Override
	public Entry<K, V> maxEntry() {
		return  tail.getElement();
	}
}
