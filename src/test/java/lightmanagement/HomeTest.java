package lightmanagement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class HomeTest {

    private Home home;

    @BeforeEach
    void setUp() {
        home = new Home();
    }

    @Test
    void testAddRoom() {
        assertEquals(0, home.getRoomCount());

        Room room1 = new Room();
        Room room2 = new Room();

        home.addRoom(room1);
        home.addRoom(room2);

        ArrayList<Room> rooms = home.getRooms();
        assertEquals(2, rooms.size());
        assertEquals(room1, rooms.get(0));
        assertEquals(room2, rooms.get(1));
    }

    @Test
    void testRoomCount() {
        assertEquals(0, home.getRoomCount());

        Room room1 = new Room();
        Room room2 = new Room();

        home.addRoom(room1);
        home.addRoom(room2);

        assertEquals(2, home.getRoomCount());
    }

    @Test
    void testToString() {
        Room room1 = new Room();
        Room room2 = new Room();

        home.addRoom(room1);
        home.addRoom(room2);

        String expectedString = "Home [rooms=[" + room1.toString() + ", " + room2.toString() + "]]";
        assertEquals(expectedString, home.toString());
    }
}
