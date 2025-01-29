import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer implements Runnable
{


	// TODO Auto-generated method stub
	private Boolean startAndContinue = true; 

	public void carryOutService()
	{
		try(
				ServerSocket SS =new ServerSocket(4321); //Instantiating ServerSocket
				Socket SOC= SS.accept();//Instantiating Socket with ServerSocket Output Stream
				PrintWriter out=new PrintWriter(SOC.getOutputStream(),true);//Used to write to socket
				BufferedReader in=new BufferedReader(new InputStreamReader(SOC.getInputStream()));//Used to read from socket
			)
		{


			System.out.println("Connection accepted.");//once socket connection with client is established then , output response	
			String UserPrompt ="";//used to get user input
			UserPrompt= in.readLine();//Read in user input

			int queryCount=0;//used to store number of queries client makes 
			int PromptCount=0;//used to store number of prompts user has made 

			if(UserPrompt.matches("START"))//if user enters the "START" value then the following follows:
			{
				PromptCount++;//Increase prompt count 
				out.println("#"+PromptCount+" Request or Done.//nHELLO-you may make 4 requests");		
				startAndContinue=true;//while boolean value is true the loop , user is able to enter in prompts
			}


			for(int i =1;i<5;i++)
			{
				if(startAndContinue==true)	
				{
					UserPrompt ="";//clear user input 
					UserPrompt= in.readLine();//read in user input 
					switch(UserPrompt)
					{


						case "Johannesburg":
						{
							PromptCount++;//increase prompt count 
							out.println( "#"+PromptCount+" Clear Skies in Joburg");//display requested information
							queryCount++;//increase query count 
		
							break;
						}
		
						case "Cape Town":
						{
							PromptCount++;//increase prompt count 
							out.println( "#"+PromptCount+" Cool and Cloudy in Cape Town");//display requested information
							queryCount++;//increase query count 
							break;
						}
		
						case "Durban":
						{
							PromptCount++;//increase prompt count 
							out.println( "# "+PromptCount+" Sunny and Warm in Durban");//display requested information
							queryCount++;//increase query count 
							break;
						}
		
						case "DONE":
						{
		
							queryCount=i;//increase query count
							out.println("0# GOOD BYE-"+queryCount+" queries answered");//display requested information
							startAndContinue=false;//while false user cannot enter any prompts 
							break;
						}
		
						default:
						{
							out.println( "No data available");
							break;
						}
					}
				}
			}	
			//close prompts 
			if((SS != null) &&(SOC!=null)&&(in!=null)&&(out!=null))
			{
				SS.close();
				SOC.close();
				in.close();
				out.close();
			}
		}catch(IOException IO)
		{
			IO.printStackTrace();
		}

	}
		
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		carryOutService();
	}
}
