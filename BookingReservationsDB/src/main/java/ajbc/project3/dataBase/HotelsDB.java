package ajbc.project3.dataBase;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;
import org.bson.Document;

import ajbc.project3.models.Hotel;
import ajbc.project3.models.Room;
import ajbc.project3.models.Hotel.HotelName;

public class HotelsDB {

	public static List<ArrayList<Room>> listOfRoomsLists;
	public static List<Hotel> hotels;

	public HotelsDB() {
		listOfRoomsLists = new ArrayList<ArrayList<Room>>();
		initListOfHermosoRooms();
		initListOfLindoRooms();
		initListOfBelloRooms();
	}

	private void initListOfHermosoRooms() {
		ArrayList<Room> roomsList_1 = new ArrayList<Room>();
		roomsList_1.add(new Room(610, 20, 2, true, true));
		roomsList_1.add(new Room(611, 22, 2, true, false));
		roomsList_1.add(new Room(612, 24, 2, true, true));
		roomsList_1.add(new Room(613, 18, 2, false, true));
		listOfRoomsLists.add(roomsList_1);

		ArrayList<Room> roomsList_2 = new ArrayList<Room>();
		roomsList_2.add(new Room(300, 25, 2, true, true));
		roomsList_2.add(new Room(301, 22, 2, true, true));
		roomsList_2.add(new Room(302, 32, 2, true, true));
		roomsList_2.add(new Room(303, 20, 2, true, false));
		listOfRoomsLists.add(roomsList_2);

		ArrayList<Room> roomsList_3 = new ArrayList<Room>();
		roomsList_3.add(new Room(220, 16, 2, false, true));
		roomsList_3.add(new Room(230, 20, 2, false, false));
		roomsList_3.add(new Room(1010, 36, 2, true, true));
		roomsList_3.add(new Room(1020, 45, 2, true, true));
		listOfRoomsLists.add(roomsList_3);
	}

	private void initListOfLindoRooms() {
		ArrayList<Room> roomsList_1 = new ArrayList<Room>();
		roomsList_1.add(new Room(614, 25, 4, true, true));
		roomsList_1.add(new Room(615, 25, 4, false, false));
		roomsList_1.add(new Room(616, 32, 4, false, true));
		listOfRoomsLists.add(roomsList_1);

		ArrayList<Room> roomsList_2 = new ArrayList<Room>();
		roomsList_2.add(new Room(304, 33, 4, true, true));
		roomsList_2.add(new Room(305, 23, 4, false, true));
		roomsList_2.add(new Room(306, 36, 4, true, true));
		listOfRoomsLists.add(roomsList_2);

		ArrayList<Room> roomsList_3 = new ArrayList<Room>();
		roomsList_3.add(new Room(230, 30, 4, false, true));
		roomsList_3.add(new Room(240, 25, 4, true, true));
		roomsList_3.add(new Room(1030, 45, 3, true, false));
		listOfRoomsLists.add(roomsList_3);
	}

	private void initListOfBelloRooms() {
		ArrayList<Room> roomsList_1 = new ArrayList<Room>();
		roomsList_1.add(new Room(710, 22, 3, false, true));
		roomsList_1.add(new Room(711, 24, 3, true, false));
		listOfRoomsLists.add(roomsList_1);

		ArrayList<Room> roomsList_2 = new ArrayList<Room>();
		roomsList_2.add(new Room(307, 36, 3, true, true));
		roomsList_2.add(new Room(308, 25, 3, true, true));
		listOfRoomsLists.add(roomsList_2);

		ArrayList<Room> roomsList_3 = new ArrayList<Room>();
		roomsList_3.add(new Room(410, 26, 3, false, true));
		roomsList_3.add(new Room(420, 22, 2, false, true));
		listOfRoomsLists.add(roomsList_3);
	}

	public static InsertManyResult createListOfHotels(MongoDatabase DB) 
	{
		hotels = new ArrayList<Hotel>();
	
		hotels.add(new Hotel(HotelName.HERMOSO, AddressDB.addressList.get(0), 5, listOfRoomsLists.get(0), 650));
		hotels.add(new Hotel(HotelName.LINDO, AddressDB.addressList.get(1), 4, listOfRoomsLists.get(3), 500));
		hotels.add(new Hotel(HotelName.BELLO, AddressDB.addressList.get(2), 4, listOfRoomsLists.get(6), 550));
		hotels.add(new Hotel(HotelName.HERMOSO, AddressDB.addressList.get(3), 4, listOfRoomsLists.get(1), 700));
		hotels.add(new Hotel(HotelName.LINDO, AddressDB.addressList.get(4), 3, listOfRoomsLists.get(4), 230));
		hotels.add(new Hotel(HotelName.BELLO, AddressDB.addressList.get(5), 5, listOfRoomsLists.get(7), 750));
		hotels.add(new Hotel(HotelName.HERMOSO, AddressDB.addressList.get(6), 4, listOfRoomsLists.get(2), 700));
		hotels.add(new Hotel(HotelName.LINDO, AddressDB.addressList.get(7), 3, listOfRoomsLists.get(5), 300));
		hotels.add(new Hotel(HotelName.BELLO, AddressDB.addressList.get(8), 5, listOfRoomsLists.get(8), 990));

		List<Document> hotelDocs = new ArrayList<>();
		MongoCollection<Document> hotelsCollection = DB.getCollection("hotels");
		
		for (Hotel oneHotel: hotels) {
			
			hotelDocs.add(new Document("name", oneHotel.getName())
					.append("address", oneHotel.getAddress())
					.append("rank", oneHotel.getRank())
					.append("rooms", oneHotel.getRooms())
					.append("price_per_night", oneHotel.getPricePerNight())
					.append("orders", oneHotel.getOrders()));
		}
		
		InsertManyResult addResult = hotelsCollection.insertMany(hotelDocs);
		return addResult;
	}

}
