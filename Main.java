import java.util.Scanner;

import homeAway.InexistentUserException;
import homeAway.InvalidInformationException;
import homeAway.PropertyAlreadyExistException;
import homeAway.User;
import homeAway.UserAlreadyExistException;
import homeAway.UserIsOwnerException;
import homeAway.homeAwayManager;
import homeAway.homeAwayManagerClass;

/**
 * Main Class
 * 
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public class Main {

	// COMANDS
	public static final String ADD_USER = "IU";
	public static final String CHANGE_USER_INFO = "UU";
	public static final String REMOVE_USER = "RU";
	public static final String GET_USER_INFO = "GU";
	public static final String ADD_PROPERTY = "AH";
	public static final String REMOVE_PROPERTY = "RH";
	public static final String GET_PROPERTY_INFO = "GH";
	public static final String ADD_STAY = "AT";
	public static final String LIST_OWNER_PROPERTIES = "LH";
	public static final String LIST_TRAVELLER_STAYS = "LT";
	public static final String SEARCH_PROPERTY = "PH";
	public static final String LIST_BEST_PROPERTIES = "LB";
	public static final String EXIT = "XS";

	// OUTPUT
	public static final String USER_ADDED = "Insercao de utilizador com sucesso.";
	public static final String USER_UPDATED = "Utilizador atualizado com sucesso.";
	public static final String USER_REMOVED = "Utilizador removido com sucesso.";
	public static final String USER_SEARCH_RESULT = "%s: %s, %s, %s, %s\n";
	public static final String PROPERTY_ADDED = "Propriedade adicionada com sucesso.";
	public static final String PROPERTY_REMOVED = "Propriedade removida com sucesso.";
	public static final String PROPERTY_DESCRIPTION = "descricao: %s, %s, %d, %d, %d, %s\n";
	public static final String STAY_ADDED = "Estadia adicionada com sucesso.";
	public static final String PROPERTY_SEARCH = " %s %s %s %s %d %d %d\n";
	public static final String EXIT_MENSSAGE = "Gravado e terminado.";

	// OUTPUT_ERROR
	public static final String USER_ALREADY_ADDED = "Utilizador exitente.";
	public static final String USER_DOES_NOT_EXIST = "Utilizador inexitente.";
	public static final String USER_IS_OWNER = "Utilizador e propietario.";
	public static final String INVALID_INFO = "Dados invalidos.";
	public static final String PROPERTY_ALREADY_EXIST = "Propriedade existente.";
	public static final String PROPERTY_INEXISTENT = "Propriedade inexistente.";
	public static final String PROPERTY_VISITED = "Propriedade ja foi visitada.";
	public static final String TRAVELLER_IS_OWNER = "Viajante e o proprietario.";
	public static final String TRAVELLER_IS_NOT_OWNER = "Viajante nao e o proprietario.";
	public static final String USER_DIDNT_TRAVELLED = "Utilizador nao viajou.";
	public static final String NO_RESULTS = "Pesquisa nao devolveu resultados.";

	public static void main(String[] args) {
		homeAwayManager hm = load();
		Scanner in = new Scanner(System.in);
		String command = "";

		while (!command.equalsIgnoreCase(EXIT)) {
			command = command(in);
			switch (command) {
			case ADD_USER:
				addUser(in,hm);
				break;
			case CHANGE_USER_INFO:
				changeUserInfo(in, hm);
				break;
			case REMOVE_USER:
				removerUser(in, hm);
				break;
			case GET_USER_INFO:
				getUserInfo(in, hm);
				break;
			case ADD_PROPERTY:
				addProperty(in, hm);
				break;
			case REMOVE_PROPERTY:
				removeProperty(in, hm);
				break;
			case GET_PROPERTY_INFO:
				getPropertyInfo(in, hm);
				break;
			case ADD_STAY:
				addStay(in, hm);
				break;
			case LIST_OWNER_PROPERTIES:
				listOwnerProperties(in, hm);
				break;
			case LIST_TRAVELLER_STAYS:
				listTravellerStays(in, hm);
				break;
			case SEARCH_PROPERTY:
				searchProperty(in, hm);
				break;
			case LIST_BEST_PROPERTIES:
				listBestProperties(in, hm);
				break;
			}
			System.out.println();
		}
		Main.save(hm);
		in.close();

	}
	
	/**
	 * Gets the rest of the line that include the arguments
	 * to start a command
	 * @param in Scanner to read the next of the line
	 * @return arguments as a Array of String
	 */
	private static String[] makeArgs(Scanner in) {
		String fullArgs = in.nextLine().trim();
		String tmp[] = new String[20];
		String args[];
		int numberOfArgs = 0, first = 0;
		
			for(int i = 0; i < fullArgs.length(); i++) {
				if(fullArgs.charAt(i) == ' ') {
					tmp[numberOfArgs++] = fullArgs.substring(first , i).trim();
					first = i;
				}
			}
			tmp[numberOfArgs++] = fullArgs.substring(first).trim();
			args = new String[numberOfArgs];
			for(int i = 0 ; i < numberOfArgs; i++) {
				args[i] = tmp[i];
			}
		return args;
	}
	
	/**
	 * Get Command
	 * @param in Scanner to get input
	 * @return command as a String
	 */
	private static String command(Scanner in) {
		String command = in.next().toUpperCase();
		return command;
	}

	/**
	 * Add user
	 * @param in Scanner to receive additional arguments
	 * @param hm Top Class in order to execute the command
	 */
	private static void addUser(Scanner in, homeAwayManager hm) {
		String[] args = makeArgs(in);
		String nacionality = in.nextLine();
		String address = in.nextLine();
		
		try {
			hm.newUser(args[0], args[1], args[2], nacionality, address,args[3]);
			System.out.println(USER_ADDED);
		} catch (UserAlreadyExistException e) {
			System.out.println(USER_ALREADY_ADDED);
		}
	}

	/**
	 * This method allows to change user information
	 * @param in Scanner to receive additional arguments
	 * @param hm Top Class in order to execute the command
	 */
	private static void changeUserInfo(Scanner in, homeAwayManager hm) {
		String[] args = makeArgs(in);
		String address = in.nextLine();
		
		try {
			hm.changeUserInformation(args[0], args[1], args[2], address);
			System.out.println(USER_UPDATED);
		} catch (InexistentUserException e) {
			System.out.println(USER_DOES_NOT_EXIST);
		}
	}

	/**
	 * This method allows to remove users
	 * @param in Scanner to receive additional arguments
	 * @param hm Top Class in order to execute the command
	 */
	private static void removerUser(Scanner in, homeAwayManager hm) {
		String args[] = makeArgs(in);
		
		try {
			hm.removeUser(args[0]);
			System.out.println(USER_REMOVED);
		} catch (InexistentUserException e) {
			System.out.println(USER_DOES_NOT_EXIST);
		} catch (UserIsOwnerException e) {
			System.out.println(USER_IS_OWNER);
		}
	}
	/**
	 * This method allows to get user Information
	 * @param in Scanner to receive additional arguments
	 * @param hm Top Class in order to execute the command
	 */
	private static void getUserInfo(Scanner in, homeAwayManager hm) {
		String[] args = makeArgs(in);
		User user = null;
		try {
			user = hm.getUserInformation(args[0]);
			System.out.printf(USER_SEARCH_RESULT,user.getName(),user.getAddress(),user.getNacionality(),user.getEmail(),user.getPhoneNumber());
		} catch (InexistentUserException e) {
			System.out.println(USER_DOES_NOT_EXIST);
		}
	}

	/**
	 * This method allows to add a new Property
	 * @param in Scanner to receive additional arguments
	 * @param hm Top Class in order to execute the command
	 */
	private static void addProperty(Scanner in, homeAwayManager hm) {
		String[] args = makeArgs(in);
		String description = in.nextLine();
		String address = in.nextLine();
		
		try {
			hm.addProperty(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), args[4], description, address);
		} catch (InvalidInformationException e) {
			System.out.println(INVALID_INFO);
		} catch (InexistentUserException e) {
			System.out.println(USER_DOES_NOT_EXIST);
		} catch (PropertyAlreadyExistException e) {
			System.out.println(PROPERTY_ALREADY_EXIST);
		}
	}

	private static void removeProperty(Scanner in, homeAwayManager hm) {

	}

	private static void getPropertyInfo(Scanner in, homeAwayManager hm) {

	}

	private static void addStay(Scanner in, homeAwayManager hm) {

	}

	private static void listOwnerProperties(Scanner in, homeAwayManager hm) {

	}

	private static void listTravellerStays(Scanner in, homeAwayManager hm) {

	}

	private static void searchProperty(Scanner in, homeAwayManager hm) {

	}

	private static void listBestProperties(Scanner in, homeAwayManager hm) {

	}

	private static void save(homeAwayManager hm) {

	}

	private static homeAwayManager load() {

		return new homeAwayManagerClass();
	}
}
