package acsse.csc02b2.Leecher;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LeecherPane extends GridPane 
{
	TextField txtHostAddress;
	Label lblHostAddress;
	TextField txtPortNumber;
	Label lblPortNumber;
	Button btnConnect;
	
	TextArea txtListOfFiles;
	Label lblListOfFiles;
	Button btnGetList;
	
	
	TextField txtDesiredUserFile;
	Label lblDesiredUserFile;
	Button btnRetrieve;
	
	Leecher leecher ;
	
	public LeecherPane() 
	{
		// TODO Auto-generated constructor stub	
		setUpGui();	
		displayListOfFiles();
	}

	private void setUpGui() 
	{
		// TODO Auto-generated method stub
		this.txtHostAddress=new TextField();
		this.lblHostAddress=new Label("Enter your host address : ");
		this.txtPortNumber=new TextField();
		this.lblPortNumber=new Label("Enter seeder port number : ");
		this.btnConnect=new Button("Connect to seeder.");
		
		
		GridPane.setConstraints(lblHostAddress,0,0);
		GridPane.setConstraints(txtHostAddress,1,0);
		
		GridPane.setConstraints(lblPortNumber,0, 1);
		GridPane.setConstraints(txtPortNumber,1, 1);	
		
		GridPane.setConstraints(btnConnect,0,3);
		
		this.getChildren().addAll(this.txtHostAddress,this.txtPortNumber,this.btnConnect,this.lblHostAddress,this.lblPortNumber);

		
		
		this.txtListOfFiles = new TextArea();
		this.txtListOfFiles.autosize();
		this.lblListOfFiles=new Label("List Of Available Files ->");
		GridPane.setConstraints(lblListOfFiles, 0,5);
		GridPane.setConstraints(txtListOfFiles, 1,5);
		this.getChildren().addAll(txtListOfFiles,lblListOfFiles);
		this.hgapProperty().add(10);
		this.txtDesiredUserFile = new TextField();
		this.lblDesiredUserFile = new Label("Enter File Number");
		this.btnRetrieve=new Button("Retrieve Desired File");

		GridPane.setConstraints(lblDesiredUserFile,0 ,11);
		GridPane.setConstraints(txtDesiredUserFile,1 ,11);		
		GridPane.setConstraints(btnRetrieve,0,9);
		
		this.getChildren().addAll(txtDesiredUserFile,lblDesiredUserFile,btnRetrieve);
		
		
		this.btnConnect.setOnAction(e->
		{
			this.leecher = new Leecher((Integer.parseInt(txtPortNumber.getText())));
			this.leecher.getCommand("LIST");
			
			this.leecher.send();
			this.leecher.receive();	
		});
		
		this.btnRetrieve.setOnAction(e->{
			
			this.leecher.getCommand("FILE");
			this.leecher.setUserChoice(Integer.parseInt(txtDesiredUserFile.getText()));
			
			this.leecher.send();
			this.leecher.receive();
			
			
		});
	}
	
	private void displayListOfFiles() 
	{
		this.txtListOfFiles.clear();
		// TODO Auto-generated method stub
		File listDirectoryFiles = new File("data/Leecher");
		String[] files = listDirectoryFiles.list();
		int n = 0;
		for(String file : files)
		{
			n++;
			this.txtListOfFiles.appendText(n+". "+file+"\n");
		}
		
	}
}
