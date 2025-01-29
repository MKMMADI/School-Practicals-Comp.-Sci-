package acsse.csc02b2.Seeder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Seeder 
{
	
	DatagramSocket socToRecieve ;
	DatagramPacket packetToRecieve;
	
	DatagramSocket socToSend ;
	DatagramPacket packetToSend;
	
	InetAddress LeecherAddress ;
	int LeecherPort ;

	File fileToBeSent ;
	File txtAvailableFiles;
	int Port ;
	
	int BufferSize = 1028;
	String command;
	
	public Seeder(int portNumber) 
	{
		// TODO Auto-generated constructor stub
		this.Port = portNumber;
		try 
		{	
			this.socToRecieve=new DatagramSocket(portNumber);
			
		} catch (SocketException e) 
		{
			e.printStackTrace();
		}
		populateTextFile();
	}
	
	public void send()
	{
		System.out.println("Sending file ...");
		proceessCommand(this.command);
		populateTextFile();
		try 
		{
			/*String strFilesize=null;
			if(this.command.equals("LIST"))
			{
				this.socToSend=new DatagramSocket();
				long fileSize = this.txtAvailableFiles.length();
				strFilesize=String.valueOf(fileSize);
				System.out.println(strFilesize);
				byte[] FileSize = strFilesize.getBytes();
				this.packetToSend=new DatagramPacket(FileSize,FileSize.length,this.LeecherAddress,this.LeecherPort);
				this.socToSend.send(packetToSend);
			}else
			{
				this.socToSend=new DatagramSocket();
				long fileSize = fileToBeSent.length();
				strFilesize=String.valueOf(fileSize);
				byte[] FileSize = strFilesize.getBytes()s;
				this.packetToSend=new DatagramPacket(FileSize,FileSize.length,this.LeecherAddress,this.LeecherPort);
				this.socToSend.send(packetToSend);
			}*/
		
			this.socToSend=new DatagramSocket();
			byte[] buffer = new byte[BufferSize];
			int n =0;
			int bytesSent=0;
			
			if(this.command.equals("FILE"))
			{
				FileInputStream fis = new FileInputStream(fileToBeSent);
				while((n=fis.read(buffer))>0)
				{
					this.packetToSend=new DatagramPacket(buffer,buffer.length,this.LeecherAddress,this.Port);
					this.socToSend.send(this.packetToSend);
					bytesSent=n;
				}
				System.out.println("File sent successfully.");
				
			}else
			{
				FileInputStream fis = new FileInputStream(txtAvailableFiles);
				while((n=fis.read(buffer))>0)
				{
					this.packetToSend=new DatagramPacket(buffer,buffer.length,this.LeecherAddress,this.Port);
					this.socToSend.send(this.packetToSend);
					bytesSent=n;
				}
				System.out.println("File sent successfully.");
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally 
		{
			if(this.socToSend != null)
			{
				this.socToSend.close();
			}
		}
		
		
		
	}
	
	public void recieve()
	{
		try 
		{
			
			while(true)
			{
				byte[] buffer = new byte[this.BufferSize];
				this.packetToRecieve=new DatagramPacket(buffer, buffer.length);
				this.socToRecieve.receive(packetToRecieve);
				
				System.out.println("Listining for incoming conections.");
				
				command = new String(packetToRecieve.getData(),0,packetToRecieve.getLength(),StandardCharsets.UTF_8).trim();
				this.LeecherAddress=packetToRecieve.getAddress();
				this.LeecherPort=packetToRecieve.getPort();
				System.out.println("User Command : "+command);
				System.out.println("Connection establised");
				
				if (command != null && !command.isEmpty()) {
	                break;
	            }
			}	
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally 
		{
			if(this.socToRecieve!=null)
			{
				this.socToRecieve.close();
			}
		}
		
		
	
	}
	
	private void proceessCommand(String command) 
	{
		String[] FileNames=getFileNames();
		// TODO Auto-generated method stub
		if(command.contains("FILE"))
		{
			String[] splitCommand = command.split(". ");
			String fileName = splitCommand[1];	
			
			this.fileToBeSent = new File("data/Seeder"+fileName);	
			
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


	public void populateTextFile() 
	{
		// TODO Auto-generated method stub
		this.txtAvailableFiles=new File("data/Seeder/ListOfFiles.txt");

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

	public static void main(String[] args)
	{
		Seeder seeder = new Seeder(4562);
		seeder.recieve();
		seeder.send();
	}


}
