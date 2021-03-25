package Music;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import java.io.File;
import java.io.IOException;

public class MusicPlayer implements IMusicPlayer {
    private final static Player player = new Player();

    public void playMusic(String musicString){
        Pattern pattern = new Pattern(musicString);
        player.play(pattern);
    }

    public boolean saveMusic(String musicString, String filename) {
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
}