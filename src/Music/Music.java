package Music;

public class Music {
    private static final int MAX_VOLUME = 127;
    // protected int initialBpm;
    // protected int initialOctave;
    // protected int initialInstrument;
    public final int initialVolume;
    public String music;

    public Music(String raw_text) {
        TextConversor textConversor = new TextConversor();
        initialVolume = (int) (0.2 * MAX_VOLUME);
        music = textConversor.convert(raw_text, initialVolume);
    }
}