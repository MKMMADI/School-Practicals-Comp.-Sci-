

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args)
	{
		WebServer WB1 = new WebServer();
		WebServer WB2 = new WebServer();
		WebServer WB3 = new WebServer();
		WebServer WB4 = new WebServer();
		WebServer WB5 = new WebServer();
		
		WB1.start();
	}
}
