package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import Music.NoteEnum;

public class NoteEnumTest {
    @Test
    public void testRandomNote() {
        String randomNote = NoteEnum.getRandomNote(0);
        boolean noteFound = false;

        for (NoteEnum notes : NoteEnum.values()) {
            if (String.valueOf(notes.getValue()).equalsIgnoreCase(randomNote))
                noteFound = true;
        }

        assertTrue(noteFound);
    }
}