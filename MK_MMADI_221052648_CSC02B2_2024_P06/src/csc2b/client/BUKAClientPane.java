package csc2b.client;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class BUKAClientPane extends GridPane //You may change the JavaFX pane layout
{
	private Socket soc;
	private Button btnLogin;
	private Label lblUsername;
	private Label lblPassword;
	private Label serverResponse;
	private TextField txtUserName;
	private TextField txtPassword;
	
	
	private Button btnList;
	private TextArea txtList;
	
	private Label lblUserChoice;
	private TextField txtUserChoice;
	
	private Button btnDownload;
	private Button btnLogOff;
	private BUKAClient client;
	

    public BUKAClientPane()
    {
    	setUpGUI();
    	this.client = new BUKAClient(2018);		 	
    }
    
    
    
    
    public void setUpGUI()
    {
    	this.setAlignment(Pos.CENTER);
    	this.btnLogin=new Button("Login");
    	
    	this.btnLogin.setOnAction(e->
    	{
    		new Thread(()->{
    			
    			String userName = txtUserName.getText();
        		String userPass = txtPassword.getText();
        		
        		System.out.println(userName+" "+userPass);
        		this.serverResponse.setText(this.client.sendComand("AUTH "+userName+" "+userPass));
        		this.client.recieve();
    			
    		}).start();
    		
    	});
    	
    	
		this.lblUsername=new Label("Enter You User Name : ");
		this.lblPassword=new Label("Enter your password : ");
		this.txtUserName=new TextField();
		this.txtPassword=new TextField();
		this.serverResponse=new Label();

		
		
		
		this.btnList=new Button("Get List");
		
		this.btnList.setOnAction(e->{
			
			new Thread(()->{
				String command =this.client.sendComand("LIST");
				ArrayList<String> files =this.client.recieveList();
				
				for(String file : files)
				{
					System.out.println(file);
					this.txtList.appendText(file+"\n");
				}		
			}).start();
			
		
		});
		
		this.txtList=new TextArea();
		
		this.lblUserChoice=new Label("Enter you file choice.");
		this.txtUserChoice=new TextField();	
		
		this.btnDownload=new Button("Download File.");
		
		this.btnDownload.setOnAction(e->{
			
			String serverResponse = this.client.sendComand("PDFRET "+this.txtUserChoice.getText());	
			System.out.println(txtUserChoice.getText());
			File FileRecived = this.client.recieveFile(this.txtUserChoice.getText());	
			
		});
		

		this.btnLogOff=new Button("Log-off");
		
		this.btnLogOff.setOnAction(e->{
			
			new Thread(()->{
				this.client.sendComand("LOGOFF");
				this.client.recieve();
				
			}).start();
			
			
			
		});
		
		this.getChildren().addAll(btnDownload,btnList,btnLogin,btnLogOff,txtList,txtPassword,txtUserChoice,txtUserName,lblPassword,lblUserChoice,lblUsername,serverResponse);
		
		setVgap(10);
		setHgap(2);
		
		GridPane.setConstraints(lblUsername,0,1);
		GridPane.setConstraints(txtUserName,1,1);
		GridPane.setConstraints(lblPassword,0,2);
		GridPane.setConstraints(txtPassword,1,2);
		GridPane.setConstraints(serverResponse,0,3);

		
		GridPane.setConstraints(btnLogin,0,4);
		
		GridPane.setConstraints(btnList,0,6);
		GridPane.setConstraints(txtList,1,6);
		this.txtList.setPrefHeight(100);
		this.txtList.setPrefWidth(5);
		this.txtList.scrollTopProperty();

		
		GridPane.setConstraints(lblUserChoice,0,9);
		GridPane.setConstraints(txtUserChoice,1,9);
		
		GridPane.setConstraints(btnDownload,0,11);
		GridPane.setConstraints(btnLogOff,0,12);

		

    }
}
