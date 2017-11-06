package homeAway;

import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;
import dataStructures.Iterator;

public class HomeAwayManagerClass implements HomeAwayManager {
	// Constants
	private static final int NUMBER_USER = 15000;
	private static final int NUMBER_PROPERTIES = 7500;

	private static final long serialVersionUID = 0L;

	// Variables
	//private Property properties;
	private Dictionary<String, PropertyWritable> properties;
	private Dictionary<String, UserWritable> users;
	private Dictionary<String,PropertyWritable> localProperties;
	
	// Constructor
	public HomeAwayManagerClass() {
		users = new ChainedHashTable<String, UserWritable>(NUMBER_USER);
		properties = new ChainedHashTable<String, PropertyWritable>(NUMBER_PROPERTIES);
		localProperties = new ChainedHashTable<String, PropertyWritable>(NUMBER_PROPERTIES);
	}

	@Override
	public void newUser(String idUser, String email, String phoneNumber, String nacionality, String address,
			String name) throws UserAlreadyExistException {
		UserWritable user = users.find(idUser.toLowerCase());

		if (user != null) 
			throw new UserAlreadyExistException();
		
		user = new UserClass(idUser, email, phoneNumber, nacionality, address, name);
		users.insert(idUser.toLowerCase(), user);
	}

	@Override
	public void changeUserInformation(String idUser, String email, String phoneNumber, String address)
			throws UserDoesNotExistException {
		UserWritable user = users.find(idUser.toLowerCase());
		if (user == null)
			throw new UserDoesNotExistException();

		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setAddress(address);

	}

	@Override
	public void removeUser(String idUser) throws UserDoesNotExistException, UserIsOwnerException {
		UserWritable user = users.find(idUser.toLowerCase());
		if (user == null)
			throw new UserDoesNotExistException();
		else if (user.isOwner())
			throw new UserIsOwnerException();

		users.remove(idUser.toLowerCase());
	}

	@Override
	public User getUserInformation(String idUser) throws UserDoesNotExistException {
		UserWritable user = users.find(idUser.toLowerCase());
		if (user == null)
			throw new UserDoesNotExistException();

		return user;
	}

	public void addProperty(String idHome, String idUser, int price, int maxPersons, String local, String description,
			String address) throws InvalidInformationException, UserDoesNotExistException, PropertyAlreadyExistException {
		
		UserWritable user = users.find(idUser.toLowerCase());
		PropertyWritable property = properties.find(idHome.toLowerCase());
		
		if (price <= 0 || maxPersons <= 0 || maxPersons > 20)
			throw new InvalidInformationException();
		else if (user == null)
			throw new UserDoesNotExistException();
		else if (property != null)
			throw new PropertyAlreadyExistException();

		property = new PropertyClass(idHome, user, price, maxPersons, local, description, address);
		properties.insert(idHome.toLowerCase(), property);
		localProperties.insert(local.toLowerCase(), property);
		user.addNewProperty(property);
	}

	public void removeProperty(String idHome) throws PropertyDoesNotExistException, PropertyAlreadyVisitedException {
		PropertyWritable property = properties.find(idHome.toLowerCase());
		
		if (property == null)
			throw new PropertyDoesNotExistException();
		else if (property.getNumberOfvisits() != 0)
			throw new PropertyAlreadyVisitedException();
		
		((UserWritable)property.getOwner()).removeProperty(idHome);
		localProperties.remove(property.getLocal().toLowerCase());
		properties.remove(idHome.toLowerCase());

	}

	public Property getPropertyInformation(String idHome) throws PropertyDoesNotExistException {
		PropertyWritable property = properties.find(idHome.toLowerCase());

		if (property == null)
			throw new PropertyDoesNotExistException();

		return property;
	}

	public void addStayEvaluation(String idUser, String idHome, int points) throws InvalidInformationException,
			UserDoesNotExistException, PropertyDoesNotExistException, TravellerIsOwnerException { // Listagem dentro
																									// user
		UserWritable user = users.find(idUser.toLowerCase());
		PropertyWritable property = properties.find(idHome.toLowerCase());
		
		if (points < 0)
			throw new InvalidInformationException();
		else if (user == null)
			throw new UserDoesNotExistException();
		else if (property == null)
			throw new PropertyDoesNotExistException();
		else if (property.getOwner().getIdUser().equalsIgnoreCase(idUser))
			throw new TravellerIsOwnerException();

		property.evaluateStay(points);
		user.addStay(property, points);
	}

	public void addStay(String idUser, String idHome)
			throws UserDoesNotExistException, PropertyDoesNotExistException, TravellerIsNotOwnerException {
		
		UserWritable user = users.find(idUser.toLowerCase());
		PropertyWritable property = properties.find(idHome.toLowerCase());
		
		if (user == null)
			throw new UserDoesNotExistException();
		else if (property == null)
			throw new PropertyDoesNotExistException();
		else if (property.getOwner() != user)
			throw new TravellerIsNotOwnerException();

		user.addStay(property, 0);
		property.addStay();
	}

	public Property listOwnerProperties(String idUser) throws UserDoesNotExistException, UserIsNotOwnerException {
		UserWritable user = users.find(idUser.toLowerCase());
		
		if (user == null)
			throw new UserDoesNotExistException();

		return user.listProperties();
	}

	public Iterator<Stay> listStays(String idUser) throws UserDoesNotExistException, UserIsNotTravellerException {
		UserWritable user = users.find(idUser.toLowerCase());
		if (user == null)
			throw new UserDoesNotExistException();
		if (!user.getStaysIterator().hasNext())
			throw new UserIsNotTravellerException();

		return user.getStaysIterator();
	}

	public Property searchProperty(int persons, String local)
			throws InvalidInformationException, NoSearchResultsException {
		PropertyWritable property = localProperties.find(local.toLowerCase());

		if (persons <= 0 || persons > 20)
			throw new InvalidInformationException();
		else if (property == null || property.getMaxPersons() < persons )
			throw new NoSearchResultsException();

		return property;
	}

	public Property listBestProperty(String local) throws NoSearchResultsException {
		PropertyWritable property = localProperties.find(local.toLowerCase());
		if (property == null)
			throw new NoSearchResultsException();
		return property;
	}
}
