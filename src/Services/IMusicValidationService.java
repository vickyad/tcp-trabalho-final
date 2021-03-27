package Services;

public interface IMusicValidationService {
    boolean validateString(String text);
    boolean validateInstrument(String instrument);
    int parseBPM(String bpm_string);
}
