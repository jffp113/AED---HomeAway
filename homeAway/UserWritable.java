package homeAway;

/**
 * This Class contains methods that can not be access out of the homeAway package
 * The reason is to protected from unauthorized changes
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
interface UserWritable extends User  {
	
	/**
	 * Set user Email 
	 * @param newEmail New Email as a String
	 */
	void setEmail(String newEmail);
	
	/**
	 * Set user Phone Number
	 * @param newNumber New Phone Number as a String
	 */
	void setPhoneNumber(String newNumber);
	
	/**
	 * Set User Address
	 * @param newAddress New Address as a String
	 */
	void setAddress(String newAddress);
	
	/**
	 * Add a new Property to user ownership
	 * @param newProp New Property
	 */
	void addNewProperty(Property newProp);
	
	/**
	 * Add User stay on a Property evaluating
	 * @param property Stay Property
	 * @param points Points of the evaluation
	 */
   void addStay(Property property, int points);
	
	/**
	 * Remove a certain property
	 * @param idHome identification of the Property to be removed
	 */
	void removeProperty(String idHome);
	/**
	 * Set all user information
	 * @param newEmail New Email as a String
	 * @param newNumber New Number as a string
	 * @param newAddress  New Adress as a String
	 */
	void changeInfo(String newEmail,String newNumber,String newAddress);
}
