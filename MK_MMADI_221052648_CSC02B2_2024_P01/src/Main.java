
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
	GUISetUP GuiSetup = null;
	
	

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		// TODO Auto-generated method stub
		GuiSetup= new GUISetUP();
		primaryStage.setScene(GuiSetup.getScene());
		primaryStage.show();	
	}
	
	public static void main(String args[])
	{	
		launch(args);
	}
}
