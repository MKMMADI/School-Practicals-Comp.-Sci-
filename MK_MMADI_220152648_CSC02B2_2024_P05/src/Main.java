

import java.util.Scanner;

import acsse.csc02b2.Leecher.LeecherPane;
import acsse.csc02b2.Seeder.SeederPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	static int userChoice;

	public static void main(String[] args) 
	{
		@SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);
		System.out.println("Would you like to run as a :\n 1.Seeder /n 2.Leecher");
		userChoice = userInput.nextInt();
		
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		switch(userChoice)
		{
			case 1:
			{
				SeederPane Seeder= new SeederPane(stage);
				Scene scene = new Scene(Seeder,600,400);
				stage.setScene(scene);
				stage.setTitle("Seeder Pane");
				stage.show();
				break;
			}
			
			case 2:
			{
				LeecherPane Leecher= new LeecherPane();
				Scene scene = new Scene(Leecher,600,400);
				stage.setScene(scene);
				stage.setTitle("Seeder Pane");
				stage.show();
				break;
			}				
		}
		// TODO Auto-generated method stub
	
	}

}
