package Test;

import static Services.MusicValidationService.parseBPM;
import static Services.MusicValidationService.validateString;
import static org.junit.Assert.*;
import org.junit.Test;

public class MusicValidationServiceTest {
    @Test
    public void testEmptyText() {
        assertFalse(validateString("", "Piano"));
        assertFalse(validateString("   ", "Piano"));
        assertFalse(validateString("\n", "Piano"));
    }

    @Test
    public void testTooLongText() {
        assertFalse(validateString("This string is just a test, but, in this test, this string is too long. This string shouldn't be accepted yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada", "Piano"));
    }

    @Test
    public void testEmptyInstrument() {
        assertFalse(validateString("This string is just a test", null));
    }

    @Test
    public void testValidStringInput() {
        assertTrue(validateString("This string is just a test", "Piano"));
        assertTrue(validateString("This string is just a test, but, in this test, this string is just long enough. This string should be accepted yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada", "Bass"));
    }

    @Test
    public void testInvalidBPMType() {
        assertEquals(-1, parseBPM("this can't be transformed to int"));
    }

    @Test
    public void testInvalidBPMRange() {
        assertEquals(-1, parseBPM("39"));
        assertEquals(-1, parseBPM("225"));
    }

    @Test
    public void testValidBPMInput(){
        assertEquals(40, parseBPM("40"));
        assertEquals(220, parseBPM("220"));
        assertEquals(100, parseBPM("100"));
    }
}
