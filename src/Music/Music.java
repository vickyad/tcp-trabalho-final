package Music;

// import Services.MusicValidationService;
// import org.jfugue.pattern.Pattern;
// import org.jfugue.player.Player;

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

/*
public class Music
{

    private String rawText;
    private String initialInstrument;
    private int initialBPM;
    private int currentBPM;

    public Music(String rawText, String initialInstrument, int initialBPM){
        this.rawText = rawText;
        this.initialInstrument = initialInstrument;
        this.initialBPM = initialBPM;
    }

    // getters e setters

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public String getInitialInstrument() {
        return initialInstrument;
    }

    public void setInitialInstrument(String initialInstrument) {
        this.initialInstrument = initialInstrument;
    }

    public int getCurrentBPM() {
        return currentBPM;
    }

    public void setCurrentBPM(int currentBPM) {
        this.currentBPM = currentBPM;
    }

    public static void play_music()
    {
        Player player = new Player();
        Pattern pattern = new Pattern("C D E F G A B");
        player.play(pattern);
    }
}
 */