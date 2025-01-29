package csc2b.server;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class BUKAHandler implements Runnable
{
	private PrintWriter out ;
	private BufferedReader in;
	private DataOutputStream dos;
	boolean loggedIn=false;
	
	
	private Socket incomingConnection;
	private boolean inCorrectCredentials;
    public BUKAHandler(Socket newConnectionToClient)
    {	
    	try 
    	{
        	this.incomingConnection=newConnectionToClient;
			this.out=new PrintWriter(this.incomingConnection.getOutputStream(),true);
	    	this.in=new BufferedReader(new InputStreamReader(this.incomingConnection.getInputStream()));
	    	this.dos=new DataOutputStream(incomingConnection.getOutputStream());

		} catch (IOException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void run()
    {
    	boolean hasNotLoggedOFF=true;
    	while(hasNotLoggedOFF)
    	{
	    	String command = null;
			try {
				command = this.in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
			String splitCommand="";
			String[] splitCommands=null;
		//Process commands from client	
			if(loggedIn==(false))
			{
				if(command.contains("AUTH"))
		    	{
		    		splitCommand=command.replace("AUTH ","");
		    		System.out.println(splitCommand);
		    		splitCommands = splitCommand.split(" ");
		    		if(matchUser(splitCommands[0],splitCommands[1]))
		    		{
		    			this.out.println("200 <Logged-In Successfully>");
		    			System.out.println("User "+splitCommands[0]+" Logged In");
		    			this.loggedIn=true;
		    		}
		    	}
			}else if(loggedIn==true)
			{
				if(command.equals("LIST"))
				{
					ArrayList<String> ListOfFiles = getFileList();
					for(String file : ListOfFiles)
					{
						System.out.println(file);
						this.out.println(file);
						this.out.flush();
					}
							
					System.out.println("Successfully sent file to client");		
					this.out.println("200 <List Of Files Sent Successfully");			
				}else 
					if(command.startsWith("PDFRET"))//PDFRET
					{
			    		System.out.println(command);
			    		String fileID = command.replace("PDFRET ","");
			    		System.out.println(fileID);
						ArrayList<String> ListOfFiles = getFileList();
						
						for(String file : ListOfFiles)
						{
							if(file.startsWith(fileID))
							{					
								
								File PDFFILE = new File("data/server/"+idToFile(fileID));
								System.out.println(fileID);
								
								if(PDFFILE.exists())
								{
									this.out.println(PDFFILE.length());
									System.out.println("200 <File Size Sent Successfully>");
									
									try 
									{
										FileInputStream fis = new FileInputStream(PDFFILE);
										byte[] buffer = new byte[1028*4];
										int byteCount=0;
										
										while((byteCount=fis.read(buffer))>0)
										{
											this.dos.write(buffer, 0, byteCount);
											this.dos.flush();
										}
										System.out.println("Successfully sent file to client");
										this.out.println("<200> File Sent Successfully , File Name : "+idToFile(fileID));
										
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}		
								}	
							}
						}
				}else 
					if(command.equals("LOGOUT"))
					{
						this.out.println("200 BYE");
						System.out.println("User logged off");

						loggedIn=false;
						hasNotLoggedOFF=true;
						
					}
			
		}
	}
				
    	
}
    
    private boolean matchUser(String username,String password)
    {
	boolean found = true;
	File userFile = new File("data/server/users.txt");
	try
	{
		String userName;
		String passWord;
		//Code to search users.txt file for match with username and password
	    Scanner scan = new Scanner(userFile);
	    while(scan.hasNextLine()&&!found)
	    {
		String line = scan.nextLine();
		String lineSec[] = line.split("\\s");
    		
		userName = lineSec[0];
		passWord = lineSec[1];		
		
		if(userName.equals(username)&&passWord.equals(password))
		{
			found=false;

			break;
		}
	    }
	    scan.close();
	}
	catch(IOException ex)
	{
	    ex.printStackTrace();
	}
	
	return found;
    }
    
    private ArrayList<String> getFileList()
    {
		ArrayList<String> result = new ArrayList<String>();
		//Code to add list text file contents to the arraylist.
		File lstFile = new File("data/server/PDFList.txt"/*OMITTED - Enter file location*/);
		if(lstFile.exists())
		{
		try
		{
			Scanner scan = new Scanner(lstFile);
			//***OMITTED - Read each line of the file and add to the arraylist***

			while(scan.hasNextLine())
			{
				result.add(scan.nextLine());
			}
			
			scan.close();
		}	    
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		}else
		{
			System.err.println("File Not Found");	
		}
		
		return result;
    }
    
    private String idToFile(String ID)
    {
    	String result = "";
    	//Code to find the file name that matches strID
    	File lstFile = new File("data/server/PdfList.txt"/*OMITTED - Enter file location*/);
    	try
    	{
    		Scanner scan = new Scanner(lstFile);
    	
    		String line = "";
    		
    		while(scan.hasNextLine())
    		{
    			line=scan.nextLine();
    			if(line.startsWith(ID))
    			{
    				result = line.replace(ID+" ", "");
    				break;
    			}
    		}
    		//***OMITTED - Read filename from file and search for filename based on ID***
    		scan.close();
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}
    	return result;
    }
}
