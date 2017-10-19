package dataStructures;

/**
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 * 
 * Stack Iterator, adds the a method so that the queue can be iterated
 */
public class IterableStackInList<E> extends StackInList<E> implements IterableStack<E> {

	private static final long serialVersionUID = 1L;

	public IterableStackInList() {
		super();
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

}
