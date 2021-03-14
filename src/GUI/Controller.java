package GUI;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import Music.Music;


public class Controller {
    String errorMessage = null;

    @FXML
    private TextField fileInput;
    @FXML
    private TextField bpmInput;
    @FXML
    private TextArea textInput;
    @FXML
    private ChoiceBox choiceBox;

    @FXML
    public void initialize() {
        String instruments[] = {"Violino", "Piano", "Guitarra", "Choro"};
        choiceBox.setItems(FXCollections.observableArrayList(instruments));
    }

    @FXML
    public void OnPlayButtonClicked() {
        Music.play_music();
        //System.out.println("som na caixa, DJ");
    }

    @FXML
    public void OnDownloadButtonClicked() {
        System.out.println("aguarde, fazendo download...");
    }

    @FXML
    public void OnGenerateMusicButtonClicked() {
        String rawText = getTextInput();
        String bpmString = getBPMInput();
        String initialInstrument = onSelectInstrument();
        int initialBPM = 40;

        if(isValid(rawText, initialInstrument, bpmString, initialBPM)) {
            System.out.println("Tudo certo, hora de mandar aquele");

            String fileName = getFileInput();
            if(fileName.equals("")) {
                fileName = "music_generated";
            }
        } else {
            createAlert("Erro!", errorMessage);
        }
    }

    @FXML
    public String getFileInput() {
        return fileInput.getText();
    }

    @FXML
    public String getBPMInput() {
        return bpmInput.getText();
    }

    @FXML
    public String getTextInput() {
        return textInput.getText();
    }

    @FXML
    public String onSelectInstrument() {
        return (String) choiceBox.getSelectionModel().getSelectedItem();
    }

    public void createAlert(String title, String message) {
        Alert empty_string = new Alert(Alert.AlertType.ERROR);
        empty_string.setTitle(title);
        empty_string.setHeaderText(message);
        empty_string.showAndWait();
    }

    public boolean isValid(String text, String instrument, String bpm_string, int bpm) {
        if(text.trim().equals("")) {
            errorMessage = "Por acaso você é burro? Como eu vou musicar uma string vazia?";
            return false;
        } else if(text.length() > 240){
            errorMessage = "O texto não pode ter mais que 240 caracteres, meu consagradinho";
            return false;
        } else if(instrument == null) {
            errorMessage = "Velho, preciso de um instrumento pra tocar, né??";
            return false;
        }

        try {
            bpm = Integer.parseInt(bpm_string);

            if(bpm < 40 || bpm > 220) {
                errorMessage = "O BPM deve ser um valor entre 40 e 220";
                return false;
            }
        } catch (NumberFormatException exception) {
            errorMessage = "Mano, na moral, BPM é número, né caralho?";
            return false;
        }

        return true;
    }
}
