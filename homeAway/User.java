package homeAway;

import java.io.Serializable;

import dataStructures.Iterator;

/**
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public interface User extends Serializable{

	String getIdUser();
	
	String getName();
	
	String getNacionality();
	
	String getAddress();
	
	String getEmail();
	
	String getPhoneNumber();
	
	Property listProperties() throws UserIsNotOwnerException;
	
	boolean isOwner(); // access on main ?
	
	public int getNumberProperties();
	
	public Iterator<Property> getStaysIterator(); // access on main ?
}
