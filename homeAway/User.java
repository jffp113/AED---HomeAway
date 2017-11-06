package homeAway;

import java.io.Serializable;

import dataStructures.Iterator;

/**
 * This class contains public User methods that can be access outside the package
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public interface User extends Serializable{

	/**
	 * Get the user identification (id) 
	 * @return User identification as a String
	 */
	String getIdUser();
	
	/**
	 * Get User Name
	 * @return User Name as a String
	 */
	String getName();
	
	/**
	 * Get User Nationality
	 * @return User Nationality as a String
	 */
	String getNacionality();
	
	/**
	 * Gets User Address
	 * @return User Address as a String
	 */
	String getAddress();
	
	/**
	 * Gets User Email
	 * @return User Email as a String
	 */
	String getEmail();
	
	/**
	 * Get user phone Number
	 * @return Gets User Phone Number as a String
	 */
	String getPhoneNumber();
	
	
	Property listProperties() throws UserIsNotOwnerException;
	
	/**
	 * Checks if a user is Owner of a Properties
	 * @return <code>true<code> if user is owner , <code>false<code> otherwise
	 */
	boolean isOwner();
	
	/**
	 * Gets Number of Properties that a user own
	 * @return Number of Properties
	 */
	public int getNumberProperties();
	
	/**
	 * Get user stays Iterator
	 * @return Iterator to user stays
	 */
	public Iterator<Stay> getStaysIterator(); 
	
	
}
