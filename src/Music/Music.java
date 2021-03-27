package Music;

import Constants.ConstraintsConstants;
import Services.TextConvertorService;

public class Music {
    public final int initialVolume;
    public String musicString;

    public Music(String raw_text, int initialBpm, int initialInstrument) {
        TextConvertorService textConvertor = new TextConvertorService();
        initialVolume = (int) (0.2 * ConstraintsConstants.MAX_VOLUME);
        musicString = textConvertor.convert(raw_text, initialVolume, initialBpm, initialInstrument);
    }
}