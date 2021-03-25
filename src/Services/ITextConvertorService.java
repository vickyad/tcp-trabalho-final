package Services;

public interface ITextConvertorService {
    String convert(String raw_text, int initialVolume, int initialBpm, int initialInstrument);
}
