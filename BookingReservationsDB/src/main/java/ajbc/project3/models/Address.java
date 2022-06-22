package ajbc.project3.models;

public class Address 
{
	private String country;
	private String city;
	private String street;
	private int number;
	
	public Address() {
		
	}

	public Address(String country, String city, String street,  int number) {
		setStreet(street);
		setNumber(number);
		setCity(city);
		setCountry(country);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Address [country=" + country + ", city=" + city + ", street=" + street + ", number=" + number + "]";
	}
}