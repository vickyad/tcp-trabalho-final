package Services;

import Constants.MessagesToUserConstants;

public class MusicValidationService implements IMusicValidationService {
    public static String errorMessage = null;

    public boolean validateString(String text, String instrument) {
        if(text.trim().equals("")) {
            errorMessage = MessagesToUserConstants.EMPTY_STRING_MESSAGE;
            return false;
        } else if(text.length() > 240){
            errorMessage = MessagesToUserConstants.TOO_LONG_STRING_MESSAGE;
            return false;
        } else if(instrument == null) {
            errorMessage = MessagesToUserConstants.NULL_INSTRUMENT;
            return false;
        }
        return true;
    }

    public int parseBPM(String bpm_string){
        int bpm;
        try {
            bpm = Integer.parseInt(bpm_string);

            if(bpm < 40 || bpm > 220) {
                errorMessage = MessagesToUserConstants.INVALID_BPM_VALUE;
                bpm = -1;
            }
        } catch (NumberFormatException exception) {
            errorMessage = MessagesToUserConstants.INVALID_BPM_TYPE;
            bpm = -1;
        }
        return bpm;
    }
}
