package ajbc.project3.dataBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;

import ajbc.project3.models.Customer;


public class CustomersDB {
	
protected static List<Customer> customersList;
	
	public CustomersDB ()
	{
		customersList = new ArrayList<Customer>();
		initListOfCustomers();
	}
	

	private static void initListOfCustomers()
	{
		customersList.add(new Customer("Faraj","Khanjar","Israel"));
		customersList.add(new Customer("Nasreen","Madi","Israel"));
		customersList.add(new Customer("Wafi","Khanjar","Israel"));
		customersList.add(new Customer("Jack","Roso","Germony"));
		customersList.add(new Customer("Sari","Gotman","England"));
		customersList.add(new Customer("Forest","Black","England"));
		customersList.add(new Customer("Rafif","aodela","France"));

	}
	
	public static InsertManyResult createListOfCustomers(MongoDatabase DB)
	{
		MongoCollection<Document> collection = DB.getCollection("customers");
		List<Customer> customers = Arrays.asList(customersList.get(0),
				customersList.get(1),customersList.get(2),customersList.get(3),
				customersList.get(4),customersList.get(5),
				customersList.get(6));
		
		List<Document> customerDocs = new ArrayList<>();
		for (Customer oneCustomer: customers)
		{
			customerDocs.add(new Document("first_name",oneCustomer.getFirstName())
					.append("last_name", oneCustomer.getLastName())
					.append("country", oneCustomer.getCountry()));
		}
		InsertManyResult result = collection.insertMany(customerDocs);
		return result;
	}
}