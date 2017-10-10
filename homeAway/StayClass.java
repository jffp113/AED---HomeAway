package homeAway;

/**
 * StayClass represents a implementation of a
 *  stay of a user in a certain Property
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public class StayClass implements Stay {

	private static final long serialVersionUID = 0L;

	//variables
	private int points;
	private Property property;
	
	public StayClass(Property property, int points) {
		this.points=points;
		this.property=property;
	}
	
	@Override
	public Property getProperty() {
		return this.property;
	}

	@Override
	public int getPoints() {
		return this.points;
	}

}
