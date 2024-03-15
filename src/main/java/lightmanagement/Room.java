package lightmanagement;

import java.util.HashMap;

public class Room {

	private int roomID;
	private int numberOfLights;
	private HashMap<Integer, Light> lights;

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getNumberOfLights() {
		return numberOfLights;
	}


	public Room() {
		numberOfLights = 0;
		lights = new HashMap<>();
	}

	public HashMap<Integer, Light> getLights() {
		return lights;
	}


	public void addLight(int id, Light light) {
		numberOfLights += 1;
		lights.put(id, light);
	}

	public Light getLight(int lightIndex) {
		return lights.get(lightIndex);
	}

	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", numberOfLights=" + numberOfLights + ", lights=" + lights + "]";
	}

}
