package homeAway;

import java.io.Serializable;

import dataStructures.Iterator;

/**
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public interface homeAwayManager extends Serializable {
	
	void newUser(String idUser, String email, String phoneNumber,
			String nacionality, String address, String name) throws UserAlreadyExistException; 
	
	void changeUserInformation(String idUser, String email, String phoneNumber,
			String address) throws UserDoesNotExistException ;
	
	void removeUser(String idUser) throws UserDoesNotExistException,UserIsOwnerException;
	
	User getUserInformation(String idUser) throws UserDoesNotExistException;
	
	
	public void addProperty(String idHome, String idUser, int price, int maxPersons, String local, String description,
			String address) throws InvalidInformationException, UserDoesNotExistException, PropertyAlreadyExistException;
	
	public void removeProperty(String idHome) throws PropertyDoesNotExistException, PropertyAlreadyVisitedException;
	
	
	public Property getPropertyInformation(String idHome) throws PropertyDoesNotExistException;
	
	public void addStayEvaluation(String idUser,String idHome,int points) throws InvalidInformationException,
	UserDoesNotExistException,PropertyDoesNotExistException, TravellerIsOwnerException;
	
	public void addStay(String idUser,String idHome) throws UserDoesNotExistException,PropertyDoesNotExistException,
	TravellerIsNotOwnerException;
	
	public Property listOwnerProperties(String idUser) throws UserDoesNotExistException,UserIsNotOwnerException;
	
	public Iterator<Property> listStays(String idUser) throws UserDoesNotExistException, UserIsNotTravellerException;
	
	public Property searchProperty(int persons,String local) throws InvalidInformationException, NoSearchResultsException;
	
	public Property listBestProperty(String local) throws NoSearchResultsException;

}
