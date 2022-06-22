package ajbc.project3.main;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import ajbc.project3.connection.MyConnectionString;
import ajbc.project3.curd.CustomersDAO;
import ajbc.project3.curd.HotelsDAO;
import ajbc.project3.curd.OrdersDAO;
import ajbc.project3.dataBase.CustomersDB;
import ajbc.project3.dataBase.HotelsDB;
import ajbc.project3.dataBase.OrdersDB;
import ajbc.project3.models.Customer;
import ajbc.project3.models.Hotel;
import ajbc.project3.models.Order;


public class Runner 
{
	
	public static void main(String[] args) 
	{
	
	CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
	CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
	
	ConnectionString connectionString = MyConnectionString.uri();
	MongoClientSettings settings = MongoClientSettings.builder()
	        .applyConnectionString(connectionString)
	        .serverApi(ServerApi.builder()
	            .version(ServerApiVersion.V1)
	            .build())
	        .codecRegistry(codecRegistry)
	        .build();
	
	try (MongoClient mongoClient = MongoClients.create(settings);)
	{
		// create new DB in mongodb
		final String DB_NAME = "Booking-Reservations";
		MongoDatabase DB = mongoClient.getDatabase(DB_NAME);
					
		// create Hotels info
		//HotelsDB hotelsDb = new HotelsDB();
		//hotelsDb.createListOfHotels(DB);
		
		// create Customers info
		//CustomersDB customersDb = new CustomersDB();
		//customersDb.createListOfCustomers(DB);
		
		// create Orders info
		//OrdersDB orderssDb = new OrdersDB();
		//orderssDb.createCollectionOfOrders(DB);
		
		
		//handle: get all hotels
		//HotelsDAO hotelsDao = new HotelsDAO(DB);
		//List<Hotel> allHotels = hotelsDao.getAllHotels();
		//allHotels.forEach(System.out::println);
		
		//handle: get all customers
		//CustomersDAO customersDao = new CustomersDAO(DB);
		//List<Customer> allcustomers = customersDao.getAllCustomers();
		//allcustomers.forEach(System.out::println);
		
		//handle: get all orders of a hotel by id
		//OrdersDAO ordersDao = new OrdersDAO(DB);
		//List<Order> myOrders = ordersDao.getOrdersByHotel(new ObjectId("62b2b88164bce809bbd6348a"));
		//myOrders.forEach(System.out::println);

		

		//----------------------[Q1: get all the orders of a customer (by id)]--------------------------//
		//OrdersDAO ordersDao = new OrdersDAO(DB);
		//List<Order> myOrders = ordersDao.getOrdersByCustomer(new ObjectId("62b2b8bfe80f204edccee6f2"));
		//myOrders.forEach(System.out::println);
		
		
		//----------------------------[Q2: find hotels by a city name]----------------------------------//
		//HotelsDAO hotelsDao = new HotelsDAO(DB);
		//List<Hotel> hotelsByCity = hotelsDao.getHotelsByCity("London");
		//hotelsByCity.forEach(System.out::println);
		
		
		//--------------[Q3: check if a hotel(id) has an available room in a specific date]-------------//
		//OrdersDAO ordersDao = new OrdersDAO(DB);
		//checkAvailableHotel(ordersDao);
		
		
		//------------------------------------[Q5: cancel an order]-------------------------------------//
		//OrdersDAO ordersDao = new OrdersDAO(DB);
		//ordersDao.cancelAnOrder(new ObjectId("62b30c20c167d06dda30a3ea"));
		
		
		//-----------------[Q6: sort the hotels by total income from orders (descending)]---------------//
		//HotelsDAO hotelsDao = new HotelsDAO(DB);
		//hotelsDao.sortHotelsByIncomeOrders();
		
		
		//-----------------[Q7: what is the sum of the total prices of all orders?]---------------------//
		//OrdersDAO ordersDao = new OrdersDAO(DB);
		//ordersDao.getTheTotalPriceOfAllOrders();
	  }
	
	}
	
	private static void checkAvailableHotel(OrdersDAO ordersDao) {
		
		LocalDate specificDate = LocalDate.of(2023, 5, 5);
		//LocalDate specificDate = LocalDate.of(2024, 10, 10);
		//LocalDate specificDate = LocalDate.of(2024, 10, 4);
		//LocalDate specificDate = LocalDate.of(2024, 10, 6);    //false case
		//LocalDate specificDate = LocalDate.of(2024, 10, 7);    //false case

		int numOfPeopleInRoom = 3;
		int numOfNights = 2;
		boolean resultOfcheck = ordersDao.checkIfHotelAvailable(new ObjectId("62b2b88164bce809bbd6348a"),specificDate,numOfPeopleInRoom,numOfNights);
		if(resultOfcheck)
			System.out.println(resultOfcheck+"! you are welcome to order :)");
		else
			System.out.println(resultOfcheck+"! sorry we dont have a avalabile room in this date, please check another!");
	
	}
}