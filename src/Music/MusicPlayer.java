package Music;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import java.io.File;
import java.io.IOException;

public class MusicPlayer implements IMusicPlayer {
    public void playMusic(String musicString){
        new Thread(createThread(musicString)).start();
    }
    public boolean downloadMusic(String musicString, String filename) {
        try{
            File file = new File(filename + ".mid");
            org.jfugue.pattern.Pattern pat = new org.jfugue.pattern.Pattern();
            pat.add(musicString);
            org.jfugue.midi.MidiFileManager.savePatternToMidi(pat, file);
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    private Runnable createThread (String musicString) {
        return () -> {
            Player player = new Player();
            Pattern pattern = new Pattern(musicString);
            player.play(pattern);
        };
    }
}