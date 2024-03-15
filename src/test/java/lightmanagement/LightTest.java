package lightmanagement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LightTest {

    private Light light;

    @BeforeEach
    void setUp() {
        light = new Light(LightMode.MORNING, LightState.ON);
    }

    @Test
    void testSetLightBrightnessValid() throws InvalidBrightness {
        int brightness = 50;
        light.setLightBrightness(brightness);
        assertEquals(brightness, light.getLightBrightness());
    }

    @Test
    void testSetLightBrightnessInvalidNegative() {
        int invalidBrightness = -10;
        assertThrows(InvalidBrightness.class, () -> {
            light.setLightBrightness(invalidBrightness);
        });
    }

    @Test
    void testSetLightBrightnessInvalidExceedMax() {
        int invalidBrightness = 150;
        assertThrows(InvalidBrightness.class, () -> {
            light.setLightBrightness(invalidBrightness);
        });
    }

    @Test
    void testSetLightMode() {
        LightMode newMode = LightMode.AFTERNOON;
        light.setLightMode(newMode);
        assertEquals(newMode, light.getLightMode());
    }

    @Test
    void testSetLightState() {
        LightState newState = LightState.OFF;
        light.setLightState(newState);
        assertEquals(newState, light.getLightState());
    }

    @Test
    void testSetLightBrightnessNegative() {
        assertThrows(InvalidBrightness.class, () -> {
            light.setLightBrightness(-10);
        });
    }

    @Test
    void testSetLightBrightnessExceedMax() {
        assertThrows(InvalidBrightness.class, () -> {
            light.setLightBrightness(150);
        });
    }

    @Test
    void testGetLightMode() {
        assertEquals(LightMode.MORNING, light.getLightMode());
    }

    @Test
    void testGetLightState() {
        assertEquals(LightState.ON, light.getLightState());
    }

    @Test
    void testGetLightBrightness() {
        assertEquals(0, light.getLightBrightness());
    }

    @Test
    void testToString() {
        String expectedString = "Light [lightMode=MORNING, lightState=ON, lightBrightness=0]";
        assertEquals(expectedString, light.toString());
    }

}
