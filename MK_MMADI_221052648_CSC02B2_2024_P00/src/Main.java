

public class Main 
{
	public static void main(String[] args) 
	{
		Socket connection=null ;
		// TODO Auto-generated method stub
		for(int i = 1 ; i <65535 ; i=i+3)
		{
			
			try
			{
				connection =  new Socket("localhost",i);
				if(connection.isBound())
				{
					 System.out.println("Connected at port number "+ i);
					 System.out.println("Connected at local port number "+connection.getLocalPort());
					 System.out.println("Remote port of connection "+connection.getPort());

				}
			}
			catch (Exception e) 
			{
				 System.err.println("Could not connect to port number :  "+i);
			}
			finally {
				if(connection!=null)
				{
					try {
						connection.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			
			
		}
		
		System.out.println("Machine IP Adress IS : "+connection.getInetAddress().getHostAddress());

		
	}	
}
