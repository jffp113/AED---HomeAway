package homeAway;

import dataStructures.BinarySearchTree;
import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;
import dataStructures.Iterator;
import dataStructures.OrderedDictionary;

public class HomeAwayManagerClass implements HomeAwayManager {
	// Constants
	private static final int NUMBER_USER = 15000;
	private static final int NUMBER_PROPERTIES = 7500;
	private static final long serialVersionUID = 0L;

	// Variables
	// private Property properties;
	private Dictionary<String, PropertyWritable> properties;
	private Dictionary<String, UserWritable> users;							
	private Dictionary<String, OrderedDictionary<Integer,OrderedDictionary<String,Property>>> localProperties;
	private Dictionary<String, OrderedDictionary<Integer,OrderedDictionary<String,Property>>> searchProperties;

	
	// Constructor
	public HomeAwayManagerClass() {
		users = new ChainedHashTable<String, UserWritable>(NUMBER_USER);
		properties = new ChainedHashTable<String, PropertyWritable>(NUMBER_PROPERTIES);
		localProperties = new ChainedHashTable<String, OrderedDictionary<Integer,OrderedDictionary<String,Property>>>(NUMBER_PROPERTIES);
		searchProperties = new ChainedHashTable<String, OrderedDictionary<Integer,OrderedDictionary<String,Property>>>(NUMBER_PROPERTIES);
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
		user.changeInfo(email, phoneNumber, address);
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
			String address)
			throws InvalidInformationException, UserDoesNotExistException, PropertyAlreadyExistException {

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
		
		//FASE 3
		this.addToLocal(property, 0, local);
		this.addToSearch(property);
		//FIM
		user.addNewProperty(property);
	}

	public void removeProperty(String idHome) throws PropertyDoesNotExistException, PropertyAlreadyVisitedException {
		PropertyWritable p = properties.find(idHome.toLowerCase());

		if (p == null)
			throw new PropertyDoesNotExistException();
		else if (p.getNumberOfvisits() != 0)
			throw new PropertyAlreadyVisitedException();
		
		 properties.remove(idHome.toLowerCase());
		
		((UserWritable) p.getOwner()).removeProperty(idHome);
		
		//Fase 3
		this.removeFromLocal(p);
		this.removeFromSearch(p);
	}

	public Property getPropertyInformation(String idHome) throws PropertyDoesNotExistException {
		PropertyWritable property = properties.find(idHome.toLowerCase());

		if (property == null)
			throw new PropertyDoesNotExistException();

		return property;
	}

	public void addStayEvaluation(String idUser, String idHome, int points) throws InvalidInformationException,
			UserDoesNotExistException, PropertyDoesNotExistException, TravellerIsOwnerException { 
		
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

		this.removeFromLocal(property);
	
		
		property.evaluateStay(points);
		user.addStay(property, points);
		
		this.addToLocal(property, property.getPoints(), property.getLocal());
		
	}

	
	private void removeFromLocal(Property p) {
		OrderedDictionary<Integer,OrderedDictionary<String,Property>> dePoints = localProperties.find(p.getLocal().toLowerCase());
		OrderedDictionary<String,Property> deId = dePoints.find(p.getPoints());
		deId.remove(p.getIdHome().toLowerCase());
		
		if(deId.isEmpty()) {
			dePoints.remove(p.getPoints());
			if(dePoints.isEmpty())
				localProperties.remove(p.getLocal().toLowerCase());
		}
	}
	
	private void addToLocal(Property property, int points, String local) {
		OrderedDictionary<Integer,OrderedDictionary<String,Property>> lp  = localProperties.find(local.toLowerCase());
		
		if(lp == null) {
			OrderedDictionary<Integer,OrderedDictionary<String,Property>> p = new BinarySearchTree<Integer,OrderedDictionary<String,Property>>();
			OrderedDictionary<String, Property> s = new BinarySearchTree<String,Property>();
			s.insert(property.getIdHome().toLowerCase(), property);
			p.insert(points, s);
			localProperties.insert(local.toLowerCase(),p);
		}
		else if(lp.find(points) == null) {
			OrderedDictionary<String, Property> s = new BinarySearchTree<String,Property>();
			s.insert(property.getIdHome().toLowerCase(), property);
			lp.insert(points, s);
		}
		else {
			lp.find(points).insert(property.getIdHome().toLowerCase(), property);
		}
		
	}
	
	private void removeFromSearch(Property p) {
		OrderedDictionary<Integer,OrderedDictionary<String,Property>> dePeople = searchProperties.find(p.getLocal().toLowerCase());
		OrderedDictionary<String,Property> deId = dePeople.find(p.getMaxPersons());
		deId.remove(p.getIdHome().toLowerCase());
		
		if(deId.isEmpty()) {
			dePeople.remove(p.getMaxPersons());
			if(dePeople.isEmpty())
				localProperties.remove(p.getLocal().toLowerCase());
		}
	}

	private void addToSearch(Property property) {
			OrderedDictionary<Integer,OrderedDictionary<String,Property>> lp  = localProperties.find(property.getLocal().toLowerCase());
		
		if(lp == null) {
			OrderedDictionary<Integer,OrderedDictionary<String,Property>> p = new BinarySearchTree<Integer,OrderedDictionary<String,Property>>();
			OrderedDictionary<String, Property> s = new BinarySearchTree<String,Property>();
			s.insert(property.getIdHome().toLowerCase(), property);
			p.insert(property.getMaxPersons(), s);
			localProperties.insert(property.getLocal().toLowerCase(),p);
		}
		else if(lp.find(property.getMaxPersons()) == null) {
			OrderedDictionary<String, Property> s = new BinarySearchTree<String,Property>();
			s.insert(property.getIdHome().toLowerCase(), property);
			lp.insert(property.getMaxPersons(), s);
		}
		else {
			lp.find(property.getMaxPersons()).insert(property.getIdHome().toLowerCase(), property);
		}
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

	public Iterator<Property> listOwnerProperties(String idUser) throws UserDoesNotExistException, UserIsNotOwnerException {
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
		PropertyWritable property = null ;//localProperties.find(local.toLowerCase());

		if (persons <= 0 || persons > 20)
			throw new InvalidInformationException();
		else if (property == null || property.getMaxPersons() < persons)
			throw new NoSearchResultsException();

		return property;
	}

	public Iterator<Property> listBestProperty(String local) throws NoSearchResultsException {
		OrderedDictionary<Integer,OrderedDictionary<String,Property>> lp  = localProperties.find(local.toLowerCase());
		
		if (lp == null)
			throw new NoSearchResultsException();
		
		return new IteratorOfIterators<Integer,String,Property>(lp.iterator());
		
		
	}
}
