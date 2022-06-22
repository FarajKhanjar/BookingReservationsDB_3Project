package ajbc.project3.curd;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import static com.mongodb.client.model.Updates.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.function.Consumer;

import ajbc.project3.models.Hotel;
import ajbc.project3.models.Hotel.HotelName;
import ajbc.project3.models.Order;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import com.mongodb.client.model.Projections;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Accumulators.sum;

public class OrdersDAO {
	
	private HotelsDAO hotelDao;
	private CustomersDAO customersDao;
	
	private MongoDatabase DB;
	private MongoCollection<Order> orders;
	
	public OrdersDAO(MongoDatabase DB)
	{
		this.DB = DB;
		this.orders = DB.getCollection("orders", Order.class);
		hotelDao = new HotelsDAO(DB);
		customersDao = new CustomersDAO(DB);
	}
	
	public List<Order> getOrdersByCustomer(ObjectId customerId)
	{
		List<Order> custOrders = orders.find(Filters.eq("customer_id", customerId)).into(new ArrayList<>());
		return custOrders;
	}
	
	public List<Order> getOrdersByHotel(ObjectId hotelId)
	{
		List<Order> custOrders = orders.find(Filters.eq("hotel_id", hotelId)).into(new ArrayList<>());
		return custOrders;
	}
	
	public InsertOneResult createNewOrder(Order newOrder)
	{
		InsertOneResult addResult = null;
		Hotel currentHotel = hotelDao.getHotelById(newOrder.getHotelId());
		
		//System.out.println(newOrder);
		//System.out.println(currentHotel);
		
		if (checkIfHotelAvailable(newOrder.getHotelId(), newOrder.getStartDate(), newOrder.getNumPeople(), newOrder.getNumNights()))
		{
			newOrder.setTotalPrice(newOrder.getNumNights() * currentHotel.getPricePerNight());
			Bson hotelUpdated = addToSet("orders", newOrder);
			
			hotelDao.hotels.updateOne(Filters.eq("_id",currentHotel.getId()), hotelUpdated);
			Bson customerUpdated = addToSet("orders", newOrder);
			
			customersDao.customers.updateOne(Filters.eq("_id", newOrder.getCustomerId()), customerUpdated);
			addResult = orders.insertOne(newOrder);
			System.out.println("Order is confirmed!");
			System.out.println(newOrder);
			return addResult;
		}
		
		System.out.println("Order is rejected right now! check number of people or start order date.");
		System.out.println("Your order:\n"+currentHotel.getName()+" Hotel at: "+currentHotel.getAddress()
				+" for: "+newOrder.getNumPeople()+" people");
		return addResult;
	}
	
	public boolean checkIfHotelAvailable(ObjectId hotelId, LocalDate date, int numPeople, int numNights)
	{
		Hotel currentHotel = hotelDao.getHotelById(hotelId);
		LocalDate orderDate=date;

		if(date.isBefore(LocalDate.now())) {
			System.out.println("Error Date");
			return false;
		}
		
		int maxNumPeople;
		int numRooms;
		if(currentHotel.getName()==HotelName.HERMOSO) {
			maxNumPeople=2; numRooms=4;
		}
		else if(currentHotel.getName()==HotelName.BELLO) {
			maxNumPeople=3; numRooms=2;
		}
		else {
			maxNumPeople=4; numRooms=3;
		}
		
		if (numPeople > maxNumPeople) {
			System.out.println("Unfortunately number of people is large then the maximum in each room"
					+ "\nyou can split the rooms by another order/s.");
			return false;		
		}
			
		
		List<Order> orders = currentHotel.getOrders();
		int roomsAvailableCounter = numRooms;
		
		System.out.println("Number of rooms in "+currentHotel.getName()+" hotel - "+currentHotel.getAddress().getCity()+": "+numRooms);
		orders.forEach(System.out::println);
		
		for (Order oneOrder: orders)
		{
			LocalDate start = oneOrder.getStartDate();
			LocalDate end = oneOrder.getStartDate().plusDays(oneOrder.getNumNights());
			System.out.println("This room is out-of-service Between: "+start+" -> "+end);
			
			List<LocalDate> totalDates = new ArrayList<>();
			while (!start.isAfter(end)) {
			    totalDates.add(start);
			    start = start.plusDays(1);
			}
			//System.out.println("It means in dates: ");
			//totalDates.forEach(System.out::print);
			
			if(date.isBefore(totalDates.get(0)))
				date=date.plusDays(numNights);
			
			for (LocalDate d: totalDates) {
				if (date.isEqual(d)) {
					roomsAvailableCounter--;
					System.out.println("Number of rooms in "+currentHotel.getName()+" hotel - "+currentHotel.getAddress().getCity()+": "+roomsAvailableCounter);
				}
				
			}

		}
		System.out.println("\nOrder Between: "+orderDate+" -> "+orderDate.plusDays(numNights));
		return roomsAvailableCounter > 0;
	}
	
	public void cancelAnOrder(ObjectId orderId)
	{
		Order currentOrder = orders.findOneAndDelete(Filters.eq("_id", orderId));
		Bson hotelUpdated = pull("orders", currentOrder);
		
		hotelDao.hotels.updateOne(Filters.eq("_id", currentOrder.getHotelId()), hotelUpdated);
		Bson customerUpdated = pull("orders", currentOrder);
		
		customersDao.customers.updateOne(Filters.eq("_id", currentOrder.getCustomerId()), customerUpdated);
		System.out.println("Order has removed!");
	}
	
	public void getTheTotalPriceOfAllOrders()
	{
		MongoCollection<Document> orderDocs = DB.getCollection("orders");
		Bson group = group(null, sum("The_Orders_Total_Price", "$total_price"));
		Bson project = project(Projections.fields(Projections.excludeId(),
				          Projections.include("The_Orders_Total_Price")));
		
		List<Document> totalPrice = orderDocs.aggregate(Arrays.asList(group, project))
				                                              .into(new ArrayList<>());
		totalPrice.forEach(displayDocuments());
	}
	
	private static Consumer<Document> displayDocuments() 
	{
		return document -> System.out.println(document.toJson(JsonWriterSettings.builder()
				                                     .indent(true).build()));
	}

}
