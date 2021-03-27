package Music;

public interface IMusicPlayer {
    void playMusic(String musicString);
    boolean downloadMusic(String musicString, String filename);
}
