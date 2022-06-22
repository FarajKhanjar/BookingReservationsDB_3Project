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
		Customer current =  customers.find(Filters.eq("_id",id)).first();
		return current;
	}

}
