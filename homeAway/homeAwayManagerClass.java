package homeAway;

import dataStructures.Iterator;

public class HomeAwayManagerClass implements HomeAwayManager {
	// Constants

	private static final long serialVersionUID = 0L;
	
	// Variables
	private User users;
	private Property properties;

	// Constructor

	@Override
	public void newUser(String idUser, String email, String phoneNumber, String nacionality, String address, String name)
			throws UserAlreadyExistException {

		if (users != null && users.getIdUser().equalsIgnoreCase(idUser)) {
			throw new UserAlreadyExistException();
		}

		users = new UserClass(idUser, email, phoneNumber, nacionality, address,name);

	}

	@Override
	public void changeUserInformation(String idUser, String email, String phoneNumber, String address)
			throws UserDoesNotExistException {

		if (users == null || !users.getIdUser().equalsIgnoreCase(idUser)) {
			throw new UserDoesNotExistException();
		}
		UserWritable u;
		u = (UserWritable) users;
		u.setEmail(email);
		u.setPhoneNumber(phoneNumber);
		u.setAddress(address);

	}

	@Override
	public void removeUser(String idUser) throws UserDoesNotExistException, UserIsOwnerException {
		if (users == null || !users.getIdUser().equalsIgnoreCase(idUser))
			throw new UserDoesNotExistException();
		else if (users.isOwner())
			throw new UserIsOwnerException();
		
		users = null;
	}

	@Override
	public User getUserInformation(String idUser) throws UserDoesNotExistException {
		if (users == null || !users.getIdUser().equalsIgnoreCase(idUser))
			throw new UserDoesNotExistException();

		return users;
	}

	public void addProperty(String idHome, String idUser, int price, int maxPersons, String local, String description,
			String address) throws InvalidInformationException, UserDoesNotExistException, PropertyAlreadyExistException {
		if(price<=0 || maxPersons<=0 || maxPersons >20)
			throw new InvalidInformationException();
		else if(users == null || !users.getIdUser().equalsIgnoreCase(idUser))
			throw new UserDoesNotExistException();
		else if(properties != null && properties.getIdHome().equalsIgnoreCase(idHome))
			throw new PropertyAlreadyExistException();
		
		properties = new PropertyClass(idHome, users, price, maxPersons, local, description, address);
		((UserWritable)users).addNewProperty(properties);
	}
	
	public void removeProperty(String idHome) throws PropertyDoesNotExistException, PropertyAlreadyVisitedException{
	
		
		if(properties == null || !properties.getIdHome().equalsIgnoreCase(idHome))
			throw new PropertyDoesNotExistException();
		else if(properties.getNumberOfvisits() != 0)
			throw new PropertyAlreadyVisitedException();
		((UserWritable)properties.getOwner()).removeProperty(idHome);
		properties = null;
		
	}

	public Property getPropertyInformation(String idHome) throws PropertyDoesNotExistException{
		if(properties == null || !properties.getIdHome().equalsIgnoreCase(idHome))
			throw new PropertyDoesNotExistException();
		
		return properties;
	}
	
	public void addStayEvaluation(String idUser,String idHome,int points) throws InvalidInformationException,
	UserDoesNotExistException,PropertyDoesNotExistException, TravellerIsOwnerException{                       //Listagem dentro user
		if(points < 0)
			throw new InvalidInformationException();
		else if(users == null || !users.getIdUser().equalsIgnoreCase(idUser))
			throw new UserDoesNotExistException();
		else if(properties == null || !properties.getIdHome().equalsIgnoreCase(idHome))
			throw new PropertyDoesNotExistException();
		else if (properties.getOwner().getIdUser().equalsIgnoreCase(idUser))
			throw new TravellerIsOwnerException();
		
		((PropertyWritable)properties).evaluateStay(points);
		((UserWritable)users).addStay(properties, points);
	}
	
	public void addStay(String idUser,String idHome) throws UserDoesNotExistException,PropertyDoesNotExistException,
		TravellerIsNotOwnerException{

		if(users == null || !users.getIdUser().equalsIgnoreCase(idUser))
			throw new UserDoesNotExistException();
		else if(properties == null || !properties.getIdHome().equalsIgnoreCase(idHome))
			throw new PropertyDoesNotExistException();
		else if (!properties.getOwner().getIdUser().equalsIgnoreCase(idUser))
			throw new TravellerIsNotOwnerException();
		
		((UserWritable)users).addStay(properties, 0);
		((PropertyWritable)properties).addStay();
	}
	
	public Property listOwnerProperties(String idUser) throws UserDoesNotExistException,UserIsNotOwnerException{
		if(users == null || !users.getIdUser().equalsIgnoreCase(idUser))
			throw new UserDoesNotExistException();
		
		return users.listProperties();
	}
	
	public Iterator<Stay> listStays(String idUser) throws UserDoesNotExistException, UserIsNotTravellerException{
		if(users == null || !users.getIdUser().equalsIgnoreCase(idUser))
			throw new UserDoesNotExistException();
		if(!users.getStaysIterator().hasNext())
			throw new UserIsNotTravellerException();
		
		return users.getStaysIterator();
	}
	
	public Property searchProperty(int persons,String local) throws InvalidInformationException, NoSearchResultsException {
		if(persons <= 0 || persons > 20)
			throw new InvalidInformationException();
		else if(properties == null || properties.getMaxPersons() < persons || !properties.getLocal().equalsIgnoreCase(local))
			throw new NoSearchResultsException();
		
		return properties;
	}
	
	public Property listBestProperty(String local) throws NoSearchResultsException {
		if(properties == null || !properties.getLocal().equalsIgnoreCase(local))
			throw new NoSearchResultsException();
			return properties;
	}
}
