package Test;

import static org.junit.Assert.*;
import Services.MusicValidationService;
import org.junit.Test;

public class MusicValidationServiceTest {
    MusicValidationService musicValidationService = new MusicValidationService();

    @Test
    public void testEmptyText() {
        assertFalse(musicValidationService.validateString("", "Piano"));
        assertFalse(musicValidationService.validateString("   ", "Piano"));
        assertFalse(musicValidationService.validateString("\n", "Piano"));
    }

    @Test
    public void testTooLongText() {
        assertFalse(musicValidationService.validateString("This string is just a test, but, in this test, this string is too long. This string shouldn't be accepted yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada", "Piano"));
    }

    @Test
    public void testEmptyInstrument() {
        assertFalse(musicValidationService.validateString("This string is just a test", null));
    }

    @Test
    public void testValidStringInput() {
        assertTrue(musicValidationService.validateString("This string is just a test", "Piano"));
        assertTrue(musicValidationService.validateString("This string is just a test, but, in this test, this string is just long enough. This string should be accepted yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada yada", "Bass"));
    }

    @Test
    public void testInvalidBPMType() {
        assertEquals(-1, musicValidationService.parseBPM("this can't be transformed to int"));
    }

    @Test
    public void testInvalidBPMRange() {
        assertEquals(-1, musicValidationService.parseBPM("39"));
        assertEquals(-1, musicValidationService.parseBPM("225"));
    }

    @Test
    public void testValidBPMInput(){
        assertEquals(40, musicValidationService.parseBPM("40"));
        assertEquals(220, musicValidationService.parseBPM("220"));
        assertEquals(100, musicValidationService.parseBPM("100"));
    }
}
