package Music;

public interface IMusicPlayer {
    void playMusic(String musicString);
    boolean saveMusic(String musicString, String filename);
}
