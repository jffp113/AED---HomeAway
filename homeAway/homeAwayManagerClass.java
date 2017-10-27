package homeAway;

import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;
import dataStructures.Iterator;

public class HomeAwayManagerClass implements HomeAwayManager {
	// Constants
	private static final int NUMBER_USER = 15000;

	private static final long serialVersionUID = 0L;

	// Variables
	// private User users;
	private Property properties;
	private Dictionary<String, UserWritable> users;

	// Constructor
	public HomeAwayManagerClass() {
		users = new ChainedHashTable<String, UserWritable>(NUMBER_USER);
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

		return (User) user;
	}

	public void addProperty(String idHome, String idUser, int price, int maxPersons, String local, String description,
			String address)
			throws InvalidInformationException, UserDoesNotExistException, PropertyAlreadyExistException {
		UserWritable user = users.find(idUser.toLowerCase());
		if (price <= 0 || maxPersons <= 0 || maxPersons > 20)
			throw new InvalidInformationException();
		else if (user == null)
			throw new UserDoesNotExistException();
		else if (properties != null && properties.getIdHome().equalsIgnoreCase(idHome))
			throw new PropertyAlreadyExistException();

		properties = new PropertyClass(idHome, user, price, maxPersons, local, description, address);
		user.addNewProperty(properties);
	}

	public void removeProperty(String idHome) throws PropertyDoesNotExistException, PropertyAlreadyVisitedException {

		if (properties == null || !properties.getIdHome().equalsIgnoreCase(idHome))
			throw new PropertyDoesNotExistException();
		else if (properties.getNumberOfvisits() != 0)
			throw new PropertyAlreadyVisitedException();
		((UserWritable) properties.getOwner()).removeProperty(idHome);
		properties = null;

	}

	public Property getPropertyInformation(String idHome) throws PropertyDoesNotExistException {
		if (properties == null || !properties.getIdHome().equalsIgnoreCase(idHome))
			throw new PropertyDoesNotExistException();

		return properties;
	}

	public void addStayEvaluation(String idUser, String idHome, int points) throws InvalidInformationException,
			UserDoesNotExistException, PropertyDoesNotExistException, TravellerIsOwnerException { // Listagem dentro
																									// user
		UserWritable user = users.find(idUser.toLowerCase());

		if (points < 0)
			throw new InvalidInformationException();
		else if (user == null)
			throw new UserDoesNotExistException();
		else if (properties == null || !properties.getIdHome().equalsIgnoreCase(idHome))
			throw new PropertyDoesNotExistException();
		else if (properties.getOwner().getIdUser().equalsIgnoreCase(idUser))
			throw new TravellerIsOwnerException();

		((PropertyWritable) properties).evaluateStay(points);
		user.addStay(properties, points);
	}

	public void addStay(String idUser, String idHome)
			throws UserDoesNotExistException, PropertyDoesNotExistException, TravellerIsNotOwnerException {
		UserWritable user = users.find(idUser.toLowerCase());

		if (user == null)
			throw new UserDoesNotExistException();
		else if (properties == null || !properties.getIdHome().equalsIgnoreCase(idHome))
			throw new PropertyDoesNotExistException();
		else if (!properties.getOwner().getIdUser().equalsIgnoreCase(idUser))
			throw new TravellerIsNotOwnerException();

		user.addStay(properties, 0);
		((PropertyWritable) properties).addStay();
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
		if (persons <= 0 || persons > 20)
			throw new InvalidInformationException();
		else if (properties == null || properties.getMaxPersons() < persons
				|| !properties.getLocal().equalsIgnoreCase(local))
			throw new NoSearchResultsException();

		return properties;
	}

	public Property listBestProperty(String local) throws NoSearchResultsException {
		if (properties == null || !properties.getLocal().equalsIgnoreCase(local))
			throw new NoSearchResultsException();
		return properties;
	}
}
