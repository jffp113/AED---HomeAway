package homeAway;

public class PropertyClass implements PropertyWritable {

	private	User owner;
	private String idHome;
	private	String address;
	private String description;
	private String local;
	private int price;
	private int maxPersons;
	private int points;
	private int numberVisits;
	
	public PropertyClass(String idHome, User idUser, int price, int maxPersons, String local, String description,
			String address) {
		this.idHome = idHome;
		this.owner = idUser;
		this.price = price;
		this.maxPersons = maxPersons;
		this.local=local;
		this.description= description;
		this.address = address;
	}

	@Override
	public String getIdHome() {

		return idHome;
	}

	@Override
	public String getAdress() {

		return address;
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

	@Override
	public int getNumberOfvisits() {
		return this.numberVisits;
	}

	@Override
	public void evaluateStay(int points,User users) {
		this.numberVisits++;
		this.points += points; 
		
		this.stay(users);
	}

	@Override
	public void stay(User users) {
		((UserWritable)users).addStay(this);
	}

}
