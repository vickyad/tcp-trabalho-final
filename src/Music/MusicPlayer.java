package Music;

import Services.TextConvertorService;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private final String raw_text = "Hello! + My T+ED is \nBPM- Fox - The Boring? Camel";
    //private Player player;      // objeto da biblioteca JFugue
    private final Music music = new Music(raw_text);
    private final TextConvertorService convertor = new TextConvertorService();
    private String music_string;
    private Player player = new Player();

    public void setMusic() {
        music_string = convertor.convert(raw_text, music.initialVolume);
        System.out.println(music_string);
    }

    public void playMusic(){
        Pattern pattern = new Pattern(music_string);
        player.play(pattern);
    }

    public void saveMusic(String filename) throws IOException {
        try{
            File file = new File(filename + ".mid");
            org.jfugue.pattern.Pattern pat = new org.jfugue.pattern.Pattern();
            pat.add(music_string);
            org.jfugue.midi.MidiFileManager.savePatternToMidi(pat, file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}