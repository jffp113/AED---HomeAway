package homeAway;

public class PropertyClass implements PropertyWritable {

	private	User owner;
	private String idHome;
	private	String adress;
	private String description;
	private String local;
	private int price;
	private int maxPersons;
	private int points;

	public PropertyClass(String idHome, User idUser, int price, int maxPersons, String local, String description,
			String adress) {
		this.idHome = idHome;
		this.owner = idUser;
		this.price = price;
		this.maxPersons = maxPersons;
		this.local=local;
		this.description= description;
		this.adress = adress;
	}

	@Override
	public String getIdHome() {

		return idHome;
	}

	@Override
	public String getAdress() {

		return adress;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getLocal() {

		return local;
	}

	@Override
	public int getPrice() {

		return price;
	}

	@Override
	public int getMaxPersons() {

		return maxPersons;
	}

	@Override
	public User getOwner() {

		return owner;
	}

	@Override
	public int getPoints() {

		return points;
	}

}
