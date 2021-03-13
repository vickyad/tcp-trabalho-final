package GUI;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import Music.MyMusicApp;


public class Controller {
    String instruments[] = {"Violino", "Piano", "Guitarra", "Choro"};

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
        MyMusicApp.play_music();
        //System.out.println("som na caixa, DJ");
    }

    @FXML
    public void OnDownloadButtonClicked() {
        System.out.println("aguarde, fazendo download...");
    }

    @FXML
    public void OnGenerateMusicButtonClicked() {
        String raw_text = getTextInput();
        if(raw_text.trim() == "") {
            createAlert("Erro!", "Por acaso você é burro? Como eu vou musicar uma string vazia?");
        } else {
            System.out.println(raw_text);
        }

        String file_name = getFileInput();
        if(file_name == "") {
            file_name = "music_generated";
        }
        System.out.println(file_name);

        String bpm_string = getBPMInput();
        int initial_bpm;
        try {
            initial_bpm = Integer.parseInt(bpm_string);

            if(initial_bpm < 40 || initial_bpm > 220) {
                createAlert("Erro!", "O BPM deve ser um valor entre 40 e 220");
            } else {
                System.out.println(initial_bpm);
            }
        } catch (NumberFormatException exception) {
            createAlert("Erro!", "Mano, na moral, BPM é número, né caralho?");
        }

        String initial_instrument = onSelectInstrument();
        if(initial_instrument == null) {
            createAlert("Erro!", "Velho, preciso de um instrumento pra tocar, né??");
        } else {
            System.out.println(initial_instrument);
        }

        System.out.println("música sendo gerada...");
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
