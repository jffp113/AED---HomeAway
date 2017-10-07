package homeAway;

import java.io.Serializable;

import dataStructures.Iterator;

/**
 * This is the top Class of the HomeAway Project
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public interface homeAwayManager extends Serializable {
	
	/**
	 * Add's a new user to the database
	 * @param idUser id of the User
	 * @param email email of the User
	 * @param phoneNumber number of the User
	 * @param nacionality User nationality
	 * @param address User address
	 * @param name User Name
	 * @throws UserAlreadyExistException Exception if the User Already Exist on the Database
	 */
	void newUser(String idUser, String email, String phoneNumber,
			String nacionality, String address, String name) throws UserAlreadyExistException; 
	
	/**
	 * This method changes the User information of a certain User
	 * @param idUser id of the User to be changed
	 * @param email new email
	 * @param phoneNumber new phone number
	 * @param address new address
	 * @throws UserDoesNotExistException Exception if user does not exist on the database
	 */
	void changeUserInformation(String idUser, String email, String phoneNumber,
			String address) throws UserDoesNotExistException ;
	
	/**
	 * This method removes a certain user
	 * @param idUser id of the User to be removed
	 * @throws UserDoesNotExistException Exception if the user does not exist
	 * @throws UserIsOwnerException Exception if the user is Owner
	 */
	void removeUser(String idUser) throws UserDoesNotExistException,UserIsOwnerException;
	
	/**
	 * This method returns a User in order to get the User information.
	 * Note: The user returned only has methods to read the information
	 * @param idUser id User to get Information
	 * @return Only readable User
	 * @throws UserDoesNotExistException Exception if the User Does not exist on the database
	 */
	User getUserInformation(String idUser) throws UserDoesNotExistException;
	
	/**
	 * This method adds a new Property to the Database
	 * @param idHome id of the Home
	 * @param idUser id of the User that owns the Property
	 * @param price price of the stay
	 * @param maxPersons max number of persons that can sleep on a night
	 * @param local Local of the Property
	 * @param description Description of the Property
	 * @param address Address of the Property
	 * @throws InvalidInformationException Exception if there is any invalid information
	 * @throws UserDoesNotExistException Exception if the User Does Not exist on the system
	 * @throws PropertyAlreadyExistException Exception if the Property that is being created already exist on the database
	 */
	public void addProperty(String idHome, String idUser, int price, int maxPersons, String local, String description,
			String address) throws InvalidInformationException, UserDoesNotExistException, PropertyAlreadyExistException;
	
	/**
	 * This method removes a certain Property
	 * @param idHome id of the home to be removed
	 * @throws PropertyDoesNotExistException  Exception  if the Property does not exist
	 * @throws PropertyAlreadyVisitedException Exception if the Property was already visited
	 */
	public void removeProperty(String idHome) throws PropertyDoesNotExistException, PropertyAlreadyVisitedException;
	
	/**
	 *  This method is used to get a Property in order to list the information
	 *  About this property
	 *  Note this property is readable only
	 * @param idHome ID of the home to get the Information
	 * @return Property each was selected by idHome
	 * @throws PropertyDoesNotExistException Exception if  Property does not exist
	 */
	public Property getPropertyInformation(String idHome) throws PropertyDoesNotExistException;
	
	/**
	 * This method allows to add a stay with evaluation
	 * @param idUser 
	 * @param idHome
	 * @param points
	 * @throws InvalidInformationException
	 * @throws UserDoesNotExistException
	 * @throws PropertyDoesNotExistException
	 * @throws TravellerIsOwnerException
	 */
	public void addStayEvaluation(String idUser,String idHome,int points) throws InvalidInformationException,
	UserDoesNotExistException,PropertyDoesNotExistException, TravellerIsOwnerException;
	
	/**
	 * This method allows to add a stay of a certain user to a Property
	 * @param idUser id of the user to stay in the Property
	 * @param idHome if of the Home
	 * @throws UserDoesNotExistException Exception if the User Does not exist
	 * @throws PropertyDoesNotExistException Exception if the Property does not exist
	 * @throws TravellerIsNotOwnerException Exception if the user is not the owner of the Property
	 */
	public void addStay(String idUser,String idHome) throws UserDoesNotExistException,PropertyDoesNotExistException,
	TravellerIsNotOwnerException;
	
	/**
	 * This method allows to list a Owner Property
	 * Note: This Property is readable only
	 * @param idUser id Of the User
	 * @return Iterator to the User Properties
	 * @throws UserDoesNotExistException Exception if the User Does Not exist
	 * @throws UserIsNotOwnerException Exception if the User if not Owner
	 */
	public Property listOwnerProperties(String idUser) throws UserDoesNotExistException,UserIsNotOwnerException;
	
	/**
	 * This method returns a Iterator to the User stays
	 * @param idUser user to get his stays
	 * @return Iterator to the Stays
	 * @throws UserDoesNotExistException
	 * @throws UserIsNotTravellerException
	 */
	public Iterator<Stay> listStays(String idUser) throws UserDoesNotExistException, UserIsNotTravellerException;
	
	/**
	 * This method search for a property that match 
	 * the number of persons and the local
	 * @param persons Number of persons
	 * @param local Local Of the Property
	 * @return Iterator to Properties
	 * @throws InvalidInformationException
	 * @throws NoSearchResultsException
	 */
	public Property searchProperty(int persons,String local) throws InvalidInformationException, NoSearchResultsException;
	
	/**
	 * List Best Properties on the database
	 * @param local Local of the Property
	 * @return Iterator to best Property
	 * @throws NoSearchResultsException
	 */
	public Property listBestProperty(String local) throws NoSearchResultsException;

}
