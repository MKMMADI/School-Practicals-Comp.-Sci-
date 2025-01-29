package csc2b.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		BUKAClientPane cpane=new BUKAClientPane();
		Scene scene = new Scene(cpane,600,400);
		
		primaryStage.setTitle("BUKAClientPane");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}

}
