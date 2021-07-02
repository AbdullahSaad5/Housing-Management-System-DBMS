package frontend;

public class Ad {
	int id;
	int price;
	String owner;
	String location;
	int bedrooms;
	int bathrooms;
	int stories;
	int area;
	String purpose;

	public Ad(int id, int price, String owner, String location, int area) {
		this.id = id;
		this.price = price;
		this.owner = owner;
		this.location = location;
		this.area = area;
	}
	public Ad(int id, int price, String owner, String location, int bedrooms, int bathrooms, int stories, int area, String purpose) {
		this.id = id;
		this.price = price;
		this.owner = owner;
		this.location = location;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.stories = stories;
		this.area = area;
		this.purpose = purpose;
	}
}
