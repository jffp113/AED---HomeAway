package homeAway;

public class homeAwayManagerClass implements homeAwayManager {
	//Constants
	
	//Variables
	private User users;
	private Property properties;
	
	//Constructor
	
	
	@Override
	public void newUser(String idUser, String email, String phoneNumber, String nacionality, String address)
			throws UserAlreadyExistException {
		
		if(users.getIdUser().equals(idUser)) {
			throw new UserAlreadyExistException();
		}
		
		users = new UserClass(idUser, email, phoneNumber, nacionality,address);
		
	}
	@Override
	public void changeUserInformation(String idUser, String email, String phoneNumber, String address)
			throws InexistentUserException {
		
		if(!users.getIdUser().equals(idUser)) {
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
		if(!users.getIdUser().equals(idUser)) 
			throw new InexistentUserException();
		else if(users.isOwner())
			throw new UserIsOwnerException();
	}
	@Override
	public User getUserInformation(String idUser) throws InexistentUserException {
		if(!users.getIdUser().equals(idUser)) 
			throw new InexistentUserException();
		
		return users;
	}

	
}
