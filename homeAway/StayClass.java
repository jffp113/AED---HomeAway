package homeAway;

public class StayClass implements Stay {

	/**
	 * 
	 */
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
