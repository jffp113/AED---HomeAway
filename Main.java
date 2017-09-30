
import java.util.Scanner;
import dataStructures.Iterator;

import homeAway.PropertyDoesNotExistException;
import homeAway.TravellerIsNotOwnerException;
import homeAway.TravellerIsOwnerException;
import homeAway.UserDoesNotExistException;
import homeAway.InvalidInformationException;
import homeAway.NoSearchResultsException;
import homeAway.Property;
import homeAway.PropertyAlreadyExistException;
import homeAway.PropertyAlreadyVisitedException;
import homeAway.User;
import homeAway.UserAlreadyExistException;
import homeAway.UserIsNotOwnerException;
import homeAway.UserIsNotTravellerException;
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
	public static final String PROPERTY_DESCRIPTION = "%s: %s, %s, %d, %d, %d, %s\n";
	public static final String PROPERTY_SEARCH = "%s %s %s %s %d %d %d\n";
	public static final String STAY_ADDED = "Estadia adicionada com sucesso.";
	public static final String EXIT_MENSSAGE = "Gravado e terminado.";

	// OUTPUT_ERROR
	public static final String USER_ALREADY_ADDED = "Utilizador existente.";
	public static final String USER_DOES_NOT_EXIST = "Utilizador inexistente.";
	public static final String USER_IS_OWNER = "Utilizador e proprietario.";
	public static final String USER_IS_NOT_OWNER = "Utilizador nao e proprietario.";
	public static final String USER_IS_NOT_TRAVELLER = "Utilizador nao viajou.";
	public static final String PROPERTY_ALREADY_EXIST = "Propriedade existente.";
	public static final String PROPERTY_DOES_NOT_EXIST = "Propriedade inexistente.";
	public static final String PROPERTY_VISITED = "Propriedade ja foi visitada.";
	public static final String TRAVELLER_IS_OWNER = "Viajante e o proprietario.";
	public static final String TRAVELLER_IS_NOT_OWNER = "Viajante nao e o proprietario.";
	public static final String INVALID_INFO = "Dados invalidos.";
	public static final String NO_RESULTS = "Pesquisa nao devolveu resultados.";

	public static void main(String[] args) {
		homeAwayManager hm = load();
		Scanner in = new Scanner(System.in);
		String command = "";

		while (!command.equalsIgnoreCase(EXIT)) {
			command = command(in);
			switch (command) {
			case ADD_USER:
				addUser(in, hm);
				break;
			case CHANGE_USER_INFO:
				changeUserInfo(in, hm);
				break;
			case REMOVE_USER:
				removeUser(in, hm);
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
		save(hm);
		in.close();

	}

	/**
	 * Gets the rest of the line that include the arguments to start a command
	 * 
	 * @param in
	 *            Scanner to read the next of the line
	 * @return arguments as a Array of String
	 */
	private static String[] makeArgs(Scanner in) {
		String fullArgs = in.nextLine().trim();
		String tmp[] = new String[20];
		String args[];
		int numberOfArgs = 0, first = 0;

		for (int i = 0; i < fullArgs.length(); i++) {
			if (fullArgs.charAt(i) == ' ') {
				tmp[numberOfArgs++] = fullArgs.substring(first, i).trim();
				first = i;
			}
		}
		tmp[numberOfArgs++] = fullArgs.substring(first).trim();
		args = new String[numberOfArgs];
		for (int i = 0; i < numberOfArgs; i++) {
			args[i] = tmp[i];
		}
		return args;
	}

	/**
	 * Get Command
	 * 
	 * @param in
	 *            Scanner to get input
	 * @return command as a String
	 */
	private static String command(Scanner in) {
		String command = in.next().toUpperCase();
		return command;
	}

	/**
	 * Add user
	 * 
	 * @param in
	 *            Scanner to receive additional arguments
	 * @param hm
	 *            Top Class in order to execute the command
	 */
	private static void addUser(Scanner in, homeAwayManager hm) {
		String[] args = makeArgs(in);
		String nacionality = in.nextLine();
		String address = in.nextLine();
		StringBuilder sb = new StringBuilder(args[3]);
		
		for(int i = 4; i < args.length; i++) {
			sb.append(" " + args[i]);
		}
		
		try {
			hm.newUser(args[0], args[1], args[2], nacionality, address, sb.toString());
			System.out.println(USER_ADDED);
		} catch (UserAlreadyExistException e) {
			System.out.println(USER_ALREADY_ADDED);
		}
	}

	/**
	 * This method allows to change user information
	 * 
	 * @param in
	 *            Scanner to receive additional arguments
	 * @param hm
	 *            Top Class in order to execute the command
	 */
	private static void changeUserInfo(Scanner in, homeAwayManager hm) {
		String[] args = makeArgs(in);
		String address = in.nextLine();

		try {
			hm.changeUserInformation(args[0], args[1], args[2], address);
			System.out.println(USER_UPDATED);
		} catch (UserDoesNotExistException e) {
			System.out.println(USER_DOES_NOT_EXIST);
		}
	}

	/**
	 * This method allows to remove users
	 * 
	 * @param in
	 *            Scanner to receive additional arguments
	 * @param hm
	 *            Top Class in order to execute the command
	 */
	private static void removeUser(Scanner in, homeAwayManager hm) {
		String idUser = in.nextLine().trim();

		try {
			hm.removeUser(idUser);
			System.out.println(USER_REMOVED);
		} catch (UserDoesNotExistException e) {
			System.out.println(USER_DOES_NOT_EXIST);
		} catch (UserIsOwnerException e) {
			System.out.println(USER_IS_OWNER);
		}
	}

	/**
	 * This method allows to get user Information
	 * 
	 * @param in
	 *            Scanner to receive additional arguments
	 * @param hm
	 *            Top Class in order to execute the command
	 */
	private static void getUserInfo(Scanner in, homeAwayManager hm) {
		String[] args = makeArgs(in);
		User user = null;
		try {
			user = hm.getUserInformation(args[0]);
			System.out.printf(USER_SEARCH_RESULT, user.getName(), user.getAddress(), user.getNacionality(),
					user.getEmail(), user.getPhoneNumber());
		} catch (UserDoesNotExistException e) {
			System.out.println(USER_DOES_NOT_EXIST);
		}
	}

	/**
	 * This method allows to add a new Property
	 * 
	 * @param in
	 *            Scanner to receive additional arguments
	 * @param hm
	 *            Top Class in order to execute the command
	 */
	private static void addProperty(Scanner in, homeAwayManager hm) {
		String[] args = makeArgs(in);
		String description = in.nextLine();
		String address = in.nextLine();

		try {
			hm.addProperty(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), args[4], description,
					address);
			System.out.println(PROPERTY_ADDED);
		} catch (InvalidInformationException e) {
			System.out.println(INVALID_INFO);
		} catch (UserDoesNotExistException e) {
			System.out.println(USER_DOES_NOT_EXIST);
		} catch (PropertyAlreadyExistException e) {
			System.out.println(PROPERTY_ALREADY_EXIST);
		}
	}

	private static void removeProperty(Scanner in, homeAwayManager hm) {
		String idHome = in.nextLine().trim();
		try {
			hm.removeProperty(idHome);
			System.out.println(PROPERTY_REMOVED);

		} catch (PropertyDoesNotExistException e) {
			System.out.println(PROPERTY_DOES_NOT_EXIST);
		} catch (PropertyAlreadyVisitedException e) {
			System.out.println(PROPERTY_VISITED);
		}

	}

	private static void getPropertyInfo(Scanner in, homeAwayManager hm) {
		String idHome = in.nextLine().trim();
		Property p;
		try {
			p = hm.getPropertyInformation(idHome);
			System.out.printf(PROPERTY_DESCRIPTION,p.getDescription(), p.getAdress(), p.getLocal(), p.getPrice(), p.getMaxPersons(),
					p.getPoints(), p.getIdHome());
		} catch (PropertyDoesNotExistException e) {
			System.out.println(PROPERTY_DOES_NOT_EXIST);

		}
	}

	private static void addStay(Scanner in, homeAwayManager hm) {
		String[] args = makeArgs(in);
		try {
			if (args.length == 3) {

				hm.addStayEvaluation(args[0], args[1], Integer.parseInt(args[2]));
				System.out.println(STAY_ADDED);
			} else {
				hm.addStay(args[0], args[1]);
				System.out.println(STAY_ADDED);
			}
		} catch (InvalidInformationException e) {
			System.out.println(INVALID_INFO);
		} catch (UserDoesNotExistException e) {
			System.out.println(USER_DOES_NOT_EXIST);
		} catch (PropertyDoesNotExistException e) {
			System.out.println(PROPERTY_DOES_NOT_EXIST);
		} catch (TravellerIsOwnerException e) {
			System.out.println(TRAVELLER_IS_OWNER);
		} catch (TravellerIsNotOwnerException e) {
			System.out.println(TRAVELLER_IS_NOT_OWNER);

		}

	}

	private static void listOwnerProperties(Scanner in, homeAwayManager hm) {
		String idUser = in.nextLine().trim();
		try {
			Property p = hm.listOwnerProperties(idUser);
			System.out.printf(PROPERTY_SEARCH, p.getIdHome(), p.getDescription(), p.getAdress(), p.getLocal(),
					p.getPrice(), p.getMaxPersons(), p.getPoints());

		} catch (UserDoesNotExistException e) {
			System.out.println(USER_DOES_NOT_EXIST);

		} catch (UserIsNotOwnerException e) {
			System.out.println(USER_IS_NOT_OWNER);
		}
	}

	private static void listTravellerStays(Scanner in, homeAwayManager hm) {
		String idUser = in.nextLine().trim();
		try {
			Iterator<Property> ip = hm.listStays(idUser);
			Property p;
			while (ip.hasNext()) {
				p = ip.next();
				System.out.printf(PROPERTY_SEARCH, p.getIdHome(), p.getDescription(), p.getAdress(), p.getLocal(),
						p.getPrice(), p.getMaxPersons(), p.getPoints());
			}
		} catch (UserDoesNotExistException e) {
			System.out.println(USER_DOES_NOT_EXIST);

		} catch (UserIsNotTravellerException e) {
			System.out.println(USER_IS_NOT_TRAVELLER);
		}

	}

	private static void searchProperty(Scanner in, homeAwayManager hm) {
		String[] args = makeArgs(in);
		try {
			Property p = hm.searchProperty(Integer.parseInt(args[0]), args[1]);
			System.out.printf(PROPERTY_SEARCH, p.getIdHome(), p.getDescription(), p.getAdress(), p.getLocal(),
					p.getPrice(), p.getMaxPersons(), p.getPoints());
		} 
		catch (InvalidInformationException e) {
			System.out.println(INVALID_INFO);
		} catch (NoSearchResultsException e) {
			System.out.println(NO_RESULTS);
		}

	}

	private static void listBestProperties(Scanner in, homeAwayManager hm) {
		String local = in.nextLine().trim();
		try {
			Property p = hm.listBestProperty(local);
			System.out.printf(PROPERTY_SEARCH, p.getIdHome(), p.getDescription(), p.getAdress(), p.getLocal(),
					p.getPrice(), p.getMaxPersons(), p.getPoints());
		} catch (NoSearchResultsException e) {
			System.out.println(NO_RESULTS);
		}
	}

	private static void save(homeAwayManager hm) {
		System.out.println(EXIT_MENSSAGE);
		
	}

	private static homeAwayManager load() {

		return new homeAwayManagerClass();
	}
}