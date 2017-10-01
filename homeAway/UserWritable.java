package homeAway;

/**
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
interface UserWritable extends User  {
	
	void setEmail(String newEmail);
	void setPhoneNumber(String newNumber);
	void setAddress(String newAddress);
	
	void addNewProperty(Property newProp);
	public void addStay(Property property, int points);
	void removeProperty();
}
