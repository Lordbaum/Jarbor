package demo.sphinx.helloworld;




import datenbankenf.persConfig.*;
import java.net.*;
import java.io.*;



public class GreetClient {
    private  Socket clientSocket;
    private  PrintWriter out;
    private  BufferedReader in;

    public  void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //startet die conection zum server/bot
        } catch (IOException e) {e.printStackTrace();}
    }
    public String sendMessage(String msg) throws IOException,SocketException {

        out.println(msg);

        String resp = in.readLine();

        return resp;

    }

    public  void stopConnection()throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void send_to_server(String msg)  {
        //192.168.0.10
        try {
            GreetClient client = new GreetClient();
            client.startConnection(datenbankenf.persConfig.getconfig("ip"), Integer.parseInt( datenbankenf.persConfig.getconfig("port")));
            String response = client.sendMessage(msg + " v1.1");
            //stopConnection();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }


    }
