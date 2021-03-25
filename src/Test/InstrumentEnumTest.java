package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import Music.InstrumentEnum;

public class InstrumentEnumTest {
    @Test
    public void testRandomInstrument() {
        String randomInstrument = InstrumentEnum.getRandomInstrument();
        boolean instrumentFound = false;

        for (InstrumentEnum instruments : InstrumentEnum.values()) {
            if (String.valueOf(instruments.getValue()).equalsIgnoreCase(randomInstrument))
                instrumentFound = true;
        }

        assertTrue(instrumentFound);
    }
}
