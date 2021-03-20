package Music;

public class MusicJFugue extends Music {
    public String music;

    public MusicJFugue (String raw_text) {
        TextConversor textConversor = new TextConversor();

        music = textConversor.convert(raw_text);
    }
}
