package homeAway;

public class UserClass implements UserWritable{
	//Constants
	
	//Variables
	private String idUser;
	
	private String name;
	private String nacionality;
	private String address;
	private String email;
	private String phoneNumber;
	
	private Property properties;
	
	//Constructor
	public UserClass(String idUser, String email, String phoneNumber,
			String nacionality, String address) {
		this.idUser = idUser;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.nacionality = nacionality;
		this.address = address;
		
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
	}


	@Override
	public Property listProperties() throws UserIsNotOwnerException {
		if(this.properties == null)
			throw new UserIsNotOwnerException();
		return properties;
	}


	@Override
	public boolean isOwner() {
		return this.properties !=null;
	}
	
}
