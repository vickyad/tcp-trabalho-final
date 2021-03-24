package Music;

import static org.junit.Assert.*;
import org.junit.Test;

public class NoteEnumTest {
    @Test
    public void testEnumNames() {
        assertEquals("A",NoteEnum.A.name());
        assertEquals("B",NoteEnum.B.name());
        assertEquals("C",NoteEnum.C.name());
        assertEquals("D",NoteEnum.D.name());
        assertEquals("E",NoteEnum.E.name());
        assertEquals("F",NoteEnum.F.name());
        assertEquals("G",NoteEnum.G.name());
    }

    @Test
    public void testEnumValues() {
        assertEquals(9, NoteEnum.A.getValue());
        assertEquals(11, NoteEnum.B.getValue());
        assertEquals(0, NoteEnum.C.getValue());
        assertEquals(2, NoteEnum.D.getValue());
        assertEquals(4, NoteEnum.E.getValue());
        assertEquals(5, NoteEnum.F.getValue());
        assertEquals(7, NoteEnum.G.getValue());
    }
}