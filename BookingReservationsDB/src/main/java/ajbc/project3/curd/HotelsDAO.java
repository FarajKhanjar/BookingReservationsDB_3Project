package ajbc.project3.curd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.bson.json.JsonWriterSettings;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Accumulators.first;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Accumulators.push;

import ajbc.project3.models.Hotel;

public class HotelsDAO {
	
	private MongoDatabase DB;
	protected MongoCollection<Hotel> hotels;
	
	public HotelsDAO(MongoDatabase DB)
	{
		this.DB = DB;
		this.hotels = DB.getCollection("hotels", Hotel.class);
	}
	
	public List<Hotel> getAllHotels()
	{
		List<Hotel> allHotels = hotels.find().into(new ArrayList<>());
		return allHotels;
	}
	
	public Hotel getHotelById(ObjectId id)
	{
		Hotel current = hotels.find(Filters.eq("_id",id)).first();
		return current;
	}
	
	public List<Hotel> getHotelsByCity(String city)
	{
		List<Hotel> hotelsInCity = hotels.find(Filters.eq("address.city", city)).into(new ArrayList<>());
		return hotelsInCity;
	}
	
	public void sortHotelsByIncomeOrders()
	{
		Bson unwind = Aggregates.unwind("$orders");
		Bson group = group("$_id", sum("The_Total_Income","$orders.total_price")
			                                	   ,first("name", "$name"),first("city", "$address.city"));
		
		Bson sort = sort(Sorts.descending("The_Total_Income"));
		Bson project = project(Projections.fields(Projections.include("_id", "name","city", "The_Total_Income")));
		
		MongoCollection<Document> hotelDocuments = DB.getCollection("hotels");
		List<Document> sortedHotelsList = hotelDocuments.aggregate
				                          (Arrays.asList(unwind, group, project, sort))
				                          .into(new ArrayList<>());
		sortedHotelsList.forEach(displayDocuments());
	}
	
	private static Consumer<Document> displayDocuments() 
	{
		return document -> System.out.println(document.toJson(JsonWriterSettings.builder().indent(true).build()));
	}

}