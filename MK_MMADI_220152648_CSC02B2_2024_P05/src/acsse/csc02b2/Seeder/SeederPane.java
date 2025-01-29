package acsse.csc02b2.Seeder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SeederPane extends GridPane 
{
	
	TextArea listOfFiles ;
	Button btnAddFiles;
	Stage primaryStage;
	private File txtAvailableFiles;
	
	
	
	public SeederPane(Stage primaryStage) 
	{
		// TODO Auto-generated constructor stub
		this.primaryStage = primaryStage;
		this.listOfFiles=new TextArea();
		this.btnAddFiles=new Button("Add Files");
		setUpGUI();
	}



	private void setUpGUI() 
	{
		// TODO Auto-generated method stub
		GridPane.setConstraints(btnAddFiles, 0,0);
		GridPane.setConstraints(btnAddFiles, 0,1);

		this.getChildren().addAll(listOfFiles,btnAddFiles);
		
		
		displayListOfFiles();
		
		this.btnAddFiles.setOnAction(e->{
			
			FileChooser fileChooser= new FileChooser();
			File ChoosenFile = fileChooser.showOpenDialog(primaryStage);
			
			if(ChoosenFile!=null)
			{
				String targetDirectory = "data/Seeder";
				
				Path targetPath = Paths.get(targetDirectory,ChoosenFile.getName());
				
				try {
					Files.move(ChoosenFile.toPath(),targetPath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			displayListOfFiles();
			
		});
		
	}



	private void displayListOfFiles() 
	{
		this.listOfFiles.clear();
		// TODO Auto-generated method stub
		File listDirectoryFiles = new File("data/Seeder");
		String[] files = listDirectoryFiles.list();
		int n = 0;
		for(String file : files)
		{
			n++;
			this.listOfFiles.appendText(n+". "+file+"\n");
		}
		
		populateTextFile();
	}
	

	public void populateTextFile() 
	{
		// TODO Auto-generated method stub
		this.txtAvailableFiles = new File("data/Seeder/ListOfFiles.txt");

		try 
		{
			String[] AvailableFiles = getFileNames();
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(txtAvailableFiles)));
			
			if(AvailableFiles!=null)
			{
				for(String file : AvailableFiles)
				{
					pw.println(file);
				}
				
				pw.flush();
			}else
			{
				System.out.println("Null Array");
			}
		

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private String[] getFileNames() {
		
			
			File listDirectoryFiles = new File("data/Seeder");
			String[] files = listDirectoryFiles.list();
			int n=0;
			for(String f : files)
			{
				files[n]=(n+1)+". "+f;	
				n++;
			}
			
			return files;
		}
	

	
	
}
