package homeAway;
/**
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public interface homeAwayManager {
	
	void newUser(String idUser, String email, String phoneNumber,
			String nacionality, String address) throws UserAlreadyExistException; 
	
	void changeUserInformation(String idUser, String email, String phoneNumber,
			String address) throws InexistentUserException ;
	
	void removeUser(String idUser) throws InexistentUserException,UserIsOwnerException;
	
	User getUserInformation(String idUser) throws InexistentUserException;
	
	
}
