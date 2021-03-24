package GUI;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Music.MusicPlayer;
import Services.TextConvertorService;
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

    private final MusicPlayer musicPlayer = new MusicPlayer();

    @FXML
    private void initialize() {
        String[] instruments = {"Violino", "Piano", "Guitarra", "Baixo", "Trompete"};
        choiceBox.setItems(FXCollections.observableArrayList(instruments));
    }

    @FXML
    private void OnPlayButtonClicked() {
        musicPlayer.playMusic();
    }

    @FXML
    private void OnDownloadButtonClicked() throws IOException {
        String fileName = getFileInput();

        if(fileName.equals("")) {
            fileName = "music_generated";
        }
        musicPlayer.saveMusic(fileName);
        createSuccessAlert("Download feito com sucesso");
    }

    @FXML
    private void OnGenerateMusicButtonClicked() {
        String textInput = getTextInput();
        String selectedInstrument = onSelectInstrument();
        int initialBPM = MusicValidationService.parseBPM(getBPMInput());

        if(MusicValidationService.validateString(textInput, selectedInstrument) && initialBPM != -1) {
            TextConvertorService convertor = new TextConvertorService();
            int INITIAL_VOLUME = 25;

            convertor.convert(textInput, INITIAL_VOLUME);
            musicPlayer.setMusic();

            createSuccessAlert("Música criada com sucesso");
        } else {
            createErrorAlert(MusicValidationService.errorMessage);
        }
    }

    @FXML
    private String getFileInput() {
        return fileInput.getText();
    }

    @FXML
    private String getBPMInput() {
        return bpmInput.getText();
    }

    @FXML
    private String getTextInput() {
        return textInput.getText();
    }

    @FXML
    private String onSelectInstrument() {
        return (String) choiceBox.getSelectionModel().getSelectedItem();
    }

    private void createErrorAlert(String message) {
        Alert empty_string = new Alert(Alert.AlertType.ERROR);
        empty_string.setTitle("Erro!");
        empty_string.setHeaderText(message);
        empty_string.showAndWait();
    }

    private void createSuccessAlert(String message) {
        Alert empty_string = new Alert(Alert.AlertType.CONFIRMATION);
        empty_string.setTitle("Sucesso");
        empty_string.setHeaderText(message);
        empty_string.showAndWait();
    }
}
