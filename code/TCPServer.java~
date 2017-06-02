import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class MyTCPServer {
public static void main(String args[]) throws IOException 
{
try{
   	System.out.println("MyTCPServer");
        int portNumber = 8000;
	ServerSocket serversocket = new ServerSocket(portNumber);
        System.out.println("MyTCPServer is running at port " + serversocket.getLocalPort());
        Socket clientSocket = serversocket.accept();
        System.out.println("A client is connected from IP:" + clientSocket.getInetAddress().getHostAddress());
 	System.out.println("Client is connected");
        BufferedReader in= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	String inputLine=in.readLine();
	String receivedData = inputLine;
	while (!inputLine.isEmpty())
	{
	inputLine=in.readLine();
	receivedData += inputLine + "\n";
	}
 	System.out.println("Data received from client" + receivedData);
	String response = "MyTCPServer\n" + (new Date()).toString() + "\n" + "You have sent:" + receivedData;
          clientSocket.getOutputStream().write(response.getBytes("UTF-8"));
while(true)
{
//Socket clientSocket= serverSocket.accept();
//System.out.println("A client is connected from IP:" + clientSocket.getInetAddress().getHostAddress());
new TCPServerThread(clientSocket).start();
}
}
catch(Exception e)
{
}
}
}
class TCPServerThread extends Thread  {
	private Socket clientSocket = null;
	TCPServerThread(Socket clientSocket) {
	super("TCPServerThread");
	this.clientSocket = clientSocket;
	}
public void run()
{
try
{

clientSocket.close();
}
catch(Exception e)
{
}
}}
