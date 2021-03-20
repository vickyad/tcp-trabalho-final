package GUI;

import Services.MusicValidationService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import Music.Music;


public class Controller {
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
        System.out.println("som na caixa, DJ");
    }

    @FXML
    public void OnDownloadButtonClicked() {
        System.out.println("aguarde, fazendo download...");
    }

    @FXML
    public void OnGenerateMusicButtonClicked() {
        String textInput = getTextInput();
        String selectedInstrument = onSelectInstrument();
        int initialBPM = MusicValidationService.parseBPM(getBPMInput());

        if(MusicValidationService.validateString(textInput, selectedInstrument) && initialBPM != -1) {
            System.out.println("Tudo certo, hora de mandar aquele");

            Music music = new Music();

            String fileName = getFileInput();
            if(fileName.equals("")) {
                fileName = "music_generated";
            }
        } else {
            createAlert("Erro!", MusicValidationService.errorMessage);
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
}
