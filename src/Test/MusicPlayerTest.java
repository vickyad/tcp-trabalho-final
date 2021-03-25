package Test;

import Music.MusicPlayer;
import static org.junit.Assert.*;
import org.junit.Test;

public class MusicPlayerTest {
    @Test
    public void testSuccessfulDownload() {
        String musicString = "A B C D";
        String fileName = "saveTest";

        assertTrue(MusicPlayer.saveMusic(musicString, fileName));
    }
}
