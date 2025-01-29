package csc2b.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ClientPane extends GridPane
{

	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private PrintWriter  out;
	private BufferedReader br;
	private DataInputStream dis;
	private DataOutputStream dos;
	private String[] listData;
	
	private Button btnConnect;
	private Button btnImageList;
	
	private TextField txtIDToRetrieve;
	private Label lblID;
	
	private Button btnGetting;
	private Button btnPosting;
	private TextArea listArea;
	private TextArea responseArea;
	private Label lblList;
	private Label lblResponse;
	private ImageView imgView;
	private Button btnDisplay;
	private String fileToGetName="";
	
	
	public ClientPane(Stage stage)
	{
		GUISETUP();
		
		btnConnect.setOnAction(e->{
		
				
		
			try (Socket socket = new Socket("localhost",5432))
			{
			   os=socket.getOutputStream();
			   is=socket.getInputStream();
			   br= new BufferedReader(new InputStreamReader(is));
			   dis=new DataInputStream(is);
			   out= new PrintWriter(os,true);
			   dos=new DataOutputStream(os);
			    
			}catch(UnknownHostException UHE)
			{
				UHE.printStackTrace();
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
				
			
		}); 
		
		 
		btnImageList.setOnAction(e->{
			
			try
			{
				
				sendCommand(out,"LIST");
				String Response="";
				
				
				Response = ReadResponse(br);
				System.out.println(Response);
				listData = Response.split("#");
				
				for(int i=0;i<listData.length;i++)
				{
					listArea.appendText(listData[i]+"\n");
				}
				
			}catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			
			
		
		});
		
		
		btnGetting.setOnAction(e->{
		
				
			int idToRetrieve = Integer.parseInt(txtIDToRetrieve.getText());
			sendCommand(out,"DOWN"+idToRetrieve);
			String response="";
				
				try
				{
					response = ReadResponse(br);
					int fileSize = Integer.parseInt(response);
					responseArea.appendText("File Recieved Size:"+ response);
					
					for(String s :listData)
					{
						StringTokenizer Token = new StringTokenizer(s);
						String id = Token.nextToken();
						String name = Token.nextToken();
						
						if(id.equals(txtIDToRetrieve.getText()))
						{
							fileToGetName=name;
						}
					}
						
						File DownloadedFile = new File("data/client/"+fileToGetName);
						FileOutputStream fos = null;
					
						
						try
						{
							fos = new FileOutputStream(DownloadedFile);
							byte[] Buffer = new byte[2048];
							int n = 0;
							int Totalbytes =0;
							
							while(Totalbytes!=fileSize)
							{
								n=dis.read(Buffer,0,n);
								fos.write(Buffer,0,n);
								fos.flush();
								Totalbytes+=n;
							}
							
							
							System.out.println("Files Succesfully Saved");
						}catch (FileNotFoundException FOF) {
							FOF.printStackTrace();
						}finally 
						{
							if(fos!=null)
							{
								try
								{
									fos.close();
								}catch(IOException IO)
								{
									IO.printStackTrace();
								}
							}
						}
		
					
				}catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
		});
		
		
		btnPosting.setOnAction(e->{
			
			FileChooser fChooser = new FileChooser();
			File FileToBeSelected = fChooser.showOpenDialog(stage);
			String fileName = FileToBeSelected.getName();
			
			int FileID = listData.length+1;
			sendCommand(out,"UP"+FileID+" "+fileName+" "+FileToBeSelected.length());
			
			System.out.println("Upload Commag has been sent from client");
			
			FileInputStream fis= null;
			
			try
			{
				fis=new FileInputStream(FileToBeSelected);
				byte[] buffer = new byte[2048];
				int n = 0;
				
				while((n=fis.read(buffer))>0)
				{
					dos.write(buffer,0,n);
					dos.flush();
				}
				
				fis.close();
				
				
				System.out.println("File Sent To Server For Upload");
				String response=br.readLine(); 
				responseArea.appendText("File Upload Status : "+ response);
			}catch (FileNotFoundException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}catch (IOException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
			
		});
		
		
		btnDisplay.setOnAction(e->{
			Image image = new Image("File:data/client/"+fileToGetName);
			ImageView imgView = new ImageView();
			imgView.setImage(image);
			add(imgView,0,7,4,1);
			
		});
	}
	
	
	
	
	
	private String ReadResponse(BufferedReader br2)
	{		
		String response = "";
		
		try
		{
			response = br.readLine();
			
		}catch(IOException IO)
		{
			IO.printStackTrace();
		}
		
		return response;

	}
	





	private void sendCommand(PrintWriter out2, String string) 
	{
		out2.println(string);
	}





	private void GUISETUP() 
	{
		// TODO Auto-generated method stub
		setHgap(10);
		setVgap(10);
		setAlignment(Pos.CENTER); 
		
		btnConnect = new Button("Connect");
		btnImageList=new Button("Image List");
		txtIDToRetrieve=new TextField();
		btnGetting = new Button("Get Image");
		btnPosting = new Button("Upload Image");
		
		listArea=new TextArea();
		responseArea=new TextArea();
		responseArea.setPrefHeight(100);
		lblList=new Label("List Of Content");
		lblResponse = new Label("Server And Client Responses");
		btnDisplay=new Button("View Downloaded Image");
		
		
		add(btnConnect,0,0);
		add(btnImageList,1,0);
		add(lblID,0,1);
		add(txtIDToRetrieve,2,1);
		add(btnGetting,2,1);
		add(btnPosting,3,1);
		add(lblList,0,2);
		add(listArea,0,3,4,1);
		add(lblResponse,0,4);
		add(btnDisplay,0,6,4,1);
		
		
	}
}
