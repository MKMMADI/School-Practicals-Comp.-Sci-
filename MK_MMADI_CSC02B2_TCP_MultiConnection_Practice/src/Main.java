import acsse.csc02b2.client.clientPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		clientPane cpane = new clientPane();
		Scene scene = new Scene(cpane,400,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
