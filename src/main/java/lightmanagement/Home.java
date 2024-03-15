package lightmanagement;

import java.util.ArrayList;

public class Home {

	private final ArrayList<Room> rooms;
	private int roomCount;

	public Home() {
		rooms = new ArrayList<>();
		roomCount = 0;
	}

	public int getRoomCount() {
		return roomCount;
	}


	public ArrayList<Room> getRooms() {
		return rooms;
	}


	public void addRoom(Room room) {
		roomCount += 1;
		room.setRoomID(roomCount);
		rooms.add(room);
		
	}

	@Override
	public String toString() {
		return "Home [rooms=" + rooms + "]";
	}

}
