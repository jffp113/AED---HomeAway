package dataStructures;
/**
 * 
 * @author jorge
 *
 * @param <K>
 * @param <V>
 */
public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K,V>> {
	
	private static final long serialVersionUID = 1L;
	
	//Variaveis
	protected BSTNode<K,V> root;
	protected Stack<BSTNode<K,V>> stack;
	
	public BSTKeyOrderIterator(BSTNode<K,V> root) {
		this.root = root;
		rewind();
	}
	
	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		if(!hasNext())
			throw new NoSuchElementException();
		
		BSTNode<K,V> next = stack.pop();
		
		if(next.getLeft() != null) {
			stack.push(next.getLeft());
			pushLeft(next.getLeft());
		}
			
		
		return next.getEntry();
	}

	@Override
	public void rewind() {
		stack = new StackInList<BSTNode<K,V>>();
		stack.push(root);
		pushLeft(root);
	}
	
	protected void pushLeft(BSTNode<K,V> node){
		while(node.getRight() != null) {
			stack.push(node.getRight());
			node = node.getRight();
		}
	}
}
