package lightmanagement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;

class InvalidBrightnessTest {

    @Test
    void testErrorMessage() {
        String errorMessage = "Brightness cannot be less than 0";
        InvalidBrightness invalidBrightness = new InvalidBrightness(errorMessage);
        assertEquals(errorMessage, invalidBrightness.getMessage());
    }

    @Test
    void testToString() {
        String errorMessage = "Brightness cannot be less than 0";
        InvalidBrightness invalidBrightness = new InvalidBrightness(errorMessage);
        assertEquals("lightmanagement.InvalidBrightness: " + errorMessage, invalidBrightness.toString());
    }

    @Test   
    void testThrowingInvalidBrightnessException() {
        assertThrows(InvalidBrightness.class, () -> {
            throw new InvalidBrightness("Test Message");
        });
    }
}