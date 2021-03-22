package Music;


import org.jfugue.player.Player;
import org.jfugue.midi.*;

import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    private Player player;
    private String music;

    public MusicPlayer() {
        player = new Player();
        music="";
    }

    public MusicPlayer(String music) {
        player = new Player();
        this.music = music;
    }

    void setMusic(MusicJFugue music) {
        this.music = music.toJFuguePlayableString();
    }

    void playMusic(){
        player.play(music);
    }             // toca a música

    void saveMusic(String filename){
        
    }     // salva a música
}