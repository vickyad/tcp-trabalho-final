import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class Main extends Application {
    @FXML
    private ChoiceBox choiceBox;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI/userInterface.fxml"));
        primaryStage.setTitle("Jukebox");
        primaryStage.setScene(new Scene(root, 779, 633));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
