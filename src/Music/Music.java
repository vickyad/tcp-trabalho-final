package Music;

import Services.TextConvertorService;

public class Music {
    public String musicString;

    public void createMusicFromText(String raw_text, int initialBpm, int initialInstrument){
        TextConvertorService textConvertor = new TextConvertorService();
        musicString = textConvertor.convert(raw_text, initialBpm, initialInstrument);
    }
}