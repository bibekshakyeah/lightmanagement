package lightmanagement;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InvalidRoomCountExceptionTest {

    @Test
    void testErrorMessage() {
        String errorMessage = "Invalid room count exception message";
        InvalidRoomCountException invalidRoomCountException = new InvalidRoomCountException(errorMessage);
        assertEquals(errorMessage, invalidRoomCountException.getMessage());
    }
}