package homeAway;

public class PropertyClass implements PropertyWritable {

	User owner;
	String idHome;
	String adress;
	String description;
	String local;
	int price;
	int maxPersons;
	int points;
	
	
	public PropertyClass(String idHome, String idUser, int price, int maxPersons, String local, String description, String adress) {
		this.idHome=idHome;
		this.owner=idUser;
		this.price=price;
		this.maxPersons=maxPersons;
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
