import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class EmailClient{

	public EmailClient(GUISetUP GUI) 
    {
        String SocketNumber = GUI.get_Socket().getText();
        int SocketNum = Integer.parseInt(SocketNumber);
        String HostName = GUI.getHostName().getText();

        try (Socket soc = new Socket(HostName, SocketNum);
             PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()))) 
        {

            System.out.println("Connection Established: " + InetAddress.getLocalHost());

            // Initiate TCP connection
            String myPC = String.valueOf(InetAddress.getLocalHost());
            String[] tokens = myPC.split("/");
            out.println("HELO " + tokens[1]);
            String response = in.readLine();
            System.out.println(response);

            // MAIL FROM command
            out.println("MAIL FROM:<" + GUI.getFrom().getText() + ">");
            response = in.readLine();
            System.out.println(response);

            // RCPT TO command
            out.println("RCPT TO:<" + GUI.getTo().getText() + ">");
            response = in.readLine();
            System.out.println(response);

            // DATA command
            out.println("DATA");
            response = in.readLine();
            System.out.println(response);

            // Email content
            out.println(GUI.getText().getText());
            out.println(".");
            response = in.readLine();
            System.out.println(response);

            // QUIT command
            out.println("QUIT");
            response = in.readLine();
            System.out.println(response);

        } catch (IOException IO) {
            IO.printStackTrace();
        }
    }
}
