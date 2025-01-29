package csc2b.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileHandler implements Runnable 
{
	
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private PrintWriter  out;
	private BufferedReader br;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public FileHandler(Socket soc)
	{
		
		this.socket=soc;
		try 
		{			
			is= soc.getInputStream();
			os=soc.getOutputStream();			
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out=new PrintWriter(os);
		br = new BufferedReader(new InputStreamReader(is));
		dis=new DataInputStream(is);
		dos=new DataOutputStream(os);
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Geiing User Requests");
		boolean Handling=true;
		String Message = "";
		try {
			Message = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Message:"+Message);
		StringTokenizer stokenizer = new StringTokenizer(Message);
		String command=stokenizer.nextToken().toUpperCase();
		
		while(Handling)
		{
		
			
			switch(command)
			{	
			
			case "LIST":
			{
				out.println(loadIMGList());
				break;
			}
			
			case "DOWN":
			{
				String fileID = stokenizer.nextToken();
				out.println("");
				String fileName="";
				
				File fileList = new File("data/server/Image-List.txt");
	
				Scanner sc = null;
				
				try {
					sc=new Scanner(fileList);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				String line ="";
				
				while(sc.hasNext())
				{
					line=sc.nextLine();
					StringTokenizer tokenizer = new StringTokenizer(line);
					String id = tokenizer.nextToken();
					String FileName = tokenizer.nextToken();
					if(id.equals(fileID))
					{
						fileName=FileName;
					}
					
					
					sc.close();
					
					System.out.println("Name of the file that was requested"+fileName);
					
					File FileToBeReturned = new File("data/server/"+fileName);
					
					if(FileToBeReturned.exists())
					{
						out.println(FileToBeReturned.length());
						out.flush();
						FileInputStream fis =null;
						try {
							fis= new FileInputStream(FileToBeReturned);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						byte[] buffer = new byte[1024];
						int n = 0;
						
						try {
							while((n=fis.read(buffer))>0)
							{
								dos.write(buffer,0,n);
								dos.flush();
							}
							
							fis.close();
							System.out.println("File Sent To Client");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						break;
					}			
				}
				  
			}
			
			
			case "UP":
			{
				String FileRecordId = stokenizer.nextToken();
				String fileRecordName=stokenizer.nextToken();
				
				int size = Integer.parseInt(stokenizer.nextToken());
				PrintWriter out = null;
				try {
					out = new PrintWriter(new BufferedWriter(new FileWriter("./data/server/Image-List.txt",true)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println("\n"+FileRecordId+" "+fileRecordName);
				out.close();
				
				System.out.println("File added to the list");
				File fileToRecieve = new File("data/server"+fileRecordName);
				FileOutputStream fos = null;
				System.out.println("Waiting on client...");
				
				
				try
				{
					fos=new FileOutputStream(fileToRecieve);
					byte [] buffer =new byte[1024];
					int n = 0;
					int totalbytes=0;
					
					while(totalbytes!=size)
					{
						n=dis.read(buffer,0,buffer.length);
						fos.write(buffer,0,n);
						fos.flush();
						totalbytes+=n;
					}
					
					out.println("SUCCESS");
					out .flush();
					System.out.println("DONE File Has Been Uploaded");
					
					
				}catch (Exception e) {
					
					this.out.println("FAILURE");
					this.out.flush();
					e.printStackTrace();
					
					// TODO: handle exception
				}finally
				{
					try
					{
						fos.close();
					}catch(IOException e)
					{
						e.printStackTrace();
					}
				}
				
				
				break;
			}
			
			}
		}
	}




	private String loadIMGList() 
	{
		
		String toReturn=null;
		
		try
		{
			Scanner Sc = new Scanner(new File("./data/server/Image-List.txt"));
			
			while(Sc.hasNextLine())
			{
				String img = Sc.nextLine();
				toReturn+=img+"#";
				
			}
			
			System.out.println("Image list has been loaded");
			Sc.close();
			
			
		}catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();  
		}
		return toReturn;
	}

}
