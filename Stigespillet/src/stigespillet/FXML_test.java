package stigespillet;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXML_test extends Application{

	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Group root = new Group();
		Scene scene = new Scene(root, 600, 400);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Stigespillet");
		primaryStage.show();
	}

}
