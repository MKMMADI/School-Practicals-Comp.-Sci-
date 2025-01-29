package acsse.csc2a.fmb.gui;



import java.io.File;
import java.util.ArrayList;

import acsse.csc2a.fmb.file.OrchestratorFileHandler;
import acsse.csc2a.fmb.model.DisplayBundle;
import acsse.csc2a.fmb.model.Firework;
import acsse.csc2a.fmb.model.FountainFirework;
import acsse.csc2a.fmb.model.RocketFirework;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FireworkDisplayPane extends StackPane
{
	private FireworkDisplayCanvas FDC = new FireworkDisplayCanvas();




	public FireworkDisplayPane(Stage primaryStage) 
	{

		Menu menu = new Menu();
		MenuBar menuBar = new MenuBar();
		MenuItem menuItem = new MenuItem("Open File");
		FileChooser fileChooser = new FileChooser();		

		fileChooser.setTitle("File");
		File file = fileChooser.showOpenDialog(primaryStage);
		String strfile = file.getAbsolutePath();
		
		 ArrayList<DisplayBundle> DisplayBundle = new ArrayList<>();
		 DisplayBundle.add(OrchestratorFileHandler.readLayoutFile(strfile,file.getAbsolutePath()));

		

		menu.getItems().add(menuItem);
		menuBar.getMenus().add(menu);

		GridPane DisplayGrid = new GridPane();
		GridPane TechGrid = new GridPane();
		GridPane FireworkGrid = new GridPane();
		
		TextField text = new TextField(); 
		
		DisplayGrid.add(new Label("Display_ID"),0,1);
		DisplayGrid.add(new TextField(), 10, 1);
		
		DisplayGrid.add(new Label("Display_Name"),0,6);
		DisplayGrid.add(new TextField(), 10, 6);

		DisplayGrid.add(new Label("Display Theme"),0,9);
		DisplayGrid.add(new TextField(), 10, 9);

		DisplayGrid.add(new Label("Display_Total Duration"),0,12);
		DisplayGrid.add(new TextField(), 10, 12);
		
		TechGrid.add(new Label("PyroTechnician Full Name"), 0, 1);
		TechGrid.add(new TextField(), 10, 1);

		TechGrid.add(new Label("PyroTechnician Phone Number"), 0, 6);
		TechGrid.add(new TextField(), 10, 6);
		
		for(int i = 0 ; i<DisplayBundle.size();i++)
		{
			
			for(Firew f : DisplayBundle.get(i).)
			{
				if(f instanceof FountainFirework Fountain)
				{
					 FireworkGrid.add(new Label("Firework ID : "),0,i);
					 FireworkGrid.add(new TextField(), 10, i);

					 
					 FireworkGrid.add(new Label("Firework Name : "),0,i+5);
					 FireworkGrid.add(new TextField(), 10, i+5);


					 FireworkGrid.add(new Label("Fuse Length : "),0,i+8);
					 FireworkGrid.add(new TextField(), 10, i+8);


					 FireworkGrid.add(new Label("Colour : "),0,i+11);
					 FireworkGrid.add(new TextField(), 10, i+11);


					 FireworkGrid.add(new Label("Fountain Duration : "),0,i+14);
					 FireworkGrid.add(new TextField(), 10, i+14);

					 
					 FireworkGrid.add(new Label("Transition Colours : "),0,i+17);
					 FireworkGrid.add(new TextField(), 10, i+17);


				}
				
				if(f instanceof RocketFirework Rocket)
				{
					 FireworkGrid.add(new Label("Firework ID"),0,i);
					 FireworkGrid.add(new TextField(), 10, i);

					 
					 FireworkGrid.add(new Label("Firework Name"),0,i+5);
					 FireworkGrid.add(new TextField(), 10, i+5);


					 FireworkGrid.add(new Label("Colour"),0,i+8);
					 FireworkGrid.add(new TextField(), 10, i+8);


					 FireworkGrid.add(new Label("Black Powder"),0,i+11);
					 FireworkGrid.add(new TextField(), 10, i+11);


					 FireworkGrid.add(new Label("Star Count"),0,i+14);
					 FireworkGrid.add(new TextField(), 10, i+14);

					 
					 FireworkGrid.add(new Label("Star Raduis"),0,i+17);
					 FireworkGrid.add(new TextField(), 10, i+17);


				}
			}
			

		}




		



		
		TitledPane FireWork_Display = new TitledPane("Firwork Display",DisplayGrid);
		TitledPane Lead_PyroTechnician = new TitledPane("Lead PyroTechnician Display",TechGrid);
		TitledPane FireWorks = new TitledPane("Firworks Display",FireworkGrid);


		GraphicsContext GC = FDC.getGraphicsContext2D();

		
		HBox MenuPane = new HBox();
		MenuPane.getChildren().add(menuBar);

		Accordion accordion = new Accordion();
		accordion.getPanes().addAll(FireWork_Display,Lead_PyroTechnician,FireWorks);
		
		VBox LeftPane = new VBox();
		LeftPane = new VBox(accordion);
		this.FDC.redrawConvas();
		
		StackPane.setAlignment(menuBar,Pos.TOP_CENTER);
		StackPane.setAlignment(FDC,Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(accordion,Pos.BOTTOM_LEFT);

		this.getChildren().add(menuBar);
		this.getChildren().add(FDC);
		this.getChildren().add(accordion);
	

		ScrollBar scrollbar = new ScrollBar();
		
		
	


	}






}
