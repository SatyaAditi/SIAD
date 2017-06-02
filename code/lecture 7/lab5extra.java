import java.io.*;
import java.net.*;

public class lab5extra{
    public static void main(String args[]) throws IOException {
    String hostName=args[0];
    try{
        if(args.length !=2){
            System.err.println(
            "Usage: java MyTCPClient<host name><port number>");
            System.exit(1);
    }

    
    int portNumber = Integer.parseInt(args[1]);

    Socket socket= new Socket(hostName, portNumber);
    //System.out.println(" connected to server\" + hostName + portNumber);

// output stream to send data
    PrintWriter out=new PrintWriter(socket. getOutputStream(),true);

//input stream to read data
    BufferedReader in=new BufferedReader(
                    new InputStreamReader(socket. getInputStream()));
//buffer reader to read from user input
    BufferedReader stdln=new BufferedReader(new InputStreamReader(System.in));
    
    String userInput;
    while((userInput = stdln.readLine())!=null){
        out.println(userInput);
        System.out.println("received from server:"+ in.readLine());
    String msg = stdln.readLine();
    if(msg.equals("exit")){
    socket.close();
    System.exit(0);
}
}
}catch(UnknownHostException e)
{
    System.err.println("Dont know about host" + hostName);
}
catch(IOException e)
{
    System.err.println("Couldnt get i/o for the connection to" + hostName);
    System.exit(1);
}
}

    
}
class TCPClientThread extends Thread
{
    private Socket clientSocket = null;
    TCPClientThread(Socket clientSocket)
    {
    super("TCPServerThread");
    this.clientSocket = clientSocket;
    }
    public void run()
    {
        try{
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    while(true){
    String inputLine = in.readLine();
    while((inputLine!= null)&& !inputLine.isEmpty()){
    System.out.println("received from client:" +inputLine);
    inputLine=in.readLine();
        }
    }
}
catch(IOException e)
{
    e.printStackTrace();
}
}}
