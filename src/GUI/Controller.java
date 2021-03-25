package GUI;

import Constants.MessagesToUserConstants;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Music.Music;
import Music.MusicPlayer;
import Music.InstrumentEnum;
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

    private Music music;

    @FXML
    private void initialize() {
        String[] instruments = {"Violin", "Piano", "Guitar", "Bass", "Trumpet"};
        choiceBox.setItems(FXCollections.observableArrayList(instruments));
    }

    @FXML
    private void OnGenerateMusicButtonClicked() {
        String textInput = getTextInput();
        String selectedInstrument = onSelectInstrument();
        int initialBPM = MusicValidationService.parseBPM(getBPMInput());

        if(MusicValidationService.validateString(textInput, selectedInstrument) && initialBPM != -1) {
            music = new Music(textInput, initialBPM, InstrumentEnum.valueOf(selectedInstrument).getValue());

            if(!music.musicString.isBlank()) {
                createSuccessAlert(MessagesToUserConstants.SUCCESSFUL_MUSIC_CREATION);
            } else {
                createErrorAlert(MessagesToUserConstants.FAILURE_MUSIC_CREATION);
            }
        } else {
            createErrorAlert(MusicValidationService.errorMessage);
        }
    }

    @FXML
    private void OnPlayButtonClicked() {
        MusicPlayer.playMusic(music.musicString);
    }

    @FXML
    private void OnDownloadButtonClicked() {
        String fileName = getFileInput();

        if(fileName.equals("")) {
            fileName = "music_generated";
        }
        if(MusicPlayer.saveMusic(music.musicString, fileName)) {
            createSuccessAlert(MessagesToUserConstants.SUCCESSFUL_DOWNLOAD);
        } else {
            createErrorAlert(MessagesToUserConstants.UNSUCCESSFUL_DOWNLOAD);
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
        empty_string.setTitle(MessagesToUserConstants.ERROR_MESSAGE);
        empty_string.setHeaderText(message);
        empty_string.showAndWait();
    }

    private void createSuccessAlert(String message) {
        Alert empty_string = new Alert(Alert.AlertType.CONFIRMATION);
        empty_string.setTitle(MessagesToUserConstants.SUCCESS_MESSAGE);
        empty_string.setHeaderText(message);
        empty_string.showAndWait();
    }
}
