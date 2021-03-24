package GUI;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Music.MusicPlayer;
import Music.TextConversor;
import Services.MusicValidationService;

public class Controller {
    @FXML
    private TextField fileInput;
    @FXML
    private TextField bpmInput;
    @FXML
    private TextArea textInput;
    @FXML
    private ChoiceBox choiceBox;

    private final MusicPlayer mp = new MusicPlayer();

    @FXML
    public void initialize() {
        String[] instruments = {"Violino", "Piano", "Guitarra", "Baixo", "Trompete"};
        choiceBox.setItems(FXCollections.observableArrayList(instruments));
    }

    @FXML
    public void OnPlayButtonClicked() {
        mp.playMusic();
    }

    @FXML
    public void OnDownloadButtonClicked() throws IOException {
        String fileName = getFileInput();

        if(fileName.equals("")) {
            fileName = "music_generated";
        }
        mp.saveMusic(fileName);
        createSuccessAlert("Download feito com sucesso");
    }

    @FXML
    public void OnGenerateMusicButtonClicked() {
        String textInput = getTextInput();
        String selectedInstrument = onSelectInstrument();
        int initialBPM = MusicValidationService.parseBPM(getBPMInput());

        if(MusicValidationService.validateString(textInput, selectedInstrument) && initialBPM != -1) {
            TextConversor convertor = new TextConversor();
            int INITIAL_VOLUME = 25;

            convertor.convert(textInput, INITIAL_VOLUME);
            mp.setMusic();

            createSuccessAlert("Música criada com sucesso");
        } else {
            createErrorAlert(MusicValidationService.errorMessage);
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

    public void createErrorAlert(String message) {
        Alert empty_string = new Alert(Alert.AlertType.ERROR);
        empty_string.setTitle("Erro!");
        empty_string.setHeaderText(message);
        empty_string.showAndWait();
    }

    public void createSuccessAlert(String message) {
        Alert empty_string = new Alert(Alert.AlertType.CONFIRMATION);
        empty_string.setTitle("Sucesso");
        empty_string.setHeaderText(message);
        empty_string.showAndWait();
    }
}
