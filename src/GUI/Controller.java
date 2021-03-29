package GUI;

import Constants.ConstraintsConstants;
import Constants.MessagesToUserConstants;
import Constants.TextConstants;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

public class Controller {
    @FXML
    private TextField fileInput;
    @FXML
    private TextField bpmInput;
    @FXML
    private TextArea textInput;
    @FXML
    private ChoiceBox<String> choiceBox;

    private final Music music = new Music();
    private final MusicPlayer player = new MusicPlayer();

    @FXML
    private void initialize() {
        String[] instruments = {"Harpsichord", "TubularBells", "Agogo", "PanFlute", "ChurchOrgan"};
        choiceBox.setItems(FXCollections.observableArrayList(instruments));
    }

    @FXML
    private void OnGenerateMusicButtonClicked() {
        MusicValidationService musicValidationService = new MusicValidationService();
        UserInputs userInputs = new UserInputs(getTextInput(), onSelectInstrument(), musicValidationService.parseBPM(getBPMInput()));

        if(validateUserInputs(musicValidationService, userInputs)) {
            music.createMusicFromText(userInputs.getTextInput(), userInputs.getInitialBPM(), InstrumentEnum.valueOf(userInputs.getInitialInstrument()).getValue());
            createSuccessAlert(MessagesToUserConstants.SUCCESSFUL_MUSIC_CREATION);
        } else {
            createErrorAlert(MusicValidationService.errorMessage);
        }
    }

    private boolean validateUserInputs(MusicValidationService musicValidationService, UserInputs userInputs) {
        return musicValidationService.validateString(userInputs.getTextInput())
                && musicValidationService.validateInstrument(userInputs.getInitialInstrument())
                && userInputs.getInitialBPM() != ConstraintsConstants.INVALID_BPM;
    }

    @FXML
    private void OnPlayButtonClicked() {
        if(music.musicString != null){
            player.playMusic(music.musicString);
        } else {
            createErrorAlert(MessagesToUserConstants.MUSIC_NOT_GENERATED);
        }
    }

    @FXML
    private void OnDownloadButtonClicked() {
        String fileName = getFileName();

        if(fileName.equals(TextConstants.EMPTY_STRING)) {
            fileName = ConstraintsConstants.DEFAULT_FILE_NAME;
        }

        if(music.musicString != null) {
            if(player.downloadMusic(music.musicString, fileName)) {
                createSuccessAlert(MessagesToUserConstants.SUCCESSFUL_DOWNLOAD);
            } else {
                createErrorAlert(MessagesToUserConstants.UNSUCCESSFUL_DOWNLOAD);
            }
        } else {
            createErrorAlert(MessagesToUserConstants.MUSIC_NOT_GENERATED);
        }

    }

    private String getFileName() {
        String fileName = getFileInput();

        if(fileName.equals(TextConstants.EMPTY_STRING)) {
            fileName = ConstraintsConstants.DEFAULT_FILE_NAME;
        }

        return fileName;
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
        return choiceBox.getSelectionModel().getSelectedItem();
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

    @FXML
    private void chooseInputFile() {
        final Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(MessagesToUserConstants.OPEN_TEXT_FILE);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
        }
    }

    private void openFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String stringRead;
            while ((stringRead = bufferedReader.readLine()) != null)
                stringBuilder.append(stringRead).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String finalString = stringBuilder.toString();
        textInput.setText(finalString);
    }
}
