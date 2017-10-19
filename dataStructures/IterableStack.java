package dataStructures;

/**
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 * 
 * Stack with Iterator
 */
public interface IterableStack<E> extends Stack<E> {
	public Iterator<E> iterator();
}
