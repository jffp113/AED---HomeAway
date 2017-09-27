package homeAway;

import dataStructures.Iterator;

public class homeAwayManagerClass implements homeAwayManager {
	// Constants

	// Variables
	private User users;
	private Property properties;

	// Constructor

	@Override
	public void newUser(String idUser, String email, String phoneNumber, String nacionality, String address)
			throws UserAlreadyExistException {

		if (users.getIdUser().equals(idUser)) {
			throw new UserAlreadyExistException();
		}

		users = new UserClass(idUser, email, phoneNumber, nacionality, address);

	}

	@Override
	public void changeUserInformation(String idUser, String email, String phoneNumber, String address)
			throws InexistentUserException {

		if (!users.getIdUser().equals(idUser)) {
			throw new InexistentUserException();
		}
		UserWritable u;
		u = (UserWritable) users;
		u.setEmail(email);
		u.setPhoneNumber(phoneNumber);
		u.setAddress(address);

	}

	@Override
	public void removeUser(String idUser) throws InexistentUserException, UserIsOwnerException {
		if (!users.getIdUser().equals(idUser))
			throw new InexistentUserException();
		else if (users.isOwner())
			throw new UserIsOwnerException();
	}

	@Override
	public User getUserInformation(String idUser) throws InexistentUserException {
		if (!users.getIdUser().equals(idUser))
			throw new InexistentUserException();

		return users;
	}

	public void addProperty(String idHome, String idUser, int price, int maxPersons, String local, String description,
			String address) throws InvalidInformationException, InexistentUserException, PropertyAlreadyExistException {
		if(price<0 || maxPersons<0)
			throw new InvalidInformationException();
		else if(users == null || !users.getIdUser().equals(idUser))
			throw new InexistentUserException();
		else if(properties.getIdHome().equals(idHome))
			throw new PropertyAlreadyExistException();
		
		properties = new PropertyClass(idHome, users, price, maxPersons, local, description, address);
	}
	
	public void removeProperty(String idHome) throws InexistentPropertyException, PropertyAlreadyVisitedException{
		if(properties == null || !properties.getIdHome().equals(idHome))
			throw new InexistentPropertyException();
		else if(properties.getNumberOfvisits() != 0)
			throw new PropertyAlreadyVisitedException();
		
		properties = null;
		
	}

	public Property getPropertyInformation(String idHome) throws InexistentPropertyException{
		if(properties == null || !properties.getIdHome().equals(idHome))
			throw new InexistentPropertyException();
		
		return properties;
	}
	
	public void addStayEvaluation(String idUser,String idHome,int points) throws InvalidInformationException,
	InexistentUserException,InexistentPropertyException, UserIsOwnerException{//Listagem dentro user
		if(points > 0)
			throw new InvalidInformationException();
		else if(users == null || !users.getIdUser().equals(idUser))
			throw new InexistentUserException();
		else if(properties == null || !properties.getIdHome().equals(idHome))
			throw new InexistentPropertyException();
		else if (properties.getOwner().getIdUser().equals(idUser))
			throw new UserIsOwnerException();
		
		((PropertyWritable)properties).evaluateStay(points,users);
	}
	
	public void addStay(String idUser,String idHome) throws InexistentUserException,InexistentPropertyException,
		UserIsNotOwnerException{

		if(users == null || !users.getIdUser().equals(idUser))
			throw new InexistentUserException();
		else if(properties == null || !properties.getIdHome().equals(idHome))
			throw new InexistentPropertyException();
		else if (!properties.getOwner().getIdUser().equals(idUser))
			throw new UserIsNotOwnerException();
		
		((PropertyWritable)properties).stay(users);
	}
	
	public Property listOwnerProperties(String idUser) throws InexistentUserException,UserIsNotOwnerException{
		if(users == null || !users.getIdUser().equals(idUser))
			throw new InexistentUserException();
		
		return users.listProperties();
	}
	
	public Iterator<Property> listStays(String idUser) throws InexistentUserException, UserIsNotTraveller{
		if(users == null || !users.getIdUser().equals(idUser))
			throw new InexistentUserException();
		if(!users.getStaysIterator().hasNext())
			throw new UserIsNotTraveller();
		
		return users.getStaysIterator();
	}
	
	public Property searchProperty(int persons,String local) throws InvalidInformationException, NoSearchResultsException {
		if(persons < 0)
			throw new InvalidInformationException();
		else if(properties.getMaxPersons() != persons || properties.getLocal().equals(local))
			throw new NoSearchResultsException();
		
		return properties;
	}
	
	public Property listBestProperty(String local) throws NoSearchResultsException {
		if(properties == null || !properties.getLocal().equals(local))
			throw new NoSearchResultsException();
			return properties;
	}
}
