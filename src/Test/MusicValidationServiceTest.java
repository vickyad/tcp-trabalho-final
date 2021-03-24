package Test;

import static Services.MusicValidationService.validateString;
import static org.junit.Assert.*;
import org.junit.Test;

public class MusicValidationServiceTest {
    @Test
    public void testEmptyText() {
        assertEquals(false, validateString("", "Piano"));
        assertEquals(false, validateString("   ", "Piano"));
        assertEquals(false, validateString("\n", "Piano"));
    }

    @Test
    public void testTooLongText() {
        assertEquals(false, validateString("This string is just a test, but, in this test, this string is too long. This string shouldn't be accepted yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada", "Piano"));
    }

    @Test
    public void testEmptyInstrument() {
        assertEquals(false, validateString("This string is just a test", null));
    }

    @Test
    public void testValidInput() {
        assertEquals(true, validateString("This string is just a test", "Piano"));
        assertEquals(true, validateString("This string is just a test, but, in this test, this string is too long. This string shouldn't be accepted yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada", "Bass"));
    }
}
