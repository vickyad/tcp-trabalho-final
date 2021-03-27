import Constants.ConstraintsConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(ConstraintsConstants.USER_INTERFACE_PATH));
        primaryStage.setTitle(ConstraintsConstants.USER_INTERFACE_WINDOW_NAME);
        primaryStage.setScene(new Scene(root, ConstraintsConstants.WINDOW_WIDTH, ConstraintsConstants.WINDOW_HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
