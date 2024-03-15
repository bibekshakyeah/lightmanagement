package lightmanagement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomTest {

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room();
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(0, room.getNumberOfLights());
        assertEquals(0, room.getRoomID());
        assertTrue(room.getLights().isEmpty());
    }

    @Test
    void testAddLight() {
        Light light1 = new Light(LightMode.MORNING, LightState.ON);
        Light light2 = new Light(LightMode.EVENING, LightState.OFF);

        room.addLight(1, light1);
        room.addLight(2, light2);

        HashMap<Integer, Light> lights = room.getLights();
        assertEquals(2, lights.size());
        assertTrue(lights.containsKey(1));
        assertTrue(lights.containsKey(2));
        assertEquals(light1, lights.get(1));
        assertEquals(light2, lights.get(2));
    }

    @Test
    void testNumberOfLights() {
        assertEquals(0, room.getNumberOfLights());

        Light light1 = new Light(LightMode.MORNING, LightState.ON);
        Light light2 = new Light(LightMode.EVENING, LightState.OFF);

        room.addLight(1, light1);
        room.addLight(2, light2);

        assertEquals(2, room.getNumberOfLights());
    }

    @Test
    void testGetLight() {
        Light light1 = new Light(LightMode.MORNING, LightState.ON);
        Light light2 = new Light(LightMode.EVENING, LightState.OFF);

        room.addLight(1, light1);
        room.addLight(2, light2);

        assertEquals(light1, room.getLight(1));
        assertEquals(light2, room.getLight(2));
        assertNull(room.getLight(3)); // Light with ID 3 does not exist
    }
    @Test
    void testSetRoomID() {
        room.setRoomID(10);
        assertEquals(10, room.getRoomID());
    }

    @Test
    void testToString() {
        Light light1 = new Light(LightMode.MORNING, LightState.ON);
        Light light2 = new Light(LightMode.EVENING, LightState.OFF);

        room.addLight(1, light1);
        room.addLight(2, light2);

        String expectedString = "Room [roomID=0, numberOfLights=2, lights={1=Light [lightMode=MORNING, lightState=ON, lightBrightness=0], 2=Light [lightMode=EVENING, lightState=OFF, lightBrightness=0]}]";
        assertEquals(expectedString, room.toString());
    }
}

