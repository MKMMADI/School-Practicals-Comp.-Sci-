 package csc2b.client;

import java.net.InetAddress;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {

	public static void main(String[] args) 
	{
		
		
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage pstage) throws Exception {
		// TODO Auto-generated method stub
		ClientPane Root = new ClientPane(pstage);
		Scene scene = new Scene(Root,800,600);
		pstage.setScene(scene);
		pstage.setTitle("Client");
		pstage.show();
		
		
	}

}
