package ajbc.project3.dataBase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;

import ajbc.project3.curd.OrdersDAO;
import ajbc.project3.models.Hotel;
import ajbc.project3.models.Order;


public class OrdersDB {
	
	private static List<ObjectId> hotels;
	private static List<ObjectId> customers;
	
	public OrdersDB ()
	{
		giveHotelObjectId();
		giveCustomersObjectId();
	}
	
	public static void createCollectionOfOrders(MongoDatabase DB)
	{
		Order order_1 = new Order(hotels.get(0), customers.get(0), LocalDate.now()
				,LocalDate.of(2022, 10, 9), 7, 2, 3, true);
		
		Order order_2 = new Order(hotels.get(1), customers.get(1), LocalDate.now()
				,LocalDate.of(2022, 10, 10), 5, 4, 2, false);

		Order order_3 = new Order(hotels.get(2), customers.get(2), LocalDate.now()
				,LocalDate.of(2022, 12, 30), 3, 3, 2, true);

		Order order_4 = new Order(hotels.get(3), customers.get(3), LocalDate.now()
				,LocalDate.of(2023, 1, 1), 7, 2, 1, true);
		
		Order order_5 = new Order(hotels.get(4), customers.get(3), LocalDate.now()
				,LocalDate.of(2022, 7, 1), 3, 2, 1, false);
		
		Order order_6 = new Order(hotels.get(5), customers.get(4), LocalDate.now()
				,LocalDate.of(2023, 4, 1), 4, 3, 0, false);
		
		Order order_7 = new Order(hotels.get(6), customers.get(5), LocalDate.now()
				,LocalDate.of(2023, 5, 5), 7, 2, 3, true);
		
		Order order_8 = new Order(hotels.get(7), customers.get(6), LocalDate.now()
				,LocalDate.of(2023, 5, 5), 6, 4, 2, false);

		Order order_9 = new Order(hotels.get(1), customers.get(6), LocalDate.now()
				,LocalDate.of(2024, 5, 5), 5, 1, 0, true);
		
		Order order_10 = new Order(hotels.get(7), customers.get(6), LocalDate.now()
				,LocalDate.of(2024, 10, 7), 2, 4, 0, true);
		
		Order order_11 = new Order(hotels.get(7), customers.get(5), LocalDate.now()
				,LocalDate.of(2024, 10, 7), 3, 4, 0, true);

		
		OrdersDAO ordersDao = new OrdersDAO(DB);
		/*
		ordersDao.createNewOrder(order_1);
		ordersDao.createNewOrder(order_2);
		ordersDao.createNewOrder(order_3);
		ordersDao.createNewOrder(order_4);
		ordersDao.createNewOrder(order_5);
		ordersDao.createNewOrder(order_6);
		ordersDao.createNewOrder(order_7);
		ordersDao.createNewOrder(order_8);
		ordersDao.createNewOrder(order_9);
		ordersDao.createNewOrder(order_10);
		ordersDao.createNewOrder(order_11);
		*/
		
		
		//-----------[Q4: create an order for a hotel room in a specific date for x number of nights]---------//
		//(1) Not available room because of the number of people in it.
		/*
		Order  notApprovedOrder_1 = new Order(hotels.get(8), customers.get(0), LocalDate.now()
				,LocalDate.of(2022, 9, 25), 3, 4, 1, false);
		ordersDao.createNewOrder(notApprovedOrder_1);
		*/
	
		
		//(2) Not available room because of a out-of-service date.	
		/*
		Order notApprovedOrder_2 = new Order(hotels.get(7), customers.get(0), LocalDate.now()
				,LocalDate.of(2024, 10, 7), 2, 4, 1, true);
		ordersDao.createNewOrder(notApprovedOrder_2);
		*/
		
		//(3) available Order
		/*
		Order order_12 = new Order(hotels.get(0), customers.get(1), LocalDate.now()
				,LocalDate.of(2022, 11, 30), 5, 2, 1, true);
		ordersDao.createNewOrder(order_12);
		*/
		
		

	}
	
	private static void giveHotelObjectId()
	{
		ObjectId hermosoHotel_1 = new ObjectId("62b2b88164bce809bbd63483");
		ObjectId lindoHotel_1 = new ObjectId("62b2b88164bce809bbd63484");
		ObjectId belloHotel_1 = new ObjectId("62b2b88164bce809bbd63485");
		
		ObjectId hermosoHotel_2 = new ObjectId("62b2b88164bce809bbd63486");
		ObjectId lindoHotel_2 = new ObjectId("62b2b88164bce809bbd63487");
		ObjectId belloHotel_2 = new ObjectId("62b2b88164bce809bbd63488");
		
		ObjectId hermosoHotel_3 = new ObjectId("62b2b88164bce809bbd63489");
		ObjectId lindoHotel_3 = new ObjectId("62b2b88164bce809bbd6348a");
		ObjectId belloHotel_3 = new ObjectId("62b2b88164bce809bbd6348b");
		
		hotels = Arrays.asList(hermosoHotel_1, lindoHotel_1, belloHotel_1
				              ,hermosoHotel_2, lindoHotel_2, belloHotel_2
			               	,hermosoHotel_3, lindoHotel_3, belloHotel_3);
			 
	}
	
	private static void giveCustomersObjectId()
	{
		ObjectId customer_1 = new ObjectId("62b2b8bfe80f204edccee6ec");
		ObjectId customer_2 = new ObjectId("62b2b8bfe80f204edccee6ed");
		ObjectId customer_3 = new ObjectId("62b2b8bfe80f204edccee6ee");
		ObjectId customer_4 = new ObjectId("62b2b8bfe80f204edccee6ef");
		ObjectId customer_5 = new ObjectId("62b2b8bfe80f204edccee6f0");
		ObjectId customer_6 = new ObjectId("62b2b8bfe80f204edccee6f1");
		ObjectId customer_7 = new ObjectId("62b2b8bfe80f204edccee6f2");
		
		customers = Arrays.asList(customer_1, customer_2, customer_3
                                       ,customer_4,customer_5,customer_6,customer_7);
			
	}

}
