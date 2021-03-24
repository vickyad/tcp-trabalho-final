package Music;

import static org.junit.Assert.*;
import org.junit.Test;

public class InstrumentEnumTest {
    @Test
    public void testEnumNames() {
        assertEquals("Piano",InstrumentEnum.Piano.name());
        assertEquals("Guitar",InstrumentEnum.Guitar.name());
        assertEquals("Violin",InstrumentEnum.Violin.name());
        assertEquals("Trumpet",InstrumentEnum.Trumpet.name());
        assertEquals("Bass",InstrumentEnum.Bass.name());
    }

    @Test
    public void testEnumValues() {
        assertEquals(0, InstrumentEnum.Piano.getValue());
        assertEquals(24, InstrumentEnum.Guitar.getValue());
        assertEquals(40, InstrumentEnum.Violin.getValue());
        assertEquals(56, InstrumentEnum.Trumpet.getValue());
        assertEquals(32, InstrumentEnum.Bass.getValue());
    }
}
