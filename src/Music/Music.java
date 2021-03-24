package Music;

public class Music {
    private static final int MAX_VOLUME = 127;
    // protected int initialBpm;
    // protected int initialOctave;
    // protected int initialInstrument;
    protected int initialVolume;

    public Music() {
        initialVolume = (int) (0.2 * MAX_VOLUME);
    }
}