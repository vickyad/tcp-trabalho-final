package Services;

public interface IMusicValidationService {
    boolean validateString(String text, String instrument);
    int parseBPM(String bpm_string);
}
