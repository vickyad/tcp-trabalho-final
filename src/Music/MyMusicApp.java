package Music;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class MyMusicApp
{
    public static void main(String[] args)
    {
        Player player = new Player();
        Pattern pattern = new Pattern("C D E F G A B");
        player.play(pattern);
    }
}