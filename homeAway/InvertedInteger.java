package homeAway;

import java.io.Serializable;

/**
 *This class inverts the compareTo method of a Integer 
 * 
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
class InvertedInteger implements Comparable<InvertedInteger> , Serializable{


	private static final long serialVersionUID = 1L;
	
	//Variables
	private Integer a;
	
	public InvertedInteger(int a) {
		this.a = new Integer(a);
	}
	/**
	 * Inverted compareTo(to save the properties from the higher key to the lower one)
	 */
	
	@Override
	public int compareTo(InvertedInteger anotherInteger) {
		
		return a.compareTo(anotherInteger.getInteger()) * -1;
	}

	/**
	 * Gets the Object
	 * @return Integer
	 */
	public Integer getInteger() {
		return a;
	}

}
