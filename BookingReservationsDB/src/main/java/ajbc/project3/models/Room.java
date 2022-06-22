package ajbc.project3.models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Room {
	
	private ObjectId id;
	private int number;
	private int size;
	private int capacity;
	@BsonProperty(value="has_bath")
	private boolean hasBath;
	private boolean hasTerrace;
	
	public Room(int number, int size, int capacity, boolean hasBath, boolean hasTerrace) {

		this.number = number;
		this.size = size;
		this.capacity = capacity;
		this.hasBath = hasBath;
		this.hasTerrace = hasTerrace;
	}
	
	public Room()
	{
		
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isHasBath() {
		return hasBath;
	}

	public void setHasBath(boolean hasBath) {
		this.hasBath = hasBath;
	}

	public boolean isHasTerrace() {
		return hasTerrace;
	}

	public void setHasTerrace(boolean hasTerrace) {
		this.hasTerrace = hasTerrace;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", number=" + number + ", size=" + size + ", capacity=" + capacity + ", hasBath="
				+ hasBath + ", hasTerrace=" + hasTerrace + "]";
	}
	
	
	
}