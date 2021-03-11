package GUI;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import java.awt.*;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import java.io.*;

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
        System.out.println("som na caixa, DJ");
    }

    @FXML
    public void OnDownloadButtonClicked() {
        System.out.println("aguarde, fazendo download...");
    }

    @FXML
    public void OnGenerateMusicButtonClicked() {
        System.out.println("música sendo gerada...");

        getTextInput();
        getFileInput();
        getBPMInput();
        onSelectInstrument();
    }

    @FXML
    public void getFileInput() {
        System.out.println(fileInput.getText());
    }

    @FXML
    public void getBPMInput() {
        System.out.println(bpmInput.getText());
    }

    @FXML
    public void getTextInput() {
        System.out.println(textInput.getText());
    }

    @FXML
    public void onSelectInstrument() {
        System.out.println(choiceBox.getSelectionModel().getSelectedItem());
    }


}
