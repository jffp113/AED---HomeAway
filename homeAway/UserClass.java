package homeAway;

import dataStructures.Iterator;
import dataStructures.IterableStackInList;
import dataStructures.IterableStack;

/**
 * The User class represents a User in the database containing various
 * Information about a person.
 * 
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
class UserClass implements UserWritable {
	// Constants

	private static final long serialVersionUID = 0L;

	// Variables
	private String idUser;

	private String name;
	private String nacionality;
	private String address;
	private String email;
	private String phoneNumber;

	private Property properties;
	private int numberProperties;

	private IterableStack<Stay> stays;

	// Constructor
	public UserClass(String idUser, String email, String phoneNumber, String nacionality, String address, String name) {
		this.idUser = idUser;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.nacionality = nacionality;
		this.address = address;
		this.name = name;
		this.numberProperties = 0;

		stays = new IterableStackInList<Stay>();
		properties = null;
	}

	@Override
	public String getIdUser() {
		return this.idUser;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getNacionality() {
		return this.nacionality;
	}

	@Override
	public String getAddress() {
		return this.address;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	@Override
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	@Override
	public void setPhoneNumber(String newNumber) {
		this.phoneNumber = newNumber;
	}

	@Override
	public void setAddress(String newAddress) {
		this.address = newAddress;
	}

	@Override
	public void addNewProperty(Property newProp) {
		this.properties = newProp;
		this.numberProperties++;
	}

	@Override
	public Property listProperties() throws UserIsNotOwnerException {
		if (this.getNumberProperties() == 0)
			throw new UserIsNotOwnerException();
		return properties;
	}

	@Override
	public boolean isOwner() {
		return this.numberProperties > 0;
	}

	public int getNumberProperties() {
		return this.numberProperties;
	}

	@Override
	public void addStay(Property property, int points) {
		stays.push(new StayClass(property, points));
	}

	@Override
	public Iterator<Stay> getStaysIterator() {
		return stays.iterator();
	}

	@Override
	public void removeProperty(String idHome) {
		this.properties = null;
		this.numberProperties--;
	}

	@Override
	public void changeInfo(String newEmail, String newNumber, String newAddress) {
		this.setAddress(newAddress);
		this.setEmail(newEmail);
		this.setPhoneNumber(newNumber);
		
	}

}