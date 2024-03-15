package lightmanagement;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InvalidLightExceptionTest {

    @Test
    void testErrorMessage() {
        String errorMessage = "Invalid light exception message";
        InvalidLightException invalidLightException = new InvalidLightException(errorMessage);
        assertEquals(errorMessage, invalidLightException.getMessage());
    }
}