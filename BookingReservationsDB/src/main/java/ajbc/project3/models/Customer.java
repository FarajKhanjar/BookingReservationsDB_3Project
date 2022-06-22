package ajbc.project3.models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Customer 
{
	private ObjectId id;
	@BsonProperty(value="last_name")
	private String lastName;
	@BsonProperty(value="first_name")
	private String firstName;
	private String country;
	
	private List<Order> orders;
	
	public Customer(ObjectId id, String firstName, String lastName, String country, List<Order> orders) 
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.orders = orders;
	}

	public Customer(String firstName, String lastName, String country) 
	{
		setFirstName(firstName);
		setLastName(lastName);
		setCountry(country);
		this.orders = new ArrayList<>();
	}
	
	public Customer() {
		
	}

	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void addOrder(Order newOrder)
	{
		orders.add(newOrder);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country
				+ ", orders=" + orders + "]";
	}

}