import java.util.Scanner;

import homeAway.homeAwayManager;
import homeAway.homeAwayManagerClass;

/**
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public class Main {

	// COMANDS
	public static final String ADDUSER = "IU";
	public static final String CHANGUSERINFO = "UU";
	public static final String REMOVEUSER = "RU";
	public static final String GETUSERINFO = "GU";
	public static final String ADDPROPERTY = "AH";
	public static final String REMOVEPROPERTY = "RH";
	public static final String GETPROPERTYINFO = "GH";
	public static final String ADDSTAY = "AT";
	public static final String LISTOWNERPROPERTIES = "LH";
	public static final String LISTTRAVELLERSTAYS = "LT";
	public static final String SEARCHPROPERTY = "PH";
	public static final String LISTBESTPROPERTIES = "LB";
	public static final String EXIT = "XS";

	// OUTPUT
	public static final String SUCESSUSERADD = "Insercao de utilizador com sucesso.";
	public static final String USERUPDATED = "Utilizador atualizado com sucesso.";
	public static final String REMOVEDUSER = "Utilizador removido com sucesso.";
	public static final String USERSEARCHRESULT = "nome: %s, %s, %s, %s\n";
	public static final String SUCESSEFULLPROPRTYADDED = "Propriedade adicionada com sucesso.";
	public static final String SUCESSEFULLPROPERTYREMOVED = "Propriedade removida com sucesso.";
	public static final String PROPERTYDESCRIPTION = "descricao: %s, %s, %d, %d, %d, %s\n";
	public static final String STAYADDED = "Estadia adicionada com sucesso.";
	public static final String PROPERTYSEARCH = " %s %s %s %s %d %d %d\n";
	public static final String EXITMENSSAGE = "Gravado e terminado.";

	// OUTPUTERROR
	public static final String USERALREADYADDED = "Utilizador exitente.";
	public static final String USERDOESNOTEXIST = "Utilizador inexitente.";
	public static final String USERISOWNER = "Utilizador e propietario.";
	public static final String INVALIDINFO = "Dados invalidos.";
	public static final String PROPERTYALREADYEXIST = "Propriedade existente.";
	public static final String INEXISTENTPROPERTY = "Propriedade inexistente.";
	public static final String PROPERTYVISITED = "Propriedade ja foi visitada.";
	public static final String TRAVELLERISOWNER = "Viajante e o proprietario.";
	public static final String TRAVELLERISNOTOWNER = "Viajante nao e o proprietario.";
	public static final String USERDIDNTTRAVELLED = "Utilizador nao viajou.";
	public static final String NORESULTS = "Pesquisa nao devolveu resultados.";

	public static void main(String[] args) {
		homeAwayManager hm = load();
		Scanner in = new Scanner(System.in);
		String command = "";

		while (!command.equalsIgnoreCase(EXIT)) {
			command = command(in);
			switch (command) {
			case CHANGUSERINFO:
				Main.changeUserInfo(in, hm);
				break;
			case REMOVEUSER:
				Main.removerUser(in, hm);
				break;
			case GETUSERINFO:
				Main.getUserInfo(in, hm);
				break;
			case ADDPROPERTY:
				Main.addProperty(in, hm);
				break;
			case REMOVEPROPERTY:
				Main.removeProperty(in, hm);
				break;
			case GETPROPERTYINFO:
				Main.getPropertyInfo(in, hm);
				break;
			case ADDSTAY:
				Main.addStay(in, hm);
				break;
			case LISTOWNERPROPERTIES:
				Main.listOwnerProperties(in, hm);
				break;
			case LISTTRAVELLERSTAYS:
				Main.listTravellerStays(in, hm);
				break;
			case SEARCHPROPERTY:
				Main.searchProperty(in, hm);
				break;
			case LISTBESTPROPERTIES:
				Main.listBestProperties(in, hm);
				break;
			}
			System.out.println();
		}
		Main.save(hm);
		in.close();

	}

	private static String command(Scanner in) {
		String command = in.next().toUpperCase();
		in.nextLine();
		return command;
	}

	private static void addUser(Scanner in, homeAwayManager hm) {

	}

	private static void changeUserInfo(Scanner in, homeAwayManager hm) {

	}

	private static void removerUser(Scanner in, homeAwayManager hm) {

	}

	private static void getUserInfo(Scanner in, homeAwayManager hm) {

	}

	private static void addProperty(Scanner in, homeAwayManager hm) {

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
