package bauernhof.app;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Netzmain {
    public static void main(String[] args) throws UnknownHostException, IOException{
        String IP = "1111";
        int Port = 11;
        //int length = args.length;
        //client to server
        if(IP != null){
            try (Socket ss = new Socket(IP, Port)) {
            }

        }

        else if(IP == null){
            Socket ss = new Socket("localhost", Port);
            //ss.accept();
        }        
    }
    
}
