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
	
	
	public void addProperty(String idHome, String idUser, int price, int maxPersons, String local, String description,
			String address) throws InvalidInformationException, InexistentUserException, PropertyAlreadyExistException;
	
	public void removeProperty(String idHome) throws InexistentPropertyException, PropertyAlreadyVisitedException;
	
	
	public Property getPropertyInformation(String idHome) throws InexistentPropertyException;
	
	public void addStayEvaluation(String idUser,String idHome,int points) throws InvalidInformationException,
	InexistentUserException,InexistentPropertyException, UserIsOwnerException;
	
	public void addStay(String idUser,String idHome) throws InexistentUserException,InexistentPropertyException,
	UserIsNotOwnerException;
	
	public Property listOwnerProperties(String idUser) throws InexistentUserException,UserIsNotOwnerException;
}
