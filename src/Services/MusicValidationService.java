package Services;

import Constants.ConstraintsConstants;
import Constants.MessagesToUserConstants;
import Constants.TextConstants;

public class MusicValidationService implements IMusicValidationService {
    public static String errorMessage = null;

    public boolean validateString(String text) {
        if(text.trim().equals(TextConstants.EMPTY_STRING)) {
            errorMessage = MessagesToUserConstants.EMPTY_STRING_MESSAGE;
            return false;
        } else if(text.length() > ConstraintsConstants.MAX_TEXT_SIZE){
            errorMessage = MessagesToUserConstants.TOO_LONG_STRING_MESSAGE;
            return false;
        }
        return true;
    }

    public boolean validateInstrument(String instrument) {
        if(instrument == null) {
            errorMessage = MessagesToUserConstants.NULL_INSTRUMENT;
            return false;
        }
        return true;
    }

    public int parseBPM(String bpmString){
        int bpm;
        try {
            bpm = Integer.parseInt(bpmString);

            if(bpm < ConstraintsConstants.MIN_BPM || bpm > ConstraintsConstants.MAX_BPM) {
                errorMessage = MessagesToUserConstants.INVALID_BPM_VALUE;
                bpm = ConstraintsConstants.INVALID_BPM;
            }
        } catch (NumberFormatException exception) {
            errorMessage = MessagesToUserConstants.INVALID_BPM_TYPE;
            bpm = ConstraintsConstants.INVALID_BPM;
        }
        return bpm;
    }
}
