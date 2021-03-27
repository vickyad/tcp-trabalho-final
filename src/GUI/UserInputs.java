package GUI;

public class UserInputs {
    private final String textInput;
    private final String initialInstrument;
    private final int initialBPM;

    public UserInputs(String textInput, String initialInstrument, int initialBPM) {
        this.textInput = textInput;
        this.initialInstrument = initialInstrument;
        this.initialBPM = initialBPM;
    }

    public String getTextInput() {
        return textInput;
    }

    public String getInitialInstrument() {
        return initialInstrument;
    }

    public int getInitialBPM() {
        return initialBPM;
    }
}
