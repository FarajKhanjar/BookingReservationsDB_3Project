package ajbc.project3.curd;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import ajbc.project3.models.Customer;

public class CustomersDAO {
	
	private MongoDatabase DB;
	protected MongoCollection<Customer> customers;
	
	public CustomersDAO(MongoDatabase DB)
	{
		this.DB = DB;
		this.customers = DB.getCollection("customers", Customer.class);
	}
	
	public List<Customer> getAllCustomers()
	{
		return customers.find().into(new ArrayList<>());
	}
	
	public Customer getCustomerById(ObjectId id)
	{
		Customer currentCustomer =  customers.find(Filters.eq("_id",id)).first();
		return currentCustomer;
	}
	
	public List<Customer> getCustomerByCountry(String country)
	{
		List<Customer> customerByCountry =  customers.find(Filters.eq("country",country)).into(new ArrayList<>());
		return customerByCountry;
	}
	
	public List<Customer> getTouristsCustomer(String country)
	{
		List<Customer> customerTourists =  customers.find(Filters.ne("country",country)).into(new ArrayList<>());
		return customerTourists;
	}

}
