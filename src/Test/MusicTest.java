package Test;

import Music.Music;
import org.junit.Test;
import static org.junit.Assert.*;

public class MusicTest {
    Music music = new Music();

    @Test
    public void testEmptyString() {
        music.createMusicFromText("", 100, 79);
        assertNull(music.musicString);

        music.createMusicFromText("            ", 100, 79);
        assertNull(music.musicString);
    }

    @Test
    public void testMusicStringWithoutNotes() {
        music.createMusicFromText("la, oo. \n? ieeei", 40, 113);
        assertNull(music.musicString);
    }

    @Test
    public void testValidMusicString() {
        music.createMusicFromText("Ola, eu sou a Ametista. \nVamos ser amigos? Eu já tenho 9 amigos ieeei", 220, 6);
        assertEquals("T220 I6 I6 R R I19 :CON(7,50) R I6 :CON(7,100) R I6 I6 :CON(7,50) R :CON(7,100) 21 R R R I6 R R R :CON(7,50) I14 R R R I6 R :CON(7,100) R R R :CON(7,50) R R I6 R I6 R :CON(7,100) 40 I6 :CON(7,50) R R :CON(7,100) R R R R I6 :CON(7,50) I15 :CON(7,100) R R I6 R I6 R :CON(7,50) I6 R R R I6 ", music.musicString);
    }
}
