import acsse.csc2a.fmb.gui.FireworkDisplayPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception 
	{
		// TODO Auto-generated method stub
		FireworkDisplayPane FDP = new FireworkDisplayPane(arg0);
		
		arg0.setScene(new Scene(FDP,800,600));
		arg0.show();
	}

}
