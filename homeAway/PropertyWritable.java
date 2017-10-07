package homeAway;

/**
 * This is a Interface of a Property. Note this Inteface can not be used outside
 * the package. If you want to use it outside please search for Property Inteface in this package.
 * Note: With the other Inteface you will not be able not edit property information only read it.
 * 
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
interface PropertyWritable extends Property{
	
	/**
	 * This method adds a evaluation to the Property.
	 * This is only to be used if user is not the owner of this property.
	 * @requires User can not be the owner of the property
	 * @param points
	 */
	void evaluateStay(int points);
	
	/**
	 * This method adds a stay without evaluation
	 * This method is not to be used if the user is not the Owner
	 * @requires User needs to be the Owner
	 */
	void addStay();
	
}
