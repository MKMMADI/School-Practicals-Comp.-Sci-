package acsse.csc02b2.client;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class clientPane extends GridPane
{
	Button btnConnect;
	
	Button btnGetTxtFile;
	
	Button btnLoggoFF;
	
	Stage stage;
	client Client;
	
	public clientPane() 
	{
		this.btnConnect=new Button("Connect");
		
		this.btnGetTxtFile=new Button("Get Text File");
		
		this.btnLoggoFF = new Button("Logg Off");
		
		this.setAlignment(Pos.CENTER);
		
		GridPane.setConstraints(btnConnect, 0, 0);
		GridPane.setConstraints(btnGetTxtFile, 0, 1);
		GridPane.setConstraints(btnLoggoFF, 0, 2);
		
		btnConnect.setOnAction(e->{
			new Thread(()->{
				this.Client = new client();
				Client.send("HELLO");
				Client.recieve();	
			}).start();
			
		});
		
		btnGetTxtFile.setOnAction(e->{
			
			new Thread(()->{
				
				Client.send("LOGOFF");
				Client.recieve();
			}).start();
			
		});
		
		btnLoggoFF.setOnAction(e->{
			
			new Thread(()->{	
				Client.send("LOGOFF");
				Client.recieve();
			}).start();
			
		});
		
		this.getChildren().addAll(btnConnect,btnGetTxtFile,btnLoggoFF);

		
	}
	
	
	
	
}
