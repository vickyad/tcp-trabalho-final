package Music;

import Services.TextConvertorService;

public class Music {
    private static final int MAX_VOLUME = 127;
    public final int initialVolume;
    public String musicString;

    public Music(String raw_text, int initialBpm, int initialInstrument) {
        TextConvertorService textConvertor = new TextConvertorService();
        initialVolume = (int) (0.2 * MAX_VOLUME);
        musicString = textConvertor.convert(raw_text, initialVolume, initialBpm, initialInstrument);
    }
}