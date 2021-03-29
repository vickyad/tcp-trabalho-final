package Music;

import Services.TextConvertorService;

public class Music implements IMusic {
    public String musicString;

    public void createMusicFromText(String rawText, int initialBpm, int initialInstrument) {
        TextConvertorService textConvertor = new TextConvertorService();
        musicString = textConvertor.convert(rawText, initialBpm, initialInstrument);
    }
}