package acsse.csc02b2.Leecher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Leecher 
{
	private DatagramPacket packetToSend;
	private DatagramSocket socToSend;
	
	private DatagramPacket packetToBeReceived;
	private DatagramSocket socToReceive;
	
	private String userCommand;
	private int SeederPort;
	private int bufferSize  =1028;


	private int userChoice;
	
	
	public Leecher(int port) 
	{
		this.SeederPort=port;
	}
	
	public void send()
	{
		try 
		{
			this.socToSend=new DatagramSocket();
			byte[] buffer = this.userCommand.trim().getBytes(StandardCharsets.UTF_8);
			this.packetToSend = new DatagramPacket(buffer, buffer.length,InetAddress.getByName("localhost"),this.SeederPort);
			this.socToSend.send(packetToSend);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void receive() 
	{
		try 
		{
			
			this.socToReceive=new DatagramSocket();
			
			/*byte[] fileSize=new byte[this.bufferSize];
			
			this.packetToBeReceived=new DatagramPacket(fileSize, fileSize.length);
			
			String strFileSize= new String(packetToBeReceived.getData(),0,packetToBeReceived.getLength(),StandardCharsets.UTF_8).trim();
			System.out.println("File Size : "+strFileSize);
			Long FileSize = Long.parseLong(strFileSize);
			
			int intFileSize = roundUpToInt(FileSize);
			
			System.out.println("FileSize : "+FileSize);*/
			
			while(true)
			{
			if(this.userCommand.equals("LIST"))
			{
				byte[] buffer=new byte[bufferSize];
				
				this.packetToBeReceived=new DatagramPacket(buffer, buffer.length);
					
				FileOutputStream fos = new FileOutputStream(new File("data/Leecher/ListOfFiles.txt"));
				
			
				
					this.socToReceive.receive(packetToBeReceived);
					fos.write(packetToBeReceived.getData(),0,packetToBeReceived.getLength());
					
				
					if(packetToBeReceived.getLength()<bufferSize)
					{
						break;
					}
					
					if (packetToBeReceived != null) 
					{
		                break;
		            }
					
					
					
				
			}else if(this.userCommand.equals("FILE"))
			{
				File toBeSaved = processCommand(this.userChoice);
				byte[] buffer=new byte[bufferSize];
				FileOutputStream fos = new FileOutputStream(toBeSaved);

				
				this.packetToBeReceived=new DatagramPacket(buffer, buffer.length);
				
				
					this.socToReceive.receive(packetToBeReceived);
					
					
					fos.write(packetToBeReceived.getData(),0,packetToBeReceived.getLength());
					
					if(this.packetToBeReceived.getLength() < bufferSize)
					{
						break;
					}
					
					if (packetToBeReceived != null) 
					{
		                break;
		            }
					
				fos.close();
			}
		}
			
			
		} catch (SocketException | FileNotFoundException e) 
		{
			e.printStackTrace();
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}finally 
		{
			if(this.socToReceive!=null)
			{
				this.socToReceive.close();
			}
		}
	
	}
	
	void getCommand(String Command)
	{
		this.userCommand=Command;
	}
	
	private File processCommand(int UserChoice)
	{
		
		File listOFFiles = new File("data/Leecher/ListOfFiles.txt");
		File toReturn=null;
		
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(listOFFiles));
			int n = 0;
			String Line;
			while((n=br.read())>0)
			{
			
				Line=br.readLine();
				
				if(Line.contains(String.valueOf(this.userChoice)))
				{
					String[] lineContent = Line.split(". ");
					toReturn=new File("data/Leecher/"+lineContent[1]);
				}
				
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
		
	}

	/**
	 * @param userChoice the userChoice to set
	 */
	public void setUserChoice(int userChoice) {
		this.userChoice = userChoice;
	}
	
	public static int roundUpToInt(long value) {
	    long roundedValue = (long) Math.ceil((double) value);
	    if (roundedValue > Integer.MAX_VALUE || roundedValue < Integer.MIN_VALUE) {
	        throw new ArithmeticException("Value out of int range: " + roundedValue);
	    }
	    return (int) roundedValue;
	}


	
	
	

}
